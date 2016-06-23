package com.lltech.manager.activity.bx;

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
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.Mark;
import com.lltech.manager.common.RequestCode;
import com.lltech.manager.common.WxState;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.WxListView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : WxListAty
 * @Description(描述) : 维修列表
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 16:06
 */
public class WxListAty extends BaseActivity implements View.OnClickListener, WxListView {

    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private DrawerLayout query_right;
    private PullToRefreshListView pullToRefreshListView;
    private QuickAdapter<Wx> adapter;
    private TextView text_clear, text_confirm;
    private RadioButton state1, state2;
    private EditText edit_RepairNo, edit_RepairTitle;
    private RadioGroup state;
    private String reqState = WxState.UNFINISH;
    private BxBiz bxBiz = new BxBiz(this);


    @Override
    public void setContentView() {
        setContentView(R.layout.baselist_wx);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        query_right = $(R.id.query_right);
        pullToRefreshListView = $(R.id.pullToRefreshListView);
        text_clear = $(R.id.text_clear);
        text_confirm = $(R.id.text_confirm);
        state1 = $(R.id.state1);
        state2 = $(R.id.state2);
        edit_RepairNo = $(R.id.edit_RepairNo);
        edit_RepairTitle = $(R.id.edit_RepairTitle);
        state = $(R.id.state);
    }

    @Override
    public void initListeners() {
        text_clear.setOnClickListener(this);
        text_confirm.setOnClickListener(this);
        state.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (state1.getId() == checkedId) {
                    reqState = WxState.UNFINISH;
                } else if (state2.getId() == checkedId) {
                    reqState = WxState.FINISH;
                }
            }
        });
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wx item = (Wx) parent.getAdapter().getItem(position);
                application.setMark(Mark.WX_DETAIL);
                application.setOperMark(Mark.OperMark.EDIT);
                Intent intent = new Intent(application, CommonAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                intent.putExtras(bundle);
                startActivityForResult(intent, RequestCode.WX_EDIT);
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                bxBiz.getWxList(Req.PULL_DOWN, reqState, null, null);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                bxBiz.getWxList(Req.PULL_UP, reqState, null, null);
            }
        });
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (whitchBtn) {
                    case TopBar.RIGHT1:
                        query_right.openDrawer(GravityCompat.END);
                        break;
                    case TopBar.RIGHT2:
                        application.setMark(Mark.WX_DETAIL);
                        application.setOperMark(Mark.OperMark.ADD);
                        Intent intent = new Intent(application, CommonAty.class);
                        startActivityForResult(intent, RequestCode.WX_ADD);
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
        bxBiz.getWxList(Req.PULL_UP, reqState, null, null);
    }

    @Override
    public void initConfig() {
        topBar.setTopText("维修单");
        adapter = new QuickAdapter<Wx>(application, R.layout.item_list_wx, new LinkedList<Wx>()) {
            @Override
            protected void convert(BaseAdapterHelper helper, Wx item) {
                helper.setText(R.id.name, item.getRepairTitle());
                helper.setText(R.id.textView, item.getRepairNo());
                helper.setText(R.id.time, item.getRepairTime());
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
                edit_RepairNo.setText(null);
                edit_RepairTitle.setText(null);
                break;
            //確定
            case R.id.text_confirm:
                query_right.closeDrawers();
                startProgressDialog();
                bxBiz.getWxList(Req.PULL_UP, reqState, ViewUtils.getText(edit_RepairNo), ViewUtils.getText(edit_RepairTitle));
                break;
        }
    }

    @Override
    public void onLoad(List<Wx> list) {
        adapter.replaceAll(list);
        pullToRefreshListView.onRefreshComplete(true);
        stopProgressDialog();
    }

    @Override
    public void onLoadMore(List<Wx> list) {
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
        new LoginManager().goToLogin(WxListAty.this, LoginAty.class, true);
    }
}
