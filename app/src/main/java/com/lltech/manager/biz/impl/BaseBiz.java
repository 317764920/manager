package com.lltech.manager.biz.impl;

import com.android.volley.VolleyError;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.DateUtils;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.biz.IBase;
import com.lltech.manager.entity.AppVersion;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.login.LoginReq;
import com.lltech.manager.entity.me.SystemMsg;
import com.lltech.manager.entity.me.SystemMsgReq;
import com.lltech.manager.entity.me.SystemMsgRes;
import com.lltech.manager.entity.project.ProjectRes;
import com.lltech.manager.entity.rc.Rc;
import com.lltech.manager.entity.rc.RcReq;
import com.lltech.manager.entity.rc.RcRes;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.view.AboutView;
import com.lltech.manager.view.AccountAddView;
import com.lltech.manager.view.AccountView;
import com.lltech.manager.view.MsgSendView;
import com.lltech.manager.view.MsgView;
import com.lltech.manager.view.PwdView;
import com.lltech.manager.view.RcListView;

import org.litepal.crud.DataSupport;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName(类名) : BaseBiz
 * @Description(描述) : 个人中心业务
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月21日 17:05
 */
public class BaseBiz implements IBase {
    private AccountView accountView;
    private AccountAddView accountAddView;
    private AboutView aboutView;
    private PwdView pwdView;
    private RcListView rcListView;
    private MsgSendView msgSendView;
    private MsgView msgView;
    private User lastUser, nowUser;
    private int pageIndex = 0;

    public BaseBiz(Object object) {
        if (object instanceof AccountView) {
            this.accountView = (AccountView) object;
        } else if (object instanceof AccountAddView) {
            this.accountAddView = (AccountAddView) object;
        } else if (object instanceof AboutView) {
            this.aboutView = (AboutView) object;
        } else if (object instanceof PwdView) {
            this.pwdView = (PwdView) object;
        } else if (object instanceof RcListView) {
            this.rcListView = (RcListView) object;
        } else if (object instanceof MsgSendView) {
            this.msgSendView = (MsgSendView) object;
        } else if (object instanceof MsgView) {
            this.msgView = (MsgView) object;
        }
    }

