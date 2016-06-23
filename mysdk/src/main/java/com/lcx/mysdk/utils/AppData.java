package com.lcx.mysdk.utils;

import com.lcx.mysdk.application.BaseApplication;

/**
 * @ClassName(类名) : AppData
 * @Description(描述) : app缓存数据
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月05日 11:24
 */
public class AppData {
    public static BaseApplication application = BaseApplication.getApplication();
    /**
     * 是否第一次运行
     */
    public static final String IS_FIRST_RUN = "IsFirstRun";
    /**
     * token
     */
    public static final String TOKEN = "token";
    /**
     * 用户
     */
    public static final String USER = "user";
    /**
     * 密钥
     */
    public static final String CODE = "code";
    /**
     * 请求地址
     */
    public static final String URL = "url";

    /**
     * 设置密钥
     *
     * @param key
     */
    public static void setCode(String key) {
        Shp.putSharePre(application, CODE, CODE, key);
    }

    /**
     * 获取密钥
     *
     * @return
     */
    public static String getCode() {
        return Shp.getSharePreStr(application, CODE, CODE);
    }

    /**
     * 设置是否第一次登陆
     *
     * @param flag
     */
    public static void setIsFirstRun(boolean flag) {
        Shp.putSharePre(application, IS_FIRST_RUN, IS_FIRST_RUN, flag);
    }

    /**
     * 获取是否第一次登陆
     *
     * @return
     */
    public static boolean getIsFirstRun() {
        return Shp.getSharePreBoolean(application, IS_FIRST_RUN, IS_FIRST_RUN, true);
    }

    /**
     * 设置url
     *
     * @param url
     */
    public static void setUrl(String url) {
        Shp.putSharePre(application, URL, URL, url);
    }

    /**
     * 获取url
     *
     * @return
     */
    public static String getUrl() {
        return Shp.getSharePreStr(application, URL, URL);
    }

    /**
     * 设置token
     * liuchunxu
     * 2016-04-29 16:21
     */
    public static void setToken(String json) {
        Shp.putSharePre(application, TOKEN, TOKEN, json);
    }

    /**
     * @Description(功能描述) : 获取Token
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-01 17:47
     */
    public static String getToken() {
        return Shp.getSharePreStr(application, TOKEN, TOKEN);
    }

    /**
     * @Description(功能描述) : 获取用户
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-01 17:47
     */
    public static <T> T getUser(Class<T> entity) {
        String json = Shp.getSharePreStr(application, USER, USER);
        if (CommonUtil.isNotEmpty(json)) {
            T t = JsonUtils.jsonToEntity(json, entity);
            return t;
        }
        return null;
    }

    /**
     * @Description(功能描述) : 设置用户
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-01 17:48
     */
    public static void setUser(String json) {
        Shp.putSharePre(application, USER, USER, json);
    }
}
