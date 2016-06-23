package com.lltech.manager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lltech.manager.R;

public class OperView extends LinearLayout implements OnClickListener {
    public boolean isShow = false;
    private Button leftBtn, rightBtn;
    private OnBtnClickListener listener;
    private AlphaAnimation mShowAction, mHiddenAction;

    public interface OnBtnClickListener {
        public void onLeftClick();

        public void onRightClick();
    }

    public OperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.oper, this, false);
        addView(view);
        leftBtn = (Button) findViewById(R.id.left);
        rightBtn = (Button) findViewById(R.id.right);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        mShowAction = new AlphaAnimation(0.0f, 1.0f);
        mShowAction.setDuration(300);
        mHiddenAction = new AlphaAnimation(1.0f, 0.0f);
        mHiddenAction.setDuration(300);
    }

    public void setOnBtnClickListener(OnBtnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left:
                if (null != listener) {
                    listener.onLeftClick();
                }
                break;
            case R.id.right:
                if (null != listener) {
                    listener.onRightClick();
                }
                break;
        }
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public Button getLeftBtn() {
        return leftBtn;
    }

    public void setLeftBtn(Button leftBtn) {
        this.leftBtn = leftBtn;
    }

    public Button getRightBtn() {
        return rightBtn;
    }

    public void setRightBtn(Button rightBtn) {
        this.rightBtn = rightBtn;
    }

    public void setLeftText(String text) {
        leftBtn.setText(text);
    }

    public void setLeftVisibility(int visibility) {
        leftBtn.setVisibility(visibility);
    }

    public void setRightText(String text) {
        rightBtn.setText(text);
    }

    public void setRightVisibility(int visibility) {
        rightBtn.setVisibility(visibility);
    }

    public void setLeftTextColor(int color) {
        leftBtn.setTextColor(color);
    }

    public void setRightTextColor(int color) {
        rightBtn.setTextColor(color);
    }

    /**
     * @throws : void
     * @Description(功能描述) : 动画显示
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2016年3月1日 下午2:26:56
     */
    public void show() {
        startAnimation(mShowAction);
        setVisibility(View.VISIBLE);
    }

    /**
     * @throws : void
     * @Description(功能描述) : 动画隐藏
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2016年3月1日 下午2:27:03
     */
    public void hide() {
        startAnimation(mHiddenAction);
        setVisibility(View.GONE);
    }
}
