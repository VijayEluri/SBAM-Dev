package com.scholastic.sbam.server.database.codegen;

// Generated Apr 5, 2011 10:48:26 AM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Service generated by hbm2java
 */
public class Service implements java.io.Serializable {

	private String serviceCode;
	private String description;
	private char serviceType;
	private String exportValue;
	private String exportFile;
	private String presentationPath;
	private int seq;
	private Date createdDatetime;
	private char status;

	public Service() {
	}

	public Service(String serviceCode, String description, char serviceType,
			String exportValue, String exportFile, String presentationPath,
			int seq, Date createdDatetime, char status) {
		this.serviceCode = serviceCode;
		this.description = description;
		this.serviceType = serviceType;
		this.exportValue = exportValue;
		this.exportFile = exportFile;
		this.presentationPath = presentationPath;
		this.seq = seq;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(char serviceType) {
		this.serviceType = serviceType;
	}

	public String getExportValue() {
		return this.exportValue;
	}

	public void setExportValue(String exportValue) {
		this.exportValue = exportValue;
	}

	public String getExportFile() {
		return this.exportFile;
	}

	public void setExportFile(String exportFile) {
		this.exportFile = exportFile;
	}

	public String getPresentationPath() {
		return this.presentationPath;
	}

	public void setPresentationPath(String presentationPath) {
		this.presentationPath = presentationPath;
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public char getStatus() {
		return this.status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
