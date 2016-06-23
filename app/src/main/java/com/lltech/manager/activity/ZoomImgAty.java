package com.lltech.manager.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcx.mysdk.activity.BaseActivity;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.view.ZoomImageView;
import com.lltech.manager.R;
import com.lltech.manager.entity.Attachment;

import java.util.List;

public class ZoomImgAty extends BaseActivity implements
        ViewPager.OnPageChangeListener {
    private int currentPosition;
    private List<Attachment> attachmentList;

    /**
     * 用于管理图片的滑动
     */
    private ViewPager viewPager;

    /**
     * 显示当前图片的页数
     */
    private TextView pageText;

    @Override
    public void setContentView() {
        setContentView(R.layout.vp);
    }

    @Override
    public void initViews() {
        pageText = $(R.id.page_text);
        viewPager = $(R.id.view_pager);
    }

    @Override
    public void initListeners() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initData() {
        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("position", 0);
        attachmentList = (List<Attachment>) intent.getSerializableExtra("attachmentList");
    }

    @Override
    public void initConfig() {
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentPosition);
        viewPager.setOnPageChangeListener(this);
        viewPager.setEnabled(false);
        // 设定当前的页数和总页数
        pageText.setText((currentPosition + 1) + "/" + attachmentList.size());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int currentPage) {
        // 每当页数发生改变时重新设定一遍当前的页数和总页数
        pageText.setText((currentPage + 1) + "/" + attachmentList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * ViewPager的适配器
     *
     * @author guolin
     */
    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Attachment attachment = attachmentList.get(position);
            View view = LayoutInflater.from(ZoomImgAty.this).inflate(
                    R.layout.zoom_image_layout, null);
            final ZoomImageView zoomImageView = (ZoomImageView) view
                    .findViewById(R.id.zoom_image_view);
            if (CommonUtil.isEmpty(attachment.getAttachmentUrl())) {
                zoomImageView.setImageBitmap(attachment.getLocalFile());
            } else {
                zoomImageView.setImageBitmap(attachment.getAttachmentUrl());
            }
            container.addView(view);
            return zoomImageView;
        }

        @Override
        public int getCount() {
            return attachmentList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }
}
