package com.lltech.manager.common;

public class WxState {
	/**
	 * 维修中
	 */
	public static final String UNFINISH = "1";
	/**
	 * 已完成
	 */
	public static final String FINISH = "2";

	public static String getString(String state) {
		if (UNFINISH.equals(state))
			return "维修中";
		else
			return "已完成";
	}
}
