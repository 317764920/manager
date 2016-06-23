package com.lltech.manager.fragment.js;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.utils.DateUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.BxState;
import com.lltech.manager.common.Mark;
import com.lltech.manager.common.RequestCode;
import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.entity.cd.WorkCheck;
import com.lltech.manager.entity.pg.Pg;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.MyData;
import com.lltech.manager.view.CdView;
import com.lltech.manager.widget.BottomWindow;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : CdFragment
 * @Description(描述) : 存档
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 14:16
 */
public class CdFragment extends BaseFragment implements View.OnClickListener, CdView {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private BottomWindow bottomWindow;
    private PullToRefreshListView pullToRefreshListView;
    private TextView txt_maintenance_number, edit_maintenance_theme, txt_maintenance_time, bx_size;
    private Button qd, qt, btn_history;
    private ListView listView;
    private QuickAdapter<Bx> adapter;
    private QuickAdapter<WorkCheck> w_adapter;
    private Pg pg;
    private int checkIndex;
    private BxBiz bxBiz = new BxBiz(this);

    @Override
    public int setContentView() {
        return R.layout.detail_cd;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        txt_maintenance_number = $(R.id.txt_maintenance_number);
        edit_maintenance_theme = $(R.id.edit_maintenance_theme);
        txt_maintenance_time = $(R.id.txt_maintenance_time);
        bx_size = $(R.id.bx_size);
        qd = $(R.id.qd);
        qt = $(R.id.qt);
        btn_history = $(R.id.btn_history);
        listView = $(R.id.listView);
        bottomWindow = new BottomWindow(getActivity(), R.layout.bottom_cd_history);
        pullToRefreshListView = bottomWindow.getView(R.id.pullToRefreshListView);
        TextView empty = new TextView(application);
        empty.setText("暂无历史记录");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        empty.setLayoutParams(lp);
        pullToRefreshListView.setEmptyView(empty);
    }

    @Override
    public void initListener() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int btnType, View view) {

            }

            @Override
            public void onLeftClick(View view) {
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                checkIndex = position;
                Bx item = (Bx) parent.getAdapter().getItem(position);
                application.setMark(Mark.CD_SECOND_DETAIL);
                Intent intent = new Intent(application, CommonAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                bundle.putSerializable("item1", pg);
                intent.putExtras(bundle);
                startActivityForResult(intent, RequestCode.CD_SECOND_EDIT);
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                bxBiz.getCheckHistory(pg.getRepairID());
            }
        });
        qd.setOnClickListener(this);
        qt.setOnClickListener(this);
        btn_history.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        topBar.setTopText("执行");
        adapter = new QuickAdapter<Bx>(application, R.layout.item_maintenance_bx, new LinkedList<Bx>()) {
            @Override
            protected void convert(BaseAdapterHelper helper, Bx item) {
                if (BxState.YY.equals(item.getState())) {
                    helper.setVisible(R.id.state, true);
                    helper.setText(R.id.state, BxState.getString(item.getState()));
                }
                helper.setText(R.id.name, item.getFaultReportTitle());
            }
        };
        listView.setAdapter(adapter);
        w_adapter = new QuickAdapter<WorkCheck>(application, R.layout.item_cd_history, new LinkedList<WorkCheck>()) {
            @Override
            protected void convert(BaseAdapterHelper helper, WorkCheck item) {
                helper.setText(R.id.text1, item.getCheckInTime());
                helper.setText(R.id.text2, item.getCheckOutTime());
            }
        };
        pullToRefreshListView.setAdapter(w_adapter);
        //去掉listview的selector效果
        pullToRefreshListView.getRefreshableView().setSelector(R.color.transparent);
    }

    @Override
    public void initData() {
        Intent intent = getActivity().getIntent();
        pg = (Pg) intent.getExtras().getSerializable("item");
        startProgressDialog();
        bxBiz.getCdWxEntity(pg.getRepairID());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qd:
                qd();
                break;
            case R.id.qt:
                qt();
                break;
            case R.id.btn_history:
                bottomWindow.show(btn_history);
                bxBiz.getCheckHistory(pg.getRepairID());
                break;
        }
    }

    /**
     * 签到
     */
    private void qd() {
        WorkCheck workCheck = new WorkCheck();
        workCheck.setObjectType(WorkCheck.WX);
        workCheck.setObjectID(pg.getRepairID());
        workCheck.setCheckUser(MyData.getUser(User.class).getEmployeeID());
        workCheck.setCheckInTime(DateUtils.date2String(DateUtils.currentDateTime()));
        bxBiz.checkIn(workCheck);
    }

    /**
     * 签退
     */
    private void qt() {
        WorkCheck workCheck = new WorkCheck();
        workCheck.setObjectType(WorkCheck.WX);
        workCheck.setObjectID(pg.getRepairID());
        workCheck.setCheckUser(MyData.getUser(User.class).getEmployeeID());
        workCheck.setCheckOutTime(DateUtils.date2String(DateUtils.currentDateTime()));
        bxBiz.checkOut(workCheck);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                switch (requestCode) {
                    case RequestCode.CD_SECOND_EDIT:
                        Bx bx = (Bx) data.getSerializableExtra("item");
                        adapter.replace(checkIndex, bx);
                        for (int i = 0; i < adapter.getCount(); i++) {
                            Bx item = adapter.getItem(i);
                            if (!BxState.YY.equals(item.getState())) {
                                return;
                            }
                        }
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        break;
                    default:
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        break;
                }
                break;
        }
    }

    @Override
    public void onGetCdWxEntitySuccess(Wx wx) {
        bx_size.setText("报修单，共" + wx.getFaultReportList().size() + "条");
        txt_maintenance_number.setText(wx.getRepairNo());
        edit_maintenance_theme.setText(wx.getRepairTitle());
        txt_maintenance_time.setText(wx.getRepairTime());
        adapter.replaceAll(wx.getFaultReportList());
        ViewUtils.setListViewHeightBasedOnChildren(listView);
        stopProgressDialog();
    }

    @Override
    public void onCheckInSuccess() {
        Msg.showSuccess(application, "签到成功");
    }

    @Override
    public void onCheckOutSuccess() {
        Msg.showSuccess(application, "签退成功");
    }

    @Override
    public void onGetCheckHistorySuccess(List<WorkCheck> w_list) {
        w_adapter.replaceAll(w_list);
        pullToRefreshListView.onRefreshComplete(true);
    }

    @Override
    public void onFail(Data data) {
        Msg.showError(application, VolleyHttp.errorInfo(data));
        stopProgressDialog();
        pullToRefreshListView.onRefreshComplete();
    }

    @Override
    public void onError(VolleyError volleyError) {
        Msg.showError(application, getString(R.string.net_error));
        stopProgressDialog();
        pullToRefreshListView.onRefreshComplete();
    }

    @Override
    public void onTokenError() {
        new LoginManager().goToLogin(getActivity(), LoginAty.class, true);
    }
}
