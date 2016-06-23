package com.lltech.manager.common;

public class EqStatus {
	public static final String USE = "1";
	public static final String UNUSE = "0";

	public static String getStatusName(String status) {
		if (USE.equals(status)) {
			return "已启用";
		} else if (UNUSE.equals(status)) {
			return "未启用";
		} else {
			return "";
		}
	}
}
