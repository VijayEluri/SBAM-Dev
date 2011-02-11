package com.scholastic.sbam.server.database.codegen;

// Generated Feb 11, 2011 6:12:56 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * CancelReason generated by hbm2java
 */
public class CancelReason implements java.io.Serializable {

	private String cancelReasonCode;
	private String description;
	private char changeNotCancel;
	private char status;
	private Date createdDatetime;

	public CancelReason() {
	}

	public CancelReason(String cancelReasonCode, String description,
			char changeNotCancel, char status, Date createdDatetime) {
		this.cancelReasonCode = cancelReasonCode;
		this.description = description;
		this.changeNotCancel = changeNotCancel;
		this.status = status;
		this.createdDatetime = createdDatetime;
	}

	public String getCancelReasonCode() {
		return this.cancelReasonCode;
	}

	public void setCancelReasonCode(String cancelReasonCode) {
		this.cancelReasonCode = cancelReasonCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getChangeNotCancel() {
		return this.changeNotCancel;
	}

	public void setChangeNotCancel(char changeNotCancel) {
		this.changeNotCancel = changeNotCancel;
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
