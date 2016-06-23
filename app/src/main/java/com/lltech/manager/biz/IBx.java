package com.lltech.manager.biz;

import com.lltech.manager.biz.base.IBiz;
import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.entity.cd.WorkCheck;
import com.lltech.manager.entity.pg.Apply;
import com.lltech.manager.entity.pg.Pg;

/**
 * @ClassName(类名) : IBx
 * @Description(描述) : 报修接口
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:00
 */
public interface IBx extends IBiz {
    /**
     * 获取报修列表
     *
     * @liuchunxu
     * @2016-06-07 11:47
     */
    void getBxList(int pageType, String reqState, String text_BeginReportTime, String text_EndReportTime);

    /**
     * 获取维修列表
     *
     * @liuchunxu
     * @2016-06-07 11:47
     */
    void getWxList(int pageType, String reqState, String edit_RepairNo, String edit_RepairTitle);

    /**
     * 报修单存档
     *
     * @liuchunxu
     * @2016-06-07 14:46
     */
    void inArchives(String FaultReportID, String DistributionID, String RepairDesc);

    /**
     * 维修派工审核
     *
     * @liuchunxu
     * @2016-06-22 10:54
     */
    void review(String DistributionID, String reqState, String edit_ReviewedDesc);

    /**
     * 获取完工单里维修单详情
     *
     * @liuchunxu
     * @2016-06-22 10:53
     */
    void getWgWxEntity(String RepairID);

    /**
     * 获取维修派工列表
     *
     * @liuchunxu
     * @2016-06-22 10:53
     */
    void getPgList(int pageType, String reqState, String text_EndDistributionTime, String text_BeginDistributionTime);

    /**
     * 获取报修单详情
     *
     * @liuchunxu
     * @2016-06-22 14:37
     */
    void getBxEntity(String FaultReportID);

    /**
     * 保存报修单
     *
     * @liuchunxu
     * @2016-06-22 14:51
     */
    void saveBx(Bx bx);

    /**
     * 删除报修单
     *
     * @liuchunxu
     * @2016-06-22 15:07
     */
    void delBx(Bx bx);

    /**
     * 获取维修单详情
     *
     * @liuchunxu
     * @2016-06-22 10:53
     */
    void getWxEntity(String RepairID);

    /**
     * 保存维修单
     *
     * @liuchunxu
     * @2016-06-22 15:41
     */
    void saveWx(Wx wx);

    /**
     * 删除維修单
     *
     * @liuchunxu
     * @2016-06-22 15:07
     */
    void delWx(Wx wx);

    /**
     * 维修派工
     *
     * @liuchunxu
     * @2016-06-22 15:51
     */
    void wxPg(Pg pg);

    /**
     * 领单
     *
     * @liuchunxu
     * @2016-06-22 15:55
     */
    void ld(String DistributionID);

    /**
     * 退单
     *
     * @liuchunxu
     * @2016-06-22 16:02
     */
    void td(Apply apply);

    /**
     * 获取存档维修详情
     *
     * @liuchunxu
     * @2016-06-22 16:13
     */
    void getCdWxEntity(String RepairID);

    /**
     * 签到
     *
     * @liuchunxu
     * @2016-06-22 16:11
     */
    void checkIn(WorkCheck workCheck);

    /**
     * 签退
     *
     * @liuchunxu
     * @2016-06-22 16:11
     */
    void checkOut(WorkCheck workCheck);

    /**
     * 查询签到签退历史
     *
     * @liuchunxu
     * @2016-06-22 16:12
     */
    void getCheckHistory(String ObjectID);
}
