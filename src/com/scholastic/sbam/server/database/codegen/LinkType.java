package com.scholastic.sbam.server.database.codegen;

// Generated Mar 29, 2011 4:59:05 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * LinkType generated by hbm2java
 */
public class LinkType implements java.io.Serializable {

	private String linkTypeCode;
	private String description;
	private Date createdDatetime;
	private char status;

	public LinkType() {
	}

	public LinkType(String linkTypeCode, String description,
			Date createdDatetime, char status) {
		this.linkTypeCode = linkTypeCode;
		this.description = description;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public String getLinkTypeCode() {
		return this.linkTypeCode;
	}

	public void setLinkTypeCode(String linkTypeCode) {
		this.linkTypeCode = linkTypeCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public char getStatus() {
		return this.status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
