package com.lltech.manager.util;

import android.app.Activity;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.entity.OrderCode;
import com.lltech.manager.widget.Msg;

/**
 * @ClassName(类名) : OrderCreator
 * @Description(描述) : 单号生成器
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月26日 11:11
 */
public class OrderCreator {
    /**
     * @throws : void
     * @Description(功能描述) : 获取单号
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年10月16日 下午4:38:49
     */
    public void getOrderCode(final Activity activity, final TextView orderView, String serialNumber) {
        String url = UrlCons.url(UrlCons.AutomaticNoService.GET);
        OrderCode orderCode = new OrderCode();
        orderCode.setSerialNumber(serialNumber);
        ReqData data = new ReqData(orderCode);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                OrderCode orderCode = JsonUtils.jsonToEntity(data.getResponse().toString(), OrderCode.class);
                orderView.setText(orderCode.getAutomaticNo());
            }

            @Override
            public void onFail(Data data) {
                orderView.setText("单号获取失败");
            }

            @Override
            public void onError(VolleyError volleyError) {
                Msg.showError(activity, activity.getString(R.string.net_error));
                orderView.setText("单号获取失败");
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                new LoginManager().goToLogin(activity, LoginAty.class, true);
            }
        });
    }
}
