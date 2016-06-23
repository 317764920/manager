package com.lcx.mysdk.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.ActivityManager;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.ResultCode;
import com.lcx.mysdk.view.LoadingDialog;
import com.mobsandgeeks.saripaar.Validator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseActivity extends Activity {

    // public CustomProgressDialog mDialog;
    public LoadingDialog lDialog;
    //	public MyDialog myDialog;
    public Validator validator;

    private List<TextView> tableText = new LinkedList<TextView>();
    private List<EditText> tableEdit = new LinkedList<EditText>();

    /**
     * @param viewID
     * @return T
     * @throws :
     * @Description(功能描述) : 加载view
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年8月19日 上午11:33:57
     */
    @SuppressWarnings("unchecked")
    public <T> T $(int viewID) {
        View view = findViewById(viewID);
        if (view instanceof TextView) {
            tableText.add((TextView) view);
        } else if (view instanceof EditText) {
            tableEdit.add((EditText) view);
        } else if (view instanceof SwipeMenuListView) {

        }
        return (T) view;
    }

    /**
     * @param v
     * @param viewID
     * @return T
     * @throws :
     * @Description(功能描述) : 加载view
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年11月24日 下午7:59:28
     */
    @SuppressWarnings("unchecked")
    public <T> T $(View v, int viewID) {
        View view = v.findViewById(viewID);
        // if (view instanceof TextView) {
        // tableText.add((TextView) view);
        // } else if (view instanceof EditText) {
        // tableEdit.add((EditText) view);
        // } else if (view instanceof SwipeMenuListView) {
        //
        // }
        return (T) view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        // 定制流程
        setContentView();
        if (validator == null) {
            validator = new Validator(this);
        }
        initViews();
        initListeners();
        initData();
        initConfig();
    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (isCreate) {
//            isCreate = false;
//
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
        stopProgressDialog();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Log.i("BaseActivity", "清空请求队列");
        BaseApplication.getApplication().getQueue().cancelAll(this);
//		Msg.cancel(this);
        tableText.clear();
        tableEdit.clear();
    }

    /**
     * @throws : void
     * @Description(功能描述) : 加载布局
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年7月7日 下午3:50:18
     */
    public abstract void setContentView();

    /**
     * @throws : void
     * @Description(功能描述) : 初始化组件
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年7月7日 下午3:50:27
     */
    public abstract void initViews();

    /**
     * @throws : void
     * @Description(功能描述) : 设置监听
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年7月7日 下午3:50:35
     */
    public abstract void initListeners();

    /**
     * @throws : void
     * @Description(功能描述) : 初始数据获取
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年7月7日 下午3:50:43
     */
    public abstract void initData();

    /**
     * @throws : void
     * @Description(功能描述) : 相关设置
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年7月7日 下午4:22:23
     */
    public abstract void initConfig();

    /**
     * @throws : void
     * @Description(功能描述) : 设置表格中所有TextView和EditText不可点击
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年12月21日 上午10:41:01
     */
    public void setTableChildFalse() {
        for (int i = 0; i < tableText.size(); i++) {
            tableText.get(i).setEnabled(false);
        }
        for (int i = 0; i < tableEdit.size(); i++) {
            tableEdit.get(i).setEnabled(false);
        }
    }

    /**
     * @throws : void
     * @Description(功能描述) : 开始进度
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年8月31日 下午1:49:01
     */
    public void startProgressDialog() {
        // if (mDialog == null) {
        // mDialog = CustomProgressDialog.createDialog(this);
        // mDialog.setCanceledOnTouchOutside(false);
        // }
        // mDialog.show();
        lDialog = new LoadingDialog(this);
        lDialog.setCanceledOnTouchOutside(false);
        if (lDialog != null && !lDialog.isShowing()) {
            lDialog.show();
        }
    }

    /**
     * @throws : void
     * @Description(功能描述) : 结束进度
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年8月31日 下午1:49:14
     */
    public void stopProgressDialog() {
        // if (mDialog != null) {
        // mDialog.dismiss();
        // mDialog = null;
        // }
        if (lDialog != null && lDialog.isShowing()) {
            lDialog.cancel();
        }
    }

    /**
     * @throws : void
     * @Description(功能描述) : 结束进度
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年8月31日 下午1:49:14
     */
    public void stopProgressDialog(Integer resId, String msg, CloseListener listener) {
        // if (mDialog != null) {
        // mDialog.dismiss();
        // mDialog = null;
        // }
        if (lDialog != null && lDialog.isShowing()) {
            lDialog.hideWithDelay(resId, msg, listener);
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void checkPermission(String[] permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList<String> permissions_need = new ArrayList<String>();
            for (int i = 0; i < permissions.length; i++) {
                if (checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    permissions_need.add(permissions[i]);
                }
            }
            // 申请权限
            if (CommonUtil.isEmpty(permissions_need)) {
                doNext();
                return;
            }
            requestPermissions(permissions_need.toArray(new String[permissions_need.size()]), ResultCode.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
        } else {
            doNext();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, permissions, grantResults);
    }

    public void doNext(int requestCode, String[] permissions, int[] grantResults) {

    }

    public void doNext() {
    }
}
