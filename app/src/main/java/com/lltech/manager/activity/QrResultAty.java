package com.lltech.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.activity.eq.EqInfoAty;
import com.lltech.manager.common.Mark;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.entity.eq.EqReq;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

/**
 * @ClassName(类名) : QrResultAty
 * @Description(描述) : 二维码扫描结果
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月16日 16:06
 */
public class QrResultAty extends BaseActivity {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private Button bx, btn_sign_in, btn_sign_out;
    private RelativeLayout rela_admin;
    private Eq eq;
    private TextView txt1, txt2;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_qr_result);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        bx = $(R.id.bx);
        rela_admin = $(R.id.rela_admin);
        btn_sign_in = $(R.id.btn_sign_in);
        btn_sign_out = $(R.id.btn_sign_out);
        txt1 = $(R.id.txt1);
        txt2 = $(R.id.txt2);
    }

    @Override
    public void initListeners() {
        topBar.getLeftBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.setMark(Mark.BX_DETAIL);
                application.setOperMark(Mark.OperMark.ADD);
                Intent intent = new Intent(application, CommonAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("eq",eq);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        rela_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(application, EqInfoAty.class);
                intent.putExtra("eq", eq);
                startActivity(intent);
            }
        });
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //签到操作
            }
        });
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //签退操作
            }
        });
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        eq = (Eq) intent.getSerializableExtra("eq");
        getEq();
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        topBar.setTopText("扫描信息");
    }

    private void getEq() {
        String url = UrlCons.url(UrlCons.EquipmentService.GET_ONE);
        EqReq req = new EqReq();
        req.setEquipmentNo(eq.getEquipmentNo());
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                eq = JsonUtils.jsonToEntity(response, Eq.class);
                if (eq != null) {
                    txt1.setText(eq.getEquipmentName());
                    txt2.setText("设备编号：" + eq.getEquipmentNo());
                }
                stopProgressDialog();
            }

            @Override
            public void onFail(Data data) {
                Msg.showError(application, VolleyHttp.errorInfo(data));
                stopProgressDialog();
            }

            @Override
            public void onError(VolleyError volleyError) {
                Msg.showError(application, getString(R.string.net_error));
                stopProgressDialog();
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                new LoginManager().goToLogin(QrResultAty.this, LoginAty.class, true);
            }
        });
    }

}
