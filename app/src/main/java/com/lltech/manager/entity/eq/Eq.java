package com.lltech.manager.entity.eq;

import java.io.Serializable;
import java.util.List;

import com.lltech.manager.entity.Attachment;

public class Eq implements Serializable {

	private static final long serialVersionUID = -1L;

	private String RowNum;
	private String EquipmentID;
	private String EquipmentNo;
	private String EquipmentName;
	private String EquipmentImg;
	private String Brand;
	private String Model;
	private String CycleUnit;
	private String SystemCode;
	private String StoreyId;
	private String UseDescription;
	private String UseOrganization;
	private String TypeCode;
	private String IsControl;
	private String InstallPlace;
	private String SignalAddr;
	private String ProxyPortAddr;
	private String ProxyAddr;
	private String ProxyUser;
	private String ProxyPwd;
	private String DataAddr;
	private String PortAddr;
	private String SignalPwd;
	private String SignalUser;
	private String Description;
	private String PutIntoUseDate;
	private String MeasurementUnit;
	private String MaintenanceCycle;
	private String ProtocolId;
	private String Status;
	private String CreateUserId;
	private String CreateTime;
	private String ModifyUserId;
	private String ModifyTime;
	private String ResponsibilityDep;
	private String ResponsibilityUser;
	private String UseDep;
	private String UsePeople;
	private String SupplyCommodity;
	private String MaintenanceBeginTime;
	private String MaintenanceLastTime;
	private String MaintenanceUnit;
	private String Remark;
	private String CapitalAssetsNo;
	private String CapitalAssetsSubject;
	private String CostAccounting;
	private String CostPresentValue;
	private String DepreciationYears;
	private String IsUsed;
	private String UsedYears;
	private String PurchaseUserID;
	private String PurchaseDate;
	private String Supplier;
	private String SupplierTel;
	private String SupplierUser;
	private String MaintenanceStandardsID;
	private String RemindUsers;
	private String IsMaintenance;
	private String GuaranteePeriod;
	private String SupplierAddress;
	private String QRCodePath;
	private String ProjectID;
	private String ProductionDate;
	private String Unit;
	private List<Attachment> AttachmentList;
	
