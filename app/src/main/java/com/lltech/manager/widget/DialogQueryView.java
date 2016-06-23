package com.lltech.manager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lltech.manager.R;

public class DialogQueryView extends LinearLayout implements OnClickListener {
	public Context context;
	public Button query, dialog_left_btn;
	public ImageView sanjiao;
	public EditText queryText;
	private OnBtnClickListener listener;
	private Animation operatingAnim;
	public int count = 0;

	public interface OnBtnClickListener {
		public void onLeftClick();

		public void onQueryClick();
	}

	public DialogQueryView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_query_view, this, false);
		addView(view);
		query = (Button) findViewById(R.id.query);
		query.setOnClickListener(this);
		dialog_left_btn = (Button) findViewById(R.id.dialog_left_btn);
		dialog_left_btn.setOnClickListener(this);
		sanjiao = (ImageView) findViewById(R.id.sanjiao);
		queryText = (EditText) findViewById(R.id.queryText);
		operatingAnim = AnimationUtils.loadAnimation(context, R.anim.sanjiao);
		operatingAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if (count % 2 == 0)
					sanjiao.setImageResource(R.drawable.sanjiao_down);
				if (count % 2 == 1)
					sanjiao.setImageResource(R.drawable.sanjiao_up);
			}
		});
		LinearInterpolator lin = new LinearInterpolator();
		operatingAnim.setInterpolator(lin);
	}

	public void setOnBtnClickListener(OnBtnClickListener listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_left_btn:
			if (null != listener) {
				count++;
				sanJiaoStartAnimation();
				listener.onLeftClick();
			}
			break;
		case R.id.query:
			if (null != listener) {
				listener.onQueryClick();
			}
			break;
		}

	}
	
	public void sanJiaoStartAnimation(){
		if (operatingAnim != null) {
			sanjiao.startAnimation(operatingAnim);
		}
	}

}
