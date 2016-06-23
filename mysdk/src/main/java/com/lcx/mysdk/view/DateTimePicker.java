package com.lcx.mysdk.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.lcx.mysdk.R;
import com.lcx.mysdk.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.aigestudio.datepicker.cons.DPMode;

/**
 * 日期时间选择控件 使用方法：
 * private EditText inputDate;//需要设置的日期时间文本编辑框
 * private String
 * initDateTime="2012年9月3日 14:44",//初始日期时间值 在点击事件中使用：
 * inputDate.setOnClickListener(new
 * OnClickListener() {
 *
 * @author
 * @Override public void onClick(View v) {
 * DateTimePicker dateTimePicKDialog=new
 * DateTimePicker(SinvestigateActivity.this,initDateTime);
 * dateTimePicKDialog.dateTimePicKDialog(inputDate);
 * <p/>
 * } });
 */
public class DateTimePicker implements OnDateChangedListener, OnTimeChangedListener {
    private static DateTimePicker dateTimePickDialogUtil;
    private LinearLayout dateTimeLayout;
    private DatePicker datePicker;
    private cn.aigestudio.datepicker.views.DatePicker picker;
    private TimePicker timePicker;
    private AlertDialog ad1, ad2;
    private Calendar calendar;
    private SimpleDateFormat sdf;
    private String dateTime;
    private String initDateTime;

    public static DateTimePicker getInstance() {
        if (dateTimePickDialogUtil == null) {
            dateTimePickDialogUtil = new DateTimePicker();
        }
        return dateTimePickDialogUtil;
    }

    public void init(DatePicker datePicker, TimePicker timePicker) {
        if (calendar == null)
            calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime))) {
            calendar = this.getCalendarByInintData();
        } else {
            initDateTime = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH)
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        }

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    /**
     * 弹出日期时间选择框方法(yyyy-MM-dd hh:mm:ss)
     *
     * @param view :为需要设置的日期时间文本编辑框
     * @return
     */
    public void setTimeSecond(Activity activity, final TextView view) {
        initDateTime = DateUtils.date2String(DateUtils.currentDateTime());
        if (dateTimeLayout == null)
            dateTimeLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
        init(datePicker, timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);
//        if (ad1 == null)
        ad1 = new AlertDialog.Builder(activity).setTitle(initDateTime).setView(dateTimeLayout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        view.setText(dateTime);
                        ad1.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        view.setText("");
                        ad1.dismiss();
                    }
                }).create();
        ad1.show();
        onDateChanged(null, 0, 0, 0);
    }

    /**
     * 弹出日期时间选择框方法(yyyy-MM-dd)
     *
     * @param view :为需要设置的日期时间文本编辑框
     * @return
     */
    public void setTime(Activity activity, final TextView view) {
//        if (picker == null)
        cn.aigestudio.datepicker.views.DatePicker picker = new cn.aigestudio.datepicker.views.DatePicker(activity);
        picker.setDate(DateUtils.currYear(), DateUtils.currMonth());
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new cn.aigestudio.datepicker.views.DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                view.setText(DateUtils.m2MM(date));
                ad2.dismiss();
            }
        });
//        if (ad2 == null)
        ad2 = new AlertDialog.Builder(activity).setView(picker).create();
        ad2.show();
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // 获得日历实例
        if (calendar == null)
            calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        if (sdf == null)
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateTime = sdf.format(calendar.getTime());
        ad1.setTitle(dateTime);
    }

    /**
     * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
     *
     * @return Calendar
     */
    private Calendar getCalendarByInintData() {
        if (calendar == null)
            calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
//		String date = spliteString(initDateTime, "日", "index", "front"); // 日期
//		String time = spliteString(initDateTime, "日", "index", "back"); // 时间
//
//		String yearStr = spliteString(date, "年", "index", "front"); // 年份
//		String monthAndDay = spliteString(date, "年", "index", "back"); // 月日
//
//		String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
//		String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日
//
//		String hourStr = spliteString(time, ":", "index", "front"); // 时
//		String minuteStr = spliteString(time, ":", "index", "back"); // 分

//		int currentYear = DateUtils.currYear();
//		int currentMonth = DateUtils.currMonth();
//		int currentDay = DateUtils.
//		int currentHour = Integer.valueOf(hourStr.trim()).intValue();
//		int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

        return calendar;
    }

    /**
     * 截取子串
     *
     * @param srcStr      源串
     * @param pattern     匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String spliteString(String srcStr, String pattern, String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }

}
