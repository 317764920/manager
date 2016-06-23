package com.lcx.mysdk.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ClassName(类名) : Shp
 * @Description(描述) : share文件工具类
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2015年8月31日 上午11:26:03
 */
public class Shp {
    /**
     * @param mContext
     * @param whichSp
     * @param key
     * @return String
     * @throws :
     * @Description(功能描述) :  获取share字符串
     * @author(作者) ：  liuchunxu
     * @date (开发日期)          :  2015年8月31日 上午11:26:19
     */
    public static String getSharePreStr(Context mContext, String whichSp, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(whichSp, 0);
        String s = sp.getString(key, "");//如果该字段没对应值，则取出字符串0
        return s;
    }

    /**
     * @param mContext
     * @param whichSp
     * @param field
     * @return boolean
     * @throws :
     * @Description(功能描述) :  获取share布尔值
     * @author(作者) ：  liuchunxu
     * @date (开发日期)          :  2015年9月9日 下午7:41:22
     */
    public static boolean getSharePreBoolean(Context mContext, String whichSp, String field, boolean def) {
        SharedPreferences sp = mContext.getSharedPreferences(whichSp, 0);
        return sp.getBoolean(field, def);
    }

    /**
     * @param mContext
     * @param whichSp
     * @param key
     * @param value    void
     * @throws :
     * @Description(功能描述) :  填入share数据
     * @author(作者) ：  liuchunxu
     * @date (开发日期)          :  2015年8月31日 上午11:27:44
     */
    public static void putSharePre(Context mContext, String whichSp, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(whichSp, 0);
        sp.edit().putString(key, value).commit();
    }

    /**
     * @param mContext
     * @param whichSp
     * @param field
     * @param value    void
     * @throws :
     * @Description(功能描述) :  填入share数据
     * @author(作者) ：  liuchunxu
     * @date (开发日期)          :  2015年9月9日 下午7:41:36
     */
    public static void putSharePre(Context mContext, String whichSp, String field, boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(whichSp, 0);
        sp.edit().putBoolean(field, value).commit();
    }

    /**
     *
     * @param mContext
     * @param whichSp
     * @param key
     * @throws :
     * @Description(功能描述) :  擦除share数据
     * @author(作者) ：  liuchunxu
     * @date (开发日期)          :  2015年9月9日 下午7:41:36
     */
    public static void clearShareData(Context mContext, String whichSp, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(whichSp, 0);
        sp.edit().remove(key).commit();
    }
}