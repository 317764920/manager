package com.lltech.manager.activity.me;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.biz.impl.BaseBiz;
import com.lltech.manager.view.PwdView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class PwdAty extends BaseActivity implements OnClickListener, ValidationListener, PwdView {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    @NotEmpty(message = "不能为空")
    private EditText OldPwd;
    @Password(message = "两次输入不一致")
    @Length(min = 6, message = "请输入至少6位")
    private EditText NewPwd;
    @ConfirmPassword(message = "两次输入不一致")
    @Length(min = 6, message = "请输入至少6位")
    private EditText NewPwdConfirm;
    private Button save;
    private BaseBiz meBiz = new BaseBiz(this);

    @Override
    public void setContentView() {
        setContentView(R.layout.detail_me_pwd);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        OldPwd = $(R.id.OldPwd);
        NewPwd = $(R.id.NewPwd);
        NewPwdConfirm = $(R.id.NewPwdConfirm);
        save = $(R.id.save);
    }

    @Override
    public void initListeners() {
        save.setOnClickListener(this);
        validator.setValidationListener(this);
        topBar.getLeftBtn().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public void initConfig() {
        topBar.setTopText("修改密码");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                validator.validate();
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {
        startProgressDialog();
        meBiz.updatePwd(ViewUtils.getText(OldPwd), ViewUtils.getText(NewPwd));
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(ViewUtils.setErrorValidateMsg(message));
            } else if (view instanceof TextView) {
                ((TextView) view).setError(ViewUtils.setErrorValidateMsg(message));
            }
        }
    }

    @Override
    public void onSuccess() {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                new LoginManager().goToLoginNoTip(PwdAty.this, LoginAty.class, true);
            }
        });
    }

    @Override
    public void onPwdError() {
        Msg.showError(application, "当前密码错误");
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

    @Override
    public void onTokenError() {
        new LoginManager().goToLogin(PwdAty.this, LoginAty.class, true);
    }
}
