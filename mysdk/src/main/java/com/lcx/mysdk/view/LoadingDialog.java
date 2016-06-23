package com.lcx.mysdk.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lcx.mysdk.R;
import com.lcx.mysdk.interfaces.CloseListener;
import com.lcx.mysdk.utils.CommonUtil;

/**
 * 加载中Dialog
 *
 * @author lexyhp
 */
public class LoadingDialog extends AlertDialog {

    private Context mContext;
    private TextView tips_loading_msg;
    private ProgressBar loading;
    private ImageView status;
    private String message = null;
    private Handler handler = new Handler();
    private CloseListener closeListener;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
        this.mContext = context;
        message = context.getResources().getString(R.string.dialogloading);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_tips_loading2);
        tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
        loading = (ProgressBar) findViewById(R.id.loading);
        status = (ImageView) findViewById(R.id.status);
        tips_loading_msg.setText(this.message);
    }

    public void setMessage(String message) {
        this.message = message;
        tips_loading_msg.setText(this.message);
    }

    public void setStatus(int resId) {
        status.setImageResource(resId);
    }

    public void setLoadingVisable(boolean flag) {
        if (flag) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }

    public void setStatusVisable(boolean flag) {
        if (flag) {
            status.setVisibility(View.VISIBLE);
        } else {
            status.setVisibility(View.GONE);
        }
    }

    public void hide() {
        dismiss(false, null, null, null);
    }

    public void hideWithDelay(Integer resId, String msg, CloseListener listener) {
        dismiss(true, resId, msg, listener);
    }

    public void dismiss(boolean flag, Integer resId, String msg, CloseListener listener) {
        if (flag) {
            //需要延时显示
            //需要改变图标，文字内容
            setLoadingVisable(false);
            setStatusVisable(true);
            if (CommonUtil.isNotEmpty(resId)) {
                setStatus(resId.intValue());
            }
            if (CommonUtil.isNotEmpty(msg)) {
                setMessage(msg);
            }
            if (CommonUtil.isNotEmpty(listener)) {
                this.closeListener = listener;
            }
            handler.postDelayed(new delayClose(), 1000);
        } else {
            cancel();
        }
    }

    class delayClose implements Runnable {

        @Override
        public void run() {
            cancel();
            closeListener.onDialogClose();
        }
    }
}
