package com.scholastic.sbam.server.database.codegen;

// Generated May 10, 2011 5:37:29 PM by Hibernate Tools 3.2.4.GA

/**
 * UserPortletCache generated by hbm2java
 */
public class UserPortletCache implements java.io.Serializable {

	private UserPortletCacheId id;
	private String portletType;
	private int restoreColumn;
	private int restoreRow;
	private int restoreHeight;
	private int restoreWidth;
	private char minimized;
	private String keyData;

	public UserPortletCache() {
	}

	public UserPortletCache(UserPortletCacheId id, String portletType,
			int restoreColumn, int restoreRow, int restoreHeight,
			int restoreWidth, char minimized, String keyData) {
		this.id = id;
		this.portletType = portletType;
		this.restoreColumn = restoreColumn;
		this.restoreRow = restoreRow;
		this.restoreHeight = restoreHeight;
		this.restoreWidth = restoreWidth;
		this.minimized = minimized;
		this.keyData = keyData;
	}

	public UserPortletCacheId getId() {
		return this.id;
	}

	public void setId(UserPortletCacheId id) {
		this.id = id;
	}

	public String getPortletType() {
		return this.portletType;
	}

	public void setPortletType(String portletType) {
		this.portletType = portletType;
	}

	public int getRestoreColumn() {
		return this.restoreColumn;
	}

	public void setRestoreColumn(int restoreColumn) {
		this.restoreColumn = restoreColumn;
	}

	public int getRestoreRow() {
		return this.restoreRow;
	}

	public void setRestoreRow(int restoreRow) {
		this.restoreRow = restoreRow;
	}

	public int getRestoreHeight() {
		return this.restoreHeight;
	}

	public void setRestoreHeight(int restoreHeight) {
		this.restoreHeight = restoreHeight;
	}

	public int getRestoreWidth() {
		return this.restoreWidth;
	}

	public void setRestoreWidth(int restoreWidth) {
		this.restoreWidth = restoreWidth;
	}

	public char getMinimized() {
		return this.minimized;
	}

	public void setMinimized(char minimized) {
		this.minimized = minimized;
	}

	public String getKeyData() {
		return this.keyData;
	}

	public void setKeyData(String keyData) {
		this.keyData = keyData;
	}

}
