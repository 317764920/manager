package com.lltech.manager.entity.by;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : PlanRes
  * @Description(描述)    : 设备保养计划请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class PlanRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Plan> DataList;

	public LinkedList<Plan> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<Plan> dataList) {
		DataList = dataList;
	}
}
