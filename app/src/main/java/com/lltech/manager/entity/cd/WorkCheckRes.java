package com.lltech.manager.entity.cd;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @ClassName(类名) : WorkCheckRes
 * @Description(描述) : 签到签退列表
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月03日 14:33
 */
public class WorkCheckRes implements Serializable {

    private LinkedList<WorkCheck> DataList;

    public LinkedList<WorkCheck> getDataList() {
        return DataList;
    }

    public void setDataList(LinkedList<WorkCheck> dataList) {
        DataList = dataList;
    }
}
