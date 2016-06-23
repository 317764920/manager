package com.lltech.manager.biz;

import com.lltech.manager.biz.base.IBiz;
import com.lltech.manager.entity.user.User;

/**
 * @ClassName(类名) : ILogin
 * @Description(描述) : 登录接口
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 09:39
 */
public interface ILogin extends IBiz {
    /**
     *
     * 登录
     * @liuchunxu
     * @2016-06-07 10:44
     *
     */
    void login(String loginUsername, String loginPwd);

    /**
     *
     * 加载项目数据
     * @liuchunxu
     * @2016-06-07 10:44
     *
     */
    void initProjectData(User user);
}
