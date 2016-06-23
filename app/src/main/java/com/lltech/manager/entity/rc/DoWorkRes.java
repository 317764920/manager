package com.lltech.manager.entity.rc;

import com.lltech.manager.entity.rc.DoWork;

import java.io.Serializable;
import java.util.LinkedList;

public class DoWorkRes implements Serializable {

    private static final long serialVersionUID = 1L;

    private LinkedList<DoWork> DataList;

    public LinkedList<DoWork> getDataList() {
        return DataList;
    }

    public void setDataList(LinkedList<DoWork> dataList) {
        DataList = dataList;
    }
}
