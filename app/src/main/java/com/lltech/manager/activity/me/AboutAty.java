package com.lltech.manager.activity.me;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.UpdateManager;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.biz.impl.BaseBiz;
import com.lltech.manager.entity.AppVersion;
import com.lltech.manager.view.AboutView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

public class AboutAty extends BaseActivity implements OnClickListener, AboutView {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private TextView version_text, func, help, check_update;
    private BaseBiz meBiz = new BaseBiz(this);

    @Override
    public void setContentView() {
        setContentView(R.layout.detail_me_about);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        version_text = $(R.id.version_text);
        func = $(R.id.func);
        help = $(R.id.help);
        check_update = $(R.id.check_update);
    }

    @Override
    public void initListeners() {
        func.setOnClickListener(this);
        help.setOnClickListener(this);
        check_update.setOnClickListener(this);
        topBar.getLeftBtn().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        version_text.setText("v" + getVersion());
    }

    @Override
    public void initConfig() {
        topBar.setTopText("关于我们");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.func:
                break;
            case R.id.help:
                break;
            case R.id.check_update:
                meBiz.checkUpdate(getVersion());
                break;
        }
    }

    @Override
    public void onHasNewVersion(AppVersion appVersion) {
        String apkUrl = appVersion.getAttachmentList().get(0).getAttachmentUrl();
        UpdateManager manager = new UpdateManager(AboutAty.this, apkUrl);
        manager.checkUpdateInfo("新版本" + appVersion.getVersionNumber(), appVersion.getDescription());
    }

    @Override
    public void onNoNewVersion() {
        Toast.makeText(application, "已是最新版本", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(Data data) {
        Msg.showError(application, "已是最新版本");
    }

    @Override
    public void onError(VolleyError volleyError) {
        Msg.showError(application, getString(R.string.net_error));
    }

    @Override
    public void onTokenError() {
        new LoginManager().goToLogin(AboutAty.this, LoginAty.class, true);
    }
}
