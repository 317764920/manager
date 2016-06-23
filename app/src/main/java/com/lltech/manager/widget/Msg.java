package com.lltech.manager.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lltech.manager.R;

/**
 * 
 * @ClassName(类名) : Msg
 * @Description(描述) : Toast显示
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2016年3月2日 下午12:04:20
 *
 */
public class Msg extends Toast {
	
	private static Msg mMsg;

	public Msg(Context context) {
		super(context);
	}
	
	public static Msg getInstance(Context context){
		if(mMsg == null){
			mMsg = new Msg(context);
		}
		return mMsg;
	}

	/**
	 * 
	 * @Description(功能描述) : 成功
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2016年3月2日 下午12:04:30
	 * @exception :
	 * @param context
	 * @param text
	 *            void
	 */
	public static void showSuccess(Context context, String text) {
		Msg msg = Msg.getInstance(context);
		View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
		ImageView msg_img = (ImageView) view.findViewById(R.id.msg_img);
		TextView msg_text = (TextView) view.findViewById(R.id.msg_text);
		msg_img.setImageResource(R.drawable.yes);
		msg_text.setText(text);

		msg.setView(view);
		msg.setGravity(Gravity.CENTER, 0, 150);
		msg.setDuration(LENGTH_SHORT);
		msg.show();
	}

	/**
	 * 
	 * @Description(功能描述) : 错误
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2016年3月2日 下午12:04:39
	 * @exception :
	 * @param context
	 * @param text
	 *            void
	 */
	public static void showError(Context context, String text) {
		Msg msg = Msg.getInstance(context);
		View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
		ImageView msg_img = (ImageView) view.findViewById(R.id.msg_img);
		TextView msg_text = (TextView) view.findViewById(R.id.msg_text);
		msg_img.setImageResource(R.drawable.warn);
		msg_text.setText(text);

		msg.setView(view);
		msg.setGravity(Gravity.CENTER, 0, 150);
		msg.setDuration(LENGTH_SHORT);
		msg.show();
	}
	
	/**
	 * 
	 * @Description(功能描述)    :  取消显示
	 * @author(作者)             ：  liuchunxu
	 * @date (开发日期)          :  2016年3月25日 上午9:29:19 
	 * @exception                : 
	 * @param context  void
	 */
	public static void cancel(Context context){
		Msg.getInstance(context).cancel();
	}

}
