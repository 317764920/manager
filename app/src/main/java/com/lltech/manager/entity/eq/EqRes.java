package com.lltech.manager.entity.eq;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : EqRes
  * @Description(描述)    : 设备档案请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class EqRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Eq> DataList;

	public LinkedList<Eq> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<Eq> dataList) {
		DataList = dataList;
	}
}
