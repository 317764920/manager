package com.lltech.manager.entity.by;

import java.io.Serializable;
import java.util.List;

import com.lltech.manager.entity.Attachment;
import com.lltech.manager.entity.prepare.Pre;

public class By implements Serializable {

	private static final long serialVersionUID = -1L;

	private String RowNum;
	private String MaintenanceRecordID;
	private String MaintenancePlanID;
	private String DistributionID;
	private String MaintenancePlanNo;
	private String EquipmentName;
	private String EquipmentNo;
	private String Brand;
	private String Model;
	private String MaintenanceUser;
	private String BeginTime;
	private String EndTime;
	private String TimeLength;
	private String MaintenanceRecord;
	private String IsUsePart;
	private String Score;
	private String Opinion;
	private String Remark;
	private String State;
	private String TotalCast;
	private List<Attachment> AttachmentList;
	private List<Pre> PartList;

	public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}

	public String getMaintenanceRecordID() {
		return MaintenanceRecordID;
	}

	public void setMaintenanceRecordID(String maintenanceRecordID) {
		MaintenanceRecordID = maintenanceRecordID;
	}

	public String getMaintenancePlanID() {
		return MaintenancePlanID;
	}

	public void setMaintenancePlanID(String maintenancePlanID) {
		MaintenancePlanID = maintenancePlanID;
	}

	public String getDistributionID() {
		return DistributionID;
	}

	public void setDistributionID(String distributionID) {
		DistributionID = distributionID;
	}

	public String getMaintenancePlanNo() {
		return MaintenancePlanNo;
	}

	public void setMaintenancePlanNo(String maintenancePlanNo) {
		MaintenancePlanNo = maintenancePlanNo;
	}

	public String getEquipmentName() {
		return EquipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		EquipmentName = equipmentName;
	}

	public String getEquipmentNo() {
		return EquipmentNo;
	}

	public void setEquipmentNo(String equipmentNo) {
		EquipmentNo = equipmentNo;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public String getMaintenanceUser() {
		return MaintenanceUser;
	}

	public void setMaintenanceUser(String maintenanceUser) {
		MaintenanceUser = maintenanceUser;
	}

	public String getBeginTime() {
		return BeginTime;
	}

	public void setBeginTime(String beginTime) {
		BeginTime = beginTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getTimeLength() {
		return TimeLength;
	}

	public void setTimeLength(String timeLength) {
		TimeLength = timeLength;
	}

	public String getMaintenanceRecord() {
		return MaintenanceRecord;
	}

	public void setMaintenanceRecord(String maintenanceRecord) {
		MaintenanceRecord = maintenanceRecord;
	}

	public String getIsUsePart() {
		return IsUsePart;
	}

	public void setIsUsePart(String isUsePart) {
		IsUsePart = isUsePart;
	}

	public String getScore() {
		return Score;
	}

	public void setScore(String score) {
		Score = score;
	}

	public String getOpinion() {
		return Opinion;
	}

	public void setOpinion(String opinion) {
		Opinion = opinion;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getTotalCast() {
		return TotalCast;
	}

	public void setTotalCast(String totalCast) {
		TotalCast = totalCast;
	}

	public List<Attachment> getAttachmentList() {
		return AttachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		AttachmentList = attachmentList;
	}

	public List<Pre> getPartList() {
		return PartList;
	}

	public void setPartList(List<Pre> partList) {
		PartList = partList;
	}

}
