package com.lltech.manager.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lcx.mysdk.adapter.BaseAdapterHelper;
import com.lcx.mysdk.adapter.QuickAdapter;
import com.lcx.mysdk.mutiImageSelector.MultiImageSelectorActivity;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.Cons;
import com.lcx.mysdk.utils.ImgUtils;
import com.lcx.mysdk.utils.OkHttpUtils;
import com.lcx.mysdk.utils.ViewUtils;
import com.lltech.manager.R;
import com.lltech.manager.activity.ZoomImgAty;
import com.lltech.manager.entity.Attachment;
import com.lltech.manager.entity.file.FileRes;
import com.lltech.manager.util.FileType;
import com.lltech.manager.util.UrlCons;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @ClassName(类名) : UploadFileView
 * @Description(描述) : 上传文件控件
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月15日 16:37
 */
public class UploadFileView extends LinearLayout {
    private Context context;
    private GridView fujians;
    private TextView progress;
    private QuickAdapter<Attachment> f_adapter;
    private LinkedList<Attachment> attachmentList = new LinkedList<Attachment>();
    private Attachment add;
    private boolean canEdit = true;

    private OnSelectPicListener onSelectPicListener;

    public interface OnSelectPicListener {
        void onSelectClick(View v);
    }

    public UploadFileView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public UploadFileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public UploadFileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public LinkedList<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(LinkedList<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public void setOnSelectPicListener(OnSelectPicListener onSelectPicListener) {
        this.onSelectPicListener = onSelectPicListener;
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fujian, null);
        addView(view);
        add = new Attachment();
        attachmentList.addFirst(add);
        progress = (TextView) view.findViewById(R.id.progress);
        fujians = (GridView) view.findViewById(R.id.fujians);
        fujians.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Attachment item = (Attachment) parent.getAdapter().getItem(position);
                if (CommonUtil.isEmpty(item.getAttachmentID())) {
                    if (onSelectPicListener != null) {
                        onSelectPicListener.onSelectClick(view);
                    }
                    return;
                }
                boolean isImg = FileType.isImg(item.getRealName());
                if (isImg) {
                    Intent intent = new Intent(context, ZoomImgAty.class);
                    intent.putExtra("position", canEdit == true ? position - 1 : position);
                    LinkedList<Attachment> viewList = attachmentList;
                    viewList.remove(add);
                    intent.putExtra("attachmentList", (Serializable) viewList);
                    context.startActivity(intent);
                } else {
                    return;
                }
            }
        });
        fujians.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Attachment item = (Attachment) parent.getAdapter().getItem(position);
                if (CommonUtil.isEmpty(item.getAttachmentID())) {
                    return true;
                }
                if (canEdit) {
                    attachmentList.remove(position);
                    f_adapter.remove(position);
                    ViewUtils.setGridViewHeightBasedOnChildren(fujians);
                }
                return true;
            }
        });
        f_adapter = new QuickAdapter<Attachment>(context, R.layout.common_item_fujian, attachmentList) {

            @Override
            protected void convert(BaseAdapterHelper helper, Attachment item) {
                if (CommonUtil.isEmpty(item.getAttachmentID())) {
                    helper.setImageResource(R.id.fujian_image, R.drawable.gridview_addpic);
                } else {
                    if (CommonUtil.isEmpty(item.getAttachmentUrl())) {
                        helper.setImageUrl(R.id.fujian_image, item.getLocalFile(), Cons.ImageLoadType.PICASSO);
                    } else {
                        helper.setImageUrl(R.id.fujian_image, item.getAttachmentUrl(), Cons.ImageLoadType.PICASSO);
                    }
                }
            }
        };
        fujians.setAdapter(f_adapter);
    }

    public void updateDataSource(LinkedList<Attachment> newList) {
        for (Attachment newItem : newList) {
            attachmentList.add(newItem);
        }
        if (!attachmentList.contains(add)) {
            if (canEdit) {
                attachmentList.addFirst(add);
            }
        } else {
            if (!canEdit) {
                attachmentList.removeFirst();
            }
        }
        f_adapter.replaceAll(attachmentList);
        ViewUtils.setGridViewHeightBasedOnChildren(fujians);
    }

    public void setEnable(boolean flag) {
        canEdit = flag;
    }

    /**
     * @throws :
     * @Description(功能描述) : 上传图片
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年9月3日 下午4:10:46
     */
    public void upload(Intent data) {
        progress.setVisibility(VISIBLE);
        String url = UrlCons.url(UrlCons.UPLOAD_FILE);
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            if (bundle.getStringArrayList(MultiImageSelectorActivity.EXTRA_RESULT) != null) {
                final ArrayList<String> filelist = bundle.getStringArrayList(MultiImageSelectorActivity.EXTRA_RESULT);
                final File[] files = new File[filelist.size()];
                String[] filekeys = new String[filelist.size()];
                for (int i = 0; i < filelist.size(); i++) {
                    files[i] = ImgUtils.compressFile(filelist.get(i));
                    filekeys[i] = "file";
                }
                OkHttpUtils.getUploadDelegate().postAsyn(url, filekeys, files, null, new OkHttpUtils.ResultCallback<FileRes>() {

                    @Override
                    public void onError(Request request, Exception e) {
                        Toast.makeText(context, "网络或服务器异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(FileRes imgRes) {
                        if (imgRes.getErrorCount() == 0) {
                            progress.setVisibility(GONE);
                            Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
                            String idList = imgRes.getIdList();
                            String FileNameList = imgRes.getFileNameList();
                            if (!CommonUtil.isEmpty(idList)) {
                                String[] split = idList.split(",");
                                String[] split_f = FileNameList.split(",");
                                LinkedList<Attachment> attachmentList = new LinkedList<Attachment>();
                                for (int j = 0; j < split.length; j++) {
                                    Attachment attachment = new Attachment();
                                    attachment.setAttachmentID(split[j]);
                                    attachment.setRealName(split_f[j]);
                                    attachment.setLocalFile(files[j]);
                                    attachmentList.add(attachment);
                                }
                                updateDataSource(attachmentList);
                            }
                        }
                    }
                });
            }
        }
    }
}
