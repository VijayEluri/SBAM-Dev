package com.scholastic.sbam.client.uiobjects.uiapp;

import com.scholastic.sbam.client.uiobjects.foundation.UnknownPortlet;
import com.scholastic.sbam.client.util.IconSupplier;

public enum AppPortletIds {
 
	FULL_INSTITUTION_SEARCH		("Full Institution Search", 	IconSupplier.getInstitutionIconName(),		"InstitutionSearch"	, 	InstitutionSearchPortlet.class.getName()),
	CUSTOMER_SEARCH				("Customer Search", 			IconSupplier.getCustomerIconName(),			"CustomerSearch"	, 	CustomerSearchPortlet.class.getName()),
	SITE_INSTITUTION_SEARCH		("Site Institution Search", 	IconSupplier.getSiteIconName(),				"SiteIntitutionSearch", SiteInstitutionSearchPortlet.class.getName()),
	AGREEMENT_SEARCH			("Agreement Search", 			IconSupplier.getAgreementIconName(),		"AgreementSearch", 		AgreementSearchPortlet.class.getName()),
	AGREEMENT_DISPLAY			("Agreement Display", 			IconSupplier.getAgreementIconName(),		"AgreementDisplay"	, 	AgreementPortlet.class.getName()),
	SITE_LOCATION_DISPLAY		("Site Location Display", 		IconSupplier.getSiteIconName(),				"SiteLocationDisplay", 	SiteLocationPortlet.class.getName()),
	RECENT_AGREEMENTS_DISPLAY	("Recent Agreements", 			IconSupplier.getAgreementIconName(),		"RecentAgreements"	, 	RecentAgreementsPortlet.class.getName()),
	RECENT_INSTITUTIONS_DISPLAY	("Recent Institutions", 		IconSupplier.getInstitutionIconName(),		"RecentInstitutions", 	RecentInstitutionsPortlet.class.getName()),
	RECENT_CUSTOMERS_DISPLAY	("Recent Customers", 			IconSupplier.getCustomerIconName(),			"RecentCustomers", 		RecentCustomersPortlet.class.getName()),
	RECENT_SITES_DISPLAY		("Recent Sites",	 			IconSupplier.getSiteIconName(),				"RecentSites"	, 		RecentSiteLocationsPortlet.class.getName()),
	UNKNOWN_PORTLET				("The Unknown Portlet", 		null,										"UnknownPortlet"	, 	UnknownPortlet.class.getName());
	
	String name;
	String iconName;
	String helpTextId;
	String className;
	
	AppPortletIds(String name, String iconName, String helpTextId, String className) {
		this.name			= name;
		this.iconName		= iconName;
		this.helpTextId		= helpTextId;
		this.className		= className;
	}

	public String getName() {
		return name;
	}

	public String getIconName() {
		return iconName;
	}

	public String getHelpTextId() {
		return helpTextId;
	}

	public String getClassName() {
		return className;
	};

}
