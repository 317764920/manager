package com.lltech.manager.biz.impl;

import com.android.volley.VolleyError;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.biz.ILogin;
import com.lltech.manager.entity.login.LoginReq;
import com.lltech.manager.entity.project.ProjectRes;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.view.LoginView;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @ClassName(类名) : LoginBiz
 * @Description(描述) : 登录业务
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 09:38
 */
public class LoginBiz implements ILogin {
    private LoginView loginView;

    public LoginBiz(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String loginUsername, final String loginPwd) {
        String url = UrlCons.url(UrlCons.LoginService.LOGIN);
        LoginReq loginReq = new LoginReq();
        loginReq.setPageIndex(null);
        loginReq.setLoginID(loginUsername);
        loginReq.setUserPwd(loginPwd);
        ReqData req = new ReqData(loginReq);
        VolleyHttp.send(VolleyHttp.POST, url, req, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String json = data.getResponse().toString();
                User user = JsonUtils.jsonToEntity(json, User.class);
                List<User> list = DataSupport.where("UserAccount = ?", user.getUserAccount()).find(User.class);
                if (CommonUtil.isEmpty(list)) {
                    //如果是新账号登录，直接新增，并选中
                    user.setChoose(User.Y);
                    user.setOldPwd(loginPwd);
                    user.save();
                } else {
                    //如果是旧账号
                    User lastUser = list.get(0);
                    //更新账号数据
                    CommonUtil.bean2Bean(user, lastUser);
                    lastUser.setOldPwd(loginPwd);
                    if (User.Y.equals(lastUser.getChoose())) {
                        //如果该账号之前是选中的,则保存该帐号信息
                        lastUser.save();
                    } else {
                        //如果该帐号并未为选中，将之前选中帐号设为不选中，再将该账号设为选中
                        User checkedUser = DataSupport.where("choose = ?", User.Y).find(User.class).get(0);
                        checkedUser.setChoose(User.N);
                        checkedUser.save();
                        lastUser.setChoose(User.Y);
                        lastUser.save();
                    }
                }
                MyData.setUser(json);
                MyData.setToken(user.getTokenID());
                initProjectData(user);
            }

            @Override
            public void onFail(Data data) {
                loginView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                loginView.onError(volleyError);
            }
        });
    }

    @Override
    public void initProjectData(User user) {
        String url = UrlCons.url(UrlCons.ProjectService.GET_LIST);
        LoginReq req = new LoginReq();
        req.setUserID(user.getUserId());
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                ProjectRes project = JsonUtils.jsonToEntity(data.getResponse().toString(), ProjectRes.class);
                if (!CommonUtil.isEmpty(project.getDataList())) {
                    MyData.setProjects(JsonUtils.stringify(project.getDataList()));
                }
                loginView.onLoginSuccess(data);
            }

            @Override
            public void onFail(Data data) {
                loginView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                loginView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                loginView.onTokenError();
            }
        });
    }
}
