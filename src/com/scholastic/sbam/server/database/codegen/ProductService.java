package com.scholastic.sbam.server.database.codegen;

// Generated Mar 14, 2011 8:15:15 AM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * ProductService generated by hbm2java
 */
public class ProductService implements java.io.Serializable {

	private ProductServiceId id;
	private Date createdDatetime;
	private char status;

	public ProductService() {
	}

	public ProductService(ProductServiceId id, Date createdDatetime, char status) {
		this.id = id;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public ProductServiceId getId() {
		return this.id;
	}

	public void setId(ProductServiceId id) {
		this.id = id;
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
