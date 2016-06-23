package com.lltech.manager.view;

import com.lltech.manager.entity.AppVersion;
import com.lltech.manager.view.base.BaseView;

/**
 * @ClassName(类名) : AboutView
 * @Description(描述) : 关于UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月21日 17:37
 */
public interface AboutView extends BaseView {
    /**
     * 发现更新
     *
     * @liuchunxu
     * @2016-06-21 17:40
     */
    void onHasNewVersion(AppVersion appVersion);

    /**
     * 没有更新
     *
     * @liuchunxu
     * @2016-06-21 17:40
     */
    void onNoNewVersion();
}
