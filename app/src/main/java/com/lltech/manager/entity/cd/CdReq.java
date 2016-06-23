package com.lltech.manager.entity.cd;

import com.lltech.manager.entity.Req;

/**
 * @ClassName(类名) : CdReq
 * @Description(描述) : 存档请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 16:18
 */
public class CdReq extends Req {
    private String FaultReportID;
    private String DistributionID;
    private String RepairUsers;
    private String RepairDesc;

    public String getFaultReportID() {
        return FaultReportID;
    }

    public void setFaultReportID(String faultReportID) {
        FaultReportID = faultReportID;
    }

    public String getDistributionID() {
        return DistributionID;
    }

    public void setDistributionID(String distributionID) {
        DistributionID = distributionID;
    }

    public String getRepairUsers() {
        return RepairUsers;
    }

    public void setRepairUsers(String repairUsers) {
        RepairUsers = repairUsers;
    }

    public String getRepairDesc() {
        return RepairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        RepairDesc = repairDesc;
    }
}
