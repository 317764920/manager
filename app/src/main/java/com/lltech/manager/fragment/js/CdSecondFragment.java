package com.lltech.manager.fragment.js;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.common.BxState;
import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.entity.pg.Pg;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.CdSecondView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

/**
 * @ClassName(类名) : CdSecondFragment
 * @Description(描述) : 报修，保养存档
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 14:16
 */
public class CdSecondFragment extends BaseFragment implements CdSecondView {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private EditText edit_desc;
    private Bx bx = new Bx();
    private Pg pg = new Pg();
    private BxBiz bxBiz = new BxBiz(this);

    @Override
    public int setContentView() {
        return R.layout.detail_cd_second;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        edit_desc = $(R.id.edit_desc);
    }

    @Override
    public void initListener() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int btnType, View view) {
                startProgressDialog();
                bxBiz.inArchives(bx.getFaultReportID(), pg.getDistributionID(), ViewUtils.getText(edit_desc));
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
        topBar.getRightBtn2().setBackground(null);
        topBar.getRightBtn2().setText("存档");
        topBar.setTopText("报修执行");
    }

    @Override
    public void initData() {
        bx = (Bx) getArguments().getSerializable("item");
        pg = (Pg) getArguments().getSerializable("item1");
    }

    @Override
    public void InArchivesSuccess(Data data) {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                bx.setState(BxState.YY);
                Intent intent = getActivity().getIntent();
                intent.putExtra("item", bx);
                getActivity().setResult(Activity.RESULT_OK, intent);
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
