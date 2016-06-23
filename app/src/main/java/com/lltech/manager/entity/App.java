package com.lltech.manager.entity;

import org.litepal.crud.DataSupport;


public class App extends DataSupport {
	/**
	 * APP显示名字
	 */
	private String appName;
	/**
	 * APP显示图标
	 */
	private int appIcomResId = -1;
	/**
	 * APP取消图标
	 */
	private int appIconCancelResId = -1;
	/**
	 * 栏目对应ID
	 * */
	private String aId;
	/**
	 * 栏目在整体中的排序顺序
	 * */
	private Integer orderId;
	/**
	 * 栏目是否选中
	 * */
	private String selected;
	
	/**
	 * 备用字段1
	 * */
	private String attrOne;
	/**
	 * 备用字段2
	 * */
	private String attrTwo;

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public int getAppIcomResId() {
		return appIcomResId;
	}

	public void setAppIcomResId(int appIcomResId) {
		this.appIcomResId = appIcomResId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getAppIconCancelResId() {
		return appIconCancelResId;
	}

	public void setAppIconCancelResId(int appIconCancelResId) {
		this.appIconCancelResId = appIconCancelResId;
	}

	public String getAttrOne() {
		return attrOne;
	}

	public void setAttrOne(String attrOne) {
		this.attrOne = attrOne;
	}

	public String getAttrTwo() {
		return attrTwo;
	}

	public void setAttrTwo(String attrTwo) {
		this.attrTwo = attrTwo;
	}

}
