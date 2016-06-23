package com.lltech.manager.activity.pg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lcx.mysdk.view.DateTimePicker;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.Mark;
import com.lltech.manager.common.PgState;
import com.lltech.manager.common.RequestCode;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.pg.Pg;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.MyData;
import com.lltech.manager.view.PgListView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : PgListAty
 * @Description(描述) : 派工列表
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月26日 19:43
 */
public class PgListAty extends BaseActivity implements View.OnClickListener, PgListView {

    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private PullToRefreshListView pullToRefreshListView;
    private QuickAdapter<Pg> adapter;
    private DrawerLayout query_right;
    private TextView text_clear, text_confirm, text_BeginDistributionTime, text_EndDistributionTime;
    private RadioButton state1, state2, state3, state4;
    private EditText edit_DistributionUser;
    private RadioGroup state;
    private String reqState;
    private BxBiz bxBiz = new BxBiz(this);

    @Override
    public void setContentView() {
        setContentView(R.layout.baselist_pg);
    }

    @Override
    public void initViews() {
        pullToRefreshListView = $(R.id.pullToRefreshListView);
        topBar = $(R.id.top);
        query_right = $(R.id.query_right);
        text_clear = $(R.id.text_clear);
        text_confirm = $(R.id.text_confirm);
        text_BeginDistributionTime = $(R.id.text_BeginDistributionTime);
        text_EndDistributionTime = $(R.id.text_EndDistributionTime);
        state1 = $(R.id.state1);
        state2 = $(R.id.state2);
        state3 = $(R.id.state3);
        state4 = $(R.id.state4);
        edit_DistributionUser = $(R.id.edit_DistributionUser);
        state = $(R.id.state);
    }

    @Override
    public void initListeners() {
        text_clear.setOnClickListener(this);
        text_confirm.setOnClickListener(this);
        text_BeginDistributionTime.setOnClickListener(this);
        text_EndDistributionTime.setOnClickListener(this);
        state.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                //已撤回
//                if (state0.getId() == checkedId) {
//                    reqState = PgState.YCH;
//                }
                //已派工
                if (state1.getId() == checkedId) {
                    reqState = PgState.YPG;
                    //已领单
                } else if (state2.getId() == checkedId) {
                    reqState = PgState.YLD;
                    //已存档
                } else if (state3.getId() == checkedId) {
                    reqState = PgState.YCD;
                    //已完成
                } else if (state4.getId() == checkedId) {
                    reqState = PgState.YWC;
                }
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                bxBiz.getPgList(Req.PULL_DOWN, reqState, null, null);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                bxBiz.getPgList(Req.PULL_UP, reqState, null, null);
            }
        });
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                application.setMark(Mark.PG_DETAIL);
                Pg item = (Pg) parent.getAdapter().getItem(position);
                Intent intent = new Intent(application, CommonAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                intent.putExtras(bundle);
                switch (item.getState()) {
//                    case PgState.YCH:
//                        application.setMark(Mark.LD_DETAIL);
//                        startActivityForResult(intent, RequestCode.LD_EDIT);
//                        break;
                    case PgState.YPG:
                        application.setMark(Mark.LD_DETAIL);
                        startActivityForResult(intent, RequestCode.LD_EDIT);
                        break;
                    case PgState.YLD:
                        application.setMark(Mark.CD_DETAIL);
                        startActivityForResult(intent, RequestCode.CD_EDIT);
                        break;
                    case PgState.YCD:
                        application.setMark(Mark.WG_DETAIL);
                        startActivityForResult(intent, RequestCode.LD_EDIT);
                        break;
                    case PgState.YWC:
//                        application.setMark(Mark.CD_DETAIL);
//                        startActivityForResult(intent, RequestCode.CD_EDIT);
                        break;
                }
            }
        });
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (whitchBtn) {
                    case TopBar.RIGHT1:
                        query_right.openDrawer(GravityCompat.END);
                        break;
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
        switch (MyData.getUser(User.class).getUserType()) {
            case User.JS:
                reqState = PgState.YPG;
                break;
            case User.YY:
                reqState = PgState.YCD;
                break;
        }
        bxBiz.getPgList(Req.PULL_DOWN, reqState, null, null);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        topBar.setTopText("派工单");
        switch (MyData.getUser(User.class).getUserType()) {
            case User.JS:
                state3.setVisibility(View.GONE);
                state4.setVisibility(View.GONE);
                break;
            case User.YY:
                state1.setVisibility(View.GONE);
                state2.setVisibility(View.GONE);
                state3.setChecked(true);
                break;
        }
        adapter = new QuickAdapter<Pg>(application, R.layout.item_list_pg, new LinkedList<Pg>()) {
            @Override
            protected void convert(BaseAdapterHelper helper, Pg item) {
                helper.setText(R.id.name, PgState.getString(item.getState()));
                helper.setText(R.id.textView, item.getExplain());
                helper.setText(R.id.time, item.getDistributionTime());
            }
        };
        pullToRefreshListView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                pullToRefreshListView.setRefreshing();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (query_right.isDrawerOpen(GravityCompat.END)) {
            query_right.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //重置
            case R.id.text_clear:
                edit_DistributionUser.setText(null);
                text_BeginDistributionTime.setText(null);
                text_EndDistributionTime.setText(null);
                break;
            //確定
            case R.id.text_confirm:
                query_right.closeDrawers();
                startProgressDialog();
                bxBiz.getPgList(Req.PULL_DOWN, reqState, ViewUtils.getText(text_EndDistributionTime), ViewUtils.getText(text_BeginDistributionTime));
                break;
            //開始時間
            case R.id.text_BeginDistributionTime:
                DateTimePicker.getInstance().setTime(this, text_BeginDistributionTime);
                break;
            //結束時間
            case R.id.text_EndDistributionTime:
                DateTimePicker.getInstance().setTime(this, text_EndDistributionTime);
                break;
        }
    }

    @Override
    public void onLoad(List<Pg> list) {
        adapter.replaceAll(list);
        pullToRefreshListView.onRefreshComplete(true);
        stopProgressDialog();
    }

    @Override
    public void onLoadMore(List<Pg> list) {
        adapter.addAll(list);
        pullToRefreshListView.onRefreshComplete();
        stopProgressDialog();
    }

    @Override
    public void onFail(Data data) {
        pullToRefreshListView.onRefreshComplete();
        stopProgressDialog();
        Msg.showError(application, VolleyHttp.errorInfo(data));
    }

    @Override
    public void onError(VolleyError volleyError) {
        pullToRefreshListView.onRefreshComplete();
        stopProgressDialog();
        Msg.showError(application, getString(R.string.net_error));
    }

    @Override
    public void onTokenError() {
        new LoginManager().goToLogin(PgListAty.this, LoginAty.class, true);
    }
}
