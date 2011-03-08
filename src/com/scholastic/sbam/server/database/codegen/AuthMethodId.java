package com.scholastic.sbam.server.database.codegen;

// Generated Mar 7, 2011 3:54:30 PM by Hibernate Tools 3.2.4.GA

/**
 * AuthMethodId generated by hbm2java
 */
public class AuthMethodId implements java.io.Serializable {

	private int agreementId;
	private int ucn;
	private int ucnSuffix;
	private String siteLocCode;
	private String methodType;
	private String url;
	private String userId;
	private long ipLo;
	private long ipHi;

	public AuthMethodId() {
	}

	public AuthMethodId(int agreementId, int ucn, int ucnSuffix,
			String siteLocCode, String methodType, String url, String userId,
			long ipLo, long ipHi) {
		this.agreementId = agreementId;
		this.ucn = ucn;
		this.ucnSuffix = ucnSuffix;
		this.siteLocCode = siteLocCode;
		this.methodType = methodType;
		this.url = url;
		this.userId = userId;
		this.ipLo = ipLo;
		this.ipHi = ipHi;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getIpLo() {
		return this.ipLo;
	}

	public void setIpLo(long ipLo) {
		this.ipLo = ipLo;
	}

	public long getIpHi() {
		return this.ipHi;
	}

	public void setIpHi(long ipHi) {
		this.ipHi = ipHi;
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
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null
						&& castOther.getUrl() != null && this.getUrl().equals(
						castOther.getUrl())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& (this.getIpLo() == castOther.getIpLo())
				&& (this.getIpHi() == castOther.getIpHi());
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
				+ (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (int) this.getIpLo();
		result = 37 * result + (int) this.getIpHi();
		return result;
	}

}
