package com.lltech.manager.entity.rc;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : RcRes
  * @Description(描述)    : 日程请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class RcRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Rc> DataList;

	public LinkedList<Rc> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<Rc> dataList) {
		DataList = dataList;
	}
}
