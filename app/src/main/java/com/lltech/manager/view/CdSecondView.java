package com.lltech.manager.view;

import com.lcx.mysdk.entity.Data;
import com.lltech.manager.view.base.BaseView;

/**
 * @ClassName(类名) : CdSecondView
 * @Description(描述) : 报修，保养执行
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 14:34
 */
public interface CdSecondView extends BaseView {
    /**
     *
     * 存档成功
     * @liuchunxu
     * @2016-06-07 14:36
     *
     */
    void InArchivesSuccess(Data data);
}
