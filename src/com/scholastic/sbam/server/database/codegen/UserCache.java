package com.scholastic.sbam.server.database.codegen;

// Generated Mar 10, 2011 6:14:02 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * UserCache generated by hbm2java
 */
public class UserCache implements java.io.Serializable {

	private UserCacheId id;
	private Date accessDatetime;

	public UserCache() {
	}

	public UserCache(UserCacheId id, Date accessDatetime) {
		this.id = id;
		this.accessDatetime = accessDatetime;
	}

	public UserCacheId getId() {
		return this.id;
	}

	public void setId(UserCacheId id) {
		this.id = id;
	}

	public Date getAccessDatetime() {
		return this.accessDatetime;
	}

	public void setAccessDatetime(Date accessDatetime) {
		this.accessDatetime = accessDatetime;
	}

}
