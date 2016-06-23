package com.lltech.manager.activity.eq;

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
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.Cons;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.biz.impl.EqBiz;
import com.lltech.manager.common.EqStatus;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.EqListView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;
import java.util.List;

public class EqListAty extends BaseActivity implements View.OnClickListener, EqListView {

    private BaseApplication application = BaseApplication.getApplication();
    private PullToRefreshListView pullToRefreshListView;
    private TopBar topBar;
    private LinkedList<Eq> list = new LinkedList<Eq>();
    private QuickAdapter<Eq> adapter;
    private DrawerLayout query_right;
    private EditText edit_EquipmentNo, edit_EquipmentName;
    private TextView text_clear, text_confirm;
    private StringBuffer sb = new StringBuffer();
    private String systemCode = "";
    private RadioButton state1, state2;
    private RadioGroup state;
    private String reqState = EqStatus.USE;
    private EqBiz eqBiz = new EqBiz(this);

    @Override
    public void setContentView() {
        setContentView(R.layout.baselist_eq);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        pullToRefreshListView = $(R.id.pullToRefreshListView);
        query_right = $(R.id.query_right);
        text_clear = $(R.id.text_clear);
        text_confirm = $(R.id.text_confirm);
        edit_EquipmentNo = $(R.id.edit_EquipmentNo);
        edit_EquipmentName = $(R.id.edit_EquipmentName);
        state = $(R.id.state);
        state1 = $(R.id.state1);
        state2 = $(R.id.state2);
    }

    @Override
    public void initListeners() {
        text_clear.setOnClickListener(this);
        text_confirm.setOnClickListener(this);
        state.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (state1.getId() == checkedId) {
                    reqState = EqStatus.UNUSE;
                } else if (state2.getId() == checkedId) {
                    reqState = EqStatus.USE;
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
                }
            }

            @Override
            public void onLeftClick(View view) {
                finish();
            }
        });
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Eq eq = (Eq) parent.getAdapter().getItem(position);
                Intent intent = new Intent(application, EqInfoAty.class);
                intent.putExtra("eq", eq);
                startActivity(intent);
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                eqBiz.getList(Req.PULL_DOWN, reqState, systemCode, null, null);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                eqBiz.getList(Req.PULL_UP, reqState, systemCode, null, null);
            }
        });
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (CommonUtil.isNotEmpty(bundle)) {
            systemCode = bundle.getString("systemCode");
        }
        startProgressDialog();
        eqBiz.getList(Req.PULL_DOWN, reqState, systemCode, null, null);
    }

    @Override
    public void initConfig() {
        topBar.setTopText("所有设备");
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        adapter = new QuickAdapter<Eq>(this, R.layout.item_eq_info, list) {

            @Override
            protected void convert(BaseAdapterHelper helper, Eq item) {
                helper.setImageUrl(R.id.eq_img, item.getEquipmentImg(), Cons.ImageLoadType.PICASSO);
                sb.setLength(0);
                sb.append(item.getEquipmentName());
                sb.append("（");
                sb.append(item.getModel());
                sb.append("）");
                helper.setText(R.id.content1, sb.toString());
                helper.setText(R.id.content1_1, EqStatus.getStatusName(item.getStatus()));
                if (EqStatus.USE.equals(item.getStatus()))
                    helper.setTextColorRes(R.id.content1_1, R.color.green);
                if (EqStatus.UNUSE.equals(item.getStatus()))
                    helper.setTextColorRes(R.id.content1_1, R.color.red);
                helper.setText(R.id.content2, "已用年限：" + item.getUsedYears());
                helper.setText(R.id.content3, item.getEquipmentNo());
                helper.setText(R.id.content4, "生产日期：" + item.getProductionDate());
            }
        };
        pullToRefreshListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //重置
            case R.id.text_clear:
                edit_EquipmentNo.setText(null);
                edit_EquipmentName.setText(null);
                break;
            //確定
            case R.id.text_confirm:
                query_right.closeDrawers();
                startProgressDialog();
                eqBiz.getList(Req.PULL_DOWN, reqState, systemCode, ViewUtils.getText(edit_EquipmentNo), ViewUtils.getText(edit_EquipmentName));
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
    public void onLoad(List<Eq> list) {
        adapter.replaceAll(list);
        pullToRefreshListView.onRefreshComplete(true);
        stopProgressDialog();
    }

    @Override
    public void onLoadMore(List<Eq> list) {
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
        new LoginManager().goToLogin(EqListAty.this, LoginAty.class, true);
    }
}
