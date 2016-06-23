package com.lltech.manager.entity.prepare;

import java.util.List;

import com.lltech.manager.entity.Req;

/**
 * 
 * @ClassName(类名) : InOutReq
 * @Description(描述) : 出入库请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 *
 */
public class InOutReq extends Req {

	private static final long serialVersionUID = 1L;

	private String PartsNo;
	private String PartsName;
	private String InOutPartsTypeID;
	private List<InOut> IDList;

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

	public String getInOutPartsTypeID() {
		return InOutPartsTypeID;
	}

	public void setInOutPartsTypeID(String inOutPartsTypeID) {
		InOutPartsTypeID = inOutPartsTypeID;
	}

	public List<InOut> getIDList() {
		return IDList;
	}

	public void setIDList(List<InOut> iDList) {
		IDList = iDList;
	}

}
