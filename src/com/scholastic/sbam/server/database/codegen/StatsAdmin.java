package com.scholastic.sbam.server.database.codegen;

// Generated Dec 28, 2010 1:03:40 PM by Hibernate Tools 3.2.4.GA

/**
 * StatsAdmin generated by hbm2java
 */
public class StatsAdmin implements java.io.Serializable {

	private int ucn;
	private String adminUsername;
	private String adminPassword;
	private String accessGroup;

	public StatsAdmin() {
	}

	public StatsAdmin(int ucn, String adminUsername, String adminPassword,
			String accessGroup) {
		this.ucn = ucn;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.accessGroup = accessGroup;
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

}