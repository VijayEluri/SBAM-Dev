package com.scholastic.sbam.server.database.codegen;

// Generated Jun 3, 2011 3:04:04 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * RemoteSetupUrl generated by hbm2java
 */
public class RemoteSetupUrl implements java.io.Serializable {

	private RemoteSetupUrlId id;
	private String url;
	private char approved;
	private char activated;
	private String note;
	private String orgPath;
	private Date createdDatetime;
	private char status;

	public RemoteSetupUrl() {
	}

	public RemoteSetupUrl(RemoteSetupUrlId id, String url, char approved,
			char activated, String note, String orgPath, Date createdDatetime,
			char status) {
		this.id = id;
		this.url = url;
		this.approved = approved;
		this.activated = activated;
		this.note = note;
		this.orgPath = orgPath;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public RemoteSetupUrlId getId() {
		return this.id;
	}

	public void setId(RemoteSetupUrlId id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public char getApproved() {
		return this.approved;
	}

	public void setApproved(char approved) {
		this.approved = approved;
	}

	public char getActivated() {
		return this.activated;
	}

	public void setActivated(char activated) {
		this.activated = activated;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrgPath() {
		return this.orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
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
