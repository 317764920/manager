package com.lltech.manager.fragment.info;

import android.widget.TextView;

import com.lcx.mysdk.fragment.base.BaseFragment;
import com.lltech.manager.R;
import com.lltech.manager.entity.eq.Eq;

/**
 * @ClassName(类名) : JsFragment
 * @Description(描述) : 技术参数
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月26日 下午4:10:04
 */
public class JsFragment extends BaseFragment {

    private TextView ProtocolId, SignalAddr, DataAddr, ProxyAddr, ProxyPortAddr, ProxyUser, ProxyPwd, PortAddr, SignalUser, SignalPwd;

    @Override
    public int setContentView() {
        return R.layout.detail_eq_js;
    }

    @Override
    public void initView() {
        ProtocolId = $(R.id.ProtocolId);
        SignalAddr = $(R.id.SignalAddr);
        DataAddr = $(R.id.DataAddr);
        ProxyAddr = $(R.id.ProxyAddr);
        ProxyPortAddr = $(R.id.ProxyPortAddr);
        ProxyUser = $(R.id.ProxyUser);
        ProxyPwd = $(R.id.ProxyPwd);
        PortAddr = $(R.id.PortAddr);
        SignalUser = $(R.id.SignalUser);
        SignalPwd = $(R.id.SignalPwd);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initConfig() {

    }

    @Override
    public void initData() {
        Eq eq = (Eq) this.getArguments().getSerializable("eq");
        ProtocolId.setText(eq.getProtocolId());
        SignalAddr.setText(eq.getSignalAddr());
        DataAddr.setText(eq.getDataAddr());
        ProxyAddr.setText(eq.getProxyAddr());
        ProxyPortAddr.setText(eq.getProxyPortAddr());
        ProxyUser.setText(eq.getProxyUser());
        ProxyPwd.setText(eq.getProxyPwd());
        PortAddr.setText(eq.getPortAddr());
        SignalUser.setText(eq.getSignalUser());
        SignalPwd.setText(eq.getSignalPwd());
    }
}
