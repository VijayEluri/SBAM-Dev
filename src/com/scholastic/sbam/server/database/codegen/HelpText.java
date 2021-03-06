package com.scholastic.sbam.server.database.codegen;

// Generated Feb 1, 2012 5:33:22 PM by Hibernate Tools 3.2.4.GA

/**
 * HelpText generated by hbm2java
 */
public class HelpText implements java.io.Serializable {

	private String id;
	private String parentId;
	private String firstChildId;
	private String prevSiblingId;
	private String nextSiblingId;
	private String title;
	private String iconName;
	private String text;
	private String relatedIds;

	public HelpText() {
	}

	public HelpText(String id, String parentId, String firstChildId,
			String prevSiblingId, String nextSiblingId, String title,
			String iconName, String text, String relatedIds) {
		this.id = id;
		this.parentId = parentId;
		this.firstChildId = firstChildId;
		this.prevSiblingId = prevSiblingId;
		this.nextSiblingId = nextSiblingId;
		this.title = title;
		this.iconName = iconName;
		this.text = text;
		this.relatedIds = relatedIds;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFirstChildId() {
		return this.firstChildId;
	}

	public void setFirstChildId(String firstChildId) {
		this.firstChildId = firstChildId;
	}

	public String getPrevSiblingId() {
		return this.prevSiblingId;
	}

	public void setPrevSiblingId(String prevSiblingId) {
		this.prevSiblingId = prevSiblingId;
	}

	public String getNextSiblingId() {
		return this.nextSiblingId;
	}

	public void setNextSiblingId(String nextSiblingId) {
		this.nextSiblingId = nextSiblingId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIconName() {
		return this.iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRelatedIds() {
		return this.relatedIds;
	}

	public void setRelatedIds(String relatedIds) {
		this.relatedIds = relatedIds;
	}

}
