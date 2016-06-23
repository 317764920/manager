package com.lltech.manager.activity.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;

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
import com.lltech.manager.entity.dialog.SystemInfo;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.entity.eq.EqReq;
import com.lltech.manager.entity.project.Project;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.MyResultCode;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.widget.DialogQueryView;
import com.lltech.manager.widget.DialogQueryWindow;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;
import com.mining.app.zxing.QrCodeActivity;

import java.util.LinkedList;

public class EquipmentAty extends DialogListAty {
    private BaseApplication application = BaseApplication.getApplication();
    private DialogQueryWindow queryWindow;
    private ListView menus;

    private LinkedList<Eq> list = new LinkedList<Eq>();
    private QuickAdapter<Eq> adapter;
    private LinkedList<SystemInfo> list1 = new LinkedList<SystemInfo>();
    private QuickAdapter<SystemInfo> adapter1;
    private StringBuffer sb = new StringBuffer();

    private int pageIndex = 0;
    private int pageType = Req.PULL_DOWN;
    private String projectID, systemCode;
    private DialogReq req = new DialogReq();
    private ReqData data;
    private DialogRes res, res1;

    @Override
    public void initViews() {
        super.initViews();
    }

    @Override
    public void initData() {
        Project project = MyData.getProject();
        projectID = project.getProjectID();
        getSubSystemList();
        refresh();
    }

    private void getSubSystemList() {
        DialogReq req1 = new DialogReq();
        String url = UrlCons.url(UrlCons.GetDialogData.GET);
        req1.setSerialNumber(DialogReq.SYSTEM);
        req1.setPageSize(100);
        req1.setPageIndex(0);
        req1.setProjectID(projectID);
        ReqData data1 = new ReqData(req1);
        VolleyHttp.send(VolleyHttp.POST, url, data1, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                res1 = JsonUtils.jsonToEntity(response, DialogRes.class);
                if (res1 != null) {
                    list1 = res1.getSubSystemList();
                    adapter1.replaceAll(list1);
                }
            }

            @Override
            public void onFail(Data data) {
                Msg.showError(application, VolleyHttp.errorInfo(data));
                stopProgressDialog();
            }

            @Override
            public void onError(VolleyError volleyError) {
                Msg.showError(application, getString(R.string.net_error));
                stopProgressDialog();
            }
        });
    }

    @Override
    public void initListeners() {
        super.initListeners();
        topBar.getRightBtn2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(application, QrCodeActivity.class);
                startActivityForResult(intent, MyResultCode.SCANNIN_GREQUEST_CODE);
            }
        });
        dialogQueryView.setOnBtnClickListener(new DialogQueryView.OnBtnClickListener() {

            @Override
            public void onQueryClick() {
                String EquipmentName = dialogQueryView.queryText.getText().toString();
                req.setEquipmentName(EquipmentName);
                refresh();
            }

            @Override
            public void onLeftClick() {
                if (null == queryWindow)
                    queryWindow = new DialogQueryWindow(EquipmentAty.this, R.layout.dialog_eq_querylist);
                queryWindow.setOnDismissListener(new OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        dialogQueryView.count++;
                        dialogQueryView.sanJiaoStartAnimation();
                    }
                });
                menus = queryWindow.getView(R.id.menu);
                menus.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        queryWindow.dismiss();
                        SystemInfo item = (SystemInfo) parent.getAdapter().getItem(position);
                        systemCode = item.getSystemCode();
                        dialogQueryView.dialog_left_btn.setText(item.getSystemName());
                        refresh();
                    }
                });
                menus.setAdapter(adapter1);
                queryWindow.show(dialogQueryView.dialog_left_btn, 0, 10);
            }
        });
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initConfig() {
        topBar.setTopText("选择设备");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.getRightBtn2().setBackgroundResource(R.drawable.sys);
    }

    @Override
    public void setAdapter() {
        adapter = new QuickAdapter<Eq>(this, R.layout.item_dialog, list) {

            @Override
            protected void convert(final BaseAdapterHelper helper, Eq item) {
                helper.setText(R.id.id, item.getEquipmentID());
                helper.setText(R.id.text, item.getEquipmentName());
            }
        };
        pullToRefresh.setAdapter(adapter);
        adapter1 = new QuickAdapter<SystemInfo>(this, R.layout.item_dialog_querywindow, list1) {

            @Override
            protected void convert(final BaseAdapterHelper helper, SystemInfo item) {
                helper.setText(R.id.id, item.getSystemCode());
                helper.setText(R.id.text, item.getSystemName());
            }
        };
    }

    @Override
    public void setItemClick(AdapterView<?> parent, View view, int position, long arg) {
        Eq item = (Eq) parent.getAdapter().getItem(position);
        sb.setLength(0);
        sb.append(item.getEquipmentName());
        sb.append("\n");
        sb.append("编号：");
        sb.append(item.getEquipmentNo());
        sb.append("\n");
        sb.append("品牌：");
        sb.append(item.getBrand());
        sb.append("\n");
        sb.append("规格：");
        sb.append(item.getModel());
        Intent intent = getIntent();
        intent.putExtra("id", item.getEquipmentID());
        intent.putExtra("text", sb.toString());
        setResult(DiaLogCode.EQ, intent);
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
        req.setSerialNumber(DialogReq.EQ);
        req.setPageIndex(pageIndex);
        req.setSystemCode(systemCode);
        req.setProjectID(projectID);
        data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                res = JsonUtils.jsonToEntity(response, DialogRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    list = res.getEquipmentList();
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
                new LoginManager().goToLogin(EquipmentAty.this, LoginAty.class, true);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK: {
                if (data != null) {
                    // 处理结果
                    String result = data.getExtras().getString("result");
                    if (result != null && result.substring(0, 1).equals("E")) {
                        getEq(result);
                    } else {
                        Msg.showError(application, "无效的二维码");
                    }
                }
            }
        }
    }

    private void getEq(String result) {
        String url = UrlCons.url(UrlCons.EquipmentService.GET_ONE);
        EqReq req = new EqReq();
        req.setEquipmentNo(result);
        data.setData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                Eq eq = JsonUtils.jsonToEntity(response, Eq.class);
                if (eq != null) {
                    sb.setLength(0);
                    sb.append(eq.getEquipmentName());
                    sb.append("\n");
                    sb.append("编号：");
                    sb.append(eq.getEquipmentNo());
                    sb.append("\n");
                    sb.append("品牌：");
                    sb.append(eq.getBrand());
                    sb.append("\n");
                    sb.append("规格：");
                    sb.append(eq.getModel());
                    Intent intent = getIntent();
                    intent.putExtra("id", eq.getEquipmentID());
                    intent.putExtra("text", sb.toString());
                    setResult(DiaLogCode.EQ, intent);
                    finish();
                }
                stopProgressDialog();
            }

            @Override
            public void onFail(Data data) {
                Msg.showError(application, VolleyHttp.errorInfo(data));
                stopProgressDialog();
            }

            @Override
            public void onError(VolleyError volleyError) {
                Msg.showError(application, getString(R.string.net_error));
                stopProgressDialog();
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                new LoginManager().goToLogin(EquipmentAty.this, LoginAty.class, true);
            }
        });
    }
}
