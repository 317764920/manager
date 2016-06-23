package com.lcx.mysdk.exception;

import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.utils.ActivityManager;
import com.lcx.mysdk.utils.DateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;

/**
 * 全局处理异常.
 *
 */
public class CrashHandler implements UncaughtExceptionHandler {
    /** CrashHandler实例 */

    private static CrashHandler instance;
    private StringBuffer sb;

    /** 获取CrashHandler实例 ,单例模式*/

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    /**
     * 设置当线程由于未捕获到异常而突然终止的默认处理程序。
     */
    public void setCrashHandler(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread arg0, Throwable arg1) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        arg1.printStackTrace(printWriter);
        printWriter.close();
        String uncaughtException=stringWriter.toString();
        //1 保存错误日志
        String logdir ;
        if(Environment.getExternalStorageDirectory()!=null){
            logdir = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "log" ;


            File file = new File(logdir);
            boolean mkSuccess;
            if (!file.isDirectory()) {
                mkSuccess = file.mkdirs();
                if (!mkSuccess) {
                    mkSuccess = file.mkdirs();
                }
            }
            try {
                sb = new StringBuffer();
                sb.append("------------").append(DateUtils.currentDate2String()).append("------------\n");
                FileWriter fw = new FileWriter(logdir+File.separator+"error.log",true);
                fw.write(sb.toString());
                fw.write(uncaughtException);
                fw.close();
            } catch (IOException e) {
                Log.e("crash handler", "load file failed...", e.getCause());
            }
        }
        arg1.printStackTrace();
        //2 提示Crash信息
        showCrashTipToast();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        //3 退出应用
        ActivityManager.closeAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private void showCrashTipToast() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(BaseApplication.getContext(), "程序遇到错误即将退出", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
    }
}
