package com.lltech.manager.entity;

import java.io.Serializable;

public class Req implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int PULL_DOWN = 0;
	public static final int PULL_UP = 1;
	public static final String CREATE_TIME = "CreateTime desc";
	private String OrderBy;// 排序字符串，如:CreateTimde desc,Sequence asc
	private Integer PageIndex;// 页码(从0开始)
	private Integer PageSize = 20;// 每页显示记录数

	public String getOrderBy() {
		return OrderBy;
	}

	public void setOrderBy(String orderBy) {
		OrderBy = orderBy;
	}

	public Integer getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		PageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return PageSize;
	}

	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}
}
