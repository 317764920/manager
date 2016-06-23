package com.lcx.mysdk.utils;

import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @ClassName(类名) : ViewUtils
 * @Description(描述) : View帮助类
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年04月01日 17:20
 */
public class ViewUtils {
    /**
     * @Description(功能描述) : 设置listview高度（根据孩子计算）
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-01 17:22
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * @Description(功能描述) : 设置gridview高度（根据孩子计算）
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-01 17:22
     */
    public static void setGridViewHeightBasedOnChildren(GridView gridView) {
        // 获取listview的adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = gridView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        // 设置高度
        params.height = totalHeight + (gridView.getHorizontalSpacing() * (listAdapter.getCount() - 1));
        // 设置参数
        gridView.setLayoutParams(params);
    }

    /**
     * @param message
     * @return CharSequence
     * @throws :
     * @Description(功能描述) : 设置验证错误信息
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年9月29日 上午11:38:28
     */
    public static CharSequence setErrorValidateMsg(String message) {
        return Html.fromHtml("<font color='red'>" + message + "</font>");
    }

    /**
     * 获取控件文本
     *
     * @liuchunxu
     * @2016-06-07 10:40
     */
    public static String getText(View view) {
        if (view instanceof TextView) {
            return ((TextView) view).getText().toString().trim();
        } else if (view instanceof EditText) {
            return ((EditText) view).getText().toString().trim();
        } else if (view instanceof Button) {
            return ((Button) view).getText().toString().trim();
        }
        return "";
    }
}
