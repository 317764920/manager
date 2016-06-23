package com.lltech.manager.entity.pg;

import java.io.Serializable;

/**
 * @ClassName(类名) : Apply
 * @Description(描述) : 退单申请
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月02日 09:04
 */
public class Apply implements Serializable {
    private String DistributionID;
    private String ApplyDesc;
    private String ApplyFromUser;
    private String HandleUser;
    private String ApplyTime;
    private String ApplyUser;

    public String getApplyUser() {
        return ApplyUser;
    }

    public void setApplyUser(String applyUser) {
        ApplyUser = applyUser;
    }

    public String getDistributionID() {
        return DistributionID;
    }

    public void setDistributionID(String distributionID) {
        DistributionID = distributionID;
    }

    public String getApplyDesc() {
        return ApplyDesc;
    }

    public void setApplyDesc(String applyDesc) {
        ApplyDesc = applyDesc;
    }

    public String getApplyFromUser() {
        return ApplyFromUser;
    }

    public void setApplyFromUser(String applyFromUser) {
        ApplyFromUser = applyFromUser;
    }

    public String getHandleUser() {
        return HandleUser;
    }

    public void setHandleUser(String handleUser) {
        HandleUser = handleUser;
    }

    public String getApplyTime() {
        return ApplyTime;
    }

    public void setApplyTime(String applyTime) {
        ApplyTime = applyTime;
    }
}
