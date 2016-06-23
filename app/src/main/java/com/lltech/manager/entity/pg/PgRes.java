package com.lltech.manager.entity.pg;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : PgRes
  * @Description(描述)    : 派工请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class PgRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Pg> DataList;

	public LinkedList<Pg> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<Pg> dataList) {
		DataList = dataList;
	}
}
