package com.scholastic.sbam.server.database.codegen;

// Generated Mar 8, 2011 3:29:34 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * DeleteReason generated by hbm2java
 */
public class DeleteReason implements java.io.Serializable {

	private String deleteReasonCode;
	private String description;
	private char status;
	private Date createdDatetime;

	public DeleteReason() {
	}

	public DeleteReason(String deleteReasonCode, String description,
			char status, Date createdDatetime) {
		this.deleteReasonCode = deleteReasonCode;
		this.description = description;
		this.status = status;
		this.createdDatetime = createdDatetime;
	}

	public String getDeleteReasonCode() {
		return this.deleteReasonCode;
	}

	public void setDeleteReasonCode(String deleteReasonCode) {
		this.deleteReasonCode = deleteReasonCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getStatus() {
		return this.status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

}
