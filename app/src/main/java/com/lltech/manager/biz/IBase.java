package com.lltech.manager.biz;

import com.lltech.manager.biz.base.IBiz;
import com.lltech.manager.entity.user.User;

/**
 * @ClassName(类名) : IBase
 * @Description(描述) : 基础业务接口
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月21日 17:02
 */
public interface IBase extends IBiz {
    /**
     * 切换账号
     *
     * @liuchunxu
     * @2016-06-21 17:04
     */
    void changeAccount(User user);

    /**
     * 添加账号
     *
     * @liuchunxu
     * @2016-06-21 17:14
     */
    void addAccount(String username, String pwd);

    /**
     * 检查更新
     *
     * @liuchunxu
     * @2016-06-21 17:31
     */
    void checkUpdate(String version);

    /**
     * 修改密码
     *
     * @liuchunxu
     * @2016-06-21 17:45
     */
    void updatePwd(String OldPwd, String NewPwd);

    /**
     * 获取日程列表
     *
     * @liuchunxu
     * @2016-06-21 17:53
     */
    void getRcList();

    /**
     * 发送消息
     *
     * @liuchunxu
     * @2016-06-22 10:21
     */
    void sendMsg(String MessageTitle, String MessageContent, String RecipientListID);

    /**
     * 获取消息列表
     *
     * @liuchunxu
     * @2016-06-22 16:45
     */
    void getMsgList(int pageType, String MessageType, String RecipientState);

    /**
     * 将消息标记为已读
     *
     * @liuchunxu
     * @2016-06-22 16:52
     */
    void updateMsg(String SysMsgRecipientID);
}
