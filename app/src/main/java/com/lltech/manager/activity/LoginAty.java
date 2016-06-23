package com.lltech.manager.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.ActivityManager;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lcx.mysdk.view.MyDialog;
import com.lltech.manager.R;
import com.lltech.manager.biz.impl.LoginBiz;
import com.lltech.manager.entity.project.Project;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.MyResultCode;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.view.LoginView;
import com.lltech.manager.widget.Msg;

import org.androidpn.client.Notifier;

/**
 * @ClassName(类名) : LoginAty
 * @Description(描述) : 登录页面
 * @author(作者) ：zhangyan
 * @date (开发日期)      ：2016年04月07日 18:18
 */
public class LoginAty extends BaseActivity implements LoginView, OnClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private Button loginBtn;
    private TextView url_config;
    private EditText loginUsername, loginPwd;
    private MyDialog dialog;
    private LoginBiz loginBiz = new LoginBiz(this);

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initViews() {
        loginBtn = $(R.id.loginBtn);
        loginUsername = $(R.id.loginUsername);
        loginPwd = $(R.id.loginPwd);
        url_config = $(R.id.url_config);
    }

    @Override
    public void initListeners() {
        loginBtn.setOnClickListener(this);
        url_config.setOnClickListener(this);
    }

    @Override
    public void initData() {
        String url = MyData.getUrl();
        if (CommonUtil.isEmpty(url)) {
            MyData.setUrl(UrlCons.DEFUALT_IP + ":" + UrlCons.DEFUALT_PORT);
        }
    }

    @Override
    public void initConfig() {
        ActivityManager.finishExceptOne(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //IP列表
            case R.id.url_config:
                Intent intent = new Intent(application, IpListAty.class);
                startActivity(intent);
                break;
            case R.id.loginBtn:
                if (!validate()) {
                    return;
                }
                startProgressDialog();
                lDialog.setMessage(getString(R.string.go_logining));
                loginBiz.login(ViewUtils.getText(loginUsername), ViewUtils.getText(loginPwd));
                break;
        }
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
        MyData.setToken("");
        stopProgressDialog();
    }

    @Override
    public void onLoginSuccess(Data data) {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.login_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                //弹出dialog选择项目
                chooseProject();
            }
        });
    }

    /**
     * 验证用户名密码是否为空
     *
     * @liuchunxu
     * @2016-05-23 17:03
     */
    private boolean validate() {
        String name = loginUsername.getText().toString();
        String pwd = loginPwd.getText().toString();
        if (CommonUtil.isEmpty(name)) {
            Toast.makeText(application, getString(R.string.hint_username), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (CommonUtil.isEmpty(pwd)) {
            Toast.makeText(application, getString(R.string.hint_pwd), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void chooseProject() {
        View view = getLayoutInflater().inflate(R.layout.dialog_project, null);
        ListView project_list = (ListView) view.findViewById(R.id.project_list);
        QuickAdapter<Project> adapter = new QuickAdapter<Project>(application, R.layout.item_dialog_project, MyData.getProjects()) {
            @Override
            protected void convert(BaseAdapterHelper helper, Project item) {
                helper.setText(R.id.item_text, item.getProjectName());
            }
        };
        project_list.setAdapter(adapter);
        project_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project project = (Project) parent.getAdapter().getItem(position);
                MyData.setProject(JsonUtils.stringify(project));
                dialog.dismiss();
                Intent intent = new Intent(application, MainAty.class);
                startActivity(intent);
                setResult(MyResultCode.LOGIN_CODE);
                new Notifier(application).setNotificationEnabled(true);
                finish();
            }
        });
        dialog = new MyDialog(this).setTitle("选择项目").setContentView(view);
        dialog.setCancelable(false);
        dialog.show();
    }
}
