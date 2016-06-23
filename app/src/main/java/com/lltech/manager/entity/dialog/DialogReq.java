package com.lltech.manager.entity.dialog;

import com.lltech.manager.entity.Req;

/**
 * 
 * @ClassName(类名) : DialogReq
 * @Description(描述) : 系统请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 *
 */
public class DialogReq extends Req {

	private static final long serialVersionUID = 1L;
	
	public static final String DIC = "04";
	public static final String EQ = "05";
	public static final String SYSTEM = "06";
	public static final String BYBZ = "07";
	public static final String AREA = "08";
    public static final String STOREY = "09";

	private String SerialNumber;
	private String ProjectID;
	private String SystemCode;
	
	private String DicCode;
	private String EquipmentName;

	public String getProjectID() {
		return ProjectID;
	}

	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}

	public String getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}

	public String getSystemCode() {
		return SystemCode;
	}

	public void setSystemCode(String systemCode) {
		SystemCode = systemCode;
	}

	public String getDicCode() {
		return DicCode;
	}

	public void setDicCode(String dicCode) {
		DicCode = dicCode;
	}

	public String getEquipmentName() {
		return EquipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		EquipmentName = equipmentName;
	}
}
