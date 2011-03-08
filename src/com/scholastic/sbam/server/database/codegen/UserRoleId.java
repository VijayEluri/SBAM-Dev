package com.scholastic.sbam.server.database.codegen;

// Generated Mar 7, 2011 3:54:30 PM by Hibernate Tools 3.2.4.GA

/**
 * UserRoleId generated by hbm2java
 */
public class UserRoleId implements java.io.Serializable {

	private String userName;
	private String roleName;

	public UserRoleId() {
	}

	public UserRoleId(String userName, String roleName) {
		this.userName = userName;
		this.roleName = roleName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRoleId))
			return false;
		UserRoleId castOther = (UserRoleId) other;

		return ((this.getUserName() == castOther.getUserName()) || (this
				.getUserName() != null && castOther.getUserName() != null && this
				.getUserName().equals(castOther.getUserName())))
				&& ((this.getRoleName() == castOther.getRoleName()) || (this
						.getRoleName() != null
						&& castOther.getRoleName() != null && this
						.getRoleName().equals(castOther.getRoleName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserName() == null ? 0 : this.getUserName().hashCode());
		result = 37 * result
				+ (getRoleName() == null ? 0 : this.getRoleName().hashCode());
		return result;
	}

}
