package com.lltech.manager.view;

import com.lcx.mysdk.entity.Data;
import com.lltech.manager.view.base.BaseView;


/**
 * @ClassName(类名) : WgView
 * @Description(描述) : 完工确认的UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月13日 09:52
 */
public interface WgView extends BaseView{
    /**
     * 完工确认成功
     * */
    void reviewSuccess(Data data);

    /**
     * 查询维修详情成功
     * */
    void refreshSuccess(Data data);
}
