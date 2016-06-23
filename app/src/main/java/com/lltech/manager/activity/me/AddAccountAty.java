package com.lltech.manager.activity.me;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.biz.impl.BaseBiz;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.AccountAddView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @ClassName(类名) : AddAccountAty
 * @Description(描述) : 添加账号
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月31日 14:13
 */
public class AddAccountAty extends BaseActivity implements AccountAddView {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private EditText username, pwd;
    private BaseBiz accountBiz = new BaseBiz(this);

    @Override
    public void setContentView() {
        setContentView(R.layout.detail_me_addaccount);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        username = $(R.id.username);
        pwd = $(R.id.pwd);
    }

    @Override
    public void initListeners() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                if (validate()) {
                    startProgressDialog();
                    lDialog.setMessage("正在添加...");
                    accountBiz.addAccount(ViewUtils.getText(username), ViewUtils.getText(pwd));
                }
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
        topBar.setTopText("添加帐号");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.getRightBtn2().setBackground(null);
        topBar.getRightBtn2().setText(getString(R.string.confirm));
    }

    /**
     * 验证帐号信息
     *
     * @liuchunxu
     * @2016-05-23 17:03
     */
    private boolean validate() {
        String name = username.getText().toString();
        String s_pwd = pwd.getText().toString();
        if (CommonUtil.isEmpty(name)) {
            Toast.makeText(application, getString(R.string.hint_username), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (CommonUtil.isEmpty(s_pwd)) {
            Toast.makeText(application, getString(R.string.hint_pwd), Toast.LENGTH_SHORT).show();
            return false;
        }
        List<User> list = DataSupport.where("UserAccount = ?", name).find(User.class);
        if (CommonUtil.isNotEmpty(list)) {
            Toast.makeText(application, "您已经添加过该帐号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onSuccess(Data data) {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public void onFail(Data data) {
        Msg.showError(application, VolleyHttp.errorInfo(data));
        stopProgressDialog();
    }

    @Override
    public void onError(VolleyError volleyError) {
        Toast.makeText(application, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
        stopProgressDialog();
    }

    @Override
    public void onTokenError() {

    }
}
