package com.scholastic.sbam.server.database.codegen;

// Generated Jun 3, 2011 3:04:04 PM by Hibernate Tools 3.2.4.GA

/**
 * InstitutionGroup generated by hbm2java
 */
public class InstitutionGroup implements java.io.Serializable {

	private String groupCode;
	private String description;

	public InstitutionGroup() {
	}

	public InstitutionGroup(String groupCode, String description) {
		this.groupCode = groupCode;
		this.description = description;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
