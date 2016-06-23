package com.lltech.manager.common;

public class WgState {
    /**
     * 驳回
     */
    public static final String BH = "1";
    /**
     * 通过
     */
    public static final String TG = "4";

    public static String getString(String state){
        if (BH.equals(state))
            return "驳回";
        else
            return "通过";
    }
}
