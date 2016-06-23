package com.lltech.manager.entity.dialog;

import java.io.Serializable;

/**
 * 设备所在区域
 */
public class StoreyInfo implements Serializable {

    private String StoreyID;
    private String StoreyName;

    public String getStoreyID() {
        return StoreyID;
    }

    public void setStoreyID(String storeyID) {
        StoreyID = storeyID;
    }

    public String getStoreyName() {
        return StoreyName;
    }

    public void setStoreyName(String storeyName) {
        StoreyName = storeyName;
    }
}
