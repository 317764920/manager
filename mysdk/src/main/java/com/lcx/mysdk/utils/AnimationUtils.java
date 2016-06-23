package com.lcx.mysdk.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimationUtils {
	public static TranslateAnimation mShowAction;
	public static TranslateAnimation mHiddenAction;

	static {
		mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		mShowAction.setDuration(500);

		mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, -1.0f);
		mHiddenAction.setDuration(500);
	}

	public static void startShow(View view) {
		view.startAnimation(mShowAction);
	}

	public static void startHidden(View view) {
		view.startAnimation(mHiddenAction);
	}

	public static void startShowOrHidden(View view, int visibility) {
		switch (visibility) {
		case View.VISIBLE:
			view.startAnimation(mShowAction);
			break;
		case View.INVISIBLE:
			view.startAnimation(mHiddenAction);
			break;
		case View.GONE:
			view.startAnimation(mHiddenAction);
			break;
		}
	}
}
