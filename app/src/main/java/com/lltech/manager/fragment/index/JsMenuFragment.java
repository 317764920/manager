package com.lltech.manager.fragment.index;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.activity.bx.BxListAty;
import com.lltech.manager.activity.pg.PgListAty;
import com.lltech.manager.widget.TopBar;

/**
 * @ClassName(类名) : JsMenuFragment
 * @Description(描述) : 技术端菜单
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:13
 */
public class JsMenuFragment extends BaseFragment implements View.OnClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private TextView menu_by_item1, menu_by_item2, menu_by_item3, menu_by_item4, menu_by_item5,
            menu_wh_item1, menu_wh_item4;

    @Override
    public int setContentView() {
        return R.layout.js_tab_menu;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        menu_by_item1 = $(R.id.menu_by_item1);
        menu_by_item2 = $(R.id.menu_by_item2);
        menu_by_item3 = $(R.id.menu_by_item3);
        menu_by_item4 = $(R.id.menu_by_item4);
        menu_by_item5 = $(R.id.menu_by_item5);
        menu_wh_item1 = $(R.id.menu_wh_item1);
        menu_wh_item4 = $(R.id.menu_wh_item4);
    }

    @Override
    public void initListener() {
        menu_by_item1.setOnClickListener(this);
        menu_by_item2.setOnClickListener(this);
        menu_by_item3.setOnClickListener(this);
        menu_by_item4.setOnClickListener(this);
        menu_by_item5.setOnClickListener(this);
        menu_wh_item1.setOnClickListener(this);
        menu_wh_item4.setOnClickListener(this);
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
            case R.id.menu_by_item1:
                break;
            case R.id.menu_by_item2:
                break;
            case R.id.menu_by_item3:
                break;
            case R.id.menu_by_item4:
                break;
            case R.id.menu_by_item5:
                break;
            case R.id.menu_wh_item1:
                //报修单
                intent = new Intent(application, BxListAty.class);
                startActivity(intent);
                break;
            case R.id.menu_wh_item4:
                //派工列表
                intent = new Intent(application, PgListAty.class);
                startActivity(intent);
                break;
        }
    }
}
