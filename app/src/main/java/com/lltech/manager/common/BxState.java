package com.lltech.manager.common;

public class BxState {
    /**
     * 已报修
     */
    public static final String N = "1";
    /**
     * 已完成
     */
    public static final String Y = "2";
    /**
     * 已存档
     */
    public static final String YY = "3";

    public static String getString(String state) {
        if (N.equals(state))
            return "已报修";
        else if (Y.equals(state))
            return "已完成";
        else
            return "已存档";
    }
}
