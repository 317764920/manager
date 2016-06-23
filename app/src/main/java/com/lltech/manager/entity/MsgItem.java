package com.lltech.manager.entity;

import java.io.Serializable;

/**
 * @ClassName(类名) : MsgItem
 * @Description(描述) : 消息界面item（4种）
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月20日 10:46
 */
public class MsgItem implements Serializable {
    private int imgResId;
    private String name;
    private String text;

    public MsgItem(int imgResId, String name, String text) {
        this.imgResId = imgResId;
        this.name = name;
        this.text = text;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
