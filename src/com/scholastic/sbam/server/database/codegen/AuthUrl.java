package com.scholastic.sbam.server.database.codegen;

// Generated Feb 1, 2012 5:33:22 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * AuthUrl generated by hbm2java
 */
public class AuthUrl implements java.io.Serializable {

	private AuthUrlId id;
	private int proxyId;
	private char remote;
	private char approved;
	private char activated;
	private String note;
	private Date createdDatetime;
	private Date updatedDatetime;
	private Date activatedDatetime;
	private Date deactivatedDatetime;
	private Date reactivatedDatetime;

	public AuthUrl() {
	}

	public AuthUrl(AuthUrlId id, int proxyId, char remote, char approved,
			char activated, String note, Date createdDatetime,
			Date updatedDatetime, Date activatedDatetime,
			Date deactivatedDatetime, Date reactivatedDatetime) {
		this.id = id;
		this.proxyId = proxyId;
		this.remote = remote;
		this.approved = approved;
		this.activated = activated;
		this.note = note;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.activatedDatetime = activatedDatetime;
		this.deactivatedDatetime = deactivatedDatetime;
		this.reactivatedDatetime = reactivatedDatetime;
	}

	public AuthUrlId getId() {
		return this.id;
	}

	public void setId(AuthUrlId id) {
		this.id = id;
	}

	public int getProxyId() {
		return this.proxyId;
	}

	public void setProxyId(int proxyId) {
		this.proxyId = proxyId;
	}

	public char getRemote() {
		return this.remote;
	}

	public void setRemote(char remote) {
		this.remote = remote;
	}

	public char getApproved() {
		return this.approved;
	}

	public void setApproved(char approved) {
		this.approved = approved;
	}

	public char getActivated() {
		return this.activated;
	}

	public void setActivated(char activated) {
		this.activated = activated;
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

	public Date getUpdatedDatetime() {
		return this.updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public Date getActivatedDatetime() {
		return this.activatedDatetime;
	}

	public void setActivatedDatetime(Date activatedDatetime) {
		this.activatedDatetime = activatedDatetime;
	}

	public Date getDeactivatedDatetime() {
		return this.deactivatedDatetime;
	}

	public void setDeactivatedDatetime(Date deactivatedDatetime) {
		this.deactivatedDatetime = deactivatedDatetime;
	}

	public Date getReactivatedDatetime() {
		return this.reactivatedDatetime;
	}

	public void setReactivatedDatetime(Date reactivatedDatetime) {
		this.reactivatedDatetime = reactivatedDatetime;
	}

}
