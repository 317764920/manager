package com.lltech.manager.entity;

/**
 * @ClassName(类名) : Voice
 * @Description(描述) : 语音上传类
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月26日 21:42
 */
public class Voice {
    private String TimeLength;
    private String VoicePath;
    private String FaultReportID;

    public String getTimeLength() {
        return TimeLength;
    }

    public void setTimeLength(String timeLength) {
        TimeLength = timeLength;
    }

    public String getVoicePath() {
        return VoicePath;
    }

    public void setVoicePath(String voicePath) {
        VoicePath = voicePath;
    }

    public String getFaultReportID() {
        return FaultReportID;
    }

    public void setFaultReportID(String faultReportID) {
        FaultReportID = faultReportID;
    }
}
