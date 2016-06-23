package com.lltech.manager.activity.bx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lcx.mysdk.view.DateTimePicker;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.BxState;
import com.lltech.manager.common.Mark;
import com.lltech.manager.common.RequestCode;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.entity.widget.PopMenuItem;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.MyData;
import com.lltech.manager.view.BxListView;
import com.lltech.manager.widget.ListCheckBox;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.OperView;
import com.lltech.manager.widget.PopupMenu;
import com.lltech.manager.widget.TopBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : BxListAty
 * @Description(描述) : 报修列表
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 16:06
 */
public class BxListAty extends BaseActivity implements BxListView, View.OnClickListener {

    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private DrawerLayout query_right;
    private PullToRefreshListView pullToRefreshListView;
    private QuickAdapter<Bx> adapter;
    private PopupMenu popupMenu;
    private List<PopMenuItem> poplist = new ArrayList<PopMenuItem>();
    private OperView operView;
    private TextView text_clear, text_confirm, text_BeginReportTime, text_EndReportTime;
    private RadioButton state1, state2, state3;
    private RadioGroup state;
    private String reqState = BxState.N;
    private BxBiz bxBiz = new BxBiz(this);


    @Override
    public void setContentView() {
        setContentView(R.layout.baselist_bx);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        query_right = $(R.id.query_right);
        pullToRefreshListView = $(R.id.pullToRefreshListView);
        popupMenu = new PopupMenu(this);
        operView = $(R.id.openView);
        text_clear = $(R.id.text_clear);
        text_confirm = $(R.id.text_confirm);
        text_BeginReportTime = $(R.id.text_BeginReportTime);
        text_EndReportTime = $(R.id.text_EndReportTime);
        state = $(R.id.state);
        state1 = $(R.id.state1);
        state2 = $(R.id.state2);
        state3 = $(R.id.state3);
    }

