package com.scholastic.sbam.shared.objects;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;

public class ContactTypeInstance extends BetterRowEditInstance implements BeanModelTag, IsSerializable {

	private static BeanModelFactory beanModelfactory;

	private String		contactTypeCode;
	private String		description;
	private char		status;
	private boolean		active;
	private Date		createdDatetime;
	
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

	public String getContactTypeCode() {
		return contactTypeCode;
	}

	public void setContactTypeCode(String contactTypeCode) {
		this.contactTypeCode = contactTypeCode;
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
	
	public static ContactTypeInstance getEmptyInstance() {
		ContactTypeInstance instance = new ContactTypeInstance();
		instance.contactTypeCode = "";
		instance.description = "None";
		return instance;
	}
	
	public static ContactTypeInstance getUnknownInstance(String code) {
		ContactTypeInstance instance = new ContactTypeInstance();
		instance.contactTypeCode = code;
		instance.description = "Unknwon type code " + code;
		return instance;
	}

	public static BeanModel obtainModel(ContactTypeInstance instance) {
		if (beanModelfactory == null)
			beanModelfactory  = BeanModelLookup.get().getFactory(ContactTypeInstance.class);
		BeanModel model = beanModelfactory.createModel(instance);
		return model;
	}

	public String toString() {
		return description;
	}
}
