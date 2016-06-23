package com.lltech.manager.fragment.js;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.DateUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lcx.mysdk.view.MyDialog;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.Mark;
import com.lltech.manager.common.RequestCode;
import com.lltech.manager.common.WxState;
import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.entity.pg.Pg;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.LdView;
import com.lltech.manager.widget.BottomWindow;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

/**
 * @ClassName(类名) : LdFragment
 * @Description(描述) : 领单
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 14:16
 */
public class LdFragment extends BaseFragment implements View.OnClickListener, LdView {

    private BaseApplication application = BaseApplication.getApplication();
    private TextView txt_person, txt_pg_person, txt_time, txt_explain, txt_detail_title, txt_detail_info;
    private TopBar topBar;
    private BottomWindow bottomWindow;
    private Button commit, roll_back, cancel;
    private MyDialog mDialog;
    private Pg pg;
    private BxBiz bxBiz = new BxBiz(this);

    @Override
    public int setContentView() {
        return R.layout.detail_ld;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        txt_person = $(R.id.txt_person);
        txt_pg_person = $(R.id.txt_pg_person);
        txt_time = $(R.id.txt_time);
        txt_explain = $(R.id.txt_explain);
        txt_detail_title = $(R.id.txt_detail_title);
        txt_detail_info = $(R.id.txt_detail_info);
        bottomWindow = new BottomWindow(getActivity(), R.layout.bottom_window_ld);
        commit = bottomWindow.getView(R.id.commit);
        roll_back = bottomWindow.getView(R.id.roll_back);
        cancel = bottomWindow.getView(R.id.cancel);
    }

    @Override
    public void initListener() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int btnType, View view) {
                bottomWindow.show(topBar);
            }

            @Override
            public void onLeftClick(View view) {
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        });
        txt_detail_info.setOnClickListener(this);
        commit.setOnClickListener(this);
        roll_back.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnType(TopBar.RIGHT2, TopBar.MORE);
        topBar.setTopText("领单");
    }

    @Override
    public void initData() {
        Intent intent = getActivity().getIntent();
        pg = (Pg) intent.getExtras().getSerializable("item");
        setData();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(application, CommonAty.class);
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.txt_detail_info: {
                switch (pg.getDistributionClass()) {
                    case "Dic_Repair":
                        application.setMark(Mark.WX_DETAIL);
                        application.setOperMark(Mark.OperMark.EDIT);
                        Wx wx = new Wx();
                        wx.setRepairID(pg.getRepairID());
                        wx.setState(WxState.FINISH);
                        bundle.putSerializable("item", wx);
                        break;
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
            break;
            case R.id.commit:
                //确认领单
                startProgressDialog();
                bxBiz.ld(pg.getDistributionID());
                break;
            case R.id.roll_back: {
                //退回领单
                application.setMark(Mark.TD_DETAIL);
                bundle.putSerializable("item", pg);
                intent.putExtras(bundle);
                startActivityForResult(intent, RequestCode.TD_EDIT);
            }
            break;
            case R.id.cancel:
                bottomWindow.dismiss();
                break;
        }
    }

    private void setData() {
        StringBuffer sb = new StringBuffer();
        sb.append(DateUtils.date2String(DateUtils.string2Date(pg.getLastBeginTime(), "yyyy/MM/dd HH:mm:ss"), "yyyy-MM-dd"));
        sb.append(" 至 ");
        sb.append(DateUtils.date2String(DateUtils.string2Date(pg.getLastEndTime(), "yyyy/MM/dd HH:mm:ss"), "yyyy-MM-dd"));
        txt_person.setText(pg.getRepairUsers());
        txt_pg_person.setText(pg.getDistributionUser());
        txt_time.setText(sb.toString());
        txt_explain.setText(pg.getExplain());
        switch (pg.getDistributionClass()) {
            case "Dic_Repair":
                txt_detail_title.setText("查看维修单信息");
                txt_detail_info.setText(pg.getRepairTitle());
                break;
        }
    }

    private void tipCd() {
        mDialog = new MyDialog(getActivity()).setTitle("提示").setMessage("要立即执行吗？").setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                getFragmentManager().beginTransaction()
                        .replace(R.id.content, new CdFragment()).commit();
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        });
        mDialog.setCancelable(false);
        mDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onLdSuccess() {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                bottomWindow.dismiss();
                tipCd();
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
