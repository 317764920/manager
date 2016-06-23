package com.lltech.manager.view;

import com.lltech.manager.view.base.BaseView;

/**
 * @ClassName(类名) : PwdView
 * @Description(描述) : 修改密码UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月21日 17:37
 */
public interface PwdView extends BaseView {
    /**
     * 修改成功
     *
     * @liuchunxu
     * @2016-06-21 17:40
     */
    void onSuccess();

    /**
     * 修改失败
     *
     * @liuchunxu
     * @2016-06-21 17:40
     */
    void onPwdError();
}
