package com.lltech.manager.biz.impl;

import com.android.volley.VolleyError;
import com.lcx.mysdk.entity.CKey;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.biz.ISplash;
import com.lltech.manager.entity.IpItem;
import com.lltech.manager.entity.login.LoginReq;
import com.lltech.manager.entity.project.ProjectRes;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.view.SplashView;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @ClassName(类名) : SplashBiz
 * @Description(描述) : 启动业务
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 13:59
 */
public class SplashBiz implements ISplash {
    private SplashView splashView;

    public SplashBiz(SplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void initAppData() {
        final User user = MyData.getUser(User.class);
        List<IpItem> list = DataSupport.where("choose = ?", IpItem.Y).find(IpItem.class);
        if (CommonUtil.isEmpty(list)) {
            IpItem ipItem = new IpItem();
            ipItem.setIp(UrlCons.DEFUALT_IP);
            ipItem.setPort(UrlCons.DEFUALT_PORT);
            ipItem.setChoose(IpItem.Y);
            ipItem.save();
            MyData.setUrl(ipItem.getIp() + ":" + ipItem.getPort());
        }
        String url = UrlCons.url(UrlCons.GetCodeService.GET);
        VolleyHttp.send(VolleyHttp.GET, url, null, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String json = data.getResponse().toString();
                CKey key = JsonUtils.jsonToEntity(json, CKey.class);
                MyData.setCode(key.getPassWord());
//                if (CommonUtil.isNotEmpty(user)) {
//                    initProjectData(user);
//                } else {
//                    splashView.onSuccess(data);
//                }
                splashView.onSuccess(data);
            }

            @Override
            public void onFail(Data data) {
                splashView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                splashView.onError(volleyError);
            }
        });
    }

    @Override
    public void initProjectData(User user) {
        String url = UrlCons.url(UrlCons.ProjectService.GET_LIST);
        LoginReq req = new LoginReq();
        req.setUserID(user.getUserId());
        final ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                ProjectRes project = JsonUtils.jsonToEntity(data.getResponse().toString(), ProjectRes.class);
                if (!CommonUtil.isEmpty(project.getDataList())) {
                    MyData.setProjects(JsonUtils.stringify(project.getDataList()));
                }
                splashView.onSuccess(data);
            }

            @Override
            public void onFail(Data data) {
                splashView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                splashView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                MyData.setToken("");
                splashView.onTokenError();
            }
        });
    }
}
