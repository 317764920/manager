package com.lltech.manager.entity.cd;

import java.io.Serializable;

/**
 * @ClassName(类名) : WorkCheck
 * @Description(描述) : 签到签退
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月03日 14:33
 */
public class WorkCheck implements Serializable {
    public static final String WX = "1";
    public static final String BY = "2";
    public static final String XJ = "3";
    private String ObjectType;
    private String ObjectID;
    private String CheckUser;
    private String CheckInTime;
    private String CheckOutTime;

    public String getObjectType() {
        return ObjectType;
    }

    public void setObjectType(String objectType) {
        ObjectType = objectType;
    }

    public String getObjectID() {
        return ObjectID;
    }

    public void setObjectID(String objectID) {
        ObjectID = objectID;
    }

    public String getCheckUser() {
        return CheckUser;
    }

    public void setCheckUser(String checkUser) {
        CheckUser = checkUser;
    }

    public String getCheckInTime() {
        return CheckInTime;
    }

    public void setCheckInTime(String checkInTime) {
        CheckInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return CheckOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        CheckOutTime = checkOutTime;
    }
}
