package com.lltech.manager.entity;

import java.io.File;
import java.io.Serializable;

public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String AttachmentID;
	private String RealName;
	private String ShowName;
	private String AttachmentUrl;
	private File localFile;

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String realName) {
		RealName = realName;
	}

	public String getShowName() {
		return ShowName;
	}

	public void setShowName(String showName) {
		ShowName = showName;
	}

	public String getAttachmentUrl() {
		return AttachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		AttachmentUrl = attachmentUrl;
	}

	public String getAttachmentID() {
		return AttachmentID;
	}

	public void setAttachmentID(String attachmentID) {
		AttachmentID = attachmentID;
	}

	public File getLocalFile() {
		return localFile;
	}

	public void setLocalFile(File localFile) {
		this.localFile = localFile;
	}
}
