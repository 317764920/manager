package com.lltech.manager.entity.wg;

import com.lltech.manager.entity.Req;

/**
 * @ClassName(类名) : WgReq
 * @Description(描述) : 完工请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月12日 16:19
 */
public class WgReq extends Req {

    private static final long serialVersionUID = 1L;

    private String DistributionID;
    private String ReviewedDesc;
    private String State;

    public String getDistributionID() {
        return DistributionID;
    }

    public void setDistributionID(String distributionID) {
        DistributionID = distributionID;
    }

    public String getReviewedDesc() {
        return ReviewedDesc;
    }

    public void setReviewedDesc(String reviewedDesc) {
        ReviewedDesc = reviewedDesc;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
