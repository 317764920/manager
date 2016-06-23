package com.lltech.manager.fragment.index;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.utils.CommonUtil;
import com.lltech.manager.R;
import com.lltech.manager.activity.me.AboutAty;
import com.lltech.manager.activity.me.ConfigAty;
import com.lltech.manager.activity.me.PwdAty;
import com.lltech.manager.activity.me.RcListAty;
import com.lltech.manager.activity.me.UserInfoAty;
import com.lltech.manager.common.Action;
import com.lltech.manager.entity.project.Project;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.util.MyData;
import com.lltech.manager.widget.TopBar;

/**
 * @ClassName(类名) : MeFragment
 * @Description(描述) : 运营端个人中心
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月12日 14:51
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private TextView txt_admin, txt_pwd, txt_schedule, txt_config, txt_me;
    private Project project;
    private User user;
    private StringBuffer sb = new StringBuffer();
    private UpdateUIBroadcastReceiver broadcastReceiver;

    @Override
    public int setContentView() {
        return R.layout.yy_tab_me;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        txt_admin = $(R.id.txt_admin);
        txt_pwd = $(R.id.txt_pwd);
        txt_schedule = $(R.id.txt_schedule);
        txt_config = $(R.id.txt_config);
        txt_me = $(R.id.txt_me);
    }

    @Override
    public void initListener() {
        txt_admin.setOnClickListener(this);
        txt_pwd.setOnClickListener(this);
        txt_schedule.setOnClickListener(this);
        txt_config.setOnClickListener(this);
        txt_me.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.LEFT, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        topBar.setTopText("我的");
        // 动态注册广播更新界面UI
        IntentFilter filter = new IntentFilter();
        filter.addAction(Action.ACTION_UPDATEUI);
        broadcastReceiver = new UpdateUIBroadcastReceiver();
        getActivity().registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void initData() {
//        show();
        project = MyData.getProject();
        user = MyData.getUser(User.class);
        sb.setLength(0);
        if (CommonUtil.isNotEmpty(user)) {
            sb.append(user.getUserName());
            sb.append("\n");
            sb.append(user.getCompanyName());
        }
        sb.append("\n");
        if (CommonUtil.isNotEmpty(project)) {
            sb.append(project.getProjectName());
        }
        txt_admin.setText(sb.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_admin: {
                //个人信息
                Intent intent = new Intent(application, UserInfoAty.class);
                startActivity(intent);
                break;
            }
            case R.id.txt_schedule: {
                //我的日程
                Intent intent = new Intent(application, RcListAty.class);
                startActivity(intent);
                break;
            }
            case R.id.txt_pwd: {
                //密码修改
                Intent intent = new Intent(application, PwdAty.class);
                startActivity(intent);
                break;
            }
            case R.id.txt_config: {
                //设置
                Intent intent = new Intent(application, ConfigAty.class);
                startActivity(intent);
                break;
            }
            case R.id.txt_me: {
                //关于我们
                Intent intent = new Intent(application, AboutAty.class);
                startActivity(intent);
                break;
            }
        }
    }

    /**
     * @ClassName(类名) : UpdateUIBroadcastReceiver
     * @Description(描述) : 更新UI
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年06月02日 17:22
     */
    public class UpdateUIBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//        textView.setText(String.valueOf(intent.getExtras().getInt("count")));
            Log.d("个人中心接收到了广播了", "----------------------------------------");
            initData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销广播
        getActivity().unregisterReceiver(broadcastReceiver);
    }
}
