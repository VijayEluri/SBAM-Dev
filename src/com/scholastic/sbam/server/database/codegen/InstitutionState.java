package com.scholastic.sbam.server.database.codegen;

// Generated Jul 20, 2011 10:53:06 AM by Hibernate Tools 3.2.4.GA

/**
 * InstitutionState generated by hbm2java
 */
public class InstitutionState implements java.io.Serializable {

	private String stateCode;
	private String description;

	public InstitutionState() {
	}

	public InstitutionState(String stateCode, String description) {
		this.stateCode = stateCode;
		this.description = description;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}