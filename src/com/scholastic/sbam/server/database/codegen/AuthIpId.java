package com.scholastic.sbam.server.database.codegen;

// Generated Apr 19, 2011 7:45:44 PM by Hibernate Tools 3.2.4.GA

/**
 * AuthIpId generated by hbm2java
 */
public class AuthIpId implements java.io.Serializable {

	private int agreementId;
	private int siteUcn;
	private int siteUcnSuffix;
	private String siteLocCode;
	private long ipLo;

	public AuthIpId() {
	}

	public AuthIpId(int agreementId, int siteUcn, int siteUcnSuffix,
			String siteLocCode, long ipLo) {
		this.agreementId = agreementId;
		this.siteUcn = siteUcn;
		this.siteUcnSuffix = siteUcnSuffix;
		this.siteLocCode = siteLocCode;
		this.ipLo = ipLo;
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

	public long getIpLo() {
		return this.ipLo;
	}

	public void setIpLo(long ipLo) {
		this.ipLo = ipLo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuthIpId))
			return false;
		AuthIpId castOther = (AuthIpId) other;

		return (this.getAgreementId() == castOther.getAgreementId())
				&& (this.getSiteUcn() == castOther.getSiteUcn())
				&& (this.getSiteUcnSuffix() == castOther.getSiteUcnSuffix())
				&& ((this.getSiteLocCode() == castOther.getSiteLocCode()) || (this
						.getSiteLocCode() != null
						&& castOther.getSiteLocCode() != null && this
						.getSiteLocCode().equals(castOther.getSiteLocCode())))
				&& (this.getIpLo() == castOther.getIpLo());
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
		result = 37 * result + (int) this.getIpLo();
		return result;
	}

}
