package com.lcx.mysdk.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 弹性 scollView
 * 
 * @author Administrator
 *
 */
public class MtScrollView extends ScrollView {
	private GestureDetector mGestureDetector;
	private View inner;
	private float y;
	private Rect normal = new Rect();
	private boolean animationFinish = true;

	public MtScrollView(Context context) {
		super(context);
	}

	public MtScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(context, new YScrollDetector());
	}

	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			inner = getChildAt(0);
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (inner == null) {
			return super.onTouchEvent(ev);
		} else {
			commOnTouchEvent(ev);
		}
		return super.onTouchEvent(ev);
	}

	private void commOnTouchEvent(MotionEvent ev) {
		if (animationFinish) {
			int action = ev.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				// System.out.println("ACTION_DOWN");
				y = ev.getY();
				super.onTouchEvent(ev);
				break;
			case MotionEvent.ACTION_UP:
				// System.out.println("ACTION_UP");
				y = 0;
				if (isNeedAnimation()) {
					animation();
				}
				super.onTouchEvent(ev);
				break;
			case MotionEvent.ACTION_MOVE:
				// System.out.println("ACTION_MOVE");
				final float preY = y == 0 ? ev.getY() : y;
				float nowY = ev.getY();
				int deltaY = (int) (preY - nowY);
				// 滚动
				// scrollBy(0, deltaY);

				y = nowY;
				// 当滚动到最上或者最下时就不会再滚动，这时移动布局
				if (isNeedMove()) {
					if (normal.isEmpty()) {
						// 保存正常的布局位置
						normal.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());
					}
					// 移动布局
					inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2, inner.getRight(), inner.getBottom() - deltaY / 2);
				} else {
					super.onTouchEvent(ev);
				}
				break;
			default:
				break;
			}
		}
	}

	// 开启动画移动

	private void animation() {
		// 开启移动动画
		TranslateAnimation ta = new TranslateAnimation(0, 0, 0, normal.top - inner.getTop());
		ta.setDuration(200);
		ta.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				animationFinish = false;

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				inner.clearAnimation();
				// 设置回到正常的布局位置
				inner.layout(normal.left, normal.top, normal.right, normal.bottom);
				normal.setEmpty();
				animationFinish = true;
			}
		});
		inner.startAnimation(ta);
	}

	// 是否需要开启动画
	private boolean isNeedAnimation() {
		return !normal.isEmpty();
	}

	// 是否需要移动布局
	private boolean isNeedMove() {
		int offset = inner.getMeasuredHeight() - getHeight();
		int scrollY = getScrollY();
		if (scrollY == 0 || scrollY == offset) {
			return true;
		}
		return false;
	}

	class YScrollDetector extends SimpleOnGestureListener {

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			// 如果我们滚动更接近水平方向,返回false,让子视图来处理它
			return (Math.abs(distanceY) > Math.abs(distanceX));
		}
	}

}