package com.lcx.mysdk.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.lcx.mysdk.R;

public class CustomProgressDialog extends Dialog {
	private static CustomProgressDialog customProgressDialog = null;

	public CustomProgressDialog(Context context) {
		super(context);
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static CustomProgressDialog createDialog(Context context) {
		customProgressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
		customProgressDialog.setContentView(R.layout.customprogressdialog);
		LayoutParams attributes = customProgressDialog.getWindow().getAttributes();
		attributes.gravity = Gravity.CENTER;
//		customProgressDialog.getWindow().getAttributes();
		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {

		if (customProgressDialog == null) {
			return;
		}

		ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
		LinearInterpolator lin = new LinearInterpolator();
		Animation am = new RotateAnimation(0, +360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

		// 动画开始到结束的执行时间(1000 = 1 秒)
		am.setDuration(2000);

		// 动画重复次数(-1 表示一直重复)
		am.setRepeatCount(-1);
		am.setRepeatCount(Animation.INFINITE);
		am.setInterpolator(lin);
		imageView.startAnimation(am);
		// am.startNow();
	}
}
