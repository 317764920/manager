package com.lcx.mysdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {
	
	private float lastX,lastY;

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean result = false;

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:

			lastX = ev.getX();
			lastY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int distanceX = (int) Math.abs(ev.getX() - lastX);
			int distanceY = (int) Math.abs(ev.getY() - lastY);

			if (distanceX > distanceY && distanceX > 10) {
				result = true;
			} else {
				result = false;
			}
			break;

		default:
			break;
		}

		return result;
	}
}
