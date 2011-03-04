package com.scholastic.sbam.server.database.codegen;

// Generated Mar 3, 2011 5:21:42 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * UserMessage generated by hbm2java
 */
public class UserMessage implements java.io.Serializable {

	private Integer id;
	private String userName;
	private String locationTag;
	private String text;
	private Date createDate;
	private char status;
	private int windowPosX;
	private int windowPosY;
	private int windowPosZ;
	private int windowWidth;
	private int windowHeight;
	private int restorePosX;
	private int restorePosY;
	private int restoreWidth;
	private int restoreHeight;
	private char minimized;
	private char maximized;
	private char collapsed;

	public UserMessage() {
	}

	public UserMessage(String userName, String locationTag, String text,
			Date createDate, char status, int windowPosX, int windowPosY,
			int windowPosZ, int windowWidth, int windowHeight, int restorePosX,
			int restorePosY, int restoreWidth, int restoreHeight,
			char minimized, char maximized, char collapsed) {
		this.userName = userName;
		this.locationTag = locationTag;
		this.text = text;
		this.createDate = createDate;
		this.status = status;
		this.windowPosX = windowPosX;
		this.windowPosY = windowPosY;
		this.windowPosZ = windowPosZ;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.restorePosX = restorePosX;
		this.restorePosY = restorePosY;
		this.restoreWidth = restoreWidth;
		this.restoreHeight = restoreHeight;
		this.minimized = minimized;
		this.maximized = maximized;
		this.collapsed = collapsed;
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

	public String getLocationTag() {
		return this.locationTag;
	}

	public void setLocationTag(String locationTag) {
		this.locationTag = locationTag;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public char getStatus() {
		return this.status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getWindowPosX() {
		return this.windowPosX;
	}

	public void setWindowPosX(int windowPosX) {
		this.windowPosX = windowPosX;
	}

	public int getWindowPosY() {
		return this.windowPosY;
	}

	public void setWindowPosY(int windowPosY) {
		this.windowPosY = windowPosY;
	}

	public int getWindowPosZ() {
		return this.windowPosZ;
	}

	public void setWindowPosZ(int windowPosZ) {
		this.windowPosZ = windowPosZ;
	}

	public int getWindowWidth() {
		return this.windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return this.windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	public int getRestorePosX() {
		return this.restorePosX;
	}

	public void setRestorePosX(int restorePosX) {
		this.restorePosX = restorePosX;
	}

	public int getRestorePosY() {
		return this.restorePosY;
	}

	public void setRestorePosY(int restorePosY) {
		this.restorePosY = restorePosY;
	}

	public int getRestoreWidth() {
		return this.restoreWidth;
	}

	public void setRestoreWidth(int restoreWidth) {
		this.restoreWidth = restoreWidth;
	}

	public int getRestoreHeight() {
		return this.restoreHeight;
	}

	public void setRestoreHeight(int restoreHeight) {
		this.restoreHeight = restoreHeight;
	}

	public char getMinimized() {
		return this.minimized;
	}

	public void setMinimized(char minimized) {
		this.minimized = minimized;
	}

	public char getMaximized() {
		return this.maximized;
	}

	public void setMaximized(char maximized) {
		this.maximized = maximized;
	}

	public char getCollapsed() {
		return this.collapsed;
	}

	public void setCollapsed(char collapsed) {
		this.collapsed = collapsed;
	}

}
