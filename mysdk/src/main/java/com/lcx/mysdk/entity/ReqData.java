package com.lcx.mysdk.entity;

import android.util.Log;

import com.lcx.mysdk.utils.AppData;
import com.lcx.mysdk.utils.Code;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;

import java.io.Serializable;

/**
 * @ClassName(类名) : ReqData
 * @Description(描述) : 请求对象实体
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:30:58
 */
public class ReqData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object data;

    public ReqData(Object data) {
        super();
        Log.d("Code", AppData.getCode());
        if (CommonUtil.isEmpty(AppData.getCode())) {
            this.data = Code.Encrypt(JsonUtils.stringify(data), "1234567890abcDEF");
        } else {
            this.data = Code.Encrypt(JsonUtils.stringify(data), AppData.getCode());
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        Log.d("Code", AppData.getCode());
        if (CommonUtil.isEmpty(AppData.getCode())) {
            this.data = Code.Encrypt(JsonUtils.stringify(data), "1234567890abcDEF");
        } else {
            this.data = Code.Encrypt(JsonUtils.stringify(data), AppData.getCode());
        }
    }
}
