package com.lltech.manager.entity.widget;

import java.io.Serializable;

/**
 * @ClassName(类名) : PopMenuItem
 * @Description(描述) : popmenu菜单项
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月11日 11:07
 */
public class PopMenuItem implements Serializable{
    private int imgRes;
    private String text;

    public PopMenuItem(int imgRes, String text) {
        this.imgRes = imgRes;
        this.text = text;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
