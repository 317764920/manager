package com.lltech.manager.entity.by;

import java.io.Serializable;
import java.util.List;

import com.lltech.manager.entity.Attachment;

public class Plan implements Serializable {

	private static final long serialVersionUID = -1L;

	private String RowNum;
	private String MaintenancePlanID;
	private String MaintenancePlanNo;
	private String MaintenanceStandardName;
	private String CycleTypeStr;
	private String IsMonthExecStr;
	private String IsStopStr;
	private String IntervalTime;
	private String IntervalUnitStr;
	private String LastTime;
	private String NextTime;
	private String State;
	private String EquipmentName;
	private String EquipmentNo;
	private String Brand;
	private String model;
	private String InstallPlace;
	private String PlanDemand;
	private String EquipmentPart;
	private String Remark;
	private List<Attachment> AttachmentList;
	
	private String EquipmentID;
	private String ProjectID;
	private String MaintenanceStandardsID;
	private String MaintenanceLevelCode;
	private String MaintainerID;
	private String IntervalUnit;
	private String CycleType;
	private String IsMonthExec;
	private String MaintenanceLevel;
	private String IsStop;
	private String Maintainer;

	public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}

	public String getMaintenancePlanID() {
		return MaintenancePlanID;
	}

	public void setMaintenancePlanID(String maintenancePlanID) {
		MaintenancePlanID = maintenancePlanID;
	}

	public String getMaintenancePlanNo() {
		return MaintenancePlanNo;
	}

	public void setMaintenancePlanNo(String maintenancePlanNo) {
		MaintenancePlanNo = maintenancePlanNo;
	}

	public String getMaintainer() {
		return Maintainer;
	}

	public void setMaintainer(String maintainer) {
		Maintainer = maintainer;
	}

	public String getIntervalTime() {
		return IntervalTime;
	}

	public void setIntervalTime(String intervalTime) {
		IntervalTime = intervalTime;
	}

	public String getIntervalUnit() {
		return IntervalUnit;
	}

	public void setIntervalUnit(String intervalUnit) {
		IntervalUnit = intervalUnit;
	}

	public String getLastTime() {
		return LastTime;
	}

	public void setLastTime(String lastTime) {
		LastTime = lastTime;
	}

	public String getNextTime() {
		return NextTime;
	}

	public void setNextTime(String nextTime) {
		NextTime = nextTime;
	}

	public String getIsMonthExec() {
		return IsMonthExec;
	}

	public void setIsMonthExec(String isMonthExec) {
		IsMonthExec = isMonthExec;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getEquipmentName() {
		return EquipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		EquipmentName = equipmentName;
	}

	public String getIsStop() {
		return IsStop;
	}

	public void setIsStop(String isStop) {
		IsStop = isStop;
	}

	public String getPlanDemand() {
		return PlanDemand;
	}

	public void setPlanDemand(String planDemand) {
		PlanDemand = planDemand;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public List<Attachment> getAttachmentList() {
		return AttachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		AttachmentList = attachmentList;
	}

	public String getEquipmentID() {
		return EquipmentID;
	}

	public void setEquipmentID(String equipmentID) {
		EquipmentID = equipmentID;
	}

	public String getProjectID() {
		return ProjectID;
	}

	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}

	public String getMaintenanceStandardsID() {
		return MaintenanceStandardsID;
	}

	public void setMaintenanceStandardsID(String maintenanceStandardsID) {
		MaintenanceStandardsID = maintenanceStandardsID;
	}

	public String getCycleType() {
		return CycleType;
	}

	public void setCycleType(String cycleType) {
		CycleType = cycleType;
	}

	public String getMaintenanceLevel() {
		return MaintenanceLevel;
	}

	public void setMaintenanceLevel(String maintenanceLevel) {
		MaintenanceLevel = maintenanceLevel;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getInstallPlace() {
		return InstallPlace;
	}

	public void setInstallPlace(String installPlace) {
		InstallPlace = installPlace;
	}

	public String getEquipmentPart() {
		return EquipmentPart;
	}

	public void setEquipmentPart(String equipmentPart) {
		EquipmentPart = equipmentPart;
	}

	public String getMaintenanceLevelCode() {
		return MaintenanceLevelCode;
	}

	public void setMaintenanceLevelCode(String maintenanceLevelCode) {
		MaintenanceLevelCode = maintenanceLevelCode;
	}

	public String getMaintainerID() {
		return MaintainerID;
	}

	public void setMaintainerID(String maintainerID) {
		MaintainerID = maintainerID;
	}

	public String getMaintenanceStandardName() {
		return MaintenanceStandardName;
	}

	public void setMaintenanceStandardName(String maintenanceStandardName) {
		MaintenanceStandardName = maintenanceStandardName;
	}

	public String getEquipmentNo() {
		return EquipmentNo;
	}

	public void setEquipmentNo(String equipmentNo) {
		EquipmentNo = equipmentNo;
	}

	public String getCycleTypeStr() {
		return CycleTypeStr;
	}

	public void setCycleTypeStr(String cycleTypeStr) {
		CycleTypeStr = cycleTypeStr;
	}

	public String getIsMonthExecStr() {
		return IsMonthExecStr;
	}

	public void setIsMonthExecStr(String isMonthExecStr) {
		IsMonthExecStr = isMonthExecStr;
	}

	public String getIsStopStr() {
		return IsStopStr;
	}

	public void setIsStopStr(String isStopStr) {
		IsStopStr = isStopStr;
	}

	public String getIntervalUnitStr() {
		return IntervalUnitStr;
	}

	public void setIntervalUnitStr(String intervalUnitStr) {
		IntervalUnitStr = intervalUnitStr;
	}
}
