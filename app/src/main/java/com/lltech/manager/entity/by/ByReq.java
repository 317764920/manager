package com.lltech.manager.entity.by;

import com.lltech.manager.entity.Req;

import java.util.List;

/**
 * @ClassName(类名) : ByReq
 * @Description(描述) : 设备保养记录请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 */
public class ByReq extends Req {

    private static final long serialVersionUID = 1L;

    private String MaintenancePlanNo;
    private String EquipmentName;
    private String EquipmentNo;
    private List<By> IDList;

    public String getMaintenancePlanNo() {
        return MaintenancePlanNo;
    }

    public void setMaintenancePlanNo(String maintenancePlanNo) {
        MaintenancePlanNo = maintenancePlanNo;
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

    public List<By> getIDList() {
        return IDList;
    }

    public void setIDList(List<By> iDList) {
        IDList = iDList;
    }

}
