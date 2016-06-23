package com.lcx.mysdk.fragment.base;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcx.mysdk.fragment.ProgressFragment;

public abstract class BaseProgressFragment extends ProgressFragment {

    private View mContentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(setContentView(), container, false);
        initView();
        initListener();
        initConfig();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setContentView(mContentView);
        setText("正在加载...");
        hide();
        initData();
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
		return (T) mContentView.findViewById(viewID);
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
	 * 
	 * @Description(功能描述) : 显示内容(隐藏进度条)
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年8月19日 上午10:22:31
	 * @exception : void
	 */
	public void show() {
		setContentShown(true);
	}

	/**
	 * 
	 * @Description(功能描述) : 隐藏内容(显示进度条)
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年8月19日 上午10:22:34
	 * @exception : void
	 */
	public void hide() {
		setContentShown(false);
	}

	/**
	 * 
	 * @Description(功能描述) : 设置进度条文字
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年8月19日 上午11:13:15
	 * @exception :
	 * @param text
	 *            void
	 */
	public void setText(CharSequence text) {
		setProgressText(text);
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

}
