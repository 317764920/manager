package com.lltech.manager.entity;

import org.litepal.crud.DataSupport;

/**
 * @ClassName(类名) : 请填入类名
 * @Description(描述) : 请填入描述
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月11日 10:26
 */
public class Test extends DataSupport {
    /**
     *项目名称
     * */
    private String project_name;
    /**
     * 项目状态
     * */
    private String project_state;
    /**
     * 时间
     * */
    private String project_time;

    public String getProject_time() {
        return project_time;
    }

    public void setProject_time(String project_time) {
        this.project_time = project_time;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_state() {
        return project_state;
    }

    public void setProject_state(String project_state) {
        this.project_state = project_state;
    }
}
