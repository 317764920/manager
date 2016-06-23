package com.lltech.manager.view;

import com.lcx.mysdk.entity.Data;
import com.lltech.manager.view.base.BaseView;

/**
 * @ClassName(类名) : LoginView
 * @Description(描述) : 登录UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 10:23
 */
public interface LoginView extends BaseView {
    void onLoginSuccess(Data data);
}
