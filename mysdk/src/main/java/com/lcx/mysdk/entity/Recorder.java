package com.lcx.mysdk.entity;

/**
 * @ClassName(类名) : Recorder
 * @Description(描述) : 录音音频
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月17日 16:44
 */
public class Recorder {
    private float time;
    private String filePathString;

    public Recorder(float time, String filePathString) {
        super();
        this.time = time;
        this.filePathString = filePathString;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getFilePathString() {
        return filePathString;
    }

    public void setFilePathString(String filePathString) {
        this.filePathString = filePathString;
    }
}
