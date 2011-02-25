package com.scholastic.sbam.server.database.codegen;

// Generated Feb 24, 2011 8:28:34 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * ExportAuthUnit generated by hbm2java
 */
public class ExportAuthUnit implements java.io.Serializable {

	private Integer id;
	private int siteUcn;
	private String siteLocCode;
	private int billUcn;
	private int siteParentUcn;
	private Date createdDatetime;

	public ExportAuthUnit() {
	}

	public ExportAuthUnit(int siteUcn, String siteLocCode, int billUcn,
			int siteParentUcn, Date createdDatetime) {
		this.siteUcn = siteUcn;
		this.siteLocCode = siteLocCode;
		this.billUcn = billUcn;
		this.siteParentUcn = siteParentUcn;
		this.createdDatetime = createdDatetime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSiteUcn() {
		return this.siteUcn;
	}

	public void setSiteUcn(int siteUcn) {
		this.siteUcn = siteUcn;
	}

	public String getSiteLocCode() {
		return this.siteLocCode;
	}

	public void setSiteLocCode(String siteLocCode) {
		this.siteLocCode = siteLocCode;
	}

	public int getBillUcn() {
		return this.billUcn;
	}

	public void setBillUcn(int billUcn) {
		this.billUcn = billUcn;
	}

	public int getSiteParentUcn() {
		return this.siteParentUcn;
	}

	public void setSiteParentUcn(int siteParentUcn) {
		this.siteParentUcn = siteParentUcn;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

}
