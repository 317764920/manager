package com.lltech.manager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lltech.manager.R;
import com.lltech.manager.interfaces.TopBarClickListener;

public class TopBar extends LinearLayout implements OnClickListener {
    public static final int MORE = 1;
    public static final int BACK = 2;
    public static final int QUERY = 3;
    public static final int NEW = 4;

    public static final int LEFT = 5;
    public static final int RIGHT1 = 6;
    public static final int RIGHT2 = 7;

    private Button leftBtn, rightBtn1, rightBtn2;
    private TextView top_title;
    private TopBarClickListener listener;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.top, this, false);
        addView(view);
        leftBtn = (Button) findViewById(R.id.left);
        rightBtn1 = (Button) findViewById(R.id.right1);
        rightBtn2 = (Button) findViewById(R.id.right2);
        top_title = (TextView) findViewById(R.id.top_title);
        leftBtn.setOnClickListener(this);
        rightBtn1.setOnClickListener(this);
        rightBtn2.setOnClickListener(this);
    }

    public void setOnTopBarClickListener(TopBarClickListener listener) {
        this.listener = listener;
    }

    public Button getLeftBtn() {
        return leftBtn;
    }

    public void setLeftBtn(Button leftBtn) {
        this.leftBtn = leftBtn;
    }

    public Button getRightBtn1() {
        return rightBtn1;
    }

    public void setRightBtn1(Button rightBtn1) {
        this.rightBtn1 = rightBtn1;
    }

    public Button getRightBtn2() {
        return rightBtn2;
    }

    public void setRightBtn2(Button rightBtn2) {
        this.rightBtn2 = rightBtn2;
    }

    public TextView getTop_title() {
        return top_title;
    }

    public void setTop_title(TextView top_title) {
        this.top_title = top_title;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left:
                if (null != listener) {
                    listener.onLeftClick(v);
                }
                break;
            case R.id.right1:
                if (null != listener) {
                    listener.onRightClick(RIGHT1, v);
                }
                break;
            case R.id.right2:
                if (null != listener) {
                    listener.onRightClick(RIGHT2, v);
                }
                break;
        }
    }

    /**
     * 设置按钮是否可见
     *
     * @liuchunxu
     * @2016-05-10 17:49
     */
    public void setBtnStatus(int whitchBtn, int visibility) {
        switch (whitchBtn) {
            case LEFT: {
                leftBtn.setVisibility(visibility);
                break;
            }
            case RIGHT1: {
                rightBtn1.setVisibility(visibility);
                break;
            }
            case RIGHT2: {
                rightBtn2.setVisibility(visibility);
                break;
            }
        }
    }

    /**
     * 设置按钮类型（只能设置右侧按钮）
     *
     * @liuchunxu
     * @2016-05-10 17:50
     */
    public void setBtnType(int whitchBtn, int imgType) {
        switch (imgType) {
            case QUERY:
                switch (whitchBtn) {
//                    case LEFT: {
//                        leftBtn.setBackgroundResource(R.drawable.selecter_pop_query);
//                        break;
//                    }
                    case RIGHT1: {
                        rightBtn1.setBackgroundResource(R.drawable.selecter_pop_query);
                        break;
                    }
                    case RIGHT2: {
                        rightBtn2.setBackgroundResource(R.drawable.selecter_pop_query);
                        break;
                    }
                }
                break;
            case NEW:
                switch (whitchBtn) {
//                    case LEFT: {
//                        leftBtn.setBackgroundResource(R.drawable.selecter_pop_add);
//                        break;
//                    }
                    case RIGHT1: {
                        rightBtn1.setBackgroundResource(R.drawable.selecter_pop_add);
                        break;
                    }
                    case RIGHT2: {
                        rightBtn2.setBackgroundResource(R.drawable.selecter_pop_add);
                        break;
                    }
                }
                break;
            case BACK:
                switch (whitchBtn) {
//                    case LEFT: {
//                        leftBtn.setBackgroundResource(R.drawable.selecter_pop_back);
//                        break;
//                    }
                    case RIGHT1: {
                        rightBtn1.setBackgroundResource(R.drawable.selecter_pop_back);
                        break;
                    }
                    case RIGHT2: {
                        rightBtn2.setBackgroundResource(R.drawable.selecter_pop_back);
                        break;
                    }
                }
                break;
            case MORE:
                switch (whitchBtn) {
//                    case LEFT: {
//                        leftBtn.setBackgroundResource(R.drawable.menu_icon);
//                        break;
//                    }
                    case RIGHT1: {
                        rightBtn1.setBackgroundResource(R.drawable.pop_more);
                        break;
                    }
                    case RIGHT2: {
                        rightBtn2.setBackgroundResource(R.drawable.pop_more);
                        break;
                    }
                }
                break;
        }
    }

    /**
     * 设置顶部文字
     *
     * @liuchunxu
     * @2016-05-10 17:51
     */
    public void setTopText(String text) {
        top_title.setText(text);
    }

    /**
     * 设置顶部文字是否可见
     *
     * @liuchunxu
     * @2016-05-10 17:51
     */
    public void setTopStatus(int visibility) {
        top_title.setVisibility(visibility);
    }

    /**
     * 设置顶部文字颜色
     *
     * @liuchunxu
     * @2016-05-10 17:51
     */
    public void setTopTextColor(int color) {
        top_title.setTextColor(color);
    }
}
