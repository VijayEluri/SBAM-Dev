package com.scholastic.sbam.server.database.codegen;

// Generated Dec 14, 2010 11:47:18 AM by Hibernate Tools 3.2.4.GA

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

	public User() {
	}

	public User(String userName, String password, String email, int loginCount,
			int sessionId) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.loginCount = loginCount;
		this.sessionId = sessionId;
	}

	public User(String userName, String password, String firstName,
			String lastName, String email, int loginCount, int sessionId,
			Date sessionStartTime, Date sessionExpireTime) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.loginCount = loginCount;
		this.sessionId = sessionId;
		this.sessionStartTime = sessionStartTime;
		this.sessionExpireTime = sessionExpireTime;
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

}
