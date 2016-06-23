package com.lltech.manager.fragment.index;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.activity.bx.BxListAty;
import com.lltech.manager.widget.TopBar;

/**
 * @ClassName(类名) : YzMenuFragment
 * @Description(描述) : 业主端菜单
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:13
 */
public class YzMenuFragment extends BaseFragment implements View.OnClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private TextView menu_wh_item1;

    @Override
    public int setContentView() {
        return R.layout.yz_tab_menu;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        menu_wh_item1 = $(R.id.menu_wh_item1);
    }

    @Override
    public void initListener() {
        menu_wh_item1.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.LEFT, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setTopText("办公");
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu_wh_item1:
                //报修单
                intent = new Intent(application, BxListAty.class);
                startActivity(intent);
                break;
        }
    }
}
