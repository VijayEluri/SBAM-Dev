package com.scholastic.sbam.server.database.codegen;

// Generated Apr 11, 2011 11:14:19 PM by Hibernate Tools 3.2.4.GA

/**
 * AuthMethodId generated by hbm2java
 */
public class AuthMethodId implements java.io.Serializable {

	private int agreementId;
	private int ucn;
	private int ucnSuffix;
	private String siteLocCode;
	private String methodType;
	private String methodKey;

	public AuthMethodId() {
	}

	public AuthMethodId(int agreementId, int ucn, int ucnSuffix,
			String siteLocCode, String methodType, String methodKey) {
		this.agreementId = agreementId;
		this.ucn = ucn;
		this.ucnSuffix = ucnSuffix;
		this.siteLocCode = siteLocCode;
		this.methodType = methodType;
		this.methodKey = methodKey;
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

	public String getMethodType() {
		return this.methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getMethodKey() {
		return this.methodKey;
	}

	public void setMethodKey(String methodKey) {
		this.methodKey = methodKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuthMethodId))
			return false;
		AuthMethodId castOther = (AuthMethodId) other;

		return (this.getAgreementId() == castOther.getAgreementId())
				&& (this.getUcn() == castOther.getUcn())
				&& (this.getUcnSuffix() == castOther.getUcnSuffix())
				&& ((this.getSiteLocCode() == castOther.getSiteLocCode()) || (this
						.getSiteLocCode() != null
						&& castOther.getSiteLocCode() != null && this
						.getSiteLocCode().equals(castOther.getSiteLocCode())))
				&& ((this.getMethodType() == castOther.getMethodType()) || (this
						.getMethodType() != null
						&& castOther.getMethodType() != null && this
						.getMethodType().equals(castOther.getMethodType())))
				&& ((this.getMethodKey() == castOther.getMethodKey()) || (this
						.getMethodKey() != null
						&& castOther.getMethodKey() != null && this
						.getMethodKey().equals(castOther.getMethodKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAgreementId();
		result = 37 * result + this.getUcn();
		result = 37 * result + this.getUcnSuffix();
		result = 37
				* result
				+ (getSiteLocCode() == null ? 0 : this.getSiteLocCode()
						.hashCode());
		result = 37
				* result
				+ (getMethodType() == null ? 0 : this.getMethodType()
						.hashCode());
		result = 37 * result
				+ (getMethodKey() == null ? 0 : this.getMethodKey().hashCode());
		return result;
	}

}
