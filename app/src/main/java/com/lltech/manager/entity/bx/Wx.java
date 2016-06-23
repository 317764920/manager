package com.lltech.manager.entity.bx;

import com.lltech.manager.entity.Attachment;
import com.lltech.manager.entity.pg.Apply;

import java.io.Serializable;
import java.util.List;

public class Wx implements Serializable {

    private static final long serialVersionUID = -1L;

    private String RepairID;
    private String RepairNo;
    private String CreateTime;
    private String RepairTime;
    private String Remark;
    private String State;
    private String CreateUserID;
    private String CreateUserName;
    private String RepairTitle;
    private List<Attachment> AttachmentList;
    private List<Bx> FaultReportList;
    private String RowNum;
    private List<Apply> DistributionApplyList;

    public List<Apply> getDistributionApplyList() {
        return DistributionApplyList;
    }

    public void setDistributionApplyList(List<Apply> distributionApplyList) {
        DistributionApplyList = distributionApplyList;
    }

    public String getRepairID() {
        return RepairID;
    }

    public void setRepairID(String repairID) {
        RepairID = repairID;
    }

    public String getRepairNo() {
        return RepairNo;
    }

    public void setRepairNo(String repairNo) {
        RepairNo = repairNo;
    }

    public String getRepairTime() {
        return RepairTime;
    }

    public void setRepairTime(String repairTime) {
        RepairTime = repairTime;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
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

    public String getRepairTitle() {
        return RepairTitle;
    }

    public void setRepairTitle(String repairTitle) {
        RepairTitle = repairTitle;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
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

    public List<Bx> getFaultReportList() {
        return FaultReportList;
    }

    public void setFaultReportList(List<Bx> faultReportList) {
        FaultReportList = faultReportList;
    }

    public String getRowNum() {
        return RowNum;
    }

    public void setRowNum(String rowNum) {
        RowNum = rowNum;
    }
}
