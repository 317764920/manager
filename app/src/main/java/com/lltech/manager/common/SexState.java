package com.lltech.manager.common;

public class SexState {
	/**
	 * 女
	 */
	public static final String MIS = "0";
	/**
	 * 男
	 */
	public static final String MAN = "1";

	public static String getString(String state) {
		if (MIS.equals(state))
			return "女";
		else
			return "男";
	}
}
