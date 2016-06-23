package com.lltech.manager.activity.dialog;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.utils.DateUtils;
import com.lltech.manager.R;
import com.lltech.manager.widget.DialogQueryView;
import com.lltech.manager.widget.TopBar;

/**
 * @ClassName(类名) : DialogListAty
 * @Description(描述) : 对话框列表基类
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 上午9:36:30
 */
public abstract class DialogListAty extends BaseActivity {

    public DialogQueryView dialogQueryView;
    public PullToRefreshListView pullToRefresh;
    public TopBar topBar;

    @Override
    public void setContentView() {
        setContentView(R.layout.dialog_baselist);
    }

    @Override
    public void initViews() {
        dialogQueryView = $(R.id.top_query);
        pullToRefresh = $(R.id.base_list);
        topBar = $(R.id.top);
        setAdapter();
    }

    @Override
    public void initListeners() {
        topBar.getLeftBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pullToRefresh.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setItemClick(parent, view, position, id);
            }
        });
        pullToRefresh.setOnRefreshListener(new OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
                        "最近更新：" + DateUtils.date2String(DateUtils.currentDateTime(), "MM-dd HH:mm"));
                pullDown();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pullUp();
            }

        });
    }

    /**
     * @throws : void
     * @Description(功能描述) : 设置刷新列表适配器
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年8月26日 上午11:46:29
     */
    public abstract void setAdapter();

    /**
     * @param parent
     * @param view
     * @param positon
     * @param id      void
     * @throws :
     * @Description(功能描述) : 设置列表项点击
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年8月26日 下午5:26:09
     */
    public abstract void setItemClick(AdapterView<?> parent, View view, int positon, long id);

    /**
     * @throws : void
     * @Description(功能描述) : 下拉获取数据
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年8月30日 下午2:53:53
     */
    public abstract void pullDown();

    /**
     * @throws : void
     * @Description(功能描述) : 上拉获取数据
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年8月30日 下午2:53:57
     */
    public abstract void pullUp();

}
