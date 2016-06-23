package com.lltech.manager.activity.xj;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lcx.mysdk.activity.BaseFragmentActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lltech.manager.R;
import com.lltech.manager.fragment.xj.MiddleFragment;
import com.lltech.manager.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName(类名) : XjAty
 * @Description(描述) : 巡检
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月23日 10:53
 */
public class XjAty extends BaseFragmentActivity implements MiddleFragment.OnButtonClick {

    private BaseApplication application = BaseApplication.getApplication();
    private ViewPager mViewPager;
    private TopBar topBar;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    int position = 0;
    private TextView txt_address, txt_content;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_xj);
    }

    @Override
    public void initViews() {
        mViewPager = $(R.id.id_vp);
        topBar = $(R.id.top);
        txt_address = $(R.id.txt_address);
        txt_content = $(R.id.txt_content);
    }

    @Override
    public void initListeners() {
    }

    @Override
    public void initData() {
        showFragment();
    }

    @Override
    public void initConfig() {
        topBar.getLeftBtn().setVisibility(View.GONE);
        topBar.getRightBtn1().setVisibility(View.GONE);
        topBar.getRightBtn2().setVisibility(View.GONE);
        topBar.setTopText("巡检执行");
    }

    private void showFragment() {
        mTabContents.clear();
        MiddleFragment middleFragment;
        for (int i = 0; i <= 20; i++) {
            middleFragment = new MiddleFragment();
            mTabContents.add(middleFragment);
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }

            @Override
            public int getCount() {
                return mTabContents.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void OnButtonClick(View view, Boolean value) {
        if (value) {
            position++;
            mViewPager.setCurrentItem(position);
        }
    }

}
