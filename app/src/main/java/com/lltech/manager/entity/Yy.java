package com.lltech.manager.entity;

import org.litepal.crud.DataSupport;

/**
 * @ClassName(类名) : Yy
 * @Description(描述) : 运营首页GridView
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月13日 17:08
 */
public class Yy extends DataSupport{
    private String title;
    private String entry;

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
