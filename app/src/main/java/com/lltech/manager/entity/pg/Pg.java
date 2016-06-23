package com.lltech.manager.entity.pg;

import com.lltech.manager.entity.Attachment;

import java.io.Serializable;
import java.util.List;

public class Pg implements Serializable {

    private static final long serialVersionUID = -1L;

    private String RowNum;
    private String DistributionID;
    private String DistributionClass;
    private String DistributionNo;
    private String FaultReportID;
    private String Explain;
    private String FaultReportNo;
    private String EquipmentName;
    private String EquipmentNo;
    private String Brand;
    private String Model;
    private String RepairUsers;
    private String LastBeginTime;
    private String DistributionUserID;
    private String DistributionUser;
    private String DistributionTime;
    private String EstimatedTime;
    private String LastEndTime;
    private String Remark;
    private String UseTools;
    private String State;
    private List<Attachment> AttachmentList;
    private String RepairTitle;
    private String RepairID;

    public String getRepairID() {
        return RepairID;
    }

    public void setRepairID(String repairID) {
        RepairID = repairID;
    }

    public String getRepairTitle() {
        return RepairTitle;
    }

    public void setRepairTitle(String repairTitle) {
        RepairTitle = repairTitle;
    }

    public String getRowNum() {
        return RowNum;
    }

    public void setRowNum(String rowNum) {
        RowNum = rowNum;
    }

    public String getDistributionID() {
        return DistributionID;
    }

    public void setDistributionID(String distributionID) {
        DistributionID = distributionID;
    }

    public String getDistributionClass() {
        return DistributionClass;
    }

    public void setDistributionClass(String distributionClass) {
        DistributionClass = distributionClass;
    }

    public String getDistributionNo() {
        return DistributionNo;
    }

    public void setDistributionNo(String distributionNo) {
        DistributionNo = distributionNo;
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

    public String getFaultReportID() {
        return FaultReportID;
    }

    public void setFaultReportID(String faultReportID) {
        FaultReportID = faultReportID;
    }

    public String getRepairUsers() {
        return RepairUsers;
    }

    public void setRepairUsers(String repairUsers) {
        RepairUsers = repairUsers;
    }

    public String getLastBeginTime() {
        return LastBeginTime;
    }

    public void setLastBeginTime(String lastBeginTime) {
        LastBeginTime = lastBeginTime;
    }

    public String getDistributionUserID() {
        return DistributionUserID;
    }

    public void setDistributionUserID(String distributionUserID) {
        DistributionUserID = distributionUserID;
    }

    public String getDistributionUser() {
        return DistributionUser;
    }

    public void setDistributionUser(String distributionUser) {
        DistributionUser = distributionUser;
    }

    public String getDistributionTime() {
        return DistributionTime;
    }

    public void setDistributionTime(String distributionTime) {
        DistributionTime = distributionTime;
    }

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String explain) {
        Explain = explain;
    }

    public String getEstimatedTime() {
        return EstimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        EstimatedTime = estimatedTime;
    }

    public String getLastEndTime() {
        return LastEndTime;
    }

    public void setLastEndTime(String lastEndTime) {
        LastEndTime = lastEndTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getUseTools() {
        return UseTools;
    }

    public void setUseTools(String useTools) {
        UseTools = useTools;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getFaultReportNo() {
        return FaultReportNo;
    }

    public void setFaultReportNo(String faultReportNo) {
        FaultReportNo = faultReportNo;
    }

    public List<Attachment> getAttachmentList() {
        return AttachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        AttachmentList = attachmentList;
    }

}
