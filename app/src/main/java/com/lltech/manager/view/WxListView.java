package com.lltech.manager.view;

import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.view.base.BaseView;

import java.util.List;

/**
 * @ClassName(类名) : WxListView
 * @Description(描述) : 维修列表UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:32
 */
public interface WxListView extends BaseView {
    /**
     *
     * 下拉刷新
     * @liuchunxu
     * @2016-06-07 11:35
     *
     */
    void onLoad(List<Wx> list);

    /**
     *
     * 上拉加载
     * @liuchunxu
     * @2016-06-07 11:35
     *
     */
    void onLoadMore(List<Wx> list);
}
