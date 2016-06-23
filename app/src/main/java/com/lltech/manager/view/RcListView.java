package com.lltech.manager.view;

import com.lltech.manager.entity.rc.Rc;
import com.lltech.manager.view.base.BaseView;

import java.util.LinkedList;

/**
 * @ClassName(类名) : RcListView
 * @Description(描述) : 日程列表UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:32
 */
public interface RcListView extends BaseView {
    /**
     * 切换成功
     *
     * @liuchunxu
     * @2016-06-21 16:42
     */
    void onSuccess(LinkedList<Rc> list);
}
