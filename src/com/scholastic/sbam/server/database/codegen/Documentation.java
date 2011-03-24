package com.scholastic.sbam.server.database.codegen;

// Generated Mar 24, 2011 5:22:08 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Documentation generated by hbm2java
 */
public class Documentation implements java.io.Serializable {

	private Integer id;
	private int seq;
	private String title;
	private String types;
	private String link;
	private String iconImage;
	private String docVersion;
	private String description;
	private Date updatedDatetime;
	private Date createdDatetime;
	private char status;

	public Documentation() {
	}

	public Documentation(int seq, String title, String types, String link,
			String iconImage, String docVersion, String description,
			Date updatedDatetime, Date createdDatetime, char status) {
		this.seq = seq;
		this.title = title;
		this.types = types;
		this.link = link;
		this.iconImage = iconImage;
		this.docVersion = docVersion;
		this.description = description;
		this.updatedDatetime = updatedDatetime;
		this.createdDatetime = createdDatetime;
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypes() {
		return this.types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIconImage() {
		return this.iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}

	public String getDocVersion() {
		return this.docVersion;
	}

	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdatedDatetime() {
		return this.updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
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
