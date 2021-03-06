package com.scholastic.sbam.shared.objects;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;

public class AgreementTypeInstance extends BetterRowEditInstance implements BeanModelTag, IsSerializable {

	private static BeanModelFactory beanModelfactory;

	private String agreementTypeCode;
	private String description;
	private String shortName;
	private char   status;
	private boolean active;
	private Date   createdDatetime;
	
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

	public String getAgreementTypeCode() {
		return agreementTypeCode;
	}

	public void setAgreementTypeCode(String agreementTypeCode) {
		this.agreementTypeCode = agreementTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String toString() {
		return description;
	}
	
	public static AgreementTypeInstance getEmptyInstance() {
		AgreementTypeInstance instance = new AgreementTypeInstance();
		instance.agreementTypeCode = "";
		instance.description = "";
		instance.shortName   = "";
		return instance;
	}
	
	public static AgreementTypeInstance getUnknownInstance(String code) {
		AgreementTypeInstance instance = new AgreementTypeInstance();
		instance.agreementTypeCode = code;
		instance.description = "Unknown agreement type " + code;
		return instance;
	}

	public static BeanModel obtainModel(AgreementTypeInstance instance) {
		if (beanModelfactory == null)
			beanModelfactory  = BeanModelLookup.get().getFactory(AgreementTypeInstance.class);
		BeanModel model = beanModelfactory.createModel(instance);
		return model;
	}
	
	public String getDescriptionAndCode() {
		if (agreementTypeCode == null || agreementTypeCode.length() == 0)
			return description;
		return description + " [ " + agreementTypeCode + " ]";
	}
}
