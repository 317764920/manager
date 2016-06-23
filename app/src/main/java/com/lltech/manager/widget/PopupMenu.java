package com.lltech.manager.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.utils.CommonUtil;
import com.lltech.manager.R;
import com.lltech.manager.entity.widget.PopMenuItem;

import java.util.ArrayList;
import java.util.List;

public class PopupMenu extends PopupWindow implements AdapterView.OnItemClickListener {

    private Activity activity;
    private View popView;
    private ListView menu_list;
    private OnItemClickListener onItemClickListener;
    private List<PopMenuItem> list = new ArrayList<PopMenuItem>();
    private QuickAdapter<PopMenuItem> adapter;

    public PopupMenu(Activity activity) {
        super(activity);
        this.activity = activity;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popView = inflater.inflate(R.layout.popup_menu, null);// 加载菜单布局文件
        this.setContentView(popView);// 把布局文件添加到popupwindow中
        this.setWidth(dip2px(activity, 150));// 设置菜单的宽度（需要和菜单于右边距的距离搭配，可以自己调到合适的位置）
        this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);// 获取焦点
        this.setTouchable(true); // 设置PopupWindow可触摸
        this.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
        ColorDrawable dw = new ColorDrawable(0x00000000);
        setBackgroundDrawable(dw);
        // 获取选项卡
        menu_list = (ListView) popView.findViewById(R.id.menu_list);
        // 添加监听
        menu_list.setOnItemClickListener(this);
        adapter = new QuickAdapter<PopMenuItem>(activity, R.layout.item_popmenu, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, PopMenuItem item) {
                helper.setImageResource(R.id.item_img, item.getImgRes());
                helper.setText(R.id.item_text, item.getText());
            }
        };
        menu_list.setAdapter(adapter);
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            // Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (CommonUtil.isNotEmpty(onItemClickListener)) {
            PopMenuItem item = (PopMenuItem) parent.getAdapter().getItem(position);
            onItemClickListener.onItemClick(item, position);
        }
        dismiss();
    }

    /**
     * 设置显示的位置
     *
     * @param view 这里的x,y值自己调整可以
     */
    public void showLocation(View view) {
        // 我觉得这里是API的一个bug
        backgroundAlpha(0.8f);
        setOnDismissListener(new poponDismissListener());
        showAsDropDown(view, dip2px(activity, 0),
                dip2px(activity, 10));
    }

    /**
     * 加载菜单
     *
     * @liuchunxu
     * @2016-05-11 11:17
     */
    public void loadMenu(List<PopMenuItem> list) {
        if (CommonUtil.isNotEmpty(adapter)) {
            this.list = list;
            adapter.replaceAll(list);
        }
    }

    // dip转换为px
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    // 点击监听接口
    public interface OnItemClickListener {
        void onItemClick(PopMenuItem item, int position);
    }

    // 设置监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
