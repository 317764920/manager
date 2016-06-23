package com.lltech.manager.entity.rc;

import com.lltech.manager.entity.Req;

public class DoWorkReq extends Req {

    private static final long serialVersionUID = 1L;

    private String RowNum;
    private String DoWorkID;
    private String DoWorkName;
    private String DoWorkCode;
    private String PKeyValue;
    private String CreateTime;
    private String Status;
    private String Remark;
    private String ProjectID;
    private String DoWorkUserID;

    public String getRowNum() {
        return RowNum;
    }

    public void setRowNum(String rowNum) {
        RowNum = rowNum;
    }

    public String getDoWorkID() {
        return DoWorkID;
    }

    public void setDoWorkID(String doWorkID) {
        DoWorkID = doWorkID;
    }

    public String getDoWorkName() {
        return DoWorkName;
    }

    public void setDoWorkName(String doWorkName) {
        DoWorkName = doWorkName;
    }

    public String getDoWorkCode() {
        return DoWorkCode;
    }

    public void setDoWorkCode(String doWorkCode) {
        DoWorkCode = doWorkCode;
    }

    public String getPKeyValue() {
        return PKeyValue;
    }

    public void setPKeyValue(String PKeyValue) {
        this.PKeyValue = PKeyValue;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public String getDoWorkUserID() {
        return DoWorkUserID;
    }

    public void setDoWorkUserID(String doWorkUserID) {
        DoWorkUserID = doWorkUserID;
    }

}
