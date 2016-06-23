package com.lltech.manager.biz;

import com.lltech.manager.biz.base.IBiz;
import com.lltech.manager.entity.eq.Eq;

/**
 * @ClassName(类名) : IEq
 * @Description(描述) : 设备档案接口
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 11:00
 */
public interface IEq extends IBiz {
    /**
     * 获取列表
     *
     * @liuchunxu
     * @2016-06-07 11:47
     */
    void getList(int pageType, String reqState, String systemCode, String edit_EquipmentNo, String edit_EquipmentName);

    /**
     * 维修详情
     */
    void getEntity(Eq eq);
}
