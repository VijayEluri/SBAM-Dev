package com.scholastic.sbam.server.database.codegen;

// Generated Mar 3, 2011 5:21:42 PM by Hibernate Tools 3.2.4.GA

/**
 * UcnConversionId generated by hbm2java
 */
public class UcnConversionId implements java.io.Serializable {

	private int ucn;
	private String ucnConversionSuffix;

	public UcnConversionId() {
	}

	public UcnConversionId(int ucn, String ucnConversionSuffix) {
		this.ucn = ucn;
		this.ucnConversionSuffix = ucnConversionSuffix;
	}

	public int getUcn() {
		return this.ucn;
	}

	public void setUcn(int ucn) {
		this.ucn = ucn;
	}

	public String getUcnConversionSuffix() {
		return this.ucnConversionSuffix;
	}

	public void setUcnConversionSuffix(String ucnConversionSuffix) {
		this.ucnConversionSuffix = ucnConversionSuffix;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UcnConversionId))
			return false;
		UcnConversionId castOther = (UcnConversionId) other;

		return (this.getUcn() == castOther.getUcn())
				&& ((this.getUcnConversionSuffix() == castOther
						.getUcnConversionSuffix()) || (this
						.getUcnConversionSuffix() != null
						&& castOther.getUcnConversionSuffix() != null && this
						.getUcnConversionSuffix().equals(
								castOther.getUcnConversionSuffix())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUcn();
		result = 37
				* result
				+ (getUcnConversionSuffix() == null ? 0 : this
						.getUcnConversionSuffix().hashCode());
		return result;
	}

}
