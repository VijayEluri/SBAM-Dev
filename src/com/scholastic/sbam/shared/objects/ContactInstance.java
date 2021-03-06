package com.scholastic.sbam.shared.objects;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.scholastic.sbam.client.util.AddressFormatter;

public class ContactInstance extends BetterRowEditInstance implements BeanModelTag, IsSerializable {

	private int			contactId;
	private String		contactTypeCode;
	private String		contactTypeDescription;
	private int			parentUcn;
	private String		fullName;
	private String		title;
	private String		additionalInfo;
	private String		address1;
	private String		address2;
	private String		address3;
	private String		city;
	private String		state;
	private String		zip;
	private String		country;
	private String		eMail;
	private String		eMail2;
	private String		phone;
	private String		phone2;
	private String		fax;
	private String		note;
	private char		status;
	private boolean		active;
	private Date		createdDatetime;
	
	private ContactTypeInstance	contactType;
	private InstitutionInstance	institution;
	
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

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String geteMail2() {
		return eMail2;
	}

	public void seteMail2(String eMail2) {
		this.eMail2 = eMail2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getContactTypeCode() {
		return contactTypeCode;
	}

	public void setContactTypeCode(String contactTypeCode) {
		this.contactTypeCode = contactTypeCode;
	}

	public String getContactTypeDescription() {
		return contactTypeDescription;
	}

	public void setContactTypeDescription(String contactTypeDescription) {
		this.contactTypeDescription = contactTypeDescription;
	}

	public int getParentUcn() {
		return parentUcn;
	}

	public void setParentUcn(int parentUcn) {
		this.parentUcn = parentUcn;
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
	
	public ContactTypeInstance getContactType() {
		return contactType;
	}

	public void setContactType(ContactTypeInstance contactType) {
		this.contactType = contactType;
		if (contactType == null) {
			setContactTypeCode("");
			setContactTypeDescription("None");
		} else {
			setContactTypeCode(contactType.getContactTypeCode());
			setContactTypeDescription(contactType.getDescription());
		}
	}

	public InstitutionInstance getInstitution() {
		return institution;
	}

	public void setInstitution(InstitutionInstance institution) {
		this.institution = institution;
		if (institution == null)
			setParentUcn(0);
		else
			setParentUcn(institution.getUcn());
	}
	
	public String getUniqueKey() {
		return contactId + "";
	}

	public String getHtmlAddress() {
		return AddressFormatter.getMultiLineAddress(address1, address2, address3, city, state, zip, country);
	}

	public static ContactInstance getEmptyInstance() {
		ContactInstance instance = new ContactInstance();
		instance.setContactId(0);
		instance.setFullName("");
		instance.setNewRecord(true);
		return instance;
	}

	public static ContactInstance getUnknownInstance(int contactId) {
		ContactInstance instance = new ContactInstance();
		instance.setContactId(contactId);
		instance.setFullName("Unknown contact ID " + contactId);
		instance.setNewRecord(true);
		return instance;
	}
	
	public void setValuesFrom(ContactInstance fromInstance) {
		this.contactId				=	fromInstance.contactId;
		this.contactTypeCode		=	fromInstance.	contactTypeCode;
		this.contactTypeDescription	=	fromInstance.	contactTypeDescription;
		this.parentUcn				=	fromInstance.	parentUcn;
		this.fullName				=	fromInstance.	fullName;
		this.title					=	fromInstance.	title;
		this.additionalInfo			=	fromInstance.	additionalInfo;
		this.address1				=	fromInstance.	address1;
		this.address2				=	fromInstance.	address2;
		this.address3				=	fromInstance.	address3;
		this.city					=	fromInstance.	city;
		this.state					=	fromInstance.	state;
		this.zip					=	fromInstance.	zip;
		this.country				=	fromInstance.	country;
		this.eMail					=	fromInstance.	eMail;
		this.eMail2					=	fromInstance.	eMail2;
		this.phone					=	fromInstance.	phone;
		this.phone2					=	fromInstance.	phone2;
		this.fax					=	fromInstance.	fax;
		this.note					=	fromInstance.	note;
		this.status					=	fromInstance.	status;
		this.active					=	fromInstance.	active;
		this.createdDatetime		=	fromInstance.	createdDatetime;
			
		this.contactType			=	fromInstance.	contactType;
		this.institution			=	fromInstance.	institution;
	}

	public String toString() {
		return fullName;
	}
}
