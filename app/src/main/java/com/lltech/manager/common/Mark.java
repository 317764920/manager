package com.lltech.manager.common;

/**
 * @ClassName(类名) : Mark
 * @Description(描述) : 标记常量
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 10:52
 */
public class Mark {
    /**
     * 报修单详情
     */
    public static final int BX_DETAIL = 1;
    /**
     * 维修单详情
     */
    public static final int WX_DETAIL = 2;
    /**
     * 领单详情
     * */
    public static final int LD_DETAIL = 3;
    /**
     * 退单详情
     * */
    public static final int TD_DETAIL = 4;
    /**
     *派工详情
     * */
    public static final int PG_DETAIL = 5;
    /**
     *存档详情
     * */
    public static final int CD_DETAIL = 6;
    /**
     *存档二级详情
     * */
    public static final int CD_SECOND_DETAIL = 7;
    /**
     * 完工确认
     * */
    public static final int WG_DETAIL = 8;
    /**
     * 通知公告
     */
    public static final int MSG_TZ = 9;
    /**
     * 个人消息
     */
    public static final int MSG_GR = 10;
    /**
     * 预警消息
     */
    public static final int MSG_YJ = 11;
    /**
     * 报警消息
     */
    public static final int MSG_BJ = 12;

    /**
     * 操作标记
     */
    public class OperMark{
        /**
         * 添加
         * */
        public static final int ADD = 1000;
        /**
         * 编辑
         * */
        public static final int EDIT = 1001;
    }
}
