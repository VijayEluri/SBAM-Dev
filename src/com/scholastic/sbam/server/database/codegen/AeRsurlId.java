package com.scholastic.sbam.server.database.codegen;

// Generated Feb 1, 2012 5:33:22 PM by Hibernate Tools 3.2.4.GA

/**
 * AeRsurlId generated by hbm2java
 */
public class AeRsurlId implements java.io.Serializable {

	private int auId;
	private String url;
	private int aeId;

	public AeRsurlId() {
	}

	public AeRsurlId(int auId, String url, int aeId) {
		this.auId = auId;
		this.url = url;
		this.aeId = aeId;
	}

	public int getAuId() {
		return this.auId;
	}

	public void setAuId(int auId) {
		this.auId = auId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getAeId() {
		return this.aeId;
	}

	public void setAeId(int aeId) {
		this.aeId = aeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AeRsurlId))
			return false;
		AeRsurlId castOther = (AeRsurlId) other;

		return (this.getAuId() == castOther.getAuId())
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null
						&& castOther.getUrl() != null && this.getUrl().equals(
						castOther.getUrl())))
				&& (this.getAeId() == castOther.getAeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAuId();
		result = 37 * result
				+ (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result + this.getAeId();
		return result;
	}

}
