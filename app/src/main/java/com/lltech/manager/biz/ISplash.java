package com.lltech.manager.biz;

import com.lltech.manager.biz.base.IBiz;
import com.lltech.manager.entity.user.User;

/**
 * @ClassName(类名) : ISplash
 * @Description(描述) : 启动接口
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 13:13
 */
public interface ISplash extends IBiz {

    /**
     * 初始化数据
     * */
    void initAppData();

    /**
     * 初始化项目数据
     * */
    void initProjectData(User user);
}