    @Override
    public void initListeners() {
        text_clear.setOnClickListener(this);
        text_confirm.setOnClickListener(this);
        text_BeginReportTime.setOnClickListener(this);
        text_EndReportTime.setOnClickListener(this);
        state.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (state1.getId() == checkedId) {
                    reqState = BxState.N;
                } else if (state2.getId() == checkedId) {
                    reqState = BxState.Y;
                } else if (state3.getId() == checkedId) {
                    reqState = BxState.YY;
                }
            }
        });
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (whitchBtn) {
                    case TopBar.RIGHT1: {
                        query_right.openDrawer(GravityCompat.END);
                        break;
                    }
                    case TopBar.RIGHT2:
                        popupMenu.showLocation(topBar.getRightBtn2());
                        // 设置回调监听，获取点击事件
                        popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                            @Override
                            public void onItemClick(PopMenuItem item, int position) {
                                switch (position) {
                                    case 0:
                                        application.setMark(Mark.BX_DETAIL);
                                        application.setOperMark(Mark.OperMark.ADD);
                                        Intent intent = new Intent(application, CommonAty.class);
                                        startActivityForResult(intent, RequestCode.BX_ADD);
                                        break;
                                    case 1:
                                        operView.isShow = true;
                                        operView.show();
                                        topBar.getRightBtn2().setVisibility(View.GONE);
                                        topBar.getLeftBtn().setText("取消");
                                        break;
                                }
                            }
                        });
                        break;

                }
            }

            @Override
            public void onLeftClick(View view) {
                if (operView.isShow) {
                    topBar.getRightBtn2().setVisibility(View.VISIBLE);
                    topBar.getLeftBtn().setText("返回");
                    adapter.resetCheckBox();
                    adapter.refresh();
                    operView.isShow = false;
                    operView.hide();
                } else {
                    finish();
                }
            }
        });
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bx item = (Bx) parent.getAdapter().getItem(position);
                if (operView.isShow) {
                    switch (item.getState()) {
                        case BxState.Y:
                            break;
                        case BxState.N:
                            ListCheckBox listCheckBox = $(view, R.id.checkBox);
                            boolean isCheck = !listCheckBox.isChecked();
                            adapter.setCheckBox((int) id, isCheck);
                            listCheckBox.setChecked(isCheck);
                            break;
                    }
                } else {
                    //跳转item详情
                    application.setMark(Mark.BX_DETAIL);
                    application.setOperMark(Mark.OperMark.EDIT);
                    Intent intent = new Intent(application, CommonAty.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("item", item);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, RequestCode.BX_EDIT);
                }
            }
        });
        operView.setOnBtnClickListener(new OperView.OnBtnClickListener() {
            @Override
            public void onLeftClick() {
                for (int i = 0; i < adapter.getCount(); i++) {
                    adapter.setCheckBox(i, !adapter.getCheckBox(i));
                }
                adapter.refresh();
            }

            @Override
            public void onRightClick() {
                List<Bx> genList = new LinkedList<Bx>();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getCheckBox(i)) {
                        genList.add(adapter.getItem(i));
                    }
                }
                if (CommonUtil.isEmpty(genList)) {
                    Toast.makeText(application, "请选择至少一条", Toast.LENGTH_SHORT).show();
                    return;
                }
                application.setMark(Mark.WX_DETAIL);
                application.setOperMark(Mark.OperMark.ADD);
                Intent intent = new Intent(application, CommonAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) genList);
                intent.putExtras(bundle);
                startActivityForResult(intent, RequestCode.WX_ADD);
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                bxBiz.getBxList(Req.PULL_DOWN, reqState, null, null);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                bxBiz.getBxList(Req.PULL_UP, reqState, null, null);
            }
        });
    }

    @Override
    public void initData() {
        PopMenuItem item1 = new PopMenuItem(R.drawable.pop_bx, "新增报修");
        poplist.add(item1);
        switch (MyData.getUser(User.class).getUserType()) {
            case User.YY:
                PopMenuItem item2 = new PopMenuItem(R.drawable.pop_sys, "生成维修单");
                poplist.add(item2);
                break;
        }
        popupMenu.loadMenu(poplist);
        startProgressDialog();
        bxBiz.getBxList(Req.PULL_DOWN, reqState, null, null);
    }

    @Override
    public void initConfig() {
        topBar.setTopText("报修单");
        adapter = new QuickAdapter<Bx>(application, R.layout.item_list_bx, new LinkedList<Bx>()) {
            @Override
            protected void convert(BaseAdapterHelper helper, Bx item) {
                String state = item.getState();
                switch (state) {
                    case BxState.Y:
                        break;
                    case BxState.N:
                        int index = helper.getPosition();
                        ListCheckBox listCheckBox = helper.getView(R.id.checkBox);
                        helper.setChecked(R.id.checkBox, adapter.getCheckBox(index));
                        if (operView.isShow) {
                            listCheckBox.show();
                        } else {
                            listCheckBox.hide();
                        }
                        break;
                    case BxState.YY:
                        break;
                }
                helper.setText(R.id.title, item.getFaultReportTitle());
                helper.setText(R.id.time, item.getReportTime());
                helper.setText(R.id.text, item.getFaultDescription());
            }
        };
        pullToRefreshListView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        if (query_right.isDrawerOpen(GravityCompat.END)) {
            query_right.closeDrawer(GravityCompat.END);
        } else {
            if (operView.isShow) {
                topBar.getRightBtn2().setVisibility(View.VISIBLE);
                topBar.getLeftBtn().setText("返回");
                adapter.resetCheckBox();
                adapter.refresh();
                operView.isShow = false;
                operView.hide();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                switch (requestCode) {
                    case RequestCode.WX_ADD:
                        finish();
                        break;
                    default:
                        pullToRefreshListView.setRefreshing();
                        break;
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //重置
            case R.id.text_clear:
                text_BeginReportTime.setText(null);
                text_EndReportTime.setText(null);
                break;
            //確定
            case R.id.text_confirm:
                query_right.closeDrawers();
                startProgressDialog();
                bxBiz.getBxList(Req.PULL_DOWN, reqState, ViewUtils.getText(text_BeginReportTime), ViewUtils.getText(text_EndReportTime));
                break;
            //開始時間
            case R.id.text_BeginReportTime:
                DateTimePicker.getInstance().setTime(this, text_BeginReportTime);
                break;
            //結束時間
            case R.id.text_EndReportTime:
                DateTimePicker.getInstance().setTime(this, text_EndReportTime);
                break;
        }
    }

    @Override
    public void onLoad(List<Bx> list) {
        adapter.replaceAll(list);
        pullToRefreshListView.onRefreshComplete(true);
        stopProgressDialog();
    }

    @Override
    public void onLoadMore(List<Bx> list) {
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
        new LoginManager().goToLogin(BxListAty.this, LoginAty.class, true);
    }
}
