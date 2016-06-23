package com.lltech.manager.fragment.yy;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.DateUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lcx.mysdk.view.DateTimePicker;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.activity.dialog.EmployeeAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.DiaLogCode;
import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.entity.pg.Pg;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.MyData;
import com.lltech.manager.view.PgView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.HashMap;

/**
 * @ClassName(类名) : PgFragment
 * @Description(描述) : 派工
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月19日 10:46
 */
public class PgFragment extends BaseFragment implements View.OnClickListener, PgView {

    private BaseApplication application = BaseApplication.getApplication();
    private TextView txt_person, txt_startTime, txt_LastTime;
    private EditText edit_explain;
    private TopBar topBar;
    private Wx wx;
    private HashMap<String, String> map = new HashMap<String, String>();
    private BxBiz bxBiz = new BxBiz(this);

    @Override
    public int setContentView() {
        return R.layout.detail_pg;
    }

    @Override
    public void initView() {
        txt_person = $(R.id.txt_person);
        txt_startTime = $(R.id.txt_startTime);
        txt_LastTime = $(R.id.txt_LastTime);
        edit_explain = $(R.id.edit_explain);
        topBar = $(R.id.top);
    }

    @Override
    public void initListener() {
        txt_person.setOnClickListener(this);
        txt_startTime.setOnClickListener(this);
        txt_LastTime.setOnClickListener(this);
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (whitchBtn) {
                    case TopBar.RIGHT2:
                        //点击确定按钮的点击事件
                        startProgressDialog();
                        pg();
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
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.getRightBtn2().setText(R.string.submit);
        topBar.getRightBtn2().setBackground(null);
        topBar.setTopText("派工");
    }

    @Override
    public void initData() {
        wx = (Wx) getActivity().getIntent().getExtras().getSerializable("item");
    }

    private void pg() {
        Pg pg = new Pg();
        pg.setRepairUsers(map.get("RepairUsersId"));
        pg.setLastBeginTime(txt_startTime.getText().toString());
        pg.setLastEndTime(txt_LastTime.getText().toString());
        pg.setExplain(edit_explain.getText().toString());
        pg.setFaultReportID(wx.getRepairID());
        pg.setDistributionUser(MyData.getUser(User.class).getEmployeeID());
        pg.setDistributionTime(DateUtils.date2StringBySecond(DateUtils.currentDateTime()));
        pg.setRemark("");
        bxBiz.wxPg(pg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_person:
                Intent intent1 = new Intent(getActivity(), EmployeeAty.class);
                startActivityForResult(intent1, DiaLogCode.EMPLOYEE);
                break;
            case R.id.txt_startTime:
                DateTimePicker.getInstance().setTime(getActivity(), txt_startTime);
                break;
            case R.id.txt_LastTime:
                DateTimePicker.getInstance().setTime(getActivity(), txt_LastTime);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case DiaLogCode.EMPLOYEE:
                map.put("RepairUsersId", data.getStringExtra("id"));
                txt_person.setText(data.getStringExtra("text"));
                break;
        }
    }

    @Override
    public void onPgSuccess() {
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
