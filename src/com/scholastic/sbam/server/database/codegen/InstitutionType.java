package com.scholastic.sbam.server.database.codegen;

// Generated Apr 19, 2011 7:45:44 PM by Hibernate Tools 3.2.4.GA

/**
 * InstitutionType generated by hbm2java
 */
public class InstitutionType implements java.io.Serializable {

	private String typeCode;
	private String description;
	private String longDescription;
	private String groupCode;

	public InstitutionType() {
	}

	public InstitutionType(String typeCode, String description,
			String longDescription, String groupCode) {
		this.typeCode = typeCode;
		this.description = description;
		this.longDescription = longDescription;
		this.groupCode = groupCode;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLongDescription() {
		return this.longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

}
