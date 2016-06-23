package com.lltech.manager.interfaces;

import android.view.View;

/**
 * @ClassName(类名) : TopBarClickListener
 * @Description(描述) : topbar监听接口
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月06日 16:11
 */
public interface TopBarClickListener {
    void onRightClick(int whitchBtn, View view);

    void onLeftClick(View view);
}
