package com.scholastic.sbam.server.database.codegen;

// Generated Dec 23, 2010 5:12:22 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Product generated by hbm2java
 */
public class Product implements java.io.Serializable {

	private String productCode;
	private String description;
	private String shortName;
	private Date createdDatetime;
	private char status;

	public Product() {
	}

	public Product(String productCode, String description, String shortName,
			Date createdDatetime, char status) {
		this.productCode = productCode;
		this.description = description;
		this.shortName = shortName;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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
