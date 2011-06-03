package com.scholastic.sbam.server.database.codegen;

// Generated Jun 3, 2011 3:04:04 PM by Hibernate Tools 3.2.4.GA

/**
 * AgreementSiteId generated by hbm2java
 */
public class AgreementSiteId implements java.io.Serializable {

	private int agreementId;
	private int siteUcn;
	private int siteUcnSuffix;
	private String siteLocCode;

	public AgreementSiteId() {
	}

	public AgreementSiteId(int agreementId, int siteUcn, int siteUcnSuffix,
			String siteLocCode) {
		this.agreementId = agreementId;
		this.siteUcn = siteUcn;
		this.siteUcnSuffix = siteUcnSuffix;
		this.siteLocCode = siteLocCode;
	}

	public int getAgreementId() {
		return this.agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getSiteUcn() {
		return this.siteUcn;
	}

	public void setSiteUcn(int siteUcn) {
		this.siteUcn = siteUcn;
	}

	public int getSiteUcnSuffix() {
		return this.siteUcnSuffix;
	}

	public void setSiteUcnSuffix(int siteUcnSuffix) {
		this.siteUcnSuffix = siteUcnSuffix;
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
		if (!(other instanceof AgreementSiteId))
			return false;
		AgreementSiteId castOther = (AgreementSiteId) other;

		return (this.getAgreementId() == castOther.getAgreementId())
				&& (this.getSiteUcn() == castOther.getSiteUcn())
				&& (this.getSiteUcnSuffix() == castOther.getSiteUcnSuffix())
				&& ((this.getSiteLocCode() == castOther.getSiteLocCode()) || (this
						.getSiteLocCode() != null
						&& castOther.getSiteLocCode() != null && this
						.getSiteLocCode().equals(castOther.getSiteLocCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAgreementId();
		result = 37 * result + this.getSiteUcn();
		result = 37 * result + this.getSiteUcnSuffix();
		result = 37
				* result
				+ (getSiteLocCode() == null ? 0 : this.getSiteLocCode()
						.hashCode());
		return result;
	}

}
