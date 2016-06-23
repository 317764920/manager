package com.lltech.manager.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.utils.ViewUtils;
import com.lltech.manager.R;
import com.lltech.manager.entity.IpItem;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.MyResultCode;
import com.lltech.manager.widget.TopBar;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.IpAddress;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

/**
 * @ClassName(类名) : UrlAddAty
 * @Description(描述) : 配置IP或端口界面
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年04月08日 11:23
 */
public class UrlAddAty extends BaseActivity implements Validator.ValidationListener {
    @IpAddress(message = "格式有误")
    private EditText ip;
    @NotEmpty(message = "不可为空")
    private EditText port;
    private TopBar topBar;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_url_config);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        ip = $(R.id.ip);
        port = $(R.id.port);
    }

    @Override
    public void initListeners() {
        validator.setValidationListener(this);
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int btnType, View view) {
                validator.validate();
            }

            @Override
            public void onLeftClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.getRightBtn2().setBackground(null);
        topBar.getRightBtn2().setText(getString(R.string.confirm));
        topBar.setTopText(getString(R.string.add));
    }

    @Override
    public void onValidationSucceeded() {
        IpItem ipItem = new IpItem();
        ipItem.setIp(ip.getText().toString());
        ipItem.setPort(port.getText().toString());
        ipItem.save();
        setResult(MyResultCode.IP_CODE);
        finish();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(ViewUtils.setErrorValidateMsg(message));
            } else if (view instanceof TextView) {
                ((TextView) view).setError(ViewUtils.setErrorValidateMsg(message));
            }
        }
    }
}
