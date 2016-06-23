package com.lltech.manager.entity.prepare;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : InOutRes
  * @Description(描述)    : 出入库请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class InOutRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<InOut> DataList;

	public LinkedList<InOut> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<InOut> dataList) {
		DataList = dataList;
	}
}
