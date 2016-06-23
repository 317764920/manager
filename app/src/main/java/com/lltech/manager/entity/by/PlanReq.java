package com.lltech.manager.entity.by;

import java.util.List;

import com.lltech.manager.entity.Req;

/**
 * 
 * @ClassName(类名) : PlanReq
 * @Description(描述) : 设备保养计划请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 *
 */
public class PlanReq extends Req {

	private static final long serialVersionUID = 1L;

	private String MaintenancePlanNo;
	private String Maintainer;
	private String EquipmentName;
	private List<Plan> IDList;

	public String getMaintenancePlanNo() {
		return MaintenancePlanNo;
	}

	public void setMaintenancePlanNo(String maintenancePlanNo) {
		MaintenancePlanNo = maintenancePlanNo;
	}

	public String getMaintainer() {
		return Maintainer;
	}

	public void setMaintainer(String maintainer) {
		Maintainer = maintainer;
	}

	public String getEquipmentName() {
		return EquipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		EquipmentName = equipmentName;
	}

	public List<Plan> getIDList() {
		return IDList;
	}

	public void setIDList(List<Plan> iDList) {
		IDList = iDList;
	}
}
