package com.lltech.manager.entity.me;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : SystemMsgRes
  * @Description(描述)    : 个人消息列表请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class SystemMsgRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<SystemMsg> DataList;

	public LinkedList<SystemMsg> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<SystemMsg> dataList) {
		DataList = dataList;
	}
}
