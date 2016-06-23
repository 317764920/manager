package com.lltech.manager.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lcx.mysdk.activity.BaseFragmentActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.utils.DensityUtils;
import com.lcx.mysdk.view.BadgeView;
import com.lltech.manager.R;
import com.lltech.manager.common.Action;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.fragment.index.JsHomeFragment;
import com.lltech.manager.fragment.index.JsMenuFragment;
import com.lltech.manager.fragment.index.LeadHomeFragment;
import com.lltech.manager.fragment.index.MeFragment;
import com.lltech.manager.fragment.index.MsgFragment;
import com.lltech.manager.fragment.index.YyHomeFragment;
import com.lltech.manager.fragment.index.YyMenuFragment;
import com.lltech.manager.fragment.index.YzHomeFragment;
import com.lltech.manager.fragment.index.YzMenuFragment;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.MyResultCode;
import com.lltech.manager.util.Push;
import com.lltech.manager.widget.Msg;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName(类名) : MainAty
 * @Description(描述) : 碎片框架界面
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月19日 上午11:31:26
 */
public class MainAty extends BaseFragmentActivity implements OnClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private Fragment tab_home, tab_msg, tab_menu, tab_me;
    private LinearLayout tab_home_btn, tab_msg_btn, tab_menu_btn, tab_me_btn, tab_home_badge, tab_msg_badge;
    private ImageView tab_home_img, tab_msg_img, tab_menu_img, tab_me_img;
    private TextView tab_home_text, tab_msg_text, tab_menu_text, tab_me_text;
    private BadgeView msg;
    private ScaleAnimation anim;
    private static boolean isExit = false;
    private UpdateUIBroadcastReceiver broadcastReceiver;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        tab_home_btn = $(R.id.tab_home_btn);
        tab_msg_btn = $(R.id.tab_msg_btn);
        tab_menu_btn = $(R.id.tab_menu_btn);
        tab_me_btn = $(R.id.tab_me_btn);
        tab_home_badge = $(R.id.tab_home_badge);
        tab_msg_badge = $(R.id.tab_msg_badge);
        tab_home_img = $(R.id.tab_home_img);
        tab_msg_img = $(R.id.tab_msg_img);
        tab_menu_img = $(R.id.tab_menu_img);
        tab_me_img = $(R.id.tab_me_img);
        tab_home_text = $(R.id.tab_home_text);
        tab_msg_text = $(R.id.tab_msg_text);
        tab_menu_text = $(R.id.tab_menu_text);
        tab_me_text = $(R.id.tab_me_text);
        /**
         * 可以设置一个动画
         */
        anim = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f);
        anim.setDuration(1000);// 设置动画持续时间为3秒
        anim.setFillAfter(true);// 设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
        anim.setInterpolator(new BounceInterpolator());

