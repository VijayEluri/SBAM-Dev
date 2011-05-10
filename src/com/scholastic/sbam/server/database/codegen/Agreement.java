package com.scholastic.sbam.server.database.codegen;

// Generated May 10, 2011 5:37:29 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Agreement generated by hbm2java
 */
public class Agreement implements java.io.Serializable {

	private Integer id;
	private int idCheckDigit;
	private int billUcn;
	private int billUcnSuffix;
	private int agreementLinkId;
	private String agreementTypeCode;
	private String commissionCode;
	private int workstations;
	private int buildings;
	private int population;
	private int enrollment;
	private String deleteReasonCode;
	private String orgPath;
	private String note;
	private Date createdDatetime;
	private char status;

	public Agreement() {
	}

	public Agreement(int idCheckDigit, int billUcn, int billUcnSuffix,
			int agreementLinkId, String agreementTypeCode,
			String commissionCode, int workstations, int buildings,
			int population, int enrollment, String deleteReasonCode,
			String orgPath, String note, Date createdDatetime, char status) {
		this.idCheckDigit = idCheckDigit;
		this.billUcn = billUcn;
		this.billUcnSuffix = billUcnSuffix;
		this.agreementLinkId = agreementLinkId;
		this.agreementTypeCode = agreementTypeCode;
		this.commissionCode = commissionCode;
		this.workstations = workstations;
		this.buildings = buildings;
		this.population = population;
		this.enrollment = enrollment;
		this.deleteReasonCode = deleteReasonCode;
		this.orgPath = orgPath;
		this.note = note;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getIdCheckDigit() {
		return this.idCheckDigit;
	}

	public void setIdCheckDigit(int idCheckDigit) {
		this.idCheckDigit = idCheckDigit;
	}

	public int getBillUcn() {
		return this.billUcn;
	}

	public void setBillUcn(int billUcn) {
		this.billUcn = billUcn;
	}

	public int getBillUcnSuffix() {
		return this.billUcnSuffix;
	}

	public void setBillUcnSuffix(int billUcnSuffix) {
		this.billUcnSuffix = billUcnSuffix;
	}

	public int getAgreementLinkId() {
		return this.agreementLinkId;
	}

	public void setAgreementLinkId(int agreementLinkId) {
		this.agreementLinkId = agreementLinkId;
	}

	public String getAgreementTypeCode() {
		return this.agreementTypeCode;
	}

	public void setAgreementTypeCode(String agreementTypeCode) {
		this.agreementTypeCode = agreementTypeCode;
	}

	public String getCommissionCode() {
		return this.commissionCode;
	}

	public void setCommissionCode(String commissionCode) {
		this.commissionCode = commissionCode;
	}

	public int getWorkstations() {
		return this.workstations;
	}

	public void setWorkstations(int workstations) {
		this.workstations = workstations;
	}

	public int getBuildings() {
		return this.buildings;
	}

	public void setBuildings(int buildings) {
		this.buildings = buildings;
	}

	public int getPopulation() {
		return this.population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getEnrollment() {
		return this.enrollment;
	}

	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}

	public String getDeleteReasonCode() {
		return this.deleteReasonCode;
	}

	public void setDeleteReasonCode(String deleteReasonCode) {
		this.deleteReasonCode = deleteReasonCode;
	}

	public String getOrgPath() {
		return this.orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
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
