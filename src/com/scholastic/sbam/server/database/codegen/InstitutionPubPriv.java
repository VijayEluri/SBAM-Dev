package com.scholastic.sbam.server.database.codegen;

// Generated Feb 1, 2012 5:33:22 PM by Hibernate Tools 3.2.4.GA

/**
 * InstitutionPubPriv generated by hbm2java
 */
public class InstitutionPubPriv implements java.io.Serializable {

	private String pubPrivCode;
	private String shortName;
	private String description;

	public InstitutionPubPriv() {
	}

	public InstitutionPubPriv(String pubPrivCode, String shortName,
			String description) {
		this.pubPrivCode = pubPrivCode;
		this.shortName = shortName;
		this.description = description;
	}

	public String getPubPrivCode() {
		return this.pubPrivCode;
	}

	public void setPubPrivCode(String pubPrivCode) {
		this.pubPrivCode = pubPrivCode;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
