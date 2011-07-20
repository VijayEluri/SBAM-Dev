package com.scholastic.sbam.server.database.codegen;

// Generated Jul 20, 2011 10:53:06 AM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * AgreementLink generated by hbm2java
 */
public class AgreementLink implements java.io.Serializable {

	private Integer linkId;
	private int linkIdCheckDigit;
	private int ucn;
	private String linkTypeCode;
	private String note;
	private Date createdDatetime;
	private char status;

	public AgreementLink() {
	}

	public AgreementLink(int linkIdCheckDigit, int ucn, String linkTypeCode,
			String note, Date createdDatetime, char status) {
		this.linkIdCheckDigit = linkIdCheckDigit;
		this.ucn = ucn;
		this.linkTypeCode = linkTypeCode;
		this.note = note;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public Integer getLinkId() {
		return this.linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	public int getLinkIdCheckDigit() {
		return this.linkIdCheckDigit;
	}

	public void setLinkIdCheckDigit(int linkIdCheckDigit) {
		this.linkIdCheckDigit = linkIdCheckDigit;
	}

	public int getUcn() {
		return this.ucn;
	}

	public void setUcn(int ucn) {
		this.ucn = ucn;
	}

	public String getLinkTypeCode() {
		return this.linkTypeCode;
	}

	public void setLinkTypeCode(String linkTypeCode) {
		this.linkTypeCode = linkTypeCode;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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
