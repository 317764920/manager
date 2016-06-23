package com.lltech.manager.fragment.index;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.activity.bx.BxListAty;
import com.lltech.manager.activity.bx.WxListAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.activity.pg.PgListAty;
import com.lltech.manager.common.Action;
import com.lltech.manager.common.Mark;
import com.lltech.manager.entity.Yy;
import com.lltech.manager.entity.widget.PopMenuItem;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.widget.MyGridView;
import com.lltech.manager.widget.PopupMenu;
import com.lltech.manager.widget.TopBar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : YyHomeFragment
 * @Description(描述) : 运营端
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月11日 16:03
 */
public class YyHomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private PopupMenu popupMenu;
    private List<PopMenuItem> poplist = new ArrayList<PopMenuItem>();
    private UpdateUIBroadcastReceiver broadcastReceiver;
    private MyGridView gridView;
    private LinkedList<Yy> list = new LinkedList<Yy>();
    private QuickAdapter<Yy> adapter;

    @Override
    public int setContentView() {
        return R.layout.yy_tab_home;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        popupMenu = new PopupMenu(getActivity());
        gridView = $(R.id.gridView);
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
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.LEFT, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setTopText("首页");
        // 动态注册广播更新界面UI
        IntentFilter filter = new IntentFilter();
        filter.addAction(Action.ACTION_UPDATEUI);
        broadcastReceiver = new UpdateUIBroadcastReceiver();
        getActivity().registerReceiver(broadcastReceiver, filter);
        adapter = new QuickAdapter<Yy>(application, R.layout.item_grid, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, Yy item) {
                helper.setText(R.id.title, item.getTitle());
                helper.setText(R.id.entry, item.getEntry());
            }
        };
        gridView.setAdapter(adapter);
    }

    @Override
    public void initData() {
//        show();
        PopMenuItem item1 = new PopMenuItem(R.drawable.pop_bx, "我要保养");
        PopMenuItem item2 = new PopMenuItem(R.drawable.pop_bx, "我要报修");
        PopMenuItem item3 = new PopMenuItem(R.drawable.pop_bx, "创建维修单");
        poplist.add(item1);
        poplist.add(item2);
        poplist.add(item3);
        popupMenu.loadMenu(poplist);
        Yy yy = new Yy();
        yy.setTitle("待生成维修单");
        yy.setEntry("1");
        Yy yy1 = new Yy();
        yy1.setTitle("待生成保养单");
        yy1.setEntry("2");
        Yy yy2 = new Yy();
        yy2.setTitle("待生成巡检单");
        yy2.setEntry("3");
        Yy yy3 = new Yy();
        yy3.setTitle("维修待派工");
        yy3.setEntry("4");
        Yy yy4 = new Yy();
        yy4.setTitle("保养待派工");
        yy4.setEntry("5");
        Yy yy5 = new Yy();
        yy5.setTitle("巡检待派工");
        yy5.setEntry("6");
        Yy yy6 = new Yy();
        yy6.setTitle("维修待确认");
        yy6.setEntry("7");
        Yy yy7 = new Yy();
        yy7.setTitle("保养待确认");
        yy7.setEntry("8");
        Yy yy8 = new Yy();
        yy8.setTitle("巡检待确认");
        yy8.setEntry("9");
        list.add(yy);
        list.add(yy1);
        list.add(yy2);
        list.add(yy3);
        list.add(yy4);
        list.add(yy5);
        list.add(yy6);
        list.add(yy7);
        list.add(yy8);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: {
                Intent intent = new Intent(application, BxListAty.class);
                startActivity(intent);
                break;
            }
            case 1: {

                break;
            }
            case 2: {

                break;
            }
            case 3: {
                Intent intent = new Intent(application, WxListAty.class);
                startActivity(intent);
                break;
            }
            case 4: {

                break;
            }
            case 5: {

                break;
            }
            case 6: {
                Intent intent = new Intent(application, PgListAty.class);
                startActivity(intent);
                break;
            }
            case 7: {

                break;
            }
            case 8: {

                break;
            }
        }
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
