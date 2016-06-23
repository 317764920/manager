package com.lltech.manager.fragment.xj;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.widget.MyEditView;

/**
 * @ClassName(类名) : MiddleFragment
 * @Description(描述) : 中间的Fragment
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月23日 11:17
 */
public class MiddleFragment extends BaseFragment implements View.OnClickListener {

    private BaseApplication application = BaseApplication.getApplication();
    private TextView txt_title;
    private LinearLayout linear_edit;
    private RadioGroup state;
    private RadioButton ck_normal, ck_unnormal;
    private OnButtonClick listener;
    private MyEditView myEditView;

    @Override
    public int setContentView() {
        return R.layout.detail_middle;
    }

    @Override
    public void initView() {
        txt_title = $(R.id.txt_title);
        state = $(R.id.state);
        ck_normal = $(R.id.ck_normal);
        ck_unnormal = $(R.id.ck_unnormal);
        linear_edit = $(R.id.linear_edit);
    }

    @Override
    public void initListener() {
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnButtonClick(v, true);
                }
            }
        });
        ck_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnButtonClick(v, true);
                }
            }
        });
        ck_unnormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnButtonClick(v, true);
                }
            }
        });
    }

    @Override
    public void initConfig() {

        for (int i = 0; i < 10; i++) {
            myEditView = new MyEditView(getActivity());
            myEditView.setLeftText("本次巡检机组累计运行时间");
            linear_edit.addView(myEditView);
        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.state:

                break;
            case R.id.ck_normal:

                break;
            case R.id.ck_unnormal:

                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnButtonClick) {
            listener = (OnButtonClick) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnButtonClick {
        //value为传入的值
        public void OnButtonClick(View view, Boolean value);
    }
}
