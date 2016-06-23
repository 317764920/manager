package com.lltech.manager.fragment.index;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.circlelibrary.ImageCycleView;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.activity.bx.BxListAty;
import com.lltech.manager.activity.common.CommonAty;
import com.lltech.manager.common.Mark;
import com.lltech.manager.util.MyResultCode;
import com.lltech.manager.widget.TopBar;
import com.mining.app.zxing.QrCodeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @ClassName(类名) : YzHomeFragment
 * @Description(描述) : 业主端
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月11日 16:56
 */
public class YzHomeFragment extends BaseFragment implements View.OnClickListener {
    private BaseApplication application = BaseApplication.getApplication();
    private TopBar topBar;
    private ImageCycleView imageCycleView;
    private TextView text_add_bx, text_bx, text_sao;

    @Override
    public int setContentView() {
        return R.layout.yz_tab_home;
    }

    @Override
    public void initView() {
        topBar = $(R.id.top);
        /** 找到轮播控件*/
        imageCycleView = $(R.id.cycleView);
        text_add_bx = $(R.id.text_add_bx);
        text_bx = $(R.id.text_bx);
        text_sao = $(R.id.text_sao);
    }

    @Override
    public void initListener() {
        text_add_bx.setOnClickListener(this);
        text_bx.setOnClickListener(this);
        text_sao.setOnClickListener(this);
    }

    @Override
    public void initConfig() {
        topBar.setBtnStatus(TopBar.LEFT, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT1, View.GONE);
        topBar.setBtnStatus(TopBar.RIGHT2, View.GONE);
        topBar.setTopText("首页");
    }

    @Override
    public void initData() {
        /**装在数据的集合  文字描述*/
        ArrayList<String> imageDescList = new ArrayList<>();
        /**装在数据的集合  图片地址*/
        ArrayList<String> urlList = new ArrayList<>();

        /**添加数据*/
        urlList.add("http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg");
        urlList.add("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg");
        urlList.add("http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg");
        urlList.add("http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg");

        imageDescList.add("");
        imageDescList.add("");
        imageDescList.add("");
        imageDescList.add("");
        ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {
                /**实现点击事件*/
            }

            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                /**在此方法中，显示图片，可以用自己的图片加载库，也可以用本demo中的（Imageloader）*/
                Picasso.with(application).load(imageURL).into(imageView);
            }
        };
        /**设置数据*/
        imageCycleView.setImageResources(imageDescList, urlList, mAdCycleViewListener);
        imageCycleView.startImageCycle();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.text_add_bx:
                //跳转到报修详情（新增报修）
                application.setMark(Mark.BX_DETAIL);
                application.setOperMark(Mark.OperMark.ADD);
                intent = new Intent(getActivity(), CommonAty.class);
                getActivity().startActivity(intent);
                break;
            case R.id.text_bx:
                //我的报修
                intent = new Intent(getActivity(), BxListAty.class);
                startActivity(intent);
                break;
            case R.id.text_sao:
                intent = new Intent(getActivity(), QrCodeActivity.class);
                getActivity().startActivityForResult(intent, MyResultCode.SCANNIN_GREQUEST_CODE);
                break;
        }
    }
}
