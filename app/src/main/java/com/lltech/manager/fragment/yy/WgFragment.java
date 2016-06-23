package com.lltech.manager.fragment.yy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.BxState;
import com.lltech.manager.common.Mark;
import com.lltech.manager.common.WgState;
import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.entity.pg.Pg;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.WgView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : WgFragment
 * @Description(描述) : 完工确认
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月12日 15:13
 */
public class WgFragment extends BaseFragment implements WgView {

    private BaseApplication application = BaseApplication.getApplication();
    private RadioGroup state;
    private EditText edit_ReviewedDesc;
    private TextView txt_maintenance_number, edit_maintenance_theme, txt_maintenance_time, bx_size;
    private ListView listView;
    private TopBar topBar;
    private RadioButton state1, state2;
    private String reqState = WgState.TG;
    private Pg pg;
    private BxBiz bxBiz = new BxBiz(this);
    private Wx wx;
    private QuickAdapter<Bx> adapter;
    private List<Bx> list = new LinkedList<Bx>();

    @Override
    public int setContentView() {
        return R.layout.detail_wg;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        state = $(R.id.state);
        state1 = $(R.id.state1);
        state2 = $(R.id.state2);
        edit_ReviewedDesc = $(R.id.edit_ReviewedDesc);
        txt_maintenance_number = $(R.id.txt_maintenance_number);
        edit_maintenance_theme = $(R.id.edit_maintenance_theme);
        txt_maintenance_time = $(R.id.txt_maintenance_time);
        bx_size = $(R.id.bx_size);
        listView = $(R.id.listView);
    }

    @Override
    public void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bx item = (Bx) parent.getAdapter().getItem(position);
                application.setMark(Mark.BX_DETAIL);
                application.setOperMark(Mark.OperMark.EDIT);
                Intent intent = new Intent(application, CommonAty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        state.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (state1.getId() == checkedId) {
                    reqState = WgState.BH;
                } else if (state2.getId() == checkedId) {
                    reqState = WgState.TG;
                }
            }
        });
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (whitchBtn) {
                    case TopBar.RIGHT2:
                        startProgressDialog();
                        bxBiz.review(pg.getDistributionID(), reqState, edit_ReviewedDesc.getText().toString());
                        break;
                }
            }

            @Override
            public void onLeftClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void initConfig() {
        topBar.setTopText("完工确认");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.getRightBtn2().setText("提交");
        topBar.getRightBtn2().setBackground(null);
        adapter = new QuickAdapter<Bx>(application, R.layout.item_maintenance_bx, list) {
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
    }

    @Override
    public void initData() {
        pg = (Pg) getActivity().getIntent().getExtras().getSerializable("item");
        startProgressDialog();
        bxBiz.getWgWxEntity(pg.getRepairID());
    }


    @Override
    public void refreshSuccess(Data data) {
        String response = data.getResponse().toString();
        wx = JsonUtils.jsonToEntity(response, Wx.class);
        if (CommonUtil.isNotEmpty(wx)) {
            list = wx.getFaultReportList();
            bx_size.setText("报修单，共" + wx.getFaultReportList().size() + "条");
            txt_maintenance_number.setText(wx.getRepairNo());
            edit_maintenance_theme.setText(wx.getRepairTitle());
            txt_maintenance_time.setText(wx.getRepairTime());
            adapter.replaceAll(list);
            ViewUtils.setListViewHeightBasedOnChildren(listView);
        }
        stopProgressDialog();
    }

    @Override
    public void reviewSuccess(Data data) {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
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
