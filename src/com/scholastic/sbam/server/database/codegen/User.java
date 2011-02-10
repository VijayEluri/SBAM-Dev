package com.scholastic.sbam.server.database.codegen;

// Generated Feb 10, 2011 10:50:48 AM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int loginCount;
	private int sessionId;
	private Date sessionStartTime;
	private Date sessionExpireTime;
	private Date createdDatetime;
	private char status;

	public User() {
	}

	public User(String userName, String password, String email, int loginCount,
			int sessionId, char status) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.loginCount = loginCount;
		this.sessionId = sessionId;
		this.status = status;
	}

	public User(String userName, String password, String firstName,
			String lastName, String email, int loginCount, int sessionId,
			Date sessionStartTime, Date sessionExpireTime,
			Date createdDatetime, char status) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.loginCount = loginCount;
		this.sessionId = sessionId;
		this.sessionStartTime = sessionStartTime;
		this.sessionExpireTime = sessionExpireTime;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public Date getSessionStartTime() {
		return this.sessionStartTime;
	}

	public void setSessionStartTime(Date sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public Date getSessionExpireTime() {
		return this.sessionExpireTime;
	}

	public void setSessionExpireTime(Date sessionExpireTime) {
		this.sessionExpireTime = sessionExpireTime;
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
