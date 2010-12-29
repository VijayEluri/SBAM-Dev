package com.scholastic.sbam.shared.objects;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Authentication implements IsSerializable {
	private String userName;
	private String firstName;
	private String lastName;
	private String message;
	private boolean authenticated;
	private List<String> roleNames = new ArrayList<String>();
	
	public Authentication() {
		
	}
	
	public Authentication(String userName, String firstName, String lastName) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.message = "";
	}
	
	public void addRoleName(String roleName) {
		if (!roleNames.contains(roleName))
			roleNames.add(roleName);
	}
	
	public void removeRoleName(String roleName) {
		if (roleNames.contains(roleName))
			roleNames.remove(roleName);
	}
	
	public void clearRoleNames() {
		roleNames.clear();
	}
	
	public boolean hasRoleName(String roleName) {
		return roleNames.contains(roleName);
	}
	
	public String getDisplayName() {
		if (firstName != null)
			if (lastName != null)
				return firstName + " " + lastName;
			else
				return firstName;
		else if (lastName != null)
			return lastName;
		else
			return "Unknown.";
	}
	
	public String toString() {
		return userName + "/" + firstName + "/" + lastName + "/" + authenticated + "/" + roleNames;
	}
	
	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
}
