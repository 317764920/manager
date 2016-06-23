package com.lltech.manager.fragment.msg;

import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.DensityUtils;
import com.lcx.mysdk.utils.LoginManager;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.R;
import com.lltech.manager.activity.LoginAty;
import com.lltech.manager.biz.impl.BaseBiz;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.me.SystemMsg;
import com.lltech.manager.entity.widget.PopMenuItem;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.view.MsgView;
import com.lltech.manager.widget.Msg;
import com.lltech.manager.widget.MsgPopup;
import com.lltech.manager.widget.PopupMenu;
import com.lltech.manager.widget.TopBar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @ClassName(类名) : TzFragment
 * @Description(描述) : 通知公告
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月18日 09:50
 */
public class TzFragment extends BaseFragment implements View.OnClickListener, MsgView {

    private BaseApplication application = BaseApplication.getApplication();
    private PullToRefreshListView pullToRefreshListView;
    private TopBar topBar;
    private QuickAdapter<SystemMsg> adapter;
    private SystemMsg checkItem;
    private PopupMenu popupMenu;
    private List<PopMenuItem> poplist = new ArrayList<PopMenuItem>();
    private MsgPopup msgPopup;
    private BaseBiz baseBiz = new BaseBiz(this);

    @Override
    public int setContentView() {
        return R.layout.baselist;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        pullToRefreshListView = $(R.id.pullToRefreshListView);
        popupMenu = new PopupMenu(getActivity());
        msgPopup = new MsgPopup(application, DensityUtils.dp2px(application, 165), DensityUtils.dp2px(application, 40));
    }

    @Override
    public void initListener() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int whitchBtn, View view) {
                switch (whitchBtn) {
                    case TopBar.RIGHT2: {
                        popupMenu.showLocation(topBar.getRightBtn2());
                        // 设置回调监听，获取点击事件
                        popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                            @Override
                            public void onItemClick(PopMenuItem item, int position) {
                                switch (position) {
                                    case 0: {
                                        //未读公告0
                                        baseBiz.getMsgList(Req.PULL_DOWN, "All_meg", "0");
                                        break;
                                    }
                                    case 1: {
                                        //已读公告1
                                        baseBiz.getMsgList(Req.PULL_DOWN, "All_meg", "1");
                                        break;
                                    }
                                }
                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onLeftClick(View view) {
                getActivity().finish();
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                baseBiz.getMsgList(Req.PULL_DOWN, "All_meg", "0");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                baseBiz.getMsgList(Req.PULL_UP, "All_meg", "0");
            }
        });
        msgPopup.getRead().setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnType(TopBar.RIGHT2, TopBar.MORE);
        topBar.setTopText("通知公告");
        adapter = new QuickAdapter<SystemMsg>(getActivity(), R.layout.item_msg_second, new LinkedList<SystemMsg>()) {
            @Override
            protected void convert(BaseAdapterHelper helper, final SystemMsg item) {
                helper.setImageResource(R.id.img, R.drawable.tzgg);
                helper.setText(R.id.name, item.getSenderName());
                helper.setText(R.id.time, item.getSendTime());
                helper.setText(R.id.text, item.getMessageContent());
                switch (item.getState()) {
                    case "0":
                        helper.setVisible(R.id.img1, true);
                        helper.setOnClickListener(R.id.img1, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkItem = item;
                                msgPopup.setAnimationStyle(R.style.cricleBottomAnimation);
                                msgPopup.show(view);
                            }
                        });
                        break;
                    case "1":
                        helper.setVisible(R.id.img1, false);
                        break;
                }

            }
        };
        pullToRefreshListView.setAdapter(adapter);
        //去掉listview的selector效果
        pullToRefreshListView.getRefreshableView().setSelector(R.color.transparent);
    }

    @Override
    public void initData() {
        PopMenuItem item1 = new PopMenuItem(R.drawable.pop_bx, "查看未读");
        PopMenuItem item2 = new PopMenuItem(R.drawable.pop_bx, "查看已读");
        poplist.add(item1);
        poplist.add(item2);
        popupMenu.loadMenu(poplist);
        startProgressDialog();
        baseBiz.getMsgList(Req.PULL_DOWN, "All_meg", "0");
    }

    @Override
    public void onClick(View v) {
        startProgressDialog();
        baseBiz.updateMsg(checkItem.getSysMsgRecipientID());
    }

    @Override
    public void onLoad(List<SystemMsg> list) {
        adapter.replaceAll(list);
        pullToRefreshListView.onRefreshComplete(true);
        stopProgressDialog();
    }

    @Override
    public void onLoadMore(List<SystemMsg> list) {
        adapter.addAll(list);
        pullToRefreshListView.onRefreshComplete();
        stopProgressDialog();
    }

    @Override
    public void onUpdateSuccess() {
        stopProgressDialog(R.drawable.loading_dialog_gou, getString(R.string.oper_ok), new CloseListener() {
            @Override
            public void onDialogClose() {
                msgPopup.dismiss();
                pullToRefreshListView.setRefreshing();
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
