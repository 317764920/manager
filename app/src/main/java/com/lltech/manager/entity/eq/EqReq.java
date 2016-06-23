package com.lltech.manager.entity.eq;

import com.lltech.manager.entity.Req;

/**
 * @ClassName(类名) : EqReq
 * @Description(描述) : 设备档案请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 */
public class EqReq extends Req {

    private static final long serialVersionUID = 1L;

    private String EquipmentNo;
    private String EquipmentID;
    private String EquipmentName;
    private String SystemCode;
    private String ProjectID;
    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getEquipmentNo() {
        return EquipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        EquipmentNo = equipmentNo;
    }

    public String getEquipmentID() {
        return EquipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        EquipmentID = equipmentID;
    }

    public String getEquipmentName() {
        return EquipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        EquipmentName = equipmentName;
    }

    public String getSystemCode() {
        return SystemCode;
    }

    public void setSystemCode(String systemCode) {
        SystemCode = systemCode;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }


}
