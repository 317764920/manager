package com.lltech.manager.entity.prepare;

import java.io.Serializable;
import java.util.List;

public class InOut implements Serializable {

	private static final long serialVersionUID = -1L;

	private String RowNum;
	private String ID;
	private String InOutPartID;
	private String InOutPartNo;
	private String LeadUser;
	private String LeadTime;
	private String InOutPartsType;
	private String Address;
	private String Remark;
	private String CreateUser;
	private String CreateTime;
	private List<InOutPre> InOutPartDetailList;
	
	private String InOutPartsTypeID;
	private String LeadUserID;

	public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getInOutPartID() {
		return InOutPartID;
	}

	public void setInOutPartID(String inOutPartID) {
		InOutPartID = inOutPartID;
	}

	public String getInOutPartNo() {
		return InOutPartNo;
	}

	public void setInOutPartNo(String inOutPartNo) {
		InOutPartNo = inOutPartNo;
	}

	public String getLeadUser() {
		return LeadUser;
	}

	public void setLeadUser(String leadUser) {
		LeadUser = leadUser;
	}

	public String getLeadTime() {
		return LeadTime;
	}

	public void setLeadTime(String leadTime) {
		LeadTime = leadTime;
	}

	public String getInOutPartsType() {
		return InOutPartsType;
	}

	public void setInOutPartsType(String inOutPartsType) {
		InOutPartsType = inOutPartsType;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public List<InOutPre> getInOutPartDetailList() {
		return InOutPartDetailList;
	}

	public void setInOutPartDetailList(List<InOutPre> inOutPartDetailList) {
		InOutPartDetailList = inOutPartDetailList;
	}

	public String getInOutPartsTypeID() {
		return InOutPartsTypeID;
	}

	public void setInOutPartsTypeID(String inOutPartsTypeID) {
		InOutPartsTypeID = inOutPartsTypeID;
	}

	public String getLeadUserID() {
		return LeadUserID;
	}

	public void setLeadUserID(String leadUserID) {
		LeadUserID = leadUserID;
	}
}
