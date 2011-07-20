package com.scholastic.sbam.server.database.codegen;

// Generated Jul 20, 2011 10:53:06 AM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Institution generated by hbm2java
 */
public class Institution implements java.io.Serializable {

	private int ucn;
	private int parentUcn;
	private String institutionName;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String webUrl;
	private String phone;
	private String fax;
	private String mailAddress1;
	private String mailAddress2;
	private String mailAddress3;
	private String mailCity;
	private String mailState;
	private String mailZip;
	private String mailCountry;
	private String typeCode;
	private String groupCode;
	private String publicPrivateCode;
	private String alternateIds;
	private String source;
	private Date createdDate;
	private Date closedDate;
	private char status;

	public Institution() {
	}

	public Institution(int ucn, int parentUcn, String institutionName,
			String address1, String address2, String address3, String city,
			String state, String zip, String country, String phone, String fax,
			String typeCode, String groupCode, String publicPrivateCode,
			String alternateIds, String source, char status) {
		this.ucn = ucn;
		this.parentUcn = parentUcn;
		this.institutionName = institutionName;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.phone = phone;
		this.fax = fax;
		this.typeCode = typeCode;
		this.groupCode = groupCode;
		this.publicPrivateCode = publicPrivateCode;
		this.alternateIds = alternateIds;
		this.source = source;
		this.status = status;
	}

	public Institution(int ucn, int parentUcn, String institutionName,
			String address1, String address2, String address3, String city,
			String state, String zip, String country, String webUrl,
			String phone, String fax, String mailAddress1, String mailAddress2,
			String mailAddress3, String mailCity, String mailState,
			String mailZip, String mailCountry, String typeCode,
			String groupCode, String publicPrivateCode, String alternateIds,
			String source, Date createdDate, Date closedDate, char status) {
		this.ucn = ucn;
		this.parentUcn = parentUcn;
		this.institutionName = institutionName;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.webUrl = webUrl;
		this.phone = phone;
		this.fax = fax;
		this.mailAddress1 = mailAddress1;
		this.mailAddress2 = mailAddress2;
		this.mailAddress3 = mailAddress3;
		this.mailCity = mailCity;
		this.mailState = mailState;
		this.mailZip = mailZip;
		this.mailCountry = mailCountry;
		this.typeCode = typeCode;
		this.groupCode = groupCode;
		this.publicPrivateCode = publicPrivateCode;
		this.alternateIds = alternateIds;
		this.source = source;
		this.createdDate = createdDate;
		this.closedDate = closedDate;
		this.status = status;
	}

	public int getUcn() {
		return this.ucn;
	}

	public void setUcn(int ucn) {
		this.ucn = ucn;
	}

	public int getParentUcn() {
		return this.parentUcn;
	}

	public void setParentUcn(int parentUcn) {
		this.parentUcn = parentUcn;
	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWebUrl() {
		return this.webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMailAddress1() {
		return this.mailAddress1;
	}

	public void setMailAddress1(String mailAddress1) {
		this.mailAddress1 = mailAddress1;
	}

	public String getMailAddress2() {
		return this.mailAddress2;
	}

	public void setMailAddress2(String mailAddress2) {
		this.mailAddress2 = mailAddress2;
	}

	public String getMailAddress3() {
		return this.mailAddress3;
	}

	public void setMailAddress3(String mailAddress3) {
		this.mailAddress3 = mailAddress3;
	}

	public String getMailCity() {
		return this.mailCity;
	}

	public void setMailCity(String mailCity) {
		this.mailCity = mailCity;
	}

	public String getMailState() {
		return this.mailState;
	}

	public void setMailState(String mailState) {
		this.mailState = mailState;
	}

	public String getMailZip() {
		return this.mailZip;
	}

	public void setMailZip(String mailZip) {
		this.mailZip = mailZip;
	}

	public String getMailCountry() {
		return this.mailCountry;
	}

	public void setMailCountry(String mailCountry) {
		this.mailCountry = mailCountry;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getPublicPrivateCode() {
		return this.publicPrivateCode;
	}

	public void setPublicPrivateCode(String publicPrivateCode) {
		this.publicPrivateCode = publicPrivateCode;
	}

	public String getAlternateIds() {
		return this.alternateIds;
	}

	public void setAlternateIds(String alternateIds) {
		this.alternateIds = alternateIds;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getClosedDate() {
		return this.closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public char getStatus() {
		return this.status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
