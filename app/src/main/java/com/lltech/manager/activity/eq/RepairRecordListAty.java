package com.lltech.manager.activity.eq;

import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.ReqData;
import com.lltech.manager.R;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.entity.bx.WxReq;
import com.lltech.manager.entity.bx.WxRes;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;

public class RepairRecordListAty extends BaseActivity {
    private BaseApplication application = BaseApplication.getApplication();
    private PullToRefreshListView pullToRefreshListView;
    private TopBar topBar;
    private LinkedList<Wx> list = new LinkedList<Wx>();
    private QuickAdapter<Wx> adapter;
    private WxReq req = new WxReq();
    private WxRes res;
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
        topBar.setTopText("维修记录");
        adapter = new QuickAdapter<Wx>(this, R.layout.item_fix_wx, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, Wx item) {
//                sb.setLength(0);
//                sb.append(item.getEquipmentName());
//                sb.append("（");
//                sb.append(item.getModel());
//                sb.append("）");
//                helper.setText(R.id.content1, sb.toString());
//                helper.setText(R.id.content1_1, "已完成");
//                helper.setText(R.id.content2, item.getRepairRecordID());
            }
        };
        pullToRefreshListView.setAdapter(adapter);
    }

    private void refresh() {
//        url = UrlCons.url(UrlCons.EquipmentService.GET_RepairList);
//        req.setPageIndex(pageIndex);
//        req.setEquipmentNo(eq.getEquipmentNo());
//        data = new ReqData(req);
    }
}
