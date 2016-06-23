package com.lltech.manager.view;

import com.lcx.mysdk.entity.Data;
import com.lltech.manager.view.base.BaseView;

/**
 * @ClassName(类名) : AccountView
 * @Description(描述) : 账号管理UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:32
 */
public interface AccountView extends BaseView {
    /**
     * 切换成功
     *
     * @liuchunxu
     * @2016-06-21 16:42
     */
    void onSuccess(Data data);
}
