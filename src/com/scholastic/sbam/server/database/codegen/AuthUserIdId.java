package com.scholastic.sbam.server.database.codegen;

// Generated Dec 23, 2010 5:12:22 PM by Hibernate Tools 3.2.4.GA

/**
 * AuthUserIdId generated by hbm2java
 */
public class AuthUserIdId implements java.io.Serializable {

	private int agreementId;
	private int ucn;
	private String siteLocCode;
	private String userId;

	public AuthUserIdId() {
	}

	public AuthUserIdId(int agreementId, int ucn, String siteLocCode,
			String userId) {
		this.agreementId = agreementId;
		this.ucn = ucn;
		this.siteLocCode = siteLocCode;
		this.userId = userId;
	}

	public int getAgreementId() {
		return this.agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getUcn() {
		return this.ucn;
	}

	public void setUcn(int ucn) {
		this.ucn = ucn;
	}

	public String getSiteLocCode() {
		return this.siteLocCode;
	}

	public void setSiteLocCode(String siteLocCode) {
		this.siteLocCode = siteLocCode;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuthUserIdId))
			return false;
		AuthUserIdId castOther = (AuthUserIdId) other;

		return (this.getAgreementId() == castOther.getAgreementId())
				&& (this.getUcn() == castOther.getUcn())
				&& ((this.getSiteLocCode() == castOther.getSiteLocCode()) || (this
						.getSiteLocCode() != null
						&& castOther.getSiteLocCode() != null && this
						.getSiteLocCode().equals(castOther.getSiteLocCode())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAgreementId();
		result = 37 * result + this.getUcn();
		result = 37
				* result
				+ (getSiteLocCode() == null ? 0 : this.getSiteLocCode()
						.hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}
