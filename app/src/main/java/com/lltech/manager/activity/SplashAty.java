package com.lltech.manager.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.android.volley.VolleyError;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.LoginManager;
import com.lltech.manager.R;
import com.lltech.manager.biz.impl.SplashBiz;
import com.lltech.manager.util.MyData;
import com.lltech.manager.view.SplashView;

/**
 * @ClassName(类名) : SplashAty
 * @Description(描述) : 启动页
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年04月28日 16:23
 */
public class SplashAty extends BaseActivity implements SplashView {
    private BaseApplication application = BaseApplication.getApplication();
    private SplashBiz splashBiz = new SplashBiz(this);
    private static final int SHOW_TIME_MIN = 2000;
    private static final int CODE = 1;
    private long startTime, loadingTime;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE:// 如果获取数据完毕，就发送此消息
                    loadingTime = System.currentTimeMillis() - startTime;// 计算一下总共花费的时间
                    if (loadingTime < SHOW_TIME_MIN) {// 如果比最小显示时间还短，就延时进入MainActivity，否则直接进入
                        mHandler.postDelayed(goTo, SHOW_TIME_MIN
                                - loadingTime);
                    } else {
                        mHandler.post(goTo);
                    }
                    break;
                default:
                    break;
            }
        }
    };
    //进入下一个Activity
    private Runnable goTo = new Runnable() {

        @Override
        public void run() {
            goToNext();
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_spanish);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {
        startTime = System.currentTimeMillis();
        splashBiz.initAppData();
    }

    @Override
    public void initConfig() {

    }

    private void goToNext() {
        if (CommonUtil.isEmpty(MyData.getToken())) {
            //如果token为空，说明未登录，跳转到登录界面
            new LoginManager().goToLoginNoTip(this, LoginAty.class, true);
        } else {
            Intent intent = new Intent(this, MainAty.class);
            startActivity(intent);
            finish();
        }
    }

    private void sendMsg() {
        Message message = new Message();
        message.what = CODE;
        mHandler.sendMessage(message);
    }

    @Override
    public void onFail(Data data) {
        sendMsg();
    }

    @Override
    public void onError(VolleyError volleyError) {
        sendMsg();
    }

    @Override
    public void onTokenError() {
        sendMsg();
    }

    @Override
    public void onSuccess(Data data) {
        sendMsg();
    }
}
