package com.lltech.manager.activity.eq;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.activity.BaseFragmentActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lcx.mysdk.view.ViewPagerIndicator;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.biz.impl.EqBiz;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.entity.widget.PopMenuItem;
import com.lltech.manager.fragment.info.ByFragment;
import com.lltech.manager.fragment.info.CwFragment;
import com.lltech.manager.fragment.info.FjFragment;
import com.lltech.manager.fragment.info.JbFragment;
import com.lltech.manager.fragment.info.JsFragment;
import com.lltech.manager.fragment.info.ScFragment;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.EqInfoView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.PopupMenu;
import com.lltech.manager.widget.TopBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EqInfoAty extends BaseFragmentActivity implements OnClickListener, EqInfoView {

    private BaseApplication application = BaseApplication.getApplication();
    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;
    private TopBar topBar;
    private PopupMenu popupMenu;
    private TextView content1, content2;
    private ImageView eq_pic, two_pic;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private List<PopMenuItem> poplist = new ArrayList<PopMenuItem>();
    private List<String> mDatas;
    private EqBiz eqBiz = new EqBiz(this);

    @Override
    public void setContentView() {
        setContentView(R.layout.detail_eq);
    }

    @Override
    public void initViews() {
        mViewPager = $(R.id.id_vp);
        mIndicator = $(R.id.id_indicator);
        topBar = $(R.id.top);
        eq_pic = $(R.id.eq_pic);
        two_pic = $(R.id.two_pic);
        content1 = $(R.id.content1);
        content2 = $(R.id.content2);
        popupMenu = new PopupMenu(this);
    }

    @Override
    public void initListeners() {
        two_pic.setOnClickListener(this);
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int btnType, View view) {
                switch (btnType) {
                    case TopBar.RIGHT2: {
                        popupMenu.showLocation(topBar.getRightBtn2());
                        // 设置回调监听，获取点击事件
                        popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                            @Override
                            public void onItemClick(PopMenuItem item, int position) {
//                                Intent intent = new Intent();
                                switch (position) {
                                    case 0: {
                                        //获取保养记录
//                                        intent.setClass(application, MaintenanceRecordListAty.class);
//                                        intent.putExtra("eq", eq);
//                                        startActivity(intent);
                                        break;
                                    }
                                    case 1: {
                                        //获取维修记录
//                                        intent.setClass(application, RepairRecordListAty.class);
//                                        intent.putExtra("eq", eq);
//                                        startActivity(intent);
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
                finish();
            }
        });
    }

    @Override
    public void initData() {
        startProgressDialog();
        PopMenuItem item1 = new PopMenuItem(R.drawable.pop_bx, getString(R.string.eq_info_byList));
        PopMenuItem item2 = new PopMenuItem(R.drawable.pop_sys, getString(R.string.eq_info_wxList));
        poplist.add(item1);
        poplist.add(item2);
        popupMenu.loadMenu(poplist);
        Eq eq = (Eq) getIntent().getSerializableExtra("eq");
        eqBiz.getEntity(eq);
    }

    @Override
    public void initConfig() {
        content2.setMovementMethod(ScrollingMovementMethod.getInstance());
        topBar.setTopText("详情");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnType(TopBar.RIGHT2, TopBar.MORE);
    }

    private void dataToFragment(Eq eq) {
        mDatas = Arrays.asList("基本信息", "生产信息", "财务信息", "技术参数", "保养信息", "附件");
        // 设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        JbFragment jbFragment = new JbFragment();
        ScFragment scFragment = new ScFragment();
        CwFragment cwFragment = new CwFragment();
        JsFragment jsFragment = new JsFragment();
        ByFragment byFragment = new ByFragment();
        FjFragment fjFragment = new FjFragment();

        mTabContents.add(jbFragment);
        mTabContents.add(scFragment);
        mTabContents.add(cwFragment);
        mTabContents.add(jsFragment);
        mTabContents.add(byFragment);
        mTabContents.add(fjFragment);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
        Bundle args = new Bundle();
        args.putSerializable("eq", eq);
        for (Fragment f : mTabContents) {
            f.setArguments(args);
        }
        mViewPager.setAdapter(mAdapter);
        // 设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.two_pic:
//                Intent intent = new Intent(this, ZoomImgAty.class);
//                intent.putExtra("position", 0);
//                List<Attachment> attachmentList = new LinkedList<Attachment>();
//                Attachment attachment = new Attachment();
//                attachment.setAttachmentUrl(eq.getQRCodePath());
//                attachmentList.add(attachment);
//                intent.putExtra("attachmentList", (Serializable) attachmentList);
//                startActivity(intent);
                break;
        }
    }

    @Override
    public void onSuccess(Eq eq) {
        Picasso.with(getApplicationContext()).load(eq.getEquipmentImg()).error(R.drawable.no_pic).placeholder(R.drawable.no_pic)
                .into(eq_pic);
        Picasso.with(getApplicationContext()).load(eq.getQRCodePath()).into(two_pic);
        content1.setText(eq.getEquipmentName());
        content2.setText(eq.getDescription());
        dataToFragment(eq);
    }

    @Override
    public void onFail(Data data) {
        stopProgressDialog();
        Msg.showError(application, VolleyHttp.errorInfo(data));
    }

    @Override
    public void onError(VolleyError volleyError) {
        stopProgressDialog();
        Msg.showError(application, getString(R.string.net_error));
    }

    @Override
    public void onTokenError() {
        new LoginManager().goToLogin(EqInfoAty.this, LoginAty.class, true);
    }
}
