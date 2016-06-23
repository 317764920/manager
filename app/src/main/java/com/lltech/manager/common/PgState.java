package com.lltech.manager.common;

public class PgState {
    /**
     * 已撤回
     */
    public static final String YCH = "0";
	/**
	 * 已派工
	 */
	public static final String YPG = "1";
	/**
	 * 已领单
	 */
	public static final String YLD = "2";
	/**
	 * 已存档
	 */
	public static final String YCD = "3";
	/**
	 * 已完成
	 */
	public static final String YWC = "4";

	public static String getString(String state) {
        if (YCH.equals(state))
            return "已撤回";
		if (YPG.equals(state))
			return "已派工";
        if (YLD.equals(state))
            return "已领单";
        if (YCD.equals(state))
            return "已存档";
		else
			return "已完成";
	}
}
