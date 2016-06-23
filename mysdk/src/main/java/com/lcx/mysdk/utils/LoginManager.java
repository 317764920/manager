package com.lcx.mysdk.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.lcx.mysdk.R;
import com.lcx.mysdk.view.MyDialog;

/**
 * @ClassName(类名) : LoginManager
 * @Description(描述) : 跳转管理(new一个新的intent对象)
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月24日 10:19
 */
public class LoginManager {
    private MyDialog dialog;

    /**
     * 去登录(有对话框提示)
     *
     * @liuchunxu
     * @2016-05-24 10:29
     */
    public void goToLogin(final Activity activity, final Class<?> where) {
        if (dialog == null) {
            dialog = new MyDialog(activity).setTitle("提示")
                    .setMessage("您的账号在别处登录或登录凭证已过期，请您重新登录")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity, where);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
                        }
                    })
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityManager.closeAll();
                        }
                    });
            dialog.setCancelable(false);
        }
        if (dialog != null)
            dialog.show();
    }

    /**
     * 去登录（是否关闭当前活动,有对话框提示）
     *
     * @liuchunxu
     * @2016-05-24 10:29
     */
    public void goToLogin(final Activity activity, final Class<?> where, final boolean closeThis) {
        if (dialog == null) {
            dialog = new MyDialog(activity).setTitle("提示")
                    .setMessage("您的账号在别处登录或登录凭证已过期，请您重新登录")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity, where);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.startActivity(intent);
                            if (closeThis) {
                                activity.finish();
                            }
                            activity.overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
                        }
                    })
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityManager.closeAll();
                        }
                    });
            dialog.setCancelable(false);
        }
        if (dialog != null)
            dialog.show();
    }

    /**
     * 去登录(无对话框提示)
     *
     * @liuchunxu
     * @2016-05-24 10:29
     */
    public void goToLoginNoTip(final Activity activity, final Class<?> where) {
        Intent intent = new Intent(activity, where);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
    }

    /**
     * 去登录（是否关闭当前活动,无对话框提示）
     *
     * @liuchunxu
     * @2016-05-24 10:29
     */
    public void goToLoginNoTip(final Activity activity, final Class<?> where, final boolean closeThis) {
        Intent intent = new Intent(activity, where);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        if (closeThis) {
            activity.finish();
        }
        activity.overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
    }
}
