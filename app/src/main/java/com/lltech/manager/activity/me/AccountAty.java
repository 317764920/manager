package com.lltech.manager.activity.me;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.view.MyDialog;
import com.lltech.manager.R;
import com.lltech.manager.biz.impl.BaseBiz;
import com.lltech.manager.common.Action;
import com.lltech.manager.common.RequestCode;
import com.lltech.manager.entity.project.Project;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.Push;
import com.lltech.manager.view.AccountView;
import com.lltech.manager.widget.BottomWindow;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @ClassName(类名) : AccountAty
 * @Description(描述) :帐号管理
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月30日 15:48
 */
public class AccountAty extends BaseActivity implements View.OnClickListener, AccountView {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private ListView listView;
    private TextView account_size, addAccount;
    private List<User> list;
    private QuickAdapter<User> adapter;
    private User deleUser;
    private BottomWindow bottomWindow;
    private Button delete, cancel;
    private MyDialog dialog;
    private BaseBiz accountBiz = new BaseBiz(this);

    @Override
    public void setContentView() {
        setContentView(R.layout.detail_me_account);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        listView = $(R.id.listView);
        account_size = $(R.id.account_size);
        addAccount = $(R.id.addAccount);
        bottomWindow = new BottomWindow(AccountAty.this, R.layout.bottom_window_delete);
        delete = bottomWindow.getView(R.id.delete);
        cancel = bottomWindow.getView(R.id.cancel);
    }

    @Override
    public void initListeners() {
        topBar.getLeftBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User item = (User) parent.getAdapter().getItem(position);
                if (item.getUserId().equals(MyData.getUser(User.class).getUserId())) {
                    //如果点击的是同一个帐号
                    return;
                } else {
                    startProgressDialog();
                    lDialog.setMessage("正在切换帐号...");
                    //发送登录请求,获取一次用户信息,再保存到本地数据库
                    accountBiz.changeAccount(item);
//                    item.setChoose(User.Y);
//                    item.save();
//                    MyData.setUser(Json);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleUser = (User) parent.getAdapter().getItem(position);
                if (User.N.equals(deleUser.getChoose())) {
                    bottomWindow.show(listView);
                }
                return true;
            }
        });
        addAccount.setOnClickListener(this);
        delete.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void initData() {
        list = DataSupport.findAll(User.class);
        account_size.setText("您当前共管理" + list.size() + "个帐号");
    }

    @Override
    public void initConfig() {
        topBar.setTopText("帐号管理");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        adapter = new QuickAdapter<User>(application, R.layout.item_account, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, User item) {
                helper.setText(R.id.name, item.getUserName());
                helper.setText(R.id.text, item.getUserAccount());
                if (User.Y.equals(item.getChoose())) {
                    helper.setVisible(R.id.gou, true);
                } else {
                    helper.setVisible(R.id.gou, false);
                }
            }
        };
        listView.setAdapter(adapter);
        ViewUtils.setListViewHeightBasedOnChildren(listView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addAccount:
                Intent intent = new Intent(application, AddAccountAty.class);
                startActivityForResult(intent, RequestCode.ACCOUNT_ADD);
                break;
            case R.id.delete:
                deleUser.delete();
                list = DataSupport.findAll(User.class);
                adapter.replaceAll(list);
                ViewUtils.setListViewHeightBasedOnChildren(listView);
                bottomWindow.dismiss();
                break;
            case R.id.cancel:
                bottomWindow.dismiss();
                break;
        }
    }

    private void chooseProject() {
        View view = getLayoutInflater().inflate(R.layout.dialog_project, null);
        ListView project_list = (ListView) view.findViewById(R.id.project_list);
        QuickAdapter<Project> p_adapter = new QuickAdapter<Project>(application, R.layout.item_dialog_project, MyData.getProjects()) {
            @Override
            protected void convert(BaseAdapterHelper helper, Project item) {
                helper.setText(R.id.item_text, item.getProjectName());
            }
        };
        project_list.setAdapter(p_adapter);
        project_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project project = (Project) parent.getAdapter().getItem(position);
                MyData.setProject(JsonUtils.stringify(project));
                list = DataSupport.findAll(User.class);
                adapter.replaceAll(list);
                ViewUtils.setListViewHeightBasedOnChildren(listView);
                Push.stop(application);
                Intent intent = new Intent(Action.ACTION_UPDATEUI);
                sendBroadcast(intent);
                dialog.dismiss();
                dialog = null;
            }
        });
        dialog = new MyDialog(this).setTitle("选择项目").setContentView(view);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                list = DataSupport.findAll(User.class);
                adapter.replaceAll(list);
                ViewUtils.setListViewHeightBasedOnChildren(listView);
                break;
        }
    }

    @Override
    public void onSuccess(Data data) {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.change_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                chooseProject();
            }
        });
    }

    @Override
    public void onFail(Data data) {
        Msg.showError(application, "该帐号已过期，请删除后重新添加");
        stopProgressDialog();
    }

    @Override
    public void onError(VolleyError volleyError) {
        Toast.makeText(application, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
        stopProgressDialog();
    }

    @Override
    public void onTokenError() {
        Msg.showError(application, "该帐号已过期，请删除后重新添加");
        stopProgressDialog();
    }

}
