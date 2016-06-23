package com.lltech.manager.entity.project;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
  * @ClassName(类名)      : ProjectRes
  * @Description(描述)    : 项目请求返回对象
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年8月28日 下午2:37:03
  *
 */
public class ProjectRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Project> DataList;

	public LinkedList<Project> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<Project> dataList) {
		DataList = dataList;
	}
}