	private String StoreyName;
	private String PurchaseUser;
	public String getRowNum() {
		return RowNum;
	}
	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	public String getEquipmentID() {
		return EquipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		EquipmentID = equipmentID;
	}
	public String getEquipmentNo() {
		return EquipmentNo;
	}
	public void setEquipmentNo(String equipmentNo) {
		EquipmentNo = equipmentNo;
	}
	public String getEquipmentName() {
		return EquipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		EquipmentName = equipmentName;
	}
	public String getEquipmentImg() {
		return EquipmentImg;
	}
	public void setEquipmentImg(String equipmentImg) {
		EquipmentImg = equipmentImg;
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
	public String getCycleUnit() {
		return CycleUnit;
	}
	public void setCycleUnit(String cycleUnit) {
		CycleUnit = cycleUnit;
	}
	public String getSystemCode() {
		return SystemCode;
	}
	public void setSystemCode(String systemCode) {
		SystemCode = systemCode;
	}
	public String getStoreyId() {
		return StoreyId;
	}
	public void setStoreyId(String storeyId) {
		StoreyId = storeyId;
	}
	public String getUseDescription() {
		return UseDescription;
	}
	public void setUseDescription(String useDescription) {
		UseDescription = useDescription;
	}
	public String getUseOrganization() {
		return UseOrganization;
	}
	public void setUseOrganization(String useOrganization) {
		UseOrganization = useOrganization;
	}
	public String getTypeCode() {
		return TypeCode;
	}
	public void setTypeCode(String typeCode) {
		TypeCode = typeCode;
	}
	public String getIsControl() {
		return IsControl;
	}
	public void setIsControl(String isControl) {
		IsControl = isControl;
	}
	public String getInstallPlace() {
		return InstallPlace;
	}
	public void setInstallPlace(String installPlace) {
		InstallPlace = installPlace;
	}
	public String getSignalAddr() {
		return SignalAddr;
	}
	public void setSignalAddr(String signalAddr) {
		SignalAddr = signalAddr;
	}
	public String getProxyPortAddr() {
		return ProxyPortAddr;
	}
	public void setProxyPortAddr(String proxyPortAddr) {
		ProxyPortAddr = proxyPortAddr;
	}
	public String getProxyAddr() {
		return ProxyAddr;
	}
	public void setProxyAddr(String proxyAddr) {
		ProxyAddr = proxyAddr;
	}
	public String getProxyUser() {
		return ProxyUser;
	}
	public void setProxyUser(String proxyUser) {
		ProxyUser = proxyUser;
	}
	public String getProxyPwd() {
		return ProxyPwd;
	}
	public void setProxyPwd(String proxyPwd) {
		ProxyPwd = proxyPwd;
	}
	public String getDataAddr() {
		return DataAddr;
	}
	public void setDataAddr(String dataAddr) {
		DataAddr = dataAddr;
	}
	public String getPortAddr() {
		return PortAddr;
	}
	public void setPortAddr(String portAddr) {
		PortAddr = portAddr;
	}
	public String getSignalPwd() {
		return SignalPwd;
	}
	public void setSignalPwd(String signalPwd) {
		SignalPwd = signalPwd;
	}
	public String getSignalUser() {
		return SignalUser;
	}
	public void setSignalUser(String signalUser) {
		SignalUser = signalUser;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPutIntoUseDate() {
		return PutIntoUseDate;
	}
	public void setPutIntoUseDate(String putIntoUseDate) {
		PutIntoUseDate = putIntoUseDate;
	}
	public String getMeasurementUnit() {
		return MeasurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		MeasurementUnit = measurementUnit;
	}
	public String getMaintenanceCycle() {
		return MaintenanceCycle;
	}
	public void setMaintenanceCycle(String maintenanceCycle) {
		MaintenanceCycle = maintenanceCycle;
	}
	public String getProtocolId() {
		return ProtocolId;
	}
	public void setProtocolId(String protocolId) {
		ProtocolId = protocolId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getCreateUserId() {
		return CreateUserId;
	}
	public void setCreateUserId(String createUserId) {
		CreateUserId = createUserId;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getModifyUserId() {
		return ModifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		ModifyUserId = modifyUserId;
	}
	public String getModifyTime() {
		return ModifyTime;
	}
	public void setModifyTime(String modifyTime) {
		ModifyTime = modifyTime;
	}
	public String getResponsibilityDep() {
		return ResponsibilityDep;
	}
	public void setResponsibilityDep(String responsibilityDep) {
		ResponsibilityDep = responsibilityDep;
	}
	public String getResponsibilityUser() {
		return ResponsibilityUser;
	}
	public void setResponsibilityUser(String responsibilityUser) {
		ResponsibilityUser = responsibilityUser;
	}
	public String getUseDep() {
		return UseDep;
	}
	public void setUseDep(String useDep) {
		UseDep = useDep;
	}
	public String getUsePeople() {
		return UsePeople;
	}
	public void setUsePeople(String usePeople) {
		UsePeople = usePeople;
	}
	public String getSupplyCommodity() {
		return SupplyCommodity;
	}
	public void setSupplyCommodity(String supplyCommodity) {
		SupplyCommodity = supplyCommodity;
	}
	public String getMaintenanceBeginTime() {
		return MaintenanceBeginTime;
	}
	public void setMaintenanceBeginTime(String maintenanceBeginTime) {
		MaintenanceBeginTime = maintenanceBeginTime;
	}
	public String getMaintenanceLastTime() {
		return MaintenanceLastTime;
	}
	public void setMaintenanceLastTime(String maintenanceLastTime) {
		MaintenanceLastTime = maintenanceLastTime;
	}
	public String getMaintenanceUnit() {
		return MaintenanceUnit;
	}
	public void setMaintenanceUnit(String maintenanceUnit) {
		MaintenanceUnit = maintenanceUnit;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getCapitalAssetsNo() {
		return CapitalAssetsNo;
	}
	public void setCapitalAssetsNo(String capitalAssetsNo) {
		CapitalAssetsNo = capitalAssetsNo;
	}
	public String getCapitalAssetsSubject() {
		return CapitalAssetsSubject;
	}
	public void setCapitalAssetsSubject(String capitalAssetsSubject) {
		CapitalAssetsSubject = capitalAssetsSubject;
	}
	public String getCostAccounting() {
		return CostAccounting;
	}
	public void setCostAccounting(String costAccounting) {
		CostAccounting = costAccounting;
	}
	public String getCostPresentValue() {
		return CostPresentValue;
	}
	public void setCostPresentValue(String costPresentValue) {
		CostPresentValue = costPresentValue;
	}
	public String getDepreciationYears() {
		return DepreciationYears;
	}
	public void setDepreciationYears(String depreciationYears) {
		DepreciationYears = depreciationYears;
	}
	public String getIsUsed() {
		return IsUsed;
	}
	public void setIsUsed(String isUsed) {
		IsUsed = isUsed;
	}
	public String getUsedYears() {
		return UsedYears;
	}
	public void setUsedYears(String usedYears) {
		UsedYears = usedYears;
	}
	public String getPurchaseUserID() {
		return PurchaseUserID;
	}
	public void setPurchaseUserID(String purchaseUserID) {
		PurchaseUserID = purchaseUserID;
	}
	public String getPurchaseDate() {
		return PurchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		PurchaseDate = purchaseDate;
	}
	public String getSupplier() {
		return Supplier;
	}
	public void setSupplier(String supplier) {
		Supplier = supplier;
	}
	public String getSupplierTel() {
		return SupplierTel;
	}
	public void setSupplierTel(String supplierTel) {
		SupplierTel = supplierTel;
	}
	public String getSupplierUser() {
		return SupplierUser;
	}
	public void setSupplierUser(String supplierUser) {
		SupplierUser = supplierUser;
	}
	public String getMaintenanceStandardsID() {
		return MaintenanceStandardsID;
	}
	public void setMaintenanceStandardsID(String maintenanceStandardsID) {
		MaintenanceStandardsID = maintenanceStandardsID;
	}
	public String getRemindUsers() {
		return RemindUsers;
	}
	public void setRemindUsers(String remindUsers) {
		RemindUsers = remindUsers;
	}
	public String getIsMaintenance() {
		return IsMaintenance;
	}
	public void setIsMaintenance(String isMaintenance) {
		IsMaintenance = isMaintenance;
	}
	public String getGuaranteePeriod() {
		return GuaranteePeriod;
	}
	public void setGuaranteePeriod(String guaranteePeriod) {
		GuaranteePeriod = guaranteePeriod;
	}
	public String getSupplierAddress() {
		return SupplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		SupplierAddress = supplierAddress;
	}
	public String getQRCodePath() {
		return QRCodePath;
	}
	public void setQRCodePath(String qRCodePath) {
		QRCodePath = qRCodePath;
	}
	public String getProjectID() {
		return ProjectID;
	}
	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}
	public String getProductionDate() {
		return ProductionDate;
	}
	public void setProductionDate(String productionDate) {
		ProductionDate = productionDate;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public List<Attachment> getAttachmentList() {
		return AttachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		AttachmentList = attachmentList;
	}
	public String getStoreyName() {
		return StoreyName;
	}
	public void setStoreyName(String storeyName) {
		StoreyName = storeyName;
	}
	public String getPurchaseUser() {
		return PurchaseUser;
	}
	public void setPurchaseUser(String purchaseUser) {
		PurchaseUser = purchaseUser;
	}
	
}
