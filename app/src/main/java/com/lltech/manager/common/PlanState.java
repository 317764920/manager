package com.lltech.manager.common;

public class PlanState {
	/**
	 * 进行中
	 */
	public static final String ING = "1";
	/**
	 * 已派工
	 */
	public static final String PG = "2";
	/**
	 * 已完成
	 */
	public static final String FINISH = "3";

	public static String getString(String state) {
		if (ING.equals(state))
			return "进行中";
		else if (PG.equals(state))
			return "已派工";
		else
			return "已完成";
	}
}
