package com.lltech.manager.fragment.msg;

import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.entity.Test;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;


/**
 * @ClassName(类名) : BjFragment
 * @Description(描述) : 报警消息
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 09:50
 */
public class BjFragment extends BaseFragment {

    private PullToRefreshListView pullToRefreshListView;
    private TopBar topBar;
    private QuickAdapter<Test> adapter;

    @Override
    public int setContentView() {
        return R.layout.baselist;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        pullToRefreshListView = $(R.id.pullToRefreshListView);
    }

    @Override
    public void initListener() {
        topBar.getLeftBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().finish();
            }
        });
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        topBar.setTopText("报警消息");
        adapter = new QuickAdapter<Test>(getActivity(), R.layout.item_msg_second, new LinkedList<Test>()) {
            @Override
            protected void convert(BaseAdapterHelper helper, Test item) {
                helper.setImageResource(R.id.img, R.drawable.warn);

            }
        };
        pullToRefreshListView.setAdapter(adapter);
        //去掉listview的selector效果
        pullToRefreshListView.getRefreshableView().setSelector(R.color.transparent);
    }

    @Override
    public void initData() {
    }
}
