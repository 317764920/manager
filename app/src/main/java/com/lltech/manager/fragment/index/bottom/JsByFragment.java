package com.lltech.manager.fragment.index.bottom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.fragment.base.BaseProgressFragment;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.common.Mark;
import com.lltech.manager.common.PgState;
import com.lltech.manager.common.RequestCode;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.pg.Pg;
import com.lltech.manager.entity.pg.PgReq;
import com.lltech.manager.entity.pg.PgRes;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;

/**
 * @ClassName(类名) : JsByFragment
 * @Description(描述) : 技术端待保养
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:04
 */
public class JsByFragment extends BaseProgressFragment {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private PullToRefreshListView pullToRefreshListView;
    private QuickAdapter<Pg> adapter;
    private LinkedList<Pg> list = new LinkedList<Pg>();
    private int pageIndex = 0;
    private int pageType = Req.PULL_DOWN;
    private PgRes res;

    @Override
    public int setContentView() {
        return R.layout.baselist_wx;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        pullToRefreshListView = $(R.id.pullToRefreshListView);
    }

    @Override
    public void initListener() {
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
                    case PgState.YCH:
                        application.setMark(Mark.LD_DETAIL);
                        startActivityForResult(intent, RequestCode.LD_EDIT);
                        break;
                    case PgState.YPG:
                        application.setMark(Mark.LD_DETAIL);
                        startActivityForResult(intent, RequestCode.LD_EDIT);
                        break;
                    case PgState.YCD:
                        application.setMark(Mark.WG_DETAIL);
                        startActivityForResult(intent, RequestCode.LD_EDIT);
                        break;
//                    case PgState.YWC:
//                        break;
                    default:
                        application.setMark(Mark.CD_DETAIL);
                        startActivityForResult(intent, RequestCode.CD_EDIT);
                        break;
                }
            }
        });
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
    public void initConfig() {
        topBar.setVisibility(View.GONE);
        adapter = new QuickAdapter<Pg>(application, R.layout.item_list_pg, list) {
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
    public void initData() {
//        refresh();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                pullToRefreshListView.setRefreshing();
                break;
        }
    }

    private void refresh() {
        String url = UrlCons.url(UrlCons.DistributionService.GET_LIST);
        PgReq req = new PgReq();
        req.setPageIndex(pageIndex);
        req.setState(PgState.YPG);
        req.setOrderBy("DistributionTime desc");
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                res = JsonUtils.jsonToEntity(response, PgRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    list = res.getDataList();
                    switch (pageType) {
                        case Req.PULL_DOWN: {
                            adapter.replaceAll(list);
                            pullToRefreshListView.onRefreshComplete(true);
                            break;
                        }
                        case Req.PULL_UP: {
                            adapter.addAll(list);
                            pullToRefreshListView.onRefreshComplete();
                            break;
                        }
                    }
                }
                show();
            }

            @Override
            public void onFail(Data data) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                pullToRefreshListView.onRefreshComplete();
                show();
                Msg.showError(application, VolleyHttp.errorInfo(data));
            }

            @Override
            public void onError(VolleyError volleyError) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                pullToRefreshListView.onRefreshComplete();
                show();
                Msg.showError(application, getString(R.string.net_error));
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                new LoginManager().goToLogin(getActivity(), LoginAty.class, true);
            }
        });
    }
}
