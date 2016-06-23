package com.lltech.manager.view;

import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.view.base.BaseView;

/**
 * @ClassName(类名) : EqInfoView
 * @Description(描述) : 设备档案详情UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:32
 */
public interface EqInfoView extends BaseView {
    /**
     *
     * 获取信息成功
     * @liuchunxu
     * @2016-06-21 16:42
     *
     */
    void onSuccess(Eq eq);
}
