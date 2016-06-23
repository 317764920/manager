package com.lltech.manager.view;

import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.view.base.BaseView;

import java.util.List;

/**
 * @ClassName(类名) : EqListView
 * @Description(描述) : 设备档案列表UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:32
 */
public interface EqListView extends BaseView {
    /**
     *
     * 下拉刷新
     * @liuchunxu
     * @2016-06-07 11:5
     *
     */
    void onLoad(List<Eq> list);

    /**
     *
     * 上拉加载
     * @liuchunxu
     * @2016-06-07 11:35
     *
     */
    void onLoadMore(List<Eq> list);
}
