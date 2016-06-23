package com.lltech.manager.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.lcx.mysdk.activity.BaseFragmentActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lltech.manager.R;
import com.lltech.manager.common.Mark;
import com.lltech.manager.fragment.msg.BjFragment;
import com.lltech.manager.fragment.msg.GrFragment;
import com.lltech.manager.fragment.msg.TzFragment;
import com.lltech.manager.fragment.msg.YjFragment;

/**
 * @ClassName(类名) : MsgListAty
 * @Description(描述) : 各类消息列表
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 14:11
 */
public class MsgListAty extends BaseFragmentActivity {
    private BaseApplication application = BaseApplication.getApplication();
    private Fragment tzFragment, grFragment, yjFragment, bjFragment;

    @Override
    public void setContentView() {
        setContentView(R.layout.common);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initConfig() {
        setSelect(application.getMark());
    }

    private void setSelect(int mark) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        hide(tr);
        switch (mark) {
            case Mark.MSG_TZ:
                //通知公告
                if (tzFragment == null) {
                    tzFragment = new TzFragment();
                    tr.add(R.id.content, tzFragment);
                } else {
                    tr.show(tzFragment);
                }
                break;
            case Mark.MSG_GR:
                //个人消息
                if (grFragment == null) {
                    grFragment = new GrFragment();
                    tr.add(R.id.content, grFragment);
                } else {
                    tr.show(grFragment);
                }
                break;
            case Mark.MSG_YJ:
                //预警消息
                if (yjFragment == null) {
                    yjFragment = new YjFragment();
                    tr.add(R.id.content, yjFragment);
                } else {
                    tr.show(yjFragment);
                }
                break;
            case Mark.MSG_BJ:
                //报警消息
                if (bjFragment == null) {
                    bjFragment = new BjFragment();
                    tr.add(R.id.content, bjFragment);
                } else {
                    tr.show(bjFragment);
                }
                break;
        }
        tr.commit();
    }

    private void hide(FragmentTransaction tr) {
        if (tzFragment != null) {
            tr.hide(tzFragment);
        }
        if (grFragment != null) {
            tr.hide(grFragment);
        }
        if (yjFragment != null) {
            tr.hide(yjFragment);
        }
        if (bjFragment != null) {
            tr.hide(bjFragment);
        }
    }
}
