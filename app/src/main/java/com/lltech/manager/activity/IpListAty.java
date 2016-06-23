package com.lltech.manager.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.utils.DensityUtils;
import com.lltech.manager.R;
import com.lltech.manager.entity.IpItem;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.MyResultCode;
import com.lltech.manager.widget.TopBar;

import org.litepal.crud.DataSupport;

import java.util.LinkedList;
import java.util.List;


/**
 * @ClassName(类名) : IpListAty
 * @Description(描述) : Ip列表
 * @author(作者) ：zhangyan
 * @date (开发日期)      ：2016年04月19日 11:26
 */
public class IpListAty extends BaseActivity {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private SwipeMenuListView moreList;
    private SwipeMenuCreator creator;
    private List<IpItem> list = new LinkedList<IpItem>();
    private QuickAdapter<IpItem> adapter;
    private IpItem lastItem;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_ip);
    }

    @Override
    public void initViews() {
        topBar = $(R.id.top);
        moreList = $(R.id.moreList);
    }

    @Override
    public void initListeners() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int btnType, View view) {
                Intent intent = new Intent(application, UrlAddAty.class);
                startActivityForResult(intent, MyResultCode.IP_CODE);
            }

            @Override
            public void onLeftClick(View view) {
                finish();
            }
        });
        moreList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                if (!IpItem.Y.equals(list.get(position).getChoose())) {
                    int flag = list.get(position).delete();
                    if (flag > 0) {
                        list.remove(position);
                        adapter.replaceAll(list);
                        moreList.setAdapter(adapter);
                        Toast.makeText(application, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
        moreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IpItem checkIp = (IpItem) parent.getAdapter().getItem(position);
                if (lastItem == checkIp) {
                    return;
                }
                lastItem.setChoose(IpItem.N);
                lastItem.save();
                checkIp.setChoose(IpItem.Y);
                checkIp.save();
                MyData.setUrl(checkIp.getIp() + ":" + checkIp.getPort());
                list = DataSupport.findAll(IpItem.class);
                adapter.replaceAll(list);
                moreList.setAdapter(adapter);
            }
        });
    }

    @Override
    public void initData() {
        list = DataSupport.findAll(IpItem.class);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setTopText(getString(R.string.iplist_title));
        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(DensityUtils.dp2px(application, 70));
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };
        adapter = new QuickAdapter<IpItem>(application, R.layout.item_ip, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, IpItem item) {
                helper.setText(R.id.project_name, item.getIp());
                helper.setText(R.id.project_address, item.getPort());
                if (IpItem.Y.equals(item.getChoose())) {
                    helper.setVisible(R.id.img, true);
                    lastItem = item;
                } else {
                    helper.setVisible(R.id.img, false);
                }
            }
        };
        moreList.setAdapter(adapter);
        moreList.setMenuCreator(creator);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case MyResultCode.IP_CODE: {
                list = DataSupport.findAll(IpItem.class);
                adapter.replaceAll(list);
                moreList.setAdapter(adapter);
                break;
            }
        }
    }
}
