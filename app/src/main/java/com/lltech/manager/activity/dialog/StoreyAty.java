package com.lltech.manager.activity.dialog;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.common.DiaLogCode;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.dialog.DialogReq;
import com.lltech.manager.entity.dialog.DialogRes;
import com.lltech.manager.entity.dialog.StoreyInfo;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;

/**
 * 选择设备区域
 */
public class StoreyAty extends DialogListAty {
    private BaseApplication application = BaseApplication.getApplication();

    private LinkedList<StoreyInfo> list = new LinkedList<StoreyInfo>();
    private QuickAdapter<StoreyInfo> adapter;

    private int pageIndex = 0;
    private int pageType = Req.PULL_DOWN;
    private ReqData data;
    private DialogRes res;

    @Override
    public void initViews() {
        super.initViews();
    }

    @Override
    public void initData() {
        refresh();
    }

    @Override
    public void initListeners() {
        super.initListeners();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initConfig() {
        topBar.setTopText("选择设备区域");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
    }

    @Override
    public void setAdapter() {
        adapter = new QuickAdapter<StoreyInfo>(this, R.layout.item_dialog, list) {

            @Override
            protected void convert(final BaseAdapterHelper helper, StoreyInfo item) {
                helper.setText(R.id.id, item.getStoreyID());
                helper.setText(R.id.text, item.getStoreyName());
            }
        };
        pullToRefresh.setAdapter(adapter);
    }

    @Override
    public void setItemClick(AdapterView<?> parent, View view, int position, long arg) {
        StoreyInfo item = (StoreyInfo) parent.getAdapter().getItem(position);
        Intent intent = getIntent();
        intent.putExtra("id", item.getStoreyID());
        intent.putExtra("text", item.getStoreyName());
        setResult(DiaLogCode.STOREY, intent);
        finish();
    }

    @Override
    public void pullDown() {
        pageType = Req.PULL_DOWN;
        pageIndex = 0;
        refresh();
    }

    @Override
    public void pullUp() {
        pageType = Req.PULL_UP;
        pageIndex++;
        refresh();
    }

    private void refresh() {
        startProgressDialog();
        String url = UrlCons.url(UrlCons.GetDialogData.GET);
        DialogReq req = new DialogReq();
        req.setPageIndex(pageIndex);
        req.setSerialNumber(DialogReq.STOREY);
        data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                res = JsonUtils.jsonToEntity(response, DialogRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    list = res.getStoreyList();
                    switch (pageType) {
                        case Req.PULL_DOWN: {
                            adapter.replaceAll(list);
                            pullToRefresh.onRefreshComplete(true);
                            break;
                        }
                        case Req.PULL_UP: {
                            adapter.addAll(list);
                            pullToRefresh.onRefreshComplete();
                            break;
                        }
                    }
                }
                stopProgressDialog();
            }

            @Override
            public void onFail(Data data) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                pullToRefresh.onRefreshComplete();
                stopProgressDialog();
                Msg.showError(application, VolleyHttp.errorInfo(data));
            }

            @Override
            public void onError(VolleyError volleyError) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                pullToRefresh.onRefreshComplete();
                stopProgressDialog();
                Msg.showError(application, getString(R.string.net_error));
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                new LoginManager().goToLogin(StoreyAty.this, LoginAty.class, true);
            }
        });
    }
}
