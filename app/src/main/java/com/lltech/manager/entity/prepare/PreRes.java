package com.lltech.manager.entity.prepare;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : PreRes
  * @Description(描述)    : 备品档案请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class PreRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Pre> DataList;

	public LinkedList<Pre> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<Pre> dataList) {
		DataList = dataList;
	}
}
