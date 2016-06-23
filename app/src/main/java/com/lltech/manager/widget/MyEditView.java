package com.lltech.manager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lltech.manager.R;

public class MyEditView extends LinearLayout{
    private TextView leftText,rightText;
    private EditText edit;

    public MyEditView(Context context){
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.text, this, false);
        addView(view);
        leftText = (TextView) findViewById(R.id.leftText);
        rightText = (TextView)findViewById(R.id.rightText);
        edit = (EditText) findViewById(R.id.edit);
    }

    public MyEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView getLeftText() {
        return leftText;
    }

    public void setLeftText(TextView leftText) {
        this.leftText = leftText;
    }

    public TextView getRightText() {
        return rightText;
    }

    public void setRightText(TextView rightText) {
        this.rightText = rightText;
    }

    public EditText getEdit() {
        return edit;
    }

    public void setEdit(EditText edit) {
        this.edit = edit;
    }

    /**
     *设置左边的文字
     * */
    public void setLeftText(String text) {
        leftText.setText(text);
    }
    /**
     *设置右边的单位
     * */
    public void setRightText(String text) {
        rightText.setText(text);
    }
}
