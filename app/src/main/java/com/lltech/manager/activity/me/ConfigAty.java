package com.lltech.manager.activity.me;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.view.AudioRecordButton;
import com.lcx.mysdk.view.MyDialog;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.common.Action;
import com.lltech.manager.entity.project.Project;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.Push;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import org.androidpn.client.Notifier;

import java.io.File;
import java.util.List;

public class ConfigAty extends BaseActivity implements OnClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private MyDialog dialog;
    private LinearLayout account;
    private TextView project, clear_cache, exit_login;
    private SwitchButton open;
    private List<Project> list;
    private MyDialog myDialog;
    private Notifier notifier = new Notifier(application);

    @Override
    public void setContentView() {
        setContentView(R.layout.detail_me_config);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        account = $(R.id.account);
        project = $(R.id.project);
        clear_cache = $(R.id.clear_cache);
        exit_login = $(R.id.exit_login);
        open = $(R.id.open);
    }

    @Override
    public void initListeners() {
        account.setOnClickListener(this);
        project.setOnClickListener(this);
        clear_cache.setOnClickListener(this);
        exit_login.setOnClickListener(this);
        open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                notifier.setNotificationEnabled(isChecked);
            }
        });
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
        topBar.setTopText("设置");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        open.setChecked(notifier.isNotificationEnabled());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account:
                Intent intent = new Intent(application, AccountAty.class);
                startActivity(intent);
                break;
            case R.id.project:
                list = MyData.getProjects();
                if (CommonUtil.isNotEmpty(list)) {
                    //弹出dialog选择项目
                    chooseProject();
                }
                break;
            case R.id.clear_cache:
                //清空缓存
                myDialog = new MyDialog(ConfigAty.this).setTitle("温馨提示：").setMessage("确定要清空缓存吗？").setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File file = new File(AudioRecordButton.dir);
                        deleteFile(file);
                        Msg.showSuccess(application, "清空缓存成功");
                        myDialog.dismiss();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
                break;
            case R.id.exit_login:
                MyData.setProject("");
                MyData.setProjects("");
                MyData.setUser("");
                MyData.setToken("");
                Push.stop(application);
                new LoginManager().goToLoginNoTip(ConfigAty.this, LoginAty.class, true);
                break;
        }
    }

    private void chooseProject() {
        View view = getLayoutInflater().inflate(R.layout.dialog_project, null);
        ListView project_list = (ListView) view.findViewById(R.id.project_list);
        QuickAdapter<Project> adapter = new QuickAdapter<Project>(application, R.layout.item_dialog_project, list) {
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
                if (project.getProjectID().equals(MyData.getProject().getProjectID())) {
                    //如果点击的是同一个项目
                    Toast.makeText(application, "您正在此项目中", Toast.LENGTH_SHORT).show();
                    return;
                }
                MyData.setProject(JsonUtils.stringify(project));
                Intent intent = new Intent(Action.ACTION_UPDATEUI);
                sendBroadcast(intent);
                dialog.dismiss();
                dialog = null;
            }
        });
        dialog = new MyDialog(this).setTitle("选择项目").setContentView(view).setPositiveButton(getString(R.string.bottom_cancel), new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 删除文件夹
     */
    private void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    this.deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }
}
