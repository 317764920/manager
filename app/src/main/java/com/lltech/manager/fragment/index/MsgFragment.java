package com.lltech.manager.fragment.index;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.activity.MsgListAty;
import com.lltech.manager.activity.msg.MsgSendAty;
import com.lltech.manager.common.Mark;
import com.lltech.manager.entity.MsgItem;
import com.lltech.manager.interfaces.TopBarClickListener;
import com.lltech.manager.widget.TopBar;

import java.util.LinkedList;

/**
 * @ClassName(类名) : MsgFragment
 * @Description(描述) : 消息
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:13
 */
public class MsgFragment extends BaseFragment {
    private BaseApplication application = BaseApplication.getApplication();
    private ListView project_list;
    private QuickAdapter<MsgItem> adapter;
    private LinkedList<MsgItem> list = new LinkedList<MsgItem>();
    private TopBar topBar;

    @Override
    public int setContentView() {
        return R.layout.tab_msg;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        project_list = $(R.id.project_list);
    }

    @Override
    public void initListener() {
        topBar.setOnTopBarClickListener(new TopBarClickListener() {
            @Override
            public void onRightClick(int btnType, View view) {
                Intent intent = new Intent(getActivity(), MsgSendAty.class);
                getActivity().startActivity(intent);
            }

            @Override
            public void onLeftClick(View view) {

            }
        });
        project_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(application, MsgListAty.class);
                switch (position) {
                    case 0: {
                        application.setMark(Mark.MSG_TZ);
                        break;
                    }
                    case 1: {
                        application.setMark(Mark.MSG_GR);
                        break;
                    }
                    case 2: {
                        application.setMark(Mark.MSG_YJ);
                        break;
                    }
                    case 3: {
                        application.setMark(Mark.MSG_BJ);
                        break;
                    }
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.LEFT, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setTopText("消息");
        adapter = new QuickAdapter<MsgItem>(getActivity(), R.layout.item_msg, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, MsgItem item) {
                helper.setImageResource(R.id.img, item.getImgResId());
                helper.setText(R.id.name, item.getName());
                helper.setText(R.id.text, item.getText());
            }
        };
        project_list.setAdapter(adapter);
    }

    @Override
    public void initData() {
//        show();
        MsgItem msgItem1 = new MsgItem(R.drawable.tzgg, "通知公告", "通知公告");
        MsgItem msgItem2 = new MsgItem(R.drawable.nbxx, "个人消息", "张三收到请回答");
        MsgItem msgItem3 = new MsgItem(R.drawable.warn, "预警消息", "XX设备有预警提示");
        MsgItem msgItem4 = new MsgItem(R.drawable.warn, "报警消息", "XXX设备出现故障，请立即维修");
        list.add(msgItem1);
        list.add(msgItem2);
        list.add(msgItem3);
        list.add(msgItem4);
    }
}
