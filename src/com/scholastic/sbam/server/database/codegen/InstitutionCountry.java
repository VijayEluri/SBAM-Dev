package com.scholastic.sbam.server.database.codegen;

// Generated Jul 20, 2011 10:53:06 AM by Hibernate Tools 3.2.4.GA

/**
 * InstitutionCountry generated by hbm2java
 */
public class InstitutionCountry implements java.io.Serializable {

	private String countryCode;
	private String description;

	public InstitutionCountry() {
	}

	public InstitutionCountry(String countryCode, String description) {
		this.countryCode = countryCode;
		this.description = description;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