//        home = new BadgeView(this, tab_home_badge);// 创建一个BadgeView对象，view为你需要显示提醒的控件
//        home.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 显示的位置.中间
//        home.setBadgeMargin(0, 0); // 水平和竖直方向的间距
//        home.setText("99"); // 显示类容
//        home.setTextSize(DensityUtils.sp2px(this, 5)); // 文本大小
//        home.show(anim);

        msg = new BadgeView(this, tab_msg_badge);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        msg.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 显示的位置.中间
        msg.setBadgeMargin(0, 0); // 水平和竖直方向的间距
        msg.setText("28"); // 显示类容
        msg.setTextSize(DensityUtils.sp2px(this, 5)); // 文本大小
        msg.show(anim);
    }

    @Override
    public void initListeners() {
        tab_home_btn.setOnClickListener(this);
        tab_msg_btn.setOnClickListener(this);
        tab_menu_btn.setOnClickListener(this);
        tab_me_btn.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initConfig() {
        setSelect(0);
        // 动态注册广播更新界面UI
        IntentFilter filter = new IntentFilter();
        filter.addAction(Action.ACTION_UPDATEUI);
        broadcastReceiver = new UpdateUIBroadcastReceiver();
        registerReceiver(broadcastReceiver, filter);
        Push.start(application, MyData.getUser(User.class).getUserAccount());
    }

    @Override
    public void onClick(View v) {
        reset();
        switch (v.getId()) {
            case R.id.tab_home_btn:
                setSelect(0);
                break;
            case R.id.tab_msg_btn:
                setSelect(1);
                break;
            case R.id.tab_menu_btn:
                setSelect(2);
                break;
            case R.id.tab_me_btn:
                setSelect(3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyResultCode.SCANNIN_GREQUEST_CODE:
                if (data != null) {
                    // 处理结果
                    String result = data.getExtras().getString("result");
                    if (result != null && result.substring(0, 1).equals("E")) {
                        Intent intent = new Intent(this, QrResultAty.class);
                        Eq eq = new Eq();
                        eq.setEquipmentNo(result);
                        intent.putExtra("eq", eq);
                        startActivity(intent);
                    } else {
                        Msg.showError(application, "无效的二维码");
                    }
                }
                break;
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
            Log.d("碎片框架接收到了广播了", "----------------------------------------");
            Push.start(application, MyData.getUser(User.class).getUserAccount());
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            tr.remove(tab_home);
            tr.remove(tab_menu);
            tab_home = null;
            tab_menu = null;
            tab_home_btn.callOnClick();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private void setSelect(int i) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        hide(tr);
        switch (i) {
            case 0:
                tab_home_img.setImageResource(R.drawable.tab_home_s);
                tab_home_text.setTextColor(getResources().getColor(R.color.red));
                if (tab_home == null) {
                    switch (MyData.getUser(User.class).getUserType()) {
                        case User.JS:
                            //技术端
                            tab_home = new JsHomeFragment();
                            break;
                        case User.YZ:
                            //业主端
                            tab_home = new YzHomeFragment();
                            break;
                        case User.LD:
                            //领导端
                            tab_home = new LeadHomeFragment();
                            break;
                        default:
                            //运营端
                            tab_home = new YyHomeFragment();
                            break;
                    }
                    tr.add(R.id.content, tab_home);
                } else {
                    tr.show(tab_home);
                }
                break;
            case 1:
                tab_msg_img.setImageResource(R.drawable.tab_msg_s);
                tab_msg_text.setTextColor(getResources().getColor(R.color.red));
                if (tab_msg == null) {
                    tab_msg = new MsgFragment();
                    tr.add(R.id.content, tab_msg);
                } else {
                    tr.show(tab_msg);
                }
                break;
            case 2:
                tab_menu_img.setImageResource(R.drawable.tab_menu_s);
                tab_menu_text.setTextColor(getResources().getColor(R.color.red));
                if (tab_menu == null) {
                    switch (MyData.getUser(User.class).getUserType()) {
                        case User.JS:
                            //技术端
                            tab_menu = new JsMenuFragment();
                            break;
                        case User.YZ:
                            //业主端
                            tab_menu = new YzMenuFragment();
                            break;
                        case User.LD:
                            //领导端
                            break;
                        default:
                            //运营端
                            tab_menu = new YyMenuFragment();
                            break;
                    }
                    tr.add(R.id.content, tab_menu);
                } else {
                    tr.show(tab_menu);
                }
                break;
            case 3:
                tab_me_img.setImageResource(R.drawable.tab_me_s);
                tab_me_text.setTextColor(getResources().getColor(R.color.red));
                if (tab_me == null) {
                    tab_me = new MeFragment();
                    tr.add(R.id.content, tab_me);
                } else {
                    tr.show(tab_me);
                }
                break;
        }
        tr.commitAllowingStateLoss();
    }

    private void hide(FragmentTransaction tr) {
        if (tab_home != null) {
            tr.hide(tab_home);
        }
        if (tab_msg != null) {
            tr.hide(tab_msg);
        }
        if (tab_menu != null) {
            tr.hide(tab_menu);
        }
        if (tab_me != null) {
            tr.hide(tab_me);
        }
    }

    private void reset() {
        tab_home_img.setImageResource(R.drawable.tab_home);
        tab_msg_img.setImageResource(R.drawable.tab_msg);
        tab_menu_img.setImageResource(R.drawable.tab_menu);
        tab_me_img.setImageResource(R.drawable.tab_me);
        tab_home_text.setTextColor(getResources().getColor(R.color.gray));
        tab_msg_text.setTextColor(getResources().getColor(R.color.gray));
        tab_menu_text.setTextColor(getResources().getColor(R.color.gray));
        tab_me_text.setTextColor(getResources().getColor(R.color.gray));
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            tab_home = null;
            tab_msg = null;
            tab_menu = null;
            tab_me = null;
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return false;
    }
}
