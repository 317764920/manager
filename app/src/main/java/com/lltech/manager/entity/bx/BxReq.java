package com.lltech.manager.entity.bx;

import com.lltech.manager.entity.Req;

import java.util.List;

/**
 * @ClassName(类名) : BxReq
 * @Description(描述) : 设备报修请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 */
public class BxReq extends Req {
    private String FaultReportNo;
    private String ReportUserID;
    private String ReportUserName;
    private String BeginReportTime;
    private String EndReportTime;
    private String State;
    private List<Bx> IDList;

    private String FaultReportID;

    public String getFaultReportNo() {
        return FaultReportNo;
    }

    public void setFaultReportNo(String faultReportNo) {
        FaultReportNo = faultReportNo;
    }

    public String getReportUserID() {
        return ReportUserID;
    }

    public void setReportUserID(String reportUserID) {
        ReportUserID = reportUserID;
    }

    public String getReportUserName() {
        return ReportUserName;
    }

    public void setReportUserName(String reportUserName) {
        ReportUserName = reportUserName;
    }

    public String getBeginReportTime() {
        return BeginReportTime;
    }

    public void setBeginReportTime(String beginReportTime) {
        BeginReportTime = beginReportTime;
    }

    public String getEndReportTime() {
        return EndReportTime;
    }

    public void setEndReportTime(String endReportTime) {
        EndReportTime = endReportTime;
    }

    public List<Bx> getIDList() {
        return IDList;
    }

    public void setIDList(List<Bx> iDList) {
        IDList = iDList;
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
}
