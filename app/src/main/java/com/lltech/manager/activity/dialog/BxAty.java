package com.lltech.manager.activity.dialog;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.BxState;
import com.lltech.manager.common.DiaLogCode;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.BxListView;
import com.lltech.manager.widget.ListCheckBox;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : BxAty
 * @Description(描述) : 报修列表
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 16:06
 */
public class BxAty extends BaseActivity implements BxListView {

    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private PullToRefreshListView pullToRefreshListView;
    private QuickAdapter<Bx> adapter;
    private LinkedList<Bx> list = new LinkedList<Bx>();
    private List<Bx> newList = new ArrayList<Bx>();
    private List<Bx> oldList = new ArrayList<Bx>();
    private BxBiz bxBiz = new BxBiz(this);

    @Override
    public void setContentView() {
        setContentView(R.layout.baselist);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        pullToRefreshListView = $(R.id.pullToRefreshListView);
    }

    @Override
    public void initListeners() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (whitchBtn) {
                    case TopBar.RIGHT2:
                        for (int i = 0; i < adapter.getCount(); i++) {
                            if (adapter.getCheckBox(i)) {
                                newList.add(adapter.getItem(i));
                            }
                        }
                        if (CommonUtil.isEmpty(newList)) {
                            Toast.makeText(application, "请至少选择一条", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = getIntent();
                        intent.putExtra("list", (Serializable) newList);
                        setResult(DiaLogCode.BXD, intent);
                        finish();
                        break;
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
                ListCheckBox listCheckBox = $(view, R.id.checkBox);
                boolean isCheck = !listCheckBox.isChecked();
                adapter.setCheckBox((int) id, isCheck);
                listCheckBox.setChecked(isCheck);
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                bxBiz.getBxList(Req.PULL_DOWN, BxState.N, null, null);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                bxBiz.getBxList(Req.PULL_UP, BxState.N, null, null);
            }
        });
    }

    @Override
    public void initData() {
        startProgressDialog();
        oldList = (List<Bx>) getIntent().getExtras().getSerializable("list");
        bxBiz.getBxList(Req.PULL_DOWN, BxState.N, null, null);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setTopText("选择报修单");
        topBar.getRightBtn2().setText("确定");
        topBar.getRightBtn2().setBackground(null);
        adapter = new QuickAdapter<Bx>(application, R.layout.item_list_bx, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, Bx item) {
                String id = item.getFaultReportID();
                int index = helper.getPosition();
                for (Bx bx : oldList) {
                    if (id.equals(bx.getFaultReportID())) {
                        adapter.setCheckBox(index, true);
                    }
                }
                ListCheckBox listCheckBox = helper.getView(R.id.checkBox);
                listCheckBox.show();
                helper.setChecked(R.id.checkBox, adapter.getCheckBox(index));
                helper.setText(R.id.title, item.getFaultReportTitle());
                helper.setText(R.id.time, item.getReportTime());
                helper.setText(R.id.text, item.getFaultDescription());
            }
        };
        pullToRefreshListView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        finish();
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
        new LoginManager().goToLogin(BxAty.this, LoginAty.class, true);
    }
}
