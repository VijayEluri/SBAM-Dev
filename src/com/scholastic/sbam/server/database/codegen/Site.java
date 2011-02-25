package com.scholastic.sbam.server.database.codegen;

// Generated Feb 24, 2011 8:28:34 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Site generated by hbm2java
 */
public class Site implements java.io.Serializable {

	private SiteId id;
	private String description;
	private String commissionCode;
	private char pseudoSite;
	private Date createdDatetime;
	private String status;

	public Site() {
	}

	public Site(SiteId id, String description, String commissionCode,
			char pseudoSite, Date createdDatetime, String status) {
		this.id = id;
		this.description = description;
		this.commissionCode = commissionCode;
		this.pseudoSite = pseudoSite;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public SiteId getId() {
		return this.id;
	}

	public void setId(SiteId id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCommissionCode() {
		return this.commissionCode;
	}

	public void setCommissionCode(String commissionCode) {
		this.commissionCode = commissionCode;
	}

	public char getPseudoSite() {
		return this.pseudoSite;
	}

	public void setPseudoSite(char pseudoSite) {
		this.pseudoSite = pseudoSite;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
