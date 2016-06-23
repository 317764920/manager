package com.lltech.manager.entity;

import java.io.Serializable;

public class GroupMemberBean implements Serializable {
    private String id;
    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
