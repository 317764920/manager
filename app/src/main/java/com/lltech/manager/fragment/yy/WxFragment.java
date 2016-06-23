package com.lltech.manager.fragment.yy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.DateUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lcx.mysdk.view.DateTimePicker;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.activity.dialog.BxAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.BxState;
import com.lltech.manager.common.DiaLogCode;
import com.lltech.manager.common.Mark;
import com.lltech.manager.common.RequestCode;
import com.lltech.manager.common.WxState;
import com.lltech.manager.entity.OrderCode;
import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.entity.pg.Apply;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.OrderCreator;
import com.lltech.manager.view.WxView;
import com.lltech.manager.widget.BottomWindow;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


/**
 * @ClassName(类名) : WxFragment
 * @Description(描述) : 编辑维修单
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 09:50
 */
public class WxFragment extends BaseFragment implements View.OnClickListener, WxView {
    private BaseApplication application = BaseApplication.getApplication();
    private TextView txt_maintenance_number, txt_maintenance_time, addBx, bx_size, history_title;
    private EditText edit_maintenance_theme;
    private ListView listview, listview_ls;
    private TopBar topBar;
    private BottomWindow bottomWindow;
    private Button keep, delete, cancel;
    private List<Bx> list = new LinkedList<Bx>();
    private QuickAdapter<Bx> adapter;
    private Wx wx;
    private int lastOperMark = -1;
    private List<Apply> ls_list = new LinkedList<Apply>();
    private QuickAdapter<Apply> ls_adapter;
    private BxBiz bxBiz = new BxBiz(this);

    @Override
    public int setContentView() {
        return R.layout.detail_wx;
    }

    @Override
    public void initView() {
        txt_maintenance_number = $(R.id.txt_maintenance_number);
        edit_maintenance_theme = $(R.id.edit_maintenance_theme);
        txt_maintenance_time = $(R.id.txt_maintenance_time);
        addBx = $(R.id.addBx);
        bx_size = $(R.id.bx_size);
        history_title = $(R.id.history_title);
        listview = $(R.id.listview);
        topBar = $(R.id.top);
        bottomWindow = new BottomWindow(getActivity(), R.layout.bottom_window_wx);
        keep = bottomWindow.getView(R.id.keep);
        delete = bottomWindow.getView(R.id.delete);
        cancel = bottomWindow.getView(R.id.cancel);
        listview_ls = $(R.id.listview_ls);
    }

    @Override
    public void initListener() {
        addBx.setOnClickListener(this);
        keep.setOnClickListener(this);
        delete.setOnClickListener(this);
        cancel.setOnClickListener(this);
        txt_maintenance_number.setOnClickListener(this);
        txt_maintenance_time.setOnClickListener(this);
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (application.getOperMark()) {
                    case Mark.OperMark.ADD:
                        startProgressDialog();
                        save();
                        break;
                    case Mark.OperMark.EDIT:
                        bottomWindow.show(topBar);
                        break;
                }

            }

