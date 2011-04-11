package com.scholastic.sbam.server.database.codegen;

// Generated Apr 9, 2011 12:50:23 PM by Hibernate Tools 3.2.4.GA

/**
 * AgreementContactId generated by hbm2java
 */
public class AgreementContactId implements java.io.Serializable {

	private int agreementId;
	private int contactId;

	public AgreementContactId() {
	}

	public AgreementContactId(int agreementId, int contactId) {
		this.agreementId = agreementId;
		this.contactId = contactId;
	}

	public int getAgreementId() {
		return this.agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getContactId() {
		return this.contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AgreementContactId))
			return false;
		AgreementContactId castOther = (AgreementContactId) other;

		return (this.getAgreementId() == castOther.getAgreementId())
				&& (this.getContactId() == castOther.getContactId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAgreementId();
		result = 37 * result + this.getContactId();
		return result;
	}

}
