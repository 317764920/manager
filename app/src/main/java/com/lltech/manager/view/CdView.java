package com.lltech.manager.view;

import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.entity.cd.WorkCheck;
import com.lltech.manager.view.base.BaseView;

import java.util.List;

/**
 * @ClassName(类名) : CdView
 * @Description(描述) : 存档UI
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:32
 */
public interface CdView extends BaseView {

    /**
     * 获取存档维修单信息成功
     *
     * @liuchunxu
     * @2016-06-07 11:35
     */
    void onGetCdWxEntitySuccess(Wx wx);

    /**
     * 签到成功
     *
     * @liuchunxu
     * @2016-06-22 16:25
     */
    void onCheckInSuccess();

    /**
     * 签退成功
     *
     * @liuchunxu
     * @2016-06-22 16:25
     */
    void onCheckOutSuccess();

    /**
     * 查询签到签退历史成功
     *
     * @liuchunxu
     * @2016-06-22 16:25
     */
    void onGetCheckHistorySuccess(List<WorkCheck> w_list);
}
