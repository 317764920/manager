package com.lltech.manager.entity;

import java.util.List;


public class AppVersion extends Req {

	private static final long serialVersionUID = 1L;
	private String AppHistoryID;
	private String VersionNumber;
	private String Description;
	private String CreateTime;
	private String CreateUserID;
	private List<Attachment> AttachmentList;

	public String getAppHistoryID() {
		return AppHistoryID;
	}

	public void setAppHistoryID(String appHistoryID) {
		AppHistoryID = appHistoryID;
	}

	public String getVersionNumber() {
		return VersionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		VersionNumber = versionNumber;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getCreateUserID() {
		return CreateUserID;
	}

	public void setCreateUserID(String createUserID) {
		CreateUserID = createUserID;
	}

	public List<Attachment> getAttachmentList() {
		return AttachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		AttachmentList = attachmentList;
	}
}
