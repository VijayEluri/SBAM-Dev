package com.scholastic.sbam.shared.objects;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;

public class ServiceInstance extends BetterRowEditInstance implements BeanModelTag, IsSerializable {

	public final static char   [] SERVICE_TYPE_CODES = {'I', 'A' };
	public final static String [] SERVICE_TYPE_NAMES = {"Initial", "Add-on" };
	
	private String serviceCode;
	private String description;
	private char   serviceType;
	private String serviceTypeName;
	private String exportValue;
	private String exportFile;
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

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
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

	public char getServiceType() {
		return serviceType;
	}

	public void setServiceType(char serviceType) {
		this.serviceType = serviceType;
	//	System.out.println("Set service type to " + this.serviceType + " directly.");
		setServiceTypeName(serviceType);
	}

	public String getExportValue() {
		return exportValue;
	}

	public void setExportValue(String exportValue) {
		this.exportValue = exportValue;
	}

	public String getExportFile() {
		return exportFile;
	}

	public void setExportFile(String exportFile) {
		this.exportFile = exportFile;
	}

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	//	System.out.println("Set service type name to " + this.serviceTypeName + " directly.");
		this.serviceType = getServiceTypeCode(serviceTypeName);
	//	System.out.println("Forced service type to " + this.serviceType + " using " + serviceTypeName);
	}
	
	public void setServiceTypeName(char serviceType) {
		this.serviceTypeName = getServiceTypeName(serviceType);
	//	System.out.println("Forced service type name to " + this.serviceType + " using " + serviceType);
	}

	public String getServiceTypeName(char serviceTypeCode) {
		for (int i = 0; i < SERVICE_TYPE_CODES.length; i++)
			if (serviceTypeCode == SERVICE_TYPE_CODES [i])
				return SERVICE_TYPE_NAMES [i];
		return "Unknown '" + serviceTypeCode + "'";
	}

	public char getServiceTypeCode(String serviceTypeName) {
		if (serviceTypeName == null)
			return '_';
		for (int i = 0; i < SERVICE_TYPE_NAMES.length; i++)
			if (serviceTypeName.equals(SERVICE_TYPE_NAMES [i]))
				return SERVICE_TYPE_CODES [i];
		return '?';
	}
	
	public static ServiceInstance getEmptyInstance() {
		ServiceInstance instance = new ServiceInstance();
		instance.serviceCode = "";
		instance.description = "";
		return instance;
	}
	
	public static ServiceInstance getUnknownInstance(String code) {
		ServiceInstance instance = new ServiceInstance();
		instance.serviceCode = code;
		instance.description = "Unknown service " + code;
		return instance;
	}
}
