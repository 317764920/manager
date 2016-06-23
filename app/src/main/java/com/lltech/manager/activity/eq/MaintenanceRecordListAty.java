package com.lltech.manager.activity.eq;

import android.widget.ListView;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.by.By;
import com.lltech.manager.entity.by.ByReq;
import com.lltech.manager.entity.by.ByRes;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;


public class MaintenanceRecordListAty extends BaseActivity {
    private BaseApplication application = BaseApplication.getApplication();
    private PullToRefreshListView pullToRefreshListView;
    private TopBar topBar;
    private LinkedList<By> list = new LinkedList<By>();
    private QuickAdapter<By> adapter;
    private ByReq req = new ByReq();
    private ByRes res = new ByRes();
    private ReqData data;
    private Eq eq;
    private StringBuffer sb = new StringBuffer();
    private int pageIndex = 0;
    private int pageType = Req.PULL_DOWN;
    private String url;

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
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageType = Req.PULL_DOWN;
                pageIndex = 0;
                refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageType = Req.PULL_UP;
                pageIndex++;
                refresh();
            }
        });
    }

    @Override
    public void initData() {
        eq = (Eq) getIntent().getSerializableExtra("eq");
        refresh();
    }

    @Override
    public void initConfig() {
        topBar.setTopText("保养记录");
        adapter = new QuickAdapter<By>(this, R.layout.item_by_by, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, By item) {
                sb.setLength(0);
                sb.append(item.getEquipmentName());
                sb.append("（");
                sb.append(item.getModel());
                sb.append("）");
                helper.setText(R.id.content1, sb.toString());
                helper.setText(R.id.content1_1, "已完成");
                helper.setText(R.id.content2, item.getMaintenanceRecord());
            }
        };
        pullToRefreshListView.setAdapter(adapter);
    }

    private void refresh() {
        url = UrlCons.url(UrlCons.EquipmentService.GET_MaintenanceList);
        req.setPageIndex(pageIndex);
        req.setEquipmentNo(eq.getEquipmentNo());
        data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                res = JsonUtils.jsonToEntity(response, ByRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    list = res.getDataList();
                    switch (pageType) {
                        case Req.PULL_DOWN: {
                            adapter.replaceAll(list);
                            break;
                        }
                        case Req.PULL_UP: {
                            adapter.addAll(list);
                            break;
                        }
                    }
                }
                pullToRefreshListView.onRefreshComplete(true);
            }

            @Override
            public void onFail(Data data) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                pullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void onError(VolleyError volleyError) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                pullToRefreshListView.onRefreshComplete();
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                pullToRefreshListView.onRefreshComplete();
            }
        });
    }
}
