package com.lltech.manager.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * 
 * @ClassName(类名) : DialogQueryWindow
 * @Description(描述) : 对话框条件选择窗口
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2016年2月22日 下午5:30:52
 *
 */
public class DialogQueryWindow extends PopupWindow {
	private Context mContext;

	private View mContentView;

	public DialogQueryWindow(Context context, int layoutRes) {
		// 一个自定义的布局，作为显示的内容
		View view = LayoutInflater.from(context).inflate(layoutRes, null);
		setContentView(view);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		setWidth(width / 3);
		setHeight(LayoutParams.WRAP_CONTENT);
		setFocusable(true);
		this.mContext = context;
		this.mContentView = getContentView();
	}

	public View getmContentView() {
		return mContentView;
	}

	public void setmContentView(View mContentView) {
		this.mContentView = mContentView;
	}

	@SuppressWarnings("unchecked")
	public <T> T getView(int id) {
		View view = mContentView.findViewById(id);
		return (T) view;
	}

	/**
	 * 
	 * @Description(功能描述) : 显示在某个view周围
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2016年2月22日 下午5:30:36
	 * @exception :
	 * @param view
	 *            void
	 */
	public void show(View view, int xoff, int yoff) {
		setTouchable(true);
		setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				// Log.i("mengdd", "onTouch : ");

				return false;
				// 这里如果返回true的话，touch事件将被拦截
				// 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}
		});

		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		// 我觉得这里是API的一个bug
		setBackgroundDrawable(new BitmapDrawable());
		// 设置好参数之后再show
		// setAnimationStyle(R.style.AnimBottom);
		showAsDropDown(view, xoff, yoff);
	}

	/**
	 * 设置添加屏幕的背景透明度
	 * 
	 * @param bgAlpha
	 */
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		((Activity) mContext).getWindow().setAttributes(lp);
	}
}
