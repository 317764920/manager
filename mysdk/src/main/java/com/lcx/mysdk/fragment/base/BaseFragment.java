package com.lcx.mysdk.fragment.base;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.ResultCode;
import com.lcx.mysdk.view.LoadingDialog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public abstract class BaseFragment extends Fragment {

    public LoadingDialog lDialog;
    private View mContentView;

    private List<TextView> tableText = new LinkedList<TextView>();
    private List<EditText> tableEdit = new LinkedList<EditText>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(setContentView(), container, false);
        initView();
        initListener();
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initConfig();
    }

    /**
     * 加载布局
     */
    public abstract int setContentView();

    /**
     * 加载view
     */
    public abstract void initView();

    /**
     * 初始化事件
     */
    public abstract void initListener();

    /**
     * 其他view相关设置
     */
    public abstract void initConfig();

    /**
     * 初始化数据
     */
    public abstract void initData();

	/**
	 * 
	 * @Description(功能描述) : 加载view
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年8月19日 上午11:37:08
	 * @exception :
	 * @param viewID
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T $(int viewID) {
        View view = mContentView.findViewById(viewID);
        if (view instanceof TextView) {
            tableText.add((TextView) view);
        } else if (view instanceof EditText) {
            tableEdit.add((EditText) view);
        } else if (view instanceof SwipeMenuListView) {

        }
        return (T) view;
	}

	/**
	 *
	 * @Description(功能描述) : 根据某个布局加载view
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年8月19日 上午11:37:12
	 * @exception :
	 * @param v
	 * @param viewID
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T $(View v, int viewID) {
		return (T) v.findViewById(viewID);
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
        if (lDialog == null) {
            lDialog = new LoadingDialog(getActivity());
            lDialog.setCanceledOnTouchOutside(false);
        }
        if (lDialog != null && !lDialog.isShowing()) {
            lDialog.show();
        }
    }

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
            lDialog.hide();
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
            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
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
                if (getActivity().checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
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
