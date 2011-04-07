package com.scholastic.sbam.shared.objects;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;

public class AgreementContactInstance extends BetterRowEditInstance implements BeanModelTag, IsSerializable {

	private int		agreementId;
	private int		contactId;
	private char	renewalContact;
	
	private char	status;
	private boolean	active;
	private Date	createdDatetime;
	
	private ContactInstance contact;
	
	@Override
	public void markForDeletion() {
		setStatus('X');
	}

	@Override
	public boolean thisIsDeleted() {
		return status == 'X';
	}

	@Override
	public boolean thisIsValid() {
		return true;
	}

	@Override
	public String returnTriggerProperty() {
		return "junk";
	}

	@Override
	public String returnTriggerValue() {
		return "junk";
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
		this.active = (this.status == 'A');
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		if (this.status == 'X')
			return;
		setStatus(active?'A':'I');
	}

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public char getRenewalContact() {
		return renewalContact;
	}

	public void setRenewalContact(char renewalContact) {
		this.renewalContact = renewalContact;
	}

	public ContactInstance getContact() {
		return contact;
	}

	public void setContact(ContactInstance contact) {
		this.contact = contact;
	}
	
	public String getUniqueKey() {
		return agreementId + ":" + contactId;
	}

	public String toString() {
		return "Site " + agreementId + "-" + contactId;
	}
}
