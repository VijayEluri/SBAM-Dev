package com.scholastic.sbam.server.database.codegen;

// Generated Apr 9, 2011 12:50:23 PM by Hibernate Tools 3.2.4.GA

/**
 * SiteId generated by hbm2java
 */
public class SiteId implements java.io.Serializable {

	private int ucn;
	private int ucnSuffix;
	private String siteLocCode;

	public SiteId() {
	}

	public SiteId(int ucn, int ucnSuffix, String siteLocCode) {
		this.ucn = ucn;
		this.ucnSuffix = ucnSuffix;
		this.siteLocCode = siteLocCode;
	}

	public int getUcn() {
		return this.ucn;
	}

	public void setUcn(int ucn) {
		this.ucn = ucn;
	}

	public int getUcnSuffix() {
		return this.ucnSuffix;
	}

	public void setUcnSuffix(int ucnSuffix) {
		this.ucnSuffix = ucnSuffix;
	}

	public String getSiteLocCode() {
		return this.siteLocCode;
	}

	public void setSiteLocCode(String siteLocCode) {
		this.siteLocCode = siteLocCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SiteId))
			return false;
		SiteId castOther = (SiteId) other;

		return (this.getUcn() == castOther.getUcn())
				&& (this.getUcnSuffix() == castOther.getUcnSuffix())
				&& ((this.getSiteLocCode() == castOther.getSiteLocCode()) || (this
						.getSiteLocCode() != null
						&& castOther.getSiteLocCode() != null && this
						.getSiteLocCode().equals(castOther.getSiteLocCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUcn();
		result = 37 * result + this.getUcnSuffix();
		result = 37
				* result
				+ (getSiteLocCode() == null ? 0 : this.getSiteLocCode()
						.hashCode());
		return result;
	}

}
