package com.lltech.manager.fragment.index;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.view.ViewPagerIndicator;
import com.lltech.manager.R;
import com.lltech.manager.activity.QrResultAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.common.Mark;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.entity.widget.PopMenuItem;
import com.lltech.manager.fragment.index.bottom.JsByFragment;
import com.lltech.manager.fragment.index.bottom.JsWxFragment;
import com.lltech.manager.fragment.index.bottom.JsXjFragment;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.MyResultCode;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.PopupMenu;
import com.lltech.manager.widget.TopBar;
import com.mining.app.zxing.QrCodeActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName(类名) : JsHomeFragment
 * @Description(描述) : 首页
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:09:54
 */
public class JsHomeFragment extends BaseFragment implements OnClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private PopupMenu popupMenu;
    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private List<String> mDatas;
    private List<PopMenuItem> poplist = new ArrayList<PopMenuItem>();

    @Override
    public int setContentView() {
        return R.layout.js_tab_home;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        mViewPager = $(R.id.id_vp);
        mIndicator = $(R.id.id_indicator);
        popupMenu = new PopupMenu(getActivity());
    }

    @Override
    public void initListener() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (whitchBtn) {
                    case TopBar.RIGHT2: {
                        popupMenu.showLocation(topBar.getRightBtn2());
                        // 设置回调监听，获取点击事件
                        popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                            @Override
                            public void onItemClick(PopMenuItem item, int position) {
                                switch (position) {
                                    case 0: {
                                        application.setMark(Mark.BX_DETAIL);
                                        application.setOperMark(Mark.OperMark.ADD);
                                        Intent intent = new Intent(getActivity(), CommonAty.class);
                                        getActivity().startActivity(intent);
                                        break;
                                    }
                                    case 1: {
                                        Intent intent = new Intent(getActivity(), QrCodeActivity.class);
                                        getActivity().startActivityForResult(intent, MyResultCode.SCANNIN_GREQUEST_CODE);
                                        break;
                                    }
                                }
                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onLeftClick(View view) {

            }
        });
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.LEFT, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setTopText("首页");
        initDb();
    }

    @Override
    public void initData() {
        PopMenuItem item1 = new PopMenuItem(R.drawable.pop_bx, "我要报修");
        PopMenuItem item2 = new PopMenuItem(R.drawable.pop_sys, "扫一扫");
        poplist.add(item1);
        poplist.add(item2);
        popupMenu.loadMenu(poplist);
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK: {
                if (data != null) {
                    // 处理结果
                    String result = data.getExtras().getString("result");
                    if (result != null && result.substring(0, 1).equals("E")) {
                        Intent intent = new Intent(application, QrResultAty.class);
                        Eq eq = new Eq();
                        eq.setEquipmentNo(result);
                        intent.putExtra("eq", eq);
                        startActivity(intent);
                    } else {
                        Msg.showError(application, "无效的二维码");
                    }
                }
            }
        }
    }

    private void initDb() {
        mDatas = Arrays.asList("待维修", "待保养", "待巡检");
        // 设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        JsWxFragment f1 = new JsWxFragment();
        JsByFragment f2 = new JsByFragment();
        JsXjFragment f3 = new JsXjFragment();
        mTabContents.add(f1);
        mTabContents.add(f2);
        mTabContents.add(f3);
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);
        // 设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);
    }
}
