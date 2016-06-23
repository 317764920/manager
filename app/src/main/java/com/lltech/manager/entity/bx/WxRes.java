package com.lltech.manager.entity.bx;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : WxRes
  * @Description(描述)    : 设备维修记录请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class WxRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Wx> DataList;

	public LinkedList<Wx> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<Wx> dataList) {
		DataList = dataList;
	}
}
