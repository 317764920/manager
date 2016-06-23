package com.lltech.manager.common;

public class ByState {
	/**
	 * 保养中
	 */
	public static final String UNFINISH = "0";
	/**
	 * 已完成
	 */
	public static final String FINISH = "1";

	public static String getString(String state) {
		if (UNFINISH.equals(state))
			return "维修中";
		else
			return "已完成";
	}
}
