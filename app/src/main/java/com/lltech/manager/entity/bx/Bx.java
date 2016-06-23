package com.lltech.manager.entity.bx;

import com.lltech.manager.entity.Attachment;
import com.lltech.manager.entity.Voice;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Bx implements Serializable {

    private static final long serialVersionUID = -1L;

    private String RowNum;
    private String FaultReportID;
    private String FaultReportNo;
    private String FaultReportTitle;
    private String ReportUser;
    private String ReportTime;
    private String State;
    private String FaultDescription;
    private String ExpectedDate;
    private String ProjectID;
    private String EquipmentName;
    private String EquipmentNo;
    private String Brand;
    private String Model;
    private String FaultReportType;
    private String CompanyName;
    private String DepartName;
    private String Remark;
    private String CreateUser;
    private String CreateTime;
    private LinkedList<Attachment> AttachmentList;
    private List<Voice> VoiceList;

    private String EquipmentID;
    private String ReportUserID;
    private String CompanyID;
    private String DepartID;
    private String FaultReportTypeCode;
    private String FaultAdress;
    private String ContactPhone;
    private String ContactPeople;
    private String FaultStoreyId;
    private String ReportUserName;
    private String StoreyName;

    public String getStoreyName() {
        return StoreyName;
    }

    public void setStoreyName(String storeyName) {
        StoreyName = storeyName;
    }

    public String getReportUserName() {
        return ReportUserName;
    }

    public void setReportUserName(String reportUserName) {
        ReportUserName = reportUserName;
    }

    public String getFaultStoreyId() {
        return FaultStoreyId;
    }

    public void setFaultStoreyId(String faultStoreyId) {
        FaultStoreyId = faultStoreyId;
    }

    public String getContactPeople() {
        return ContactPeople;
    }

    public void setContactPeople(String contactPeople) {
        ContactPeople = contactPeople;
    }

    public String getContactPhone() {
        return ContactPhone;
    }

    public void setContactPhone(String contactPhone) {
        ContactPhone = contactPhone;
    }

    public String getFaultAdress() {
        return FaultAdress;
    }

    public void setFaultAdress(String faultAdress) {
        FaultAdress = faultAdress;
    }

    public String getRowNum() {
        return RowNum;
    }

    public void setRowNum(String rowNum) {
        RowNum = rowNum;
    }

    public String getFaultReportID() {
        return FaultReportID;
    }

    public void setFaultReportID(String faultReportID) {
        FaultReportID = faultReportID;
    }

    public String getFaultReportNo() {
        return FaultReportNo;
    }

    public void setFaultReportNo(String faultReportNo) {
        FaultReportNo = faultReportNo;
    }

    public String getFaultReportTitle() {
        return FaultReportTitle;
    }

    public void setFaultReportTitle(String faultReportTitle) {
        FaultReportTitle = faultReportTitle;
    }

    public String getReportUser() {
        return ReportUser;
    }

    public void setReportUser(String reportUser) {
        ReportUser = reportUser;
    }

    public String getReportTime() {
        return ReportTime;
    }

    public void setReportTime(String reportTime) {
        ReportTime = reportTime;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getFaultDescription() {
        return FaultDescription;
    }

    public void setFaultDescription(String faultDescription) {
        FaultDescription = faultDescription;
    }

    public String getExpectedDate() {
        return ExpectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        ExpectedDate = expectedDate;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
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

    public String getFaultReportType() {
        return FaultReportType;
    }

    public void setFaultReportType(String faultReportType) {
        FaultReportType = faultReportType;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDepartName() {
        return DepartName;
    }

    public void setDepartName(String departName) {
        DepartName = departName;
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

    public LinkedList<Attachment> getAttachmentList() {
        return AttachmentList;
    }

    public void setAttachmentList(LinkedList<Attachment> attachmentList) {
        AttachmentList = attachmentList;
    }

    public List<Voice> getVoiceList() {
        return VoiceList;
    }

    public void setVoiceList(List<Voice> voiceList) {
        VoiceList = voiceList;
    }

    public String getEquipmentID() {
        return EquipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        EquipmentID = equipmentID;
    }

    public String getReportUserID() {
        return ReportUserID;
    }

    public void setReportUserID(String reportUserID) {
        ReportUserID = reportUserID;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public String getDepartID() {
        return DepartID;
    }

    public void setDepartID(String departID) {
        DepartID = departID;
    }

    public String getFaultReportTypeCode() {
        return FaultReportTypeCode;
    }

    public void setFaultReportTypeCode(String FaultReportTypeCode) {
        FaultReportTypeCode = FaultReportTypeCode;
    }
}
