package com.lltech.manager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;

public class ListCheckBox extends CheckBox {

	private TranslateAnimation mShowAction, mHiddenAction;

	public ListCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		mShowAction.setDuration(300);
		mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		mHiddenAction.setDuration(300);
	}

	/**
	 * 
	 * @Description(功能描述) : 动画显示
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2016年3月1日 下午2:26:56
	 * @exception : void
	 */
	public void show() {
//		startAnimation(mShowAction);
		setVisibility(View.VISIBLE);
	}

	/**
	 * 
	 * @Description(功能描述) : 动画隐藏
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2016年3月1日 下午2:27:03
	 * @exception : void
	 */
	public void hide() {
//		startAnimation(mHiddenAction);
		setVisibility(View.GONE);
	}
}
