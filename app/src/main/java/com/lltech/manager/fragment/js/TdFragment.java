package com.lltech.manager.fragment.js;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.DateUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.ViewUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.biz.impl.BxBiz;
import com.lltech.manager.entity.pg.Apply;
import com.lltech.manager.entity.pg.Pg;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.util.MyData;
import com.lltech.manager.view.TdView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.TopBar;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : TdFragment
 * @Description(描述) : 退单
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月01日 16:18
 */
public class TdFragment extends BaseFragment implements View.OnClickListener, TdView {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private ListView listView;
    private EditText reason;
    private Button save;
    private QuickAdapter<String> adapter;
    private List<String> list = new LinkedList<String>();
    private Pg pg;
    private String checkItem;
    private BxBiz bxBiz = new BxBiz(this);

    @Override
    public int setContentView() {
        return R.layout.detail_td;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        listView = $(R.id.listView);
        reason = $(R.id.reason);
        save = $(R.id.save);
    }

    @Override
    public void initListener() {
        topBar.getLeftBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getAdapter().getItem(position);
                checkItem = item;
                if (item.contains("其他")) {
                    reason.setText(null);
                    reason.setVisibility(View.VISIBLE);
                } else {
                    reason.setText(checkItem);
                    reason.setVisibility(View.GONE);
                }
                adapter.refresh();
            }
        });
        save.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        topBar.setTopText("退单原因");
        adapter = new QuickAdapter<String>(application, R.layout.item_td, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.item_text, item);
                if (item.equals(checkItem)) {
                    helper.setVisible(R.id.img, true);
                } else {
                    helper.setVisible(R.id.img, false);
                }
            }
        };
        listView.setAdapter(adapter);
        ViewUtils.setListViewHeightBasedOnChildren(listView);
    }

    @Override
    public void initData() {
        pg = (Pg) getArguments().getSerializable("item");
        list = Arrays.asList(getResources().getStringArray(R.array.tdData));
        checkItem = list.get(0);
        reason.setText(checkItem);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                startProgressDialog();
                save();
                break;
        }
    }

    private void save() {
        Apply apply = new Apply();
        apply.setApplyDesc(reason.getText().toString());
        apply.setDistributionID(pg.getDistributionID());
        apply.setApplyFromUser(MyData.getUser(User.class).getEmployeeID());
        apply.setApplyTime(DateUtils.date2String(DateUtils.currentDateTime()));
        apply.setHandleUser(pg.getDistributionUserID());
        bxBiz.td(apply);
    }

    @Override
    public void onTdSuccess() {
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
