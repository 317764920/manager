package com.lltech.manager.view;

import com.lcx.mysdk.entity.Data;
import com.lltech.manager.view.base.BaseView;

/**
 * @ClassName(类名) : AccountAddView
 * @Description(描述) : 账号添加UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:32
 */
public interface AccountAddView extends BaseView {
    /**
     * 添加成功
     *
     * @liuchunxu
     * @2016-06-21 16:42
     */
    void onSuccess(Data data);
}
