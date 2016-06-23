package com.lltech.manager.activity.me;

import android.view.View;
import android.widget.TextView;

import com.lcx.mysdk.activity.BaseActivity;
import com.lltech.manager.R;
import com.lltech.manager.common.SexState;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.util.MyData;
import com.lltech.manager.widget.TopBar;

public class UserInfoAty extends BaseActivity {
    private TopBar topBar;
    private User user;

    private TextView LoginID, Sex, DeptName, Remark, CompanyName, EmployeeName, PostName;

    @Override
    public void setContentView() {
        setContentView(R.layout.detail_me_userinfo);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        LoginID = $(R.id.LoginID);
        Sex = $(R.id.Sex);
        DeptName = $(R.id.DeptName);
        Remark = $(R.id.Remark);
        CompanyName = $(R.id.CompanyName);
        EmployeeName = $(R.id.EmployeeName);
        PostName = $(R.id.PostName);
    }

    @Override
    public void initListeners() {
        topBar.getLeftBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        user = MyData.getUser(User.class);
        setData();
    }

    @Override
    public void initConfig() {
        topBar.setTopText("个人信息");
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        setTableChildFalse();
    }

    private void setData() {
        LoginID.setText(user.getUserAccount());
        Sex.setText(SexState.getString(user.getSex()));
        DeptName.setText(user.getDepartmentName());
        Remark.setText(user.getRemark());
        CompanyName.setText(user.getCompanyName());
        EmployeeName.setText(user.getUserName());
        PostName.setText(user.getPostName());
    }
}
