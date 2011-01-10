package com.scholastic.sbam.server.database.codegen;

// Generated Dec 28, 2010 1:03:40 PM by Hibernate Tools 3.2.4.GA

/**
 * AgreementSiteId generated by hbm2java
 */
public class AgreementSiteId implements java.io.Serializable {

	private int agreementId;
	private int id;

	public AgreementSiteId() {
	}

	public AgreementSiteId(int agreementId, int id) {
		this.agreementId = agreementId;
		this.id = id;
	}

	public int getAgreementId() {
		return this.agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
				&& (this.getId() == castOther.getId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAgreementId();
		result = 37 * result + this.getId();
		return result;
	}

}