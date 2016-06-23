package com.lltech.manager.entity.file;

import java.io.Serializable;

public class FileRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ErrorCount;
	private String ErrorMessage;
	private String IdList;
	private String FileNameList;
	private String FileName;
	private String VoicePath;

	public Integer getErrorCount() {
		return ErrorCount;
	}

	public void setErrorCount(Integer errorCount) {
		ErrorCount = errorCount;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public String getIdList() {
		return IdList;
	}

	public void setIdList(String idList) {
		IdList = idList;
	}

	public String getFileNameList() {
		return FileNameList;
	}

	public void setFileNameList(String fileNameList) {
		FileNameList = fileNameList;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getVoicePath() {
		return VoicePath;
	}

	public void setVoicePath(String voicePath) {
		VoicePath = voicePath;
	}
}
