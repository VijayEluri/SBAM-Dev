package com.scholastic.sbam.server.servlets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.scholastic.sbam.client.services.UserListService;
import com.scholastic.sbam.server.database.codegen.User;
import com.scholastic.sbam.server.database.codegen.UserRole;
import com.scholastic.sbam.server.database.objects.DbUser;
import com.scholastic.sbam.server.database.objects.DbUserRole;
import com.scholastic.sbam.server.database.util.HibernateUtil;
import com.scholastic.sbam.shared.objects.Authentication;
import com.scholastic.sbam.shared.objects.UserInstance;
import com.scholastic.sbam.shared.security.SecurityManager;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UserListServiceImpl extends RemoteServiceServlet implements UserListService {

	@Override
	public List<UserInstance> getUsers(String userName, String firstName, String lastName, String email) throws IllegalArgumentException {
		
		HibernateUtil.openSession();
		HibernateUtil.startTransaction();

		List<UserInstance> list = new ArrayList<UserInstance>();
		try {
			String authUserName = null;
			Authentication auth = ((Authentication) getServletContext().getAttribute(SecurityManager.AUTHENTICATION_ATTRIBUTE));
			if (auth != null)
				authUserName = auth.getUserName();
			if (auth == null || authUserName == null || authUserName.length() == 0)
				throw new Exception("No logged in user for whom to list users.");
			if (!auth.hasRoleName(SecurityManager.ROLE_ADMIN))
				throw new Exception("User is not privileged to list users.");
			
			//	Find only undeleted users
			List<User> users = DbUser.findFiltered(userName, firstName, lastName, email, 'X');

			for (User user : users) {
				UserInstance instance = new UserInstance();
				instance.setId(user.getId());
				instance.setUserName(user.getUserName());
				instance.setPassword(user.getPassword());
				instance.setFirstName(user.getFirstName());
				instance.setLastName(user.getLastName());
				instance.setEmail(user.getEmail());
				instance.setStatus(user.getStatus());
				instance.setCreatedDatetime(user.getCreatedDatetime());
				list.add(instance);
				
				//	Set the title for their collection of roles
				List<UserRole> roles = DbUserRole.findByUserName(user.getUserName());
				instance.setRoleGroupTitle(SecurityManager.getRoleGroupTitle(getRoleNames(roles)));
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		HibernateUtil.endTransaction();
		HibernateUtil.closeSession();
		
		return list;
	}
	
	private String [] getRoleNames(List<UserRole> roles) {
		String [] roleNames = new String [roles.size()];
		for (int i = 0; i < roles.size(); i++)
			roleNames [i] = roles.get(i).getId().getRoleName();
		return roleNames;
	}
}