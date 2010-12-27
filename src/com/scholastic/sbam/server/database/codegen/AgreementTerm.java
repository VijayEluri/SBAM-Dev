package com.scholastic.sbam.server.database.codegen;

// Generated Dec 23, 2010 5:12:22 PM by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * AgreementTerm generated by hbm2java
 */
public class AgreementTerm implements java.io.Serializable {

	private AgreementTermId id;
	private String productCode;
	private Date startDate;
	private Date endDate;
	private Date terminateDate;
	private String termType;
	private String cancelReasonCode;
	private Date cancelDate;
	private BigDecimal dollarValue;
	private Integer workstations;
	private Integer buildings;
	private Integer population;
	private Integer enrollment;
	private String poNumber;
	private Integer contractGroup;
	private Integer referenceSaId;
	private Date createdDatetime;
	private char status;

	public AgreementTerm() {
	}

	public AgreementTerm(AgreementTermId id, String productCode,
			Date startDate, Date endDate, Date terminateDate, String termType,
			Date createdDatetime, char status) {
		this.id = id;
		this.productCode = productCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.terminateDate = terminateDate;
		this.termType = termType;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public AgreementTerm(AgreementTermId id, String productCode,
			Date startDate, Date endDate, Date terminateDate, String termType,
			String cancelReasonCode, Date cancelDate, BigDecimal dollarValue,
			Integer workstations, Integer buildings, Integer population,
			Integer enrollment, String poNumber, Integer contractGroup,
			Integer referenceSaId, Date createdDatetime, char status) {
		this.id = id;
		this.productCode = productCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.terminateDate = terminateDate;
		this.termType = termType;
		this.cancelReasonCode = cancelReasonCode;
		this.cancelDate = cancelDate;
		this.dollarValue = dollarValue;
		this.workstations = workstations;
		this.buildings = buildings;
		this.population = population;
		this.enrollment = enrollment;
		this.poNumber = poNumber;
		this.contractGroup = contractGroup;
		this.referenceSaId = referenceSaId;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public AgreementTermId getId() {
		return this.id;
	}

	public void setId(AgreementTermId id) {
		this.id = id;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getTerminateDate() {
		return this.terminateDate;
	}

	public void setTerminateDate(Date terminateDate) {
		this.terminateDate = terminateDate;
	}

	public String getTermType() {
		return this.termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getCancelReasonCode() {
		return this.cancelReasonCode;
	}

	public void setCancelReasonCode(String cancelReasonCode) {
		this.cancelReasonCode = cancelReasonCode;
	}

	public Date getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public BigDecimal getDollarValue() {
		return this.dollarValue;
	}

	public void setDollarValue(BigDecimal dollarValue) {
		this.dollarValue = dollarValue;
	}

	public Integer getWorkstations() {
		return this.workstations;
	}

	public void setWorkstations(Integer workstations) {
		this.workstations = workstations;
	}

	public Integer getBuildings() {
		return this.buildings;
	}

	public void setBuildings(Integer buildings) {
		this.buildings = buildings;
	}

	public Integer getPopulation() {
		return this.population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Integer getEnrollment() {
		return this.enrollment;
	}

	public void setEnrollment(Integer enrollment) {
		this.enrollment = enrollment;
	}

	public String getPoNumber() {
		return this.poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Integer getContractGroup() {
		return this.contractGroup;
	}

	public void setContractGroup(Integer contractGroup) {
		this.contractGroup = contractGroup;
	}

	public Integer getReferenceSaId() {
		return this.referenceSaId;
	}

	public void setReferenceSaId(Integer referenceSaId) {
		this.referenceSaId = referenceSaId;
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
