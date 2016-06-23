package com.lltech.manager.entity.bx;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : BxRes
  * @Description(描述)    : 设备报修请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class BxRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Bx> DataList;

	public LinkedList<Bx> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<Bx> dataList) {
		DataList = dataList;
	}
}
