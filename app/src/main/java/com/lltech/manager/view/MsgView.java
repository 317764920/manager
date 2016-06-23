package com.lltech.manager.view;

import com.lltech.manager.entity.me.SystemMsg;
import com.lltech.manager.view.base.BaseView;

import java.util.List;

/**
 * @ClassName(类名) : MsgView
 * @Description(描述) : 消息UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月22日 16:56
 */
public interface MsgView extends BaseView {
    /**
     * 下拉刷新
     *
     * @liuchunxu
     * @2016-06-07 11:35
     */
    void onLoad(List<SystemMsg> list);

    /**
     * 上拉加载
     *
     * @liuchunxu
     * @2016-06-07 11:35
     */
    void onLoadMore(List<SystemMsg> list);

    /**
     * 标记已读成功
     *
     * @liuchunxu
     * @2016-06-22 16:58
     */
    void onUpdateSuccess();
}
