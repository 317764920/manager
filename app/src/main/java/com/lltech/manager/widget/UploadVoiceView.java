package com.lltech.manager.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.lcx.mysdk.adapter.RecorderAdapter;
import com.lcx.mysdk.entity.Recorder;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.MediaManager;
import com.lcx.mysdk.utils.OkHttpUtils;
import com.lcx.mysdk.utils.ViewUtils;
import com.lltech.manager.R;
import com.lltech.manager.entity.Voice;
import com.lltech.manager.entity.file.FileRes;
import com.lltech.manager.util.UrlCons;
import com.squareup.okhttp.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : UploadVoiceView
 * @Description(描述) : 上传语音控件
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月15日 16:37
 */
public class UploadVoiceView extends LinearLayout {
    private Context context;
    private ListView listView;
    private View viewAnim;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas = new ArrayList<Recorder>();
    private List<Voice> voiceList = new LinkedList<Voice>();
    private boolean canEdit = true;

    public UploadVoiceView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public UploadVoiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public UploadVoiceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public List<Voice> getVoiceList() {
        return voiceList;
    }

    public void setVoiceList(List<Voice> voiceList) {
        this.voiceList = voiceList;
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.voice, null);
        addView(view);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 播放动画
                if (viewAnim != null) {//让第二个播放的时候第一个停止播放
                    viewAnim.setBackgroundResource(R.drawable.v_anim3);
                    viewAnim = null;
                }
                viewAnim = view.findViewById(R.id.id_recorder_anim);
                viewAnim.setBackgroundResource(R.drawable.play);
                AnimationDrawable drawable = (AnimationDrawable) viewAnim
                        .getBackground();
                drawable.start();

                // 播放音频
                MediaManager.playSound(mDatas.get(position).getFilePathString(),
                        new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                viewAnim.setBackgroundResource(R.drawable.v_anim3);
                            }
                        });
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (canEdit) {
                    voiceList.remove(position);
                    mAdapter.notifyDataSetChanged();
                    ViewUtils.setListViewHeightBasedOnChildren(listView);
                }
                return true;
            }
        });
        mAdapter = new RecorderAdapter(context, mDatas);
        listView.setAdapter(mAdapter);
    }

    public void updateDataSource(List<Voice> voiceList) {
        this.voiceList = voiceList;
        if (CommonUtil.isNotEmpty(voiceList)) {
            for (int i = 0; i < voiceList.size(); i++) {
                Recorder recorder = new Recorder(Float.parseFloat(voiceList.get(i).getTimeLength()), voiceList.get(i).getVoicePath());
                mDatas.add(recorder);
            }
        }
        mAdapter.notifyDataSetChanged();
        ViewUtils.setListViewHeightBasedOnChildren(listView);
    }

    public void setEnable(boolean flag) {
        canEdit = flag;
    }

    /**
     * @Description(功能描述) : 上传语音
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年9月3日 下午4:10:46
     */
    public void uploadVoice(final Recorder recorder) {
        mDatas.add(recorder);
        mAdapter.notifyDataSetChanged();
        ViewUtils.setListViewHeightBasedOnChildren(listView);
        String url = UrlCons.url(UrlCons.UPLOAD_VOICE);
        String file_key = "file";
        final File file = new File(recorder.getFilePathString());
        OkHttpUtils.getUploadDelegate().postAsyn(url, file_key, file, null, new OkHttpUtils.ResultCallback<FileRes>() {

            @Override
            public void onError(Request request, Exception e) {
                Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
                file.delete();
                mDatas.remove(recorder);
                mAdapter.notifyDataSetChanged();
                ViewUtils.setListViewHeightBasedOnChildren(listView);
            }

            @Override
            public void onResponse(FileRes fileRes) {
                if (fileRes.getErrorCount() == 0) {
                    Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
                    Voice voice = new Voice();
                    voice.setTimeLength((int) recorder.getTime() + "");
                    voice.setVoicePath(fileRes.getVoicePath());
                    voiceList.add(voice);
                }
            }
        });
    }
}
