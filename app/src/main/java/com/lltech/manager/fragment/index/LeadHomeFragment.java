package com.lltech.manager.fragment.index;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.MultiItemTypeSupport;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lcx.mysdk.utils.DensityUtils;
import com.lltech.manager.R;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.activity.eq.EqListAty;
import com.lltech.manager.common.Action;
import com.lltech.manager.common.Mark;
import com.lltech.manager.entity.Test;
import com.lltech.manager.entity.widget.PopMenuItem;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.widget.PopupMenu;
import com.lltech.manager.widget.TopBar;
import com.mining.app.zxing.QrCodeActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : LeadHomeFragment
 * @Description(描述) : 领导端
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月11日 16:03
 */
public class LeadHomeFragment extends BaseFragment {
    private BaseApplication application = BaseApplication.getApplication();
    private SwipeMenuListView listView;
    private SwipeMenuCreator creator;
    private QuickAdapter<Test> adapter;
    private List<Test> list = new LinkedList<Test>();
    private TopBar topBar;
    private PopupMenu popupMenu;
    private List<PopMenuItem> poplist = new ArrayList<PopMenuItem>();
    private UpdateUIBroadcastReceiver broadcastReceiver;

    @Override
    public int setContentView() {
        return R.layout.yy_tab_home;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        listView = $(R.id.listView);
        popupMenu = new PopupMenu(getActivity());
    }

    @Override
    public void initListener() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int btnType, View view) {
                switch (btnType) {
                    case TopBar.RIGHT2: {
                        popupMenu.showLocation(topBar.getRightBtn2());
                        // 设置回调监听，获取点击事件
                        popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                            @Override
                            public void onItemClick(PopMenuItem item, int position) {
                                switch (position) {
                                    case 0: {
//                                        Intent intent = new Intent(getActivity(), BxAty.class);
//                                        getActivity().startActivity(intent);
                                        break;
                                    }
                                    case 1: {
                                        application.setMark(Mark.BX_DETAIL);
                                        application.setOperMark(Mark.OperMark.ADD);
                                        Intent intent = new Intent(getActivity(), CommonAty.class);
                                        getActivity().startActivity(intent);
                                        break;
                                    }
                                    case 2: {
                                        application.setMark(Mark.WX_DETAIL);
                                        application.setOperMark(Mark.OperMark.ADD);
                                        Intent intent = new Intent(getActivity(), CommonAty.class);
                                        getActivity().startActivity(intent);
                                        break;
                                    }
                                    case 3: {
                                        Intent intent = new Intent(getActivity(), EqListAty.class);
                                        getActivity().startActivity(intent);
                                        break;
                                    }
                                    case 4: {
                                        Intent intent = new Intent(getActivity(), QrCodeActivity.class);
                                        getActivity().startActivity(intent);
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

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int viewType = parent.getAdapter().getItemViewType((int) id);
                switch (viewType) {
                    case 1: {
//                        application.setMark(Mark.WX_DETAIL);
//                        Intent intent = new Intent(application, CommonAty.class);
//                        startActivity(intent);
                        break;
                    }
                    case 2: {
                        application.setMark(Mark.LD_DETAIL);
                        Intent intent = new Intent(application, CommonAty.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (menu.getViewType()) {
                    case 1: {
                        application.setMark(Mark.PG_DETAIL);
                        Intent intent = new Intent(application, CommonAty.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: {
                        break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.LEFT, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setTopText("首页");
        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem item = new SwipeMenuItem(application);
                switch (menu.getViewType()) {
                    case 1: {
                        item.setBackground(new ColorDrawable(getResources().getColor(R.color.lightgray)));
                        item.setWidth(DensityUtils.dp2px(application, 70));
                        item.setTitleColor(Color.WHITE);
                        item.setTitleSize(DensityUtils.sp2px(application, 9));
                        item.setTitle("派工");
                        break;
                    }
                    case 2: {
                        item.setBackground(new ColorDrawable(getResources().getColor(R.color.red)));
                        item.setWidth(DensityUtils.dp2px(application, 70));
                        item.setTitleColor(Color.WHITE);
                        item.setTitleSize(DensityUtils.sp2px(application, 9));
                        item.setTitle("确认");
                        break;
                    }
                }
                menu.addMenuItem(item);
            }
        };
        MultiItemTypeSupport<Test> multiItemTypeSupport = new MultiItemTypeSupport<Test>() {

            @Override
            public int getLayoutId(int position, Test test) {
                return R.layout.item_home_yy;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int postion, Test test) {
                if ("0".equals(test.getProject_state())) {
                    return 1;
                }
                return 2;
            }
        };
        adapter = new QuickAdapter<Test>(application, list, multiItemTypeSupport) {
            @Override
            protected void convert(BaseAdapterHelper helper, Test item) {
                if ("0".equals(item.getProject_state())) {
                    helper.setImageResource(R.id.img, R.drawable.bx_list_left4);
                } else {
                    helper.setImageResource(R.id.img, R.drawable.bx_list_left2);
                }
//                helper.setText(R.id.project_name,"保养计划");
//                helper.setText(R.id.project_time,"2016/05/06");
//                helper.setText(R.id.project_state,"派工");
            }
        };
        listView.setAdapter(adapter);
        listView.setMenuCreator(creator);
        // 动态注册广播更新界面UI
        IntentFilter filter = new IntentFilter();
        filter.addAction(Action.ACTION_UPDATEUI);
        broadcastReceiver = new UpdateUIBroadcastReceiver();
        getActivity().registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void initData() {
//        show();
        PopMenuItem item1 = new PopMenuItem(R.drawable.pop_bx, "我要保养");
        PopMenuItem item2 = new PopMenuItem(R.drawable.pop_bx, "我要报修");
        PopMenuItem item3 = new PopMenuItem(R.drawable.pop_bx, "创建维修单");
        PopMenuItem item4 = new PopMenuItem(R.drawable.pop_bx, "查看设备");
        PopMenuItem item5 = new PopMenuItem(R.drawable.pop_sys, "扫一扫");
        poplist.add(item1);
        poplist.add(item2);
        poplist.add(item3);
        poplist.add(item4);
        poplist.add(item5);
        popupMenu.loadMenu(poplist);
    }


    /**
     * @ClassName(类名) : UpdateUIBroadcastReceiver
     * @Description(描述) : 更新UI
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年06月02日 17:22
     */
    public class UpdateUIBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//        textView.setText(String.valueOf(intent.getExtras().getInt("count")));
            Log.d("首页接收到了广播了", "----------------------------------------");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销广播
        getActivity().unregisterReceiver(broadcastReceiver);
    }
}
