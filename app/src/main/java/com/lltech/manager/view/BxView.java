package com.lltech.manager.view;

import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.view.base.BaseView;

/**
 * @ClassName(类名) : BxListView
 * @Description(描述) : 报修列表UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:32
 */
public interface BxView extends BaseView {
    /**
     * 获取报修单详情成功
     *
     * @liuchunxu
     * @2016-06-07 11:35
     */
    void onGetEntitySuccess(Bx bx);

    /**
     * 保存成功
     *
     * @liuchunxu
     * @2016-06-07 11:35
     */
    void onSaveSuccess();

    /**
     * 删除成功
     *
     * @liuchunxu
     * @2016-06-22 14:46
     */
    void onDelSuccess();
}
