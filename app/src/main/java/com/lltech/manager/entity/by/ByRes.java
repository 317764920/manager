package com.lltech.manager.entity.by;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : ByRes
  * @Description(描述)    : 设备保养记录请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class ByRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<By> DataList;

	public LinkedList<By> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<By> dataList) {
		DataList = dataList;
	}
}
