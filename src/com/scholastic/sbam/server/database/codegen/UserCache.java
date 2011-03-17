package com.scholastic.sbam.server.database.codegen;

// Generated Mar 17, 2011 4:04:45 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * UserCache generated by hbm2java
 */
public class UserCache implements java.io.Serializable {

	private UserCacheId id;
	private String hint;
	private Date accessDatetime;

	public UserCache() {
	}

	public UserCache(UserCacheId id, String hint, Date accessDatetime) {
		this.id = id;
		this.hint = hint;
		this.accessDatetime = accessDatetime;
	}

	public UserCacheId getId() {
		return this.id;
	}

	public void setId(UserCacheId id) {
		this.id = id;
	}

	public String getHint() {
		return this.hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Date getAccessDatetime() {
		return this.accessDatetime;
	}

	public void setAccessDatetime(Date accessDatetime) {
		this.accessDatetime = accessDatetime;
	}

}
