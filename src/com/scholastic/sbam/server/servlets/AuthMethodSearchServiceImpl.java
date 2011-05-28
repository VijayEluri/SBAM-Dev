package com.scholastic.sbam.server.servlets;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.LoadConfig;
import com.scholastic.sbam.client.services.AuthMethodSearchService;
import com.scholastic.sbam.server.database.codegen.Agreement;
import com.scholastic.sbam.server.database.codegen.AuthMethod;
import com.scholastic.sbam.server.database.codegen.Institution;
import com.scholastic.sbam.server.database.objects.DbAgreement;
import com.scholastic.sbam.server.database.objects.DbAuthMethod;
import com.scholastic.sbam.server.database.objects.DbInstitution;
import com.scholastic.sbam.server.database.util.HibernateUtil;
import com.scholastic.sbam.server.fastSearch.InstitutionCache;
import com.scholastic.sbam.server.fastSearch.InstitutionCache.InstitutionCacheConflict;
import com.scholastic.sbam.shared.objects.AgreementInstance;
import com.scholastic.sbam.shared.objects.AuthMethodInstance;
import com.scholastic.sbam.shared.objects.AuthMethodTuple;
import com.scholastic.sbam.shared.objects.SynchronizedPagingLoadResult;
import com.scholastic.sbam.shared.security.SecurityManager;
import com.scholastic.sbam.shared.util.AppConstants;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AuthMethodSearchServiceImpl extends AuthenticatedServiceServlet implements AuthMethodSearchService {

	@Override
	public SynchronizedPagingLoadResult<AuthMethodTuple> searchAuthMethods(LoadConfig config, long syncId) throws IllegalArgumentException {
	
		authenticate("search authentication methods", SecurityManager.ROLE_QUERY);
		
		List<AuthMethodTuple> list = new ArrayList<AuthMethodTuple>();
		List<String>		  messages = null;

		String filter	= config.get("filter")   != null ? config.get("filter").toString() : null;

		if (filter				== null)
			return new SynchronizedPagingLoadResult<AuthMethodTuple>(list, 0, 0, syncId); 
		
		HibernateUtil.openSession();
		HibernateUtil.startTransaction();
		
		try {
			AppConstants.TypedTerms typedTerms = AppConstants.parseTypedFilterTerms(filter, true);
			messages = typedTerms.getMessages();
			
			List<Object []> tuples = DbAuthMethod.findFiltered(typedTerms, AppConstants.STATUS_DELETED);
			
			int loadLimit = AppConstants.STANDARD_LOAD_LIMIT;
			if (config.get("limit") != null && config.get("limit") instanceof Integer)
				loadLimit = ( (Integer) config.get("limit"));
			if (tuples.size() > loadLimit) {
				//	This is an error return with no data, but a count (too many results)
				return new SynchronizedPagingLoadResult<AuthMethodTuple>(list, 0, tuples.size(), syncId);
			}
			
			for (Object [] objects : tuples) {

				AgreementInstance 	agreement	= DbAgreement    .getInstance((Agreement) objects [0]);
				AuthMethodInstance	authMethod	= DbAuthMethod.getInstance((AuthMethod) objects [1]);
				
				setDescriptions(authMethod);
				setDescriptions(agreement);
				setInstitution(agreement);
					
				AuthMethodTuple tuple = new AuthMethodTuple(agreement, authMethod);
				
				if (filterQualified(tuple, typedTerms))
					list.add(tuple);
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		HibernateUtil.endTransaction();
		HibernateUtil.closeSession();
		
		return new SynchronizedPagingLoadResult<AuthMethodTuple>(list, 0, list.size(), syncId, messages);
	}

	private void setDescriptions(AgreementInstance agreement) {
		DbAgreement.setDescriptions(agreement);
	}
	
	private void setInstitution(AgreementInstance agreement) throws InstitutionCacheConflict {
		// Get the institution
		if (agreement.getBillUcn() > 0) {
			Institution dbInstitution = DbInstitution.getByCode(agreement.getBillUcn());
			agreement.setInstitution(DbInstitution.getInstance(dbInstitution));

			if (agreement.getInstitution() != null) {
				InstitutionCache.getSingleton().setDescriptions( agreement.getInstitution() );
			}
		}
	}

	private void setDescriptions(AuthMethodInstance AuthMethod) {
		DbAuthMethod.setDescriptions(AuthMethod);
	}
	
	protected boolean filterQualified(AuthMethodTuple tuple, AppConstants.TypedTerms typedTerms) {
		//	Test for search terms
		
		//	Have to find each word somewhere
		for (String word : typedTerms.getWords())
			if (!filterQualified(tuple, word))
				return false;
		
		//	Have to find each number somewhere -- this can include the agreement ID
		for (String number : typedTerms.getNumbers())
			if (!filterQualifiedNumber(tuple, number))
				return false;
		
		//	For a UID or URL, have to find each IP address somewhere (probably in the note)
		if (!AuthMethodInstance.AM_IP.equals(tuple.getAuthMethod().getMethodType())) {
			for (String ipString : typedTerms.getIpStrings())
				if (!filterQualified(tuple, ipString))
					return false;
		}
		
		//	We found everything, so qualify this tuple
		return true;
	}
	
	protected boolean filterQualified(AuthMethodTuple tuple, String term) {
		term = term.toLowerCase();
		if (tuple.getAuthMethod() != null) {
			if (tuple.getAuthMethod().getNote().toLowerCase().contains(term))
				return true;
			if (tuple.getAuthMethod().getOrgPath().toLowerCase().contains(term))
				return true;
			if (tuple.getAuthMethod().getUrl().toLowerCase().contains(term))
				return true;
			if (tuple.getAuthMethod().getUserId().toLowerCase().startsWith(term))
				return true;
			if (tuple.getAuthMethod().getPassword().toLowerCase().contains(term))
				return true;
		}
		if (tuple.getAgreement().getInstitution() != null) {
			if (tuple.getAgreement().getInstitution().getInstitutionName().toLowerCase().contains(term))
				return true;
			if (tuple.getAgreement().getInstitution().getCity().toLowerCase().contains(term))
				return true;
			if (tuple.getAgreement().getInstitution().getZip().toLowerCase().contains(term))
				return true;
		}
		return false;
	}
	
	protected boolean filterQualifiedNumber(AuthMethodTuple tuple, String term) {
		if (tuple.getAgreement() != null && (tuple.getAgreement().getId() + "").contains(term))
			return true;
		return filterQualified(tuple, term);
	}
}