    @Override
    public void changeAccount(final User item) {
        String url = UrlCons.url(UrlCons.LoginService.LOGIN);
        LoginReq loginReq = new LoginReq();
        loginReq.setPageIndex(null);
        loginReq.setLoginID(item.getUserAccount());
        loginReq.setUserPwd(item.getOldPwd());
        ReqData req = new ReqData(loginReq);
        VolleyHttp.send(VolleyHttp.POST, url, req, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String json = data.getResponse().toString();
                User user = JsonUtils.jsonToEntity(json, User.class);
                List<User> i_list = DataSupport.where("choose = ?", User.Y).find(User.class);
                lastUser = i_list.get(0);
                CommonUtil.bean2Bean(user, item);
                nowUser = item;
                initProjectData(user);
            }

            @Override
            public void onFail(Data data) {
                accountView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                accountView.onError(volleyError);
            }
        });
    }

    @Override
    public void addAccount(String username, final String pwd) {
        String url = UrlCons.url(UrlCons.LoginService.LOGIN);
        LoginReq loginReq = new LoginReq();
        loginReq.setPageIndex(null);
        loginReq.setLoginID(username);
        loginReq.setUserPwd(pwd);
        ReqData req = new ReqData(loginReq);
        VolleyHttp.send(VolleyHttp.POST, url, req, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String json = data.getResponse().toString();
                User user = JsonUtils.jsonToEntity(json, User.class);
                user.setOldPwd(pwd);
                user.setChoose(User.N);
                user.save();
                accountAddView.onSuccess(data);
            }

            @Override
            public void onFail(Data data) {
                accountAddView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                accountAddView.onError(volleyError);
            }
        });
    }

    @Override
    public void checkUpdate(final String version) {
        String url = UrlCons.url(UrlCons.AppVerHistory.GET_NEW);
        VolleyHttp.send(VolleyHttp.GET, url, null, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                AppVersion appVersion = JsonUtils.jsonToEntity(response, AppVersion.class);
                if (appVersion != null && !version.equalsIgnoreCase(appVersion.getVersionNumber())) {
                    aboutView.onHasNewVersion(appVersion);
                } else {
                    aboutView.onNoNewVersion();
                }
            }

            @Override
            public void onFail(Data data) {
                aboutView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                aboutView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                aboutView.onTokenError();
            }
        });
    }

    @Override
    public void updatePwd(String OldPwd, String NewPwd) {
        User user = new User();
        user.setLoginID(MyData.getUser(User.class).getUserAccount());
        user.setOldPwd(OldPwd);
        user.setNewPwd(NewPwd);
        ReqData data = new ReqData(user);
        String url = UrlCons.url(UrlCons.LoginService.UPDATE_PWD);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                Map<String, Object> map = JsonUtils.jsonToMap(response);
                if (Integer.parseInt(map.get("count").toString()) == 1) {
                    pwdView.onSuccess();
                } else {
                    pwdView.onPwdError();
                }
            }

            @Override
            public void onFail(Data data) {
                pwdView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                pwdView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                pwdView.onTokenError();
            }
        });
    }

    @Override
    public void getRcList() {
        String url = UrlCons.url(UrlCons.ScheduleService.GET_LIST);
        RcReq req = new RcReq();
        req.setPageIndex(0);
        req.setPageSize(1000);
        req.setEmployeeID(MyData.getUser(User.class).getEmployeeID());
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                RcRes res = JsonUtils.jsonToEntity(response, RcRes.class);
                if (res != null) {
                    LinkedList<Rc> list = res.getDataList();
                    rcListView.onSuccess(list);
                }
            }

            @Override
            public void onFail(Data data) {
                rcListView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                rcListView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                rcListView.onTokenError();
            }
        });
    }

    @Override
    public void sendMsg(String MessageTitle, String MessageContent, String RecipientListID) {
        String url = UrlCons.url(UrlCons.SystemInfo.INSERT);
        SystemMsg systemMsg = new SystemMsg();
        systemMsg.setMessageTitle(MessageTitle);
        systemMsg.setMessageContent(MessageContent);
        systemMsg.setRecipientListID(RecipientListID);
        systemMsg.setSender(MyData.getUser(User.class).getEmployeeID());
        systemMsg.setSendTime(DateUtils.date2String(DateUtils.currentDateTime()));
        systemMsg.setState("0");
        systemMsg.setRemark("");
        systemMsg.setCompanyID(MyData.getUser(User.class).getCompanyID());
        systemMsg.setMessageType("Personal_meg");
        ReqData data = new ReqData(systemMsg);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                msgSendView.onSuccess();
            }

            @Override
            public void onFail(Data data) {
                msgSendView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                msgSendView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                msgSendView.onTokenError();
            }
        });
    }

    @Override
    public void getMsgList(final int pageType, String MessageType, String RecipientState) {
        String url = UrlCons.url(UrlCons.SystemInfo.GET_MESSAGES);
        SystemMsgReq req = new SystemMsgReq();
        req.setRecipient(MyData.getUser(User.class).getEmployeeID());
        req.setRecipientState(RecipientState);
        req.setMessageType(MessageType);
        req.setPageIndex(pageIndex);
        req.setOrderBy("SendTime desc");
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                SystemMsgRes res = JsonUtils.jsonToEntity(response, SystemMsgRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    LinkedList<SystemMsg> list = res.getDataList();
                    switch (pageType) {
                        case Req.PULL_DOWN: {
                            msgView.onLoad(list);
                            break;
                        }
                        case Req.PULL_UP: {
                            msgView.onLoadMore(list);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFail(Data data) {
                msgView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                msgView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                msgView.onTokenError();
            }
        });
    }

    @Override
    public void updateMsg(String SysMsgRecipientID) {
        String url = UrlCons.url(UrlCons.SystemInfo.UPDATE);
        SystemMsgReq req = new SystemMsgReq();
        req.setSysMsgRecipientID(SysMsgRecipientID);
        req.setState("1");
        req.setRemark("");
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                msgView.onUpdateSuccess();
            }

            @Override
            public void onFail(Data data) {
                msgView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                msgView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                msgView.onTokenError();
            }
        });
    }

    /**
     * @throws : void
     * @Description(功能描述) : 初始化项目数据
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年10月16日 下午5:01:46
     */
    private void initProjectData(final User user) {
        String url = UrlCons.url(UrlCons.ProjectService.GET_LIST);
        LoginReq req = new LoginReq();
        req.setUserID(user.getUserId());
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                ProjectRes projects = JsonUtils.jsonToEntity(data.getResponse().toString(), ProjectRes.class);
                if (!CommonUtil.isEmpty(projects.getDataList())) {
                    MyData.setProjects(JsonUtils.stringify(projects.getDataList()));
                }
                lastUser.setChoose(User.N);
                lastUser.save();
                nowUser.setChoose(User.Y);
                nowUser.save();
                MyData.setUser(JsonUtils.stringify(user));
                MyData.setToken(user.getTokenID());
                accountView.onSuccess(data);
            }

            @Override
            public void onFail(Data data) {
                accountView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                accountView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                accountView.onTokenError();
            }
        });
    }
}
