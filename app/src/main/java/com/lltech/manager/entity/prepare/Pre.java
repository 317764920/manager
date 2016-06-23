package com.lltech.manager.entity.prepare;

import java.io.Serializable;

public class Pre implements Serializable {

	private static final long serialVersionUID = -1L;

	private String RowNum;
	private String ID;
	private String PartsID;
	private String PartsNo;
	private String PartsName;
	private String Distributor;
	private String Brand;
	private String Price;
	private String Format;
	private String StockCount;
	private String Unit;
	private String MinStock;
	private String MaxStock;
	private String AllPrice;
	private String StorageAddr;
	private String Remark;
	private String Producer;
	private String ProInStoragePrice;
	private String ProOutStoragePrice;
	
	private String UseCount;

	public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPartsID() {
		return PartsID;
	}

	public void setPartsID(String partsID) {
		PartsID = partsID;
	}

	public String getPartsNo() {
		return PartsNo;
	}

	public void setPartsNo(String partsNo) {
		PartsNo = partsNo;
	}

	public String getPartsName() {
		return PartsName;
	}

	public void setPartsName(String partsName) {
		PartsName = partsName;
	}

	public String getDistributor() {
		return Distributor;
	}

	public void setDistributor(String distributor) {
		Distributor = distributor;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getStockCount() {
		return StockCount;
	}

	public void setStockCount(String stockCount) {
		StockCount = stockCount;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getMinStock() {
		return MinStock;
	}

	public void setMinStock(String minStock) {
		MinStock = minStock;
	}

	public String getMaxStock() {
		return MaxStock;
	}

	public void setMaxStock(String maxStock) {
		MaxStock = maxStock;
	}

	public String getAllPrice() {
		return AllPrice;
	}

	public void setAllPrice(String allPrice) {
		AllPrice = allPrice;
	}

	public String getStorageAddr() {
		return StorageAddr;
	}

	public void setStorageAddr(String storageAddr) {
		StorageAddr = storageAddr;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getProducer() {
		return Producer;
	}

	public void setProducer(String producer) {
		Producer = producer;
	}

	public String getProInStoragePrice() {
		return ProInStoragePrice;
	}

	public void setProInStoragePrice(String proInStoragePrice) {
		ProInStoragePrice = proInStoragePrice;
	}

	public String getProOutStoragePrice() {
		return ProOutStoragePrice;
	}

	public void setProOutStoragePrice(String proOutStoragePrice) {
		ProOutStoragePrice = proOutStoragePrice;
	}

	public String getUseCount() {
		return UseCount;
	}

	public void setUseCount(String useCount) {
		UseCount = useCount;
	}

}
