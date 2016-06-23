package com.lcx.mysdk.entity;

import java.io.Serializable;

/**
 * @Description(功能描述) : 密钥实体
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016-04-08 13:10
 */
public class CKey implements Serializable {

    private static final long serialVersionUID = 1L;

    private String passWord;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
