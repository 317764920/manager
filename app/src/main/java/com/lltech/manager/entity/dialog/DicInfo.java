package com.lltech.manager.entity.dialog;

import java.io.Serializable;

public class DicInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	private String DataDictionaryID;
	private String DicCode;
	private String DicName;
	private String DicValue;
	private String ParentID;
	private String IsSystem;
	private String IsDirectory;
	private String IsUse;
	private String Sequence;
	private String Remark;

	public String getDataDictionaryID() {
		return DataDictionaryID;
	}

	public void setDataDictionaryID(String dataDictionaryID) {
		DataDictionaryID = dataDictionaryID;
	}

	public String getDicCode() {
		return DicCode;
	}

	public void setDicCode(String dicCode) {
		DicCode = dicCode;
	}

	public String getDicName() {
		return DicName;
	}

	public void setDicName(String dicName) {
		DicName = dicName;
	}

	public String getDicValue() {
		return DicValue;
	}

	public void setDicValue(String dicValue) {
		DicValue = dicValue;
	}

	public String getParentID() {
		return ParentID;
	}

	public void setParentID(String parentID) {
		ParentID = parentID;
	}

	public String getIsSystem() {
		return IsSystem;
	}

	public void setIsSystem(String isSystem) {
		IsSystem = isSystem;
	}

	public String getIsDirectory() {
		return IsDirectory;
	}

	public void setIsDirectory(String isDirectory) {
		IsDirectory = isDirectory;
	}

	public String getIsUse() {
		return IsUse;
	}

	public void setIsUse(String isUse) {
		IsUse = isUse;
	}

	public String getSequence() {
		return Sequence;
	}

	public void setSequence(String sequence) {
		Sequence = sequence;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
