package com.lltech.manager.activity.msg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.lltech.manager.activity.dialog.EmployeesAty;
import com.lltech.manager.biz.impl.BaseBiz;
import com.lltech.manager.common.DiaLogCode;
import com.lltech.manager.entity.GroupMemberBean;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.MsgSendView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : MsgSendAty
 * @Description(描述) : 给指定人发消息
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月31日 14:29
 */
public class MsgSendAty extends BaseActivity implements View.OnClickListener, MsgSendView {
    private BaseApplication application = BaseApplication.getApplication();
    private TextView RecipientText;
    private Button choose, save;
    private EditText MessageTitle, MessageContent;
    private TopBar topBar;
    private HashMap<String, String> map = new HashMap<String, String>();
    private List<GroupMemberBean> list = new LinkedList<GroupMemberBean>();
    private BaseBiz meBiz = new BaseBiz(this);


    @Override
    public void setContentView() {
        setContentView(R.layout.detail_msg);
    }

    @Override
    public void initViews() {
        RecipientText = $(R.id.RecipientText);
        choose = $(R.id.choose);
        save = $(R.id.save);
        MessageTitle = $(R.id.MessageTitle);
        MessageContent = $(R.id.MessageContent);
        topBar = $(R.id.top);
    }

    @Override
    public void initListeners() {
        choose.setOnClickListener(this);
        save.setOnClickListener(this);
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {

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
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        topBar.setTopText("编辑消息");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose:
                Intent intent = new Intent(application, EmployeesAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) list);
                intent.putExtras(bundle);
                startActivityForResult(intent, DiaLogCode.EMPLOYEE);
                break;
            case R.id.save:
                startProgressDialog();
                meBiz.sendMsg(ViewUtils.getText(MessageTitle), ViewUtils.getText(MessageContent), map.get("RecipientListID"));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case DiaLogCode.EMPLOYEE:
                map.put("RecipientListID", null);
                RecipientText.setText(null);
                list = (List<GroupMemberBean>) data.getSerializableExtra("list");
                StringBuffer recipient = new StringBuffer();
                StringBuffer recipientText = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    GroupMemberBean bean = list.get(i);
                    if (i != 0) {
                        recipient.append(",");
                        recipientText.append(",");
                    }
                    recipient.append(bean.getId());
                    recipientText.append(bean.getName());
                }
                map.put("RecipientListID", recipient.toString());
                RecipientText.setText(recipientText.toString());
                break;
        }
    }

    @Override
    public void onSuccess() {
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
        Msg.showError(application, getString(R.string.net_error));
        stopProgressDialog();
    }

    @Override
    public void onTokenError() {
        new LoginManager().goToLogin(MsgSendAty.this, LoginAty.class, true);
    }
}
