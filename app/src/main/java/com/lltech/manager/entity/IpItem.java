package com.lltech.manager.entity;

import org.litepal.crud.DataSupport;

/**
 * @ClassName(类名) : IpItem
 * @Description(描述) : 请填入描述
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年04月19日 15:12
 */
public class IpItem extends DataSupport {
    public static final String Y = "y";
    public static final String N = "n";
    private String ip;
    private String port;
    private String choose;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }
}
