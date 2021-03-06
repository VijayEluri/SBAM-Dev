package com.scholastic.sbam.server.database.codegen;

// Generated Feb 1, 2012 5:33:22 PM by Hibernate Tools 3.2.4.GA

/**
 * AeIp generated by hbm2java
 */
public class AeIp implements java.io.Serializable {

	private AeIpId id;
	private char remote;
	private long ipLo;
	private long ipHi;
	private String ipRangeCode;

	public AeIp() {
	}

	public AeIp(AeIpId id, char remote, long ipLo, long ipHi, String ipRangeCode) {
		this.id = id;
		this.remote = remote;
		this.ipLo = ipLo;
		this.ipHi = ipHi;
		this.ipRangeCode = ipRangeCode;
	}

	public AeIpId getId() {
		return this.id;
	}

	public void setId(AeIpId id) {
		this.id = id;
	}

	public char getRemote() {
		return this.remote;
	}

	public void setRemote(char remote) {
		this.remote = remote;
	}

	public long getIpLo() {
		return this.ipLo;
	}

	public void setIpLo(long ipLo) {
		this.ipLo = ipLo;
	}

	public long getIpHi() {
		return this.ipHi;
	}

	public void setIpHi(long ipHi) {
		this.ipHi = ipHi;
	}

	public String getIpRangeCode() {
		return this.ipRangeCode;
	}

	public void setIpRangeCode(String ipRangeCode) {
		this.ipRangeCode = ipRangeCode;
	}

}
