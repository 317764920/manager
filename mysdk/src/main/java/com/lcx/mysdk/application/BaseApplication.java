package com.lcx.mysdk.application;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lcx.mysdk.exception.CrashHandler;

import org.litepal.LitePalApplication;

import java.util.HashMap;
import java.util.Map;

public class BaseApplication extends LitePalApplication {
    private static Context context;
    private static BaseApplication application;
    public RequestQueue mQueue;
//    public ImageLoader imageLoader;
    private Map<String, Object> commonData = new HashMap<String, Object>();
    private int mark;
    private int operMark;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        application = (BaseApplication) context;
        CrashHandler.getInstance().setCrashHandler();
        initialize(context);
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(context);
        }
//        if (imageLoader == null) {
//            imageLoader = new ImageLoader(mQueue, new BitmapCache());
//        }
    }

    public static Context getContext() {
        return context;
    }

    public static BaseApplication getApplication() {
        return application;
    }

    public RequestQueue getQueue() {
        return mQueue;
    }

    /**
     * @Description(功能描述) : 获取application中存储的数据
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-01 17:49
     */
    public Map<String, Object> getCommonData() {
        return commonData;
    }

    /**
     * @Description(功能描述) : 向application中设置数据
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-01 17:50
     */
    public void setCommonData(Map<String, Object> commonData) {
        this.commonData = commonData;
    }

    /**
     * 获取fragment跳转标记
     * @return
     */
    public int getMark() {
        return mark;
    }

    /**
     * 设置fragment跳转标记
     * @return
     */
    public void setMark(int mark) {
        this.mark = mark;
    }
    /**
     * 获取操作标记
     * @return
     */
    public int getOperMark() {
        return operMark;
    }
    /**
     * 设置操作标记
     * @return
     */
    public void setOperMark(int operMark) {
        this.operMark = operMark;
    }
}
