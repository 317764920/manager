package com.lltech.manager.entity.dialog;

import com.lltech.manager.entity.eq.Eq;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @ClassName(类名) : DialogRes
 * @Description(描述) : 请求返回对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午2:37:03
 */
public class DialogRes implements Serializable {

    private static final long serialVersionUID = 1L;

    private LinkedList<SystemInfo> SubSystemList;
    private LinkedList<Eq> EquipmentList;
    private LinkedList<DicInfo> DataDicList;
    private LinkedList<MaintenanceStandardInfo> MaintenanceStandardList;
    private LinkedList<AreaInfo> AreaList;
    private LinkedList<StoreyInfo> StoreyList;

    public LinkedList<SystemInfo> getSubSystemList() {
        return SubSystemList;
    }

    public void setSubSystemList(LinkedList<SystemInfo> subSystemList) {
        SubSystemList = subSystemList;
    }

    public LinkedList<Eq> getEquipmentList() {
        return EquipmentList;
    }

    public void setEquipmentList(LinkedList<Eq> equipmentList) {
        EquipmentList = equipmentList;
    }

    public LinkedList<DicInfo> getDataDicList() {
        return DataDicList;
    }

    public void setDataDicList(LinkedList<DicInfo> dataDicList) {
        DataDicList = dataDicList;
    }

    public LinkedList<MaintenanceStandardInfo> getMaintenanceStandardList() {
        return MaintenanceStandardList;
    }

    public void setMaintenanceStandardList(LinkedList<MaintenanceStandardInfo> maintenanceStandardList) {
        MaintenanceStandardList = maintenanceStandardList;
    }

    public LinkedList<AreaInfo> getAreaList() {
        return AreaList;
    }

    public void setAreaList(LinkedList<AreaInfo> areaList) {
        AreaList = areaList;
    }

    public LinkedList<StoreyInfo> getStoreyList() {
        return StoreyList;
    }

    public void setStoreyList(LinkedList<StoreyInfo> storeyList) {
        StoreyList = storeyList;
    }
}
