package com.lltech.manager.entity.prepare;

import java.util.List;

import com.lltech.manager.entity.Req;

/**
 * 
 * @ClassName(类名) : PreReq
 * @Description(描述) : 备品档案请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 *
 */
public class PreReq extends Req {

	private static final long serialVersionUID = 1L;

	private String PartsNo;
	private String PartsName;
	private String StorageAddr;
	private List<Pre> IDList;

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

	public String getStorageAddr() {
		return StorageAddr;
	}

	public void setStorageAddr(String storageAddr) {
		StorageAddr = storageAddr;
	}

	public List<Pre> getIDList() {
		return IDList;
	}

	public void setIDList(List<Pre> iDList) {
		IDList = iDList;
	}

}
