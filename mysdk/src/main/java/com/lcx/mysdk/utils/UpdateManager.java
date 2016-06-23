package com.lcx.mysdk.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

import com.lcx.mysdk.R;
import com.lcx.mysdk.view.MyDialog;

public class UpdateManager {
	// 应用程序Context
	private Context mContext;
	// 提示消息
	private String updateMsg = "有最新的软件包，请下载！";
	// 下载安装包的网络路径
	private String apkUrl = "http://softfile.3g.qq.com:8080/msoft/179/24659/43549/qq_hd_mini_1.4.apk";
	private static final String savePath = "/sdcard/sqzy/";// 保存apk的文件夹
	private static final String saveFileName = savePath + "sqzy.apk";
	// 进度条与通知UI刷新的handler和msg常量
	private ProgressBar mProgress;
	private MyDialog myDialog;
	private MyDialog downloadDialog;// 下载对话框
	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	private int progress;// 当前进度
	private boolean interceptFlag = false;// 用户取消下载
	// 通知处理刷新界面的handler
	private Handler mHandler = new Handler() {
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:
				installApk();
				downloadDialog.dismiss();
				break;
			}
			super.handleMessage(msg);
		}
	};

	public UpdateManager(Context context, String apkUrl) {
		this.mContext = context;
		this.apkUrl = apkUrl;
	}

	// 显示更新程序对话框，供主程序调用
	public void checkUpdateInfo(String title, String message) {
		showNoticeDialog(title, message);
	}

	private void showNoticeDialog(String title, String message) {
		if (CommonUtil.isEmpty(title))
			title = "软件版本更新";
		if (CommonUtil.isEmpty(message))
			message = updateMsg;
		myDialog = new MyDialog(mContext).setTitle(title).setMessage(message).setPositiveButton("现在升级", new OnClickListener() {
			@Override
			public void onClick(View v) {
				myDialog.dismiss();
				showDownloadDialog();
			}
		}).setNegativeButton("以后再说", new OnClickListener() {
			@Override
			public void onClick(View v) {
				myDialog.dismiss();
			}
		});
		myDialog.setCanceledOnTouchOutside(false);
		myDialog.show();
	}

	protected void showDownloadDialog() {
		downloadDialog = new MyDialog(mContext);
		downloadDialog.setTitle("软件版本更新");
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.progress);
		downloadDialog.setView(v);// 设置对话框的内容为一个View
		downloadDialog.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(View v) {
				downloadDialog.dismiss();
				interceptFlag = true;
			}
		});
		downloadDialog.setCanceledOnTouchOutside(false);
		downloadDialog.show();
		downloadApk();
	}

	private void downloadApk() {
		ThreadPoolManager.getInstance().addTask(mdownApkRunnable);
	}

	protected void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");// File.toString()会返回路径信息
		mContext.startActivity(i);
	}

	public String getApkVersion() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return "";
		}
		PackageManager pm = mContext.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(saveFileName, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			String version = info.versionName; // 得到版本信息
			return version;
		} else {
			return "";
		}
	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			URL url;
			try {
				url = new URL(apkUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream ins = conn.getInputStream();
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				String apkFile = saveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream outStream = new FileOutputStream(ApkFile);
				int count = 0;
				byte buf[] = new byte[1024];
				do {
					int numread = ins.read(buf);
					count += numread;
					progress = (int) (((float) count / length) * 100);
					// 下载进度
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// 下载完成通知安装
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					outStream.write(buf, 0, numread);
				} while (!interceptFlag);// 点击取消停止下载
				outStream.close();
				ins.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}