package com.lltech.manager.entity.pg;

import com.lltech.manager.entity.Req;

import java.util.List;

/**
 * @ClassName(类名) : PgReq
 * @Description(描述) : 派工请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 */
public class PgReq extends Req {

    private static final long serialVersionUID = 1L;

    private String DistributionNo;
    private String DistributionUser;
    private String BeginDistributionTime;
    private String EndDistributionTime;
    private String DistributionClass = "Dic_Repair";
    private String RepairUserID;
    private List<Pg> IDList;

    private String DistributionID;
    private String FaultReportID;
    private String State;
    private String Explain;

    public String getDistributionID() {
        return DistributionID;
    }

    public void setDistributionID(String distributionID) {
        DistributionID = distributionID;
    }

    public String getFaultReportID() {
        return FaultReportID;
    }

    public void setFaultReportID(String faultReportID) {
        FaultReportID = faultReportID;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String explain) {
        Explain = explain;
    }

    public String getDistributionNo() {
        return DistributionNo;
    }

    public void setDistributionNo(String distributionNo) {
        DistributionNo = distributionNo;
    }

    public String getDistributionUser() {
        return DistributionUser;
    }

    public void setDistributionUser(String distributionUser) {
        DistributionUser = distributionUser;
    }

    public String getBeginDistributionTime() {
        return BeginDistributionTime;
    }

    public void setBeginDistributionTime(String beginDistributionTime) {
        BeginDistributionTime = beginDistributionTime;
    }

    public String getEndDistributionTime() {
        return EndDistributionTime;
    }

    public void setEndDistributionTime(String endDistributionTime) {
        EndDistributionTime = endDistributionTime;
    }

    public String getDistributionClass() {
        return DistributionClass;
    }

    public void setDistributionClass(String distributionClass) {
        DistributionClass = distributionClass;
    }

    public String getRepairUserID() {
        return RepairUserID;
    }

    public void setRepairUserID(String repairUserID) {
        RepairUserID = repairUserID;
    }

    public List<Pg> getIDList() {
        return IDList;
    }

    public void setIDList(List<Pg> iDList) {
        IDList = iDList;
    }

}
