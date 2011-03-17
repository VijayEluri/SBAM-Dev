package com.scholastic.sbam.server.database.codegen;

// Generated Mar 17, 2011 1:37:18 PM by Hibernate Tools 3.2.4.GA

/**
 * UserCacheId generated by hbm2java
 */
public class UserCacheId implements java.io.Serializable {

	private String userName;
	private String category;
	private int intKey;
	private String strKey;

	public UserCacheId() {
	}

	public UserCacheId(String userName, String category, int intKey,
			String strKey) {
		this.userName = userName;
		this.category = category;
		this.intKey = intKey;
		this.strKey = strKey;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getIntKey() {
		return this.intKey;
	}

	public void setIntKey(int intKey) {
		this.intKey = intKey;
	}

	public String getStrKey() {
		return this.strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserCacheId))
			return false;
		UserCacheId castOther = (UserCacheId) other;

		return ((this.getUserName() == castOther.getUserName()) || (this
				.getUserName() != null && castOther.getUserName() != null && this
				.getUserName().equals(castOther.getUserName())))
				&& ((this.getCategory() == castOther.getCategory()) || (this
						.getCategory() != null
						&& castOther.getCategory() != null && this
						.getCategory().equals(castOther.getCategory())))
				&& (this.getIntKey() == castOther.getIntKey())
				&& ((this.getStrKey() == castOther.getStrKey()) || (this
						.getStrKey() != null && castOther.getStrKey() != null && this
						.getStrKey().equals(castOther.getStrKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserName() == null ? 0 : this.getUserName().hashCode());
		result = 37 * result
				+ (getCategory() == null ? 0 : this.getCategory().hashCode());
		result = 37 * result + this.getIntKey();
		result = 37 * result
				+ (getStrKey() == null ? 0 : this.getStrKey().hashCode());
		return result;
	}

}
