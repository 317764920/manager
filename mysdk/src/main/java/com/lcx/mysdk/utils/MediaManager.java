package com.lcx.mysdk.utils;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.widget.Toast;

import com.lcx.mysdk.application.BaseApplication;

import java.io.IOException;

public class MediaManager {


    private static MediaPlayer mPlayer;

    private static boolean isPause;

    public static void playSound(String filePathString,
                                 OnCompletionListener onCompletionListener) {
        // TODO Auto-generated method stub
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            //保险起见，设置报错监听
            mPlayer.setOnErrorListener(new OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // TODO Auto-generated method stub
                    mPlayer.reset();
                    return false;
                }
            });
        } else {
            mPlayer.reset();//就回复
        }

        try {
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setOnCompletionListener(onCompletionListener);
            mPlayer.setDataSource(filePathString);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(BaseApplication.getContext(), "播放失败", Toast.LENGTH_SHORT).show();
            mPlayer.pause();
            isPause = true;
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(BaseApplication.getContext(), "无权限", Toast.LENGTH_SHORT).show();
            mPlayer.pause();
            isPause = true;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(BaseApplication.getContext(), "播放失败", Toast.LENGTH_SHORT).show();
            mPlayer.pause();
            isPause = true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(BaseApplication.getContext(), "语音文件不存在", Toast.LENGTH_SHORT).show();
            mPlayer.pause();
            isPause = true;
        }
    }

    //停止函数
    public static void pause() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            isPause = true;
        }
    }

    //继续
    public static void resume() {
        if (mPlayer != null && isPause) {
            mPlayer.start();
            isPause = false;
        }
    }


    public static void release() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
