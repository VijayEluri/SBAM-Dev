package com.scholastic.sbam.server.database.codegen;

// Generated Mar 17, 2011 9:05:23 AM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * StatsAdmin generated by hbm2java
 */
public class StatsAdmin implements java.io.Serializable {

	private int ucn;
	private String adminUsername;
	private String adminPassword;
	private String accessGroup;
	private String note;
	private Date createdDatetime;
	private char status;

	public StatsAdmin() {
	}

	public StatsAdmin(int ucn, String adminUsername, String adminPassword,
			String accessGroup, String note, Date createdDatetime, char status) {
		this.ucn = ucn;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.accessGroup = accessGroup;
		this.note = note;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public int getUcn() {
		return this.ucn;
	}

	public void setUcn(int ucn) {
		this.ucn = ucn;
	}

	public String getAdminUsername() {
		return this.adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAccessGroup() {
		return this.accessGroup;
	}

	public void setAccessGroup(String accessGroup) {
		this.accessGroup = accessGroup;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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
