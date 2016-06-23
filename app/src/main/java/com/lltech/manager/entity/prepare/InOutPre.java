package com.lltech.manager.entity.prepare;

import java.io.Serializable;

public class InOutPre implements Serializable {

	private static final long serialVersionUID = -1L;

	private String EquipmentPartID;
	private String EquipmentPartNo;
	private String Count;
	private String Remark;
	private String InOutPartsID;
	
	private String PartsName;
	private String Brand;
	private String Format;
	private String Unit;
	private String InOutPartsType;

	public String getEquipmentPartID() {
		return EquipmentPartID;
	}

	public void setEquipmentPartID(String equipmentPartID) {
		EquipmentPartID = equipmentPartID;
	}

	public String getEquipmentPartNo() {
		return EquipmentPartNo;
	}

	public void setEquipmentPartNo(String equipmentPartNo) {
		EquipmentPartNo = equipmentPartNo;
	}

	public String getCount() {
		return Count;
	}

	public void setCount(String count) {
		Count = count;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getInOutPartsID() {
		return InOutPartsID;
	}

	public void setInOutPartsID(String inOutPartsID) {
		InOutPartsID = inOutPartsID;
	}

	public String getPartsName() {
		return PartsName;
	}

	public void setPartsName(String partsName) {
		PartsName = partsName;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getInOutPartsType() {
		return InOutPartsType;
	}

	public void setInOutPartsType(String inOutPartsType) {
		InOutPartsType = inOutPartsType;
	}
}