            @Override
            public void onLeftClick(View view) {
                getActivity().finish();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastOperMark = application.getOperMark();
                Bx item = (Bx) parent.getAdapter().getItem(position);
                item.setState(BxState.Y);
                //跳转item详情
                application.setMark(Mark.BX_DETAIL);
                application.setOperMark(Mark.OperMark.EDIT);
                Intent intent = new Intent(application, CommonAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                intent.putExtras(bundle);
                startActivityForResult(intent, RequestCode.BX_EDIT);
            }
        });
    }

    @Override
    public void initConfig() {
        topBar.setTopText("维修单信息");
        setWidget();
        adapter = new QuickAdapter<Bx>(getActivity(), R.layout.item_maintenance_bx, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, Bx item) {
                helper.setText(R.id.name, item.getFaultReportTitle());
            }
        };
        listview.setAdapter(adapter);
        ls_adapter = new QuickAdapter<Apply>(getActivity(), R.layout.item_apply, ls_list) {
            @Override
            protected void convert(BaseAdapterHelper helper, Apply item) {
                helper.setText(R.id.text_time, item.getApplyTime());
                helper.setText(R.id.text_name, item.getApplyUser());
                helper.setText(R.id.text_reason, item.getApplyDesc());
            }
        };
        listview_ls.setAdapter(ls_adapter);
        ViewUtils.setListViewHeightBasedOnChildren(listview);
        ViewUtils.setListViewHeightBasedOnChildren(listview_ls);
    }

    @Override
    public void initData() {
        switch (application.getOperMark()) {
            case Mark.OperMark.ADD:
                Bundle bundle = getActivity().getIntent().getExtras();
                if (CommonUtil.isNotEmpty(bundle)) {
                    list = (List<Bx>) bundle.get("list");
                    bx_size.setText("报修单，共" + (list == null ? "0" : list.size()) + "条");
                }
                new OrderCreator().getOrderCode(getActivity(), txt_maintenance_number, OrderCode.WX);
                break;
            case Mark.OperMark.EDIT:
                wx = (Wx) getArguments().getSerializable("item");
                startProgressDialog();
                bxBiz.getWxEntity(wx.getRepairID());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lastOperMark != -1)
            application.setOperMark(lastOperMark);
    }

    private void setWidget() {
        switch (application.getOperMark()) {
            case Mark.OperMark.ADD:
                topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
                topBar.getRightBtn2().setText(R.string.submit);
                topBar.getRightBtn2().setBackground(null);
                addBx.setVisibility(View.VISIBLE);
                break;
            case Mark.OperMark.EDIT:
                topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
                switch (wx.getState()) {
                    case WxState.UNFINISH:
                        topBar.setBtnType(TopBar.RIGHT2, TopBar.MORE);
                        break;
                    case WxState.FINISH:
                        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
                        break;
                }
                addBx.setVisibility(View.GONE);
                setTableChildFalse();
                break;
        }
    }

    /**
     * 新增
     */
    private void save() {
        wx = new Wx();
        wx.setCreateUserID(MyData.getUser(User.class).getEmployeeID());
        wx.setCreateUserName(MyData.getUser(User.class).getUserName());
        wx.setCreateTime(DateUtils.date2StringBySecond(DateUtils.currentDateTime()));
        wx.setRepairNo(txt_maintenance_number.getText().toString());
        wx.setRepairTitle(edit_maintenance_theme.getText().toString());
        wx.setRepairTime(txt_maintenance_time.getText().toString());
        wx.setFaultReportList(list);
        wx.setRemark("");
        wx.setState("1");
        bxBiz.saveWx(wx);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBx: {
                Intent intent = new Intent(application, BxAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) list);
                intent.putExtras(bundle);
                startActivityForResult(intent, RequestCode.BX_SELECT);
                break;
            }
            case R.id.txt_maintenance_number:
                new OrderCreator().getOrderCode(getActivity(), txt_maintenance_number, OrderCode.WX);
                break;
            //维修执行时间
            case R.id.txt_maintenance_time:
                DateTimePicker.getInstance().setTime(getActivity(), txt_maintenance_time);
                break;
            case R.id.keep: {
                //派工
                application.setMark(Mark.PG_DETAIL);
                Intent intent = new Intent(application, CommonAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", wx);
                intent.putExtras(bundle);
                startActivityForResult(intent, RequestCode.PG);
                break;
            }
            case R.id.delete:
                startProgressDialog();
                bxBiz.delWx(wx);
                break;
            case R.id.cancel:
                //取消底部菜单
                bottomWindow.dismiss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case DiaLogCode.BXD:
                list = (List<Bx>) data.getSerializableExtra("list");
                adapter.replaceAll(list);
                ViewUtils.setListViewHeightBasedOnChildren(listview);
                bx_size.setText("报修单，共" + list.size() + "条");
                break;
            case Activity.RESULT_OK:
                bottomWindow.dismiss();
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onGetEntitySuccess(Wx wx) {
        list = wx.getFaultReportList();
        ls_list = wx.getDistributionApplyList();
        bx_size.setText("报修单，共" + wx.getFaultReportList().size() + "条");
        history_title.setVisibility(CommonUtil.isEmpty(ls_list) == true ? View.GONE : View.VISIBLE);
        txt_maintenance_number.setText(wx.getRepairNo());
        edit_maintenance_theme.setText(wx.getRepairTitle());
        txt_maintenance_time.setText(wx.getRepairTime());
        adapter.replaceAll(list);
        ls_adapter.replaceAll(ls_list);
        ViewUtils.setListViewHeightBasedOnChildren(listview);
        ViewUtils.setListViewHeightBasedOnChildren(listview_ls);
        stopProgressDialog();
    }

    @Override
    public void onSaveSuccess() {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                bottomWindow.dismiss();
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onDelSuccess() {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                bottomWindow.dismiss();
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        });
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

    @Override
    public void onTokenError() {
        new LoginManager().goToLogin(getActivity(), LoginAty.class, true);
    }
}
