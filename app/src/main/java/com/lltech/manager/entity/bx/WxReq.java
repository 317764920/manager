package com.lltech.manager.entity.bx;

import com.lltech.manager.entity.Req;

import java.util.List;

/**
 * @ClassName(类名) : WxReq
 * @Description(描述) : 设备维修记录请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 */
public class WxReq extends Req {

    private static final long serialVersionUID = 1L;
    private String RepairID;
    private String RepairNo;
    private String RepairTitle;
    private String State;
    private List<Wx> IDList;

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

    public String getRepairTitle() {
        return RepairTitle;
    }

    public void setRepairTitle(String repairTitle) {
        RepairTitle = repairTitle;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public List<Wx> getIDList() {
        return IDList;
    }

    public void setIDList(List<Wx> IDList) {
        this.IDList = IDList;
    }
}
