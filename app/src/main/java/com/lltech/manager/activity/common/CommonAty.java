package com.lltech.manager.activity.common;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.lcx.mysdk.activity.BaseFragmentActivity;
import com.lcx.mysdk.application.BaseApplication;
import com.lltech.manager.R;
import com.lltech.manager.common.Mark;
import com.lltech.manager.fragment.common.BxFragment;
import com.lltech.manager.fragment.js.CdFragment;
import com.lltech.manager.fragment.js.CdSecondFragment;
import com.lltech.manager.fragment.js.LdFragment;
import com.lltech.manager.fragment.js.TdFragment;
import com.lltech.manager.fragment.yy.PgFragment;
import com.lltech.manager.fragment.yy.WgFragment;
import com.lltech.manager.fragment.yy.WxFragment;

/**
 * @ClassName(类名) : CommonAty
 * @Description(描述) : 公共跳转器
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 14:11
 */
public class CommonAty extends BaseFragmentActivity {
    private BaseApplication application = BaseApplication.getApplication();
    private Fragment bxFragment, wxFragment, ldFragment, tdFragment, pgFragment, cdFragment, cdSecondFragment, wgFragment;

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
            case Mark.BX_DETAIL:
                //新增报修单
                if (bxFragment == null) {
                    bxFragment = new BxFragment();
                    bxFragment.setArguments(getIntent().getExtras());
                    tr.add(R.id.content, bxFragment);
                } else {
                    tr.show(bxFragment);
                }

                break;
            case Mark.WX_DETAIL:
                //生成维修单
                if (wxFragment == null) {
                    wxFragment = new WxFragment();
                    wxFragment.setArguments(getIntent().getExtras());
                    tr.add(R.id.content, wxFragment);
                } else {
                    tr.show(wxFragment);
                }
                break;
            case Mark.LD_DETAIL:
                //领单
                if (ldFragment == null) {
                    ldFragment = new LdFragment();
                    ldFragment.setArguments(getIntent().getExtras());
                    tr.add(R.id.content, ldFragment);
                } else {
                    tr.show(ldFragment);
                }
                break;
            case Mark.TD_DETAIL:
                //退单
                if (tdFragment == null) {
                    tdFragment = new TdFragment();
                    tdFragment.setArguments(getIntent().getExtras());
                    tr.add(R.id.content, tdFragment);
                } else {
                    tr.show(tdFragment);
                }
                break;
            case Mark.PG_DETAIL:
                //派工
                if (pgFragment == null) {
                    pgFragment = new PgFragment();
                    pgFragment.setArguments(getIntent().getExtras());
                    tr.add(R.id.content, pgFragment);
                } else {
                    tr.show(pgFragment);
                }
                break;
            case Mark.CD_DETAIL:
                //存档
                if (cdFragment == null) {
                    cdFragment = new CdFragment();
                    cdFragment.setArguments(getIntent().getExtras());
                    tr.add(R.id.content, cdFragment);
                } else {
                    tr.show(cdFragment);
                }
                break;
            case Mark.CD_SECOND_DETAIL:
                //存档二级
                if (cdSecondFragment == null) {
                    cdSecondFragment = new CdSecondFragment();
                    cdSecondFragment.setArguments(getIntent().getExtras());
                    tr.add(R.id.content, cdSecondFragment);
                } else {
                    tr.show(cdSecondFragment);
                }
                break;
            case Mark.WG_DETAIL:
                //完工确认
                if (wgFragment == null) {
                    wgFragment = new WgFragment();
                    wgFragment.setArguments(getIntent().getExtras());
                    tr.add(R.id.content, wgFragment);
                } else {
                    tr.show(wgFragment);
                }
                break;
        }
        tr.commit();
    }

    private void hide(FragmentTransaction tr) {
        if (bxFragment != null) {
            tr.hide(bxFragment);
        }
        if (wxFragment != null) {
            tr.hide(wxFragment);
        }
        if (ldFragment != null) {
            tr.hide(ldFragment);
        }
        if (tdFragment != null) {
            tr.hide(tdFragment);
        }
        if (pgFragment != null) {
            tr.hide(pgFragment);
        }
        if (cdFragment != null) {
            tr.hide(cdFragment);
        }
        if (cdSecondFragment != null) {
            tr.hide(cdSecondFragment);
        }
        if (wgFragment != null) {
            tr.hide(wgFragment);
        }
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
