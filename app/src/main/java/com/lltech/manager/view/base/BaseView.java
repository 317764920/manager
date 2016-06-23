package com.lltech.manager.view.base;

import com.android.volley.VolleyError;
import com.lcx.mysdk.entity.Data;

/**
 * @ClassName(类名) : BaseView
 * @Description(描述) : V层
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 10:21
 */
public interface BaseView {
    void onFail(Data data);

    void onError(VolleyError volleyError);

    void onTokenError();
}
