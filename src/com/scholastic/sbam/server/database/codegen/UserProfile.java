package com.scholastic.sbam.server.database.codegen;

// Generated Mar 24, 2011 5:22:08 PM by Hibernate Tools 3.2.4.GA

/**
 * UserProfile generated by hbm2java
 */
public class UserProfile implements java.io.Serializable {

	private String userName;
	private char tooltips;
	private int recentSearches;
	private int recentAgreements;
	private int recentCustomers;
	private int sessionTimeoutMinutes;
	private int passwordExpireDays;
	private char restorePortlets;

	public UserProfile() {
	}

	public UserProfile(String userName, char tooltips, int recentSearches,
			int recentAgreements, int recentCustomers,
			int sessionTimeoutMinutes, int passwordExpireDays,
			char restorePortlets) {
		this.userName = userName;
		this.tooltips = tooltips;
		this.recentSearches = recentSearches;
		this.recentAgreements = recentAgreements;
		this.recentCustomers = recentCustomers;
		this.sessionTimeoutMinutes = sessionTimeoutMinutes;
		this.passwordExpireDays = passwordExpireDays;
		this.restorePortlets = restorePortlets;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public char getTooltips() {
		return this.tooltips;
	}

	public void setTooltips(char tooltips) {
		this.tooltips = tooltips;
	}

	public int getRecentSearches() {
		return this.recentSearches;
	}

	public void setRecentSearches(int recentSearches) {
		this.recentSearches = recentSearches;
	}

	public int getRecentAgreements() {
		return this.recentAgreements;
	}

	public void setRecentAgreements(int recentAgreements) {
		this.recentAgreements = recentAgreements;
	}

	public int getRecentCustomers() {
		return this.recentCustomers;
	}

	public void setRecentCustomers(int recentCustomers) {
		this.recentCustomers = recentCustomers;
	}

	public int getSessionTimeoutMinutes() {
		return this.sessionTimeoutMinutes;
	}

	public void setSessionTimeoutMinutes(int sessionTimeoutMinutes) {
		this.sessionTimeoutMinutes = sessionTimeoutMinutes;
	}

	public int getPasswordExpireDays() {
		return this.passwordExpireDays;
	}

	public void setPasswordExpireDays(int passwordExpireDays) {
		this.passwordExpireDays = passwordExpireDays;
	}

	public char getRestorePortlets() {
		return this.restorePortlets;
	}

	public void setRestorePortlets(char restorePortlets) {
		this.restorePortlets = restorePortlets;
	}

}
