package com.lltech.manager.entity.cd;

import com.lltech.manager.entity.Req;

/**
 * @ClassName(类名) : WorkCheckReq
 * @Description(描述) : 签到签退请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月03日 14:33
 */
public class WorkCheckReq extends Req {
    private String ObjectType;
    private String ObjectID;
    private String CheckUser;

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
}
