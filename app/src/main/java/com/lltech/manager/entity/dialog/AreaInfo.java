package com.lltech.manager.entity.dialog;

import java.io.Serializable;

public class AreaInfo implements Serializable {

    private String AreaID;
    private String AreaName;

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }
}
