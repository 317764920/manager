package com.lltech.manager.fragment.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.activity.bx.BxListAty;
import com.lltech.manager.activity.bx.WxListAty;
import com.lltech.manager.activity.eq.EqListAty;
import com.lltech.manager.activity.pg.PgListAty;
import com.lltech.manager.widget.TopBar;

/**
 * @ClassName(类名) : YyMenuFragment
 * @Description(描述) : 菜单
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:13
 */
public class YyMenuFragment extends BaseFragment implements View.OnClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private TextView menu_by_item1, menu_by_item2, menu_by_item3, menu_by_item4, menu_by_item5,
            menu_sb_item1, menu_sb_item2, menu_sb_item3, menu_sb_item4, menu_sb_item5, menu_sb_item6,
            menu_sb_item7, menu_wh_item1, menu_wh_item2, menu_wh_item4, menu_yx_item1, menu_yx_item2,
            menu_yx_item3, menu_yx_item4, menu_yx_item5, menu_yx_item6, menu_yx_item7, menu_sj_item1,
            menu_sj_item2, menu_sj_item4;

    @Override
    public int setContentView() {
        return R.layout.yy_tab_menu;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        menu_by_item1 = $(R.id.menu_by_item1);
        menu_by_item2 = $(R.id.menu_by_item2);
        menu_by_item3 = $(R.id.menu_by_item3);
        menu_by_item4 = $(R.id.menu_by_item4);
        menu_by_item5 = $(R.id.menu_by_item5);
        menu_sb_item1 = $(R.id.menu_sb_item1);
        menu_sb_item2 = $(R.id.menu_sb_item2);
        menu_sb_item3 = $(R.id.menu_sb_item3);
        menu_sb_item4 = $(R.id.menu_sb_item4);
        menu_sb_item5 = $(R.id.menu_sb_item5);
        menu_sb_item6 = $(R.id.menu_sb_item6);
        menu_sb_item7 = $(R.id.menu_sb_item7);
        menu_wh_item1 = $(R.id.menu_wh_item1);
        menu_wh_item2 = $(R.id.menu_wh_item2);
        menu_wh_item4 = $(R.id.menu_wh_item4);
        menu_yx_item1 = $(R.id.menu_yx_item1);
        menu_yx_item2 = $(R.id.menu_yx_item2);
        menu_yx_item3 = $(R.id.menu_yx_item3);
        menu_yx_item4 = $(R.id.menu_yx_item4);
        menu_yx_item5 = $(R.id.menu_yx_item5);
        menu_yx_item6 = $(R.id.menu_yx_item6);
        menu_yx_item7 = $(R.id.menu_yx_item7);
        menu_sj_item1 = $(R.id.menu_sj_item1);
        menu_sj_item2 = $(R.id.menu_sj_item2);
        menu_sj_item4 = $(R.id.menu_sj_item4);
    }

    @Override
    public void initListener() {
        menu_by_item1.setOnClickListener(this);
        menu_by_item2.setOnClickListener(this);
        menu_by_item3.setOnClickListener(this);
        menu_by_item4.setOnClickListener(this);
        menu_by_item5.setOnClickListener(this);
        menu_sb_item1.setOnClickListener(this);
        menu_sb_item2.setOnClickListener(this);
        menu_sb_item3.setOnClickListener(this);
        menu_sb_item4.setOnClickListener(this);
        menu_sb_item5.setOnClickListener(this);
        menu_sb_item6.setOnClickListener(this);
        menu_sb_item7.setOnClickListener(this);
        menu_wh_item1.setOnClickListener(this);
        menu_wh_item2.setOnClickListener(this);
        menu_wh_item4.setOnClickListener(this);
        menu_yx_item1.setOnClickListener(this);
        menu_yx_item2.setOnClickListener(this);
        menu_yx_item3.setOnClickListener(this);
        menu_yx_item4.setOnClickListener(this);
        menu_yx_item5.setOnClickListener(this);
        menu_yx_item6.setOnClickListener(this);
        menu_yx_item7.setOnClickListener(this);
        menu_sj_item1.setOnClickListener(this);
        menu_sj_item2.setOnClickListener(this);
        menu_sj_item4.setOnClickListener(this);
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
            case R.id.menu_sb_item1: {
                //通风设备
                intent = new Intent(application, EqListAty.class);
                Bundle bundle = new Bundle();
                bundle.putString("systemCode", "TF_System");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.menu_sb_item2: {
                //照明设备
                intent = new Intent(application, EqListAty.class);
                Bundle bundle = new Bundle();
                bundle.putString("systemCode", "ZM_System");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.menu_sb_item3: {
                //电话设备
                intent = new Intent(application, EqListAty.class);
                Bundle bundle = new Bundle();
                bundle.putString("systemCode", "Phone_System");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.menu_sb_item4: {
                //火灾设备
                intent = new Intent(application, EqListAty.class);
                Bundle bundle = new Bundle();
                bundle.putString("systemCode", "HZ_System");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.menu_sb_item5: {
                //广播设备
                intent = new Intent(application, EqListAty.class);
                Bundle bundle = new Bundle();
                bundle.putString("systemCode", "Notice_System");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.menu_sb_item6: {
                //CCTV设备
                intent = new Intent(application, EqListAty.class);
                Bundle bundle = new Bundle();
                bundle.putString("systemCode", "CCTV_System");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.menu_sb_item7: {
                //交通设备
                intent = new Intent(application, EqListAty.class);
                Bundle bundle = new Bundle();
                bundle.putString("systemCode", "Trafic_System");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.menu_wh_item1:
                //报修单
                intent = new Intent(application, BxListAty.class);
                startActivity(intent);
                break;
            case R.id.menu_wh_item2:
                //维修单
                intent = new Intent(application, WxListAty.class);
                startActivity(intent);
                break;
            case R.id.menu_wh_item4:
                //派工列表
                intent = new Intent(application, PgListAty.class);
                startActivity(intent);
                break;
            case R.id.menu_yx_item1:
                break;
            case R.id.menu_yx_item2:
                break;
            case R.id.menu_yx_item4:
                break;
            case R.id.menu_yx_item5:
                break;
            case R.id.menu_yx_item6:
                break;
            case R.id.menu_yx_item7:
                break;
            case R.id.menu_sj_item1:
                break;
            case R.id.menu_sj_item2:
                break;
            case R.id.menu_sj_item3:
                break;
            case R.id.menu_sj_item4:
                break;
        }
    }
}
