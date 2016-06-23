package com.lltech.manager.biz.impl;

import com.android.volley.VolleyError;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.biz.IBx;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.bx.Bx;
import com.lltech.manager.entity.bx.BxReq;
import com.lltech.manager.entity.bx.BxRes;
import com.lltech.manager.entity.bx.Wx;
import com.lltech.manager.entity.bx.WxReq;
import com.lltech.manager.entity.bx.WxRes;
import com.lltech.manager.entity.cd.CdReq;
import com.lltech.manager.entity.cd.WorkCheck;
import com.lltech.manager.entity.cd.WorkCheckReq;
import com.lltech.manager.entity.cd.WorkCheckRes;
import com.lltech.manager.entity.pg.Apply;
import com.lltech.manager.entity.pg.Pg;
import com.lltech.manager.entity.pg.PgReq;
import com.lltech.manager.entity.pg.PgRes;
import com.lltech.manager.entity.user.User;
import com.lltech.manager.entity.wg.WgReq;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.view.BxListView;
import com.lltech.manager.view.BxView;
import com.lltech.manager.view.CdSecondView;
import com.lltech.manager.view.CdView;
import com.lltech.manager.view.LdView;
import com.lltech.manager.view.PgListView;
import com.lltech.manager.view.PgView;
import com.lltech.manager.view.TdView;
import com.lltech.manager.view.WgView;
import com.lltech.manager.view.WxListView;
import com.lltech.manager.view.WxView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName(类名) : BxBiz
 * @Description(描述) : 报修业务
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月07日 10:59
 */
public class BxBiz implements IBx {
    private BxListView bxListView;
    private BxView bxView;
    private WxListView wxListView;
    private WxView wxView;
    private CdSecondView cdSecondView;
    private WgView wgView;
    private PgListView pgListView;
    private PgView pgView;
    private LdView ldView;
    private TdView tdView;
    private CdView cdView;
    private int pageIndex = 0;

    public BxBiz(Object object) {
        if (object instanceof BxListView) {
            this.bxListView = (BxListView) object;
        } else if (object instanceof BxView) {
            this.bxView = (BxView) object;
        } else if (object instanceof WxListView) {
            this.wxListView = (WxListView) object;
        } else if (object instanceof WxView) {
            this.wxView = (WxView) object;
        } else if (object instanceof CdSecondView) {
            this.cdSecondView = (CdSecondView) object;
        } else if (object instanceof WgView) {
            this.wgView = (WgView) object;
        } else if (object instanceof PgListView) {
            this.pgListView = (PgListView) object;
        } else if (object instanceof PgView) {
            this.pgView = (PgView) object;
        } else if (object instanceof LdView) {
            this.ldView = (LdView) object;
        } else if (object instanceof TdView) {
            this.tdView = (TdView) object;
        } else if (object instanceof CdView) {
            this.cdView = (CdView) object;
        }
    }

    @Override
    public void getBxList(final int pageType, String reqState, String text_BeginReportTime, String text_EndReportTime) {
        switch (pageType) {
            case Req.PULL_DOWN:
                pageIndex = 0;
                break;
            case Req.PULL_UP:
                pageIndex++;
                break;
        }
        String url = UrlCons.url(UrlCons.FaultReportService.GET_LIST);
        BxReq req = new BxReq();
        req.setPageIndex(pageIndex);
        switch (MyData.getUser(User.class).getUserType()) {
            case User.JS:
                req.setReportUserID(MyData.getUser(User.class).getEmployeeID());
                break;
            case User.YZ:
                req.setReportUserID(MyData.getUser(User.class).getEmployeeID());
                break;
            case User.YY:
                break;
        }
        req.setState(reqState);
        req.setOrderBy("ReportTime desc");
        if (CommonUtil.isNotEmpty(text_BeginReportTime)) {
            req.setBeginReportTime(text_BeginReportTime);
        }
        if (CommonUtil.isNotEmpty(text_BeginReportTime)) {
            req.setEndReportTime(text_EndReportTime);
        }
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                BxRes res = JsonUtils.jsonToEntity(response, BxRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    LinkedList<Bx> list = res.getDataList();
                    switch (pageType) {
                        case Req.PULL_DOWN: {
                            bxListView.onLoad(list);
                            break;
                        }
                        case Req.PULL_UP: {
                            bxListView.onLoadMore(list);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFail(Data data) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                bxListView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                bxListView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                bxListView.onTokenError();
            }
        });
    }

    @Override
    public void getWxList(final int pageType, String reqState, String edit_RepairNo, String edit_RepairTitle) {
        switch (pageType) {
            case Req.PULL_DOWN:
                pageIndex = 0;
                break;
            case Req.PULL_UP:
                pageIndex++;
                break;
        }
        String url = UrlCons.url(UrlCons.RepairService.GET_LIST);
        WxReq req = new WxReq();
        req.setPageIndex(pageIndex);
        req.setOrderBy("RepairTime desc");
        req.setState(reqState);
        if (CommonUtil.isNotEmpty(edit_RepairNo)) {
            req.setRepairNo(edit_RepairNo);
        }
        if (CommonUtil.isNotEmpty(edit_RepairTitle)) {
            req.setRepairTitle(edit_RepairTitle);
        }
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                WxRes res = JsonUtils.jsonToEntity(response, WxRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    LinkedList<Wx> list = res.getDataList();
                    switch (pageType) {
                        case Req.PULL_DOWN: {
                            wxListView.onLoad(list);
                            break;
                        }
                        case Req.PULL_UP: {
                            wxListView.onLoadMore(list);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFail(Data data) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                wxListView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                wxListView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                wxListView.onTokenError();
            }
        });
    }

    @Override
    public void inArchives(String FaultReportID, String DistributionID, String RepairDesc) {
        String url = UrlCons.url(UrlCons.RepairRecordService.SAVE);
        CdReq req = new CdReq();
        req.setFaultReportID(FaultReportID);
        req.setDistributionID(DistributionID);
        req.setRepairDesc(RepairDesc);
        req.setRepairUsers(MyData.getUser(User.class).getEmployeeID());
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                cdSecondView.InArchivesSuccess(data);
            }

            @Override
            public void onFail(Data data) {
                cdSecondView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                cdSecondView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                cdSecondView.onTokenError();
            }
        });
    }

    @Override
    public void review(String DistributionID, String reqState, String edit_ReviewedDesc) {
        String url = UrlCons.url(UrlCons.DistributionService.GET_REVIEW);
        WgReq req = new WgReq();
        req.setDistributionID(DistributionID);
        req.setState(reqState);
        if (CommonUtil.isNotEmpty(edit_ReviewedDesc)) {
            req.setReviewedDesc(edit_ReviewedDesc);
        }
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                wgView.reviewSuccess(data);
            }

            @Override
            public void onFail(Data data) {
                wgView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                wgView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                wgView.onTokenError();
            }
        });
    }

    @Override
    public void getWgWxEntity(String RepairID) {
        String url = UrlCons.url(UrlCons.RepairService.GET_ENTITY);
        WxReq req = new WxReq();
        req.setRepairID(RepairID);
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                wgView.refreshSuccess(data);
            }

            @Override
            public void onFail(Data data) {
                wgView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                wgView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                wgView.onTokenError();
            }
        });
    }

    @Override
    public void getPgList(final int pageType, String reqState, String text_EndDistributionTime, String text_BeginDistributionTime) {
        String url = UrlCons.url(UrlCons.DistributionService.GET_LIST);
        PgReq req = new PgReq();
        switch (MyData.getUser(User.class).getUserType()) {
            case User.JS:
                req.setRepairUserID(MyData.getUser(User.class).getEmployeeID());
                break;
            case User.YY:
                break;
        }
        req.setPageIndex(pageIndex);
        req.setState(reqState);
        req.setOrderBy("DistributionTime desc");
        if (CommonUtil.isNotEmpty(text_BeginDistributionTime)) {
            req.setBeginDistributionTime(text_BeginDistributionTime);
        }
        if (CommonUtil.isNotEmpty(text_EndDistributionTime)) {
            req.setEndDistributionTime(text_EndDistributionTime);
        }
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                PgRes res = JsonUtils.jsonToEntity(response, PgRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    LinkedList<Pg> list = res.getDataList();
                    switch (pageType) {
                        case Req.PULL_DOWN: {
                            pgListView.onLoad(list);
                            break;
                        }
                        case Req.PULL_UP: {
                            pgListView.onLoadMore(list);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFail(Data data) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                pgListView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                pgListView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                pgListView.onTokenError();
            }
        });
    }

    @Override
    public void getBxEntity(String FaultReportID) {
        String url = UrlCons.url(UrlCons.FaultReportService.GET_ENTITY);
        BxReq req = new BxReq();
        req.setFaultReportID(FaultReportID);
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                Bx bx = JsonUtils.jsonToEntity(response, Bx.class);
                if (CommonUtil.isNotEmpty(bx)) {
                    bxView.onGetEntitySuccess(bx);
                }
            }

            @Override
            public void onFail(Data data) {
                bxView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                bxView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                bxView.onTokenError();
            }
        });
    }

    @Override
    public void saveBx(Bx bx) {
        String url;
        if (CommonUtil.isEmpty(bx.getFaultReportID())) {
            url = UrlCons.url(UrlCons.FaultReportService.INSERT);
        } else {
            url = UrlCons.url(UrlCons.FaultReportService.UPDATE);
        }
        ReqData data = new ReqData(bx);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                bxView.onSaveSuccess();
            }

            @Override
            public void onFail(Data data) {
                bxView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                bxView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                bxView.onTokenError();
            }
        });
    }

    @Override
    public void delBx(Bx bx) {
        String url = UrlCons.url(UrlCons.FaultReportService.DEL_LIST);
        List<Bx> delList = new ArrayList<Bx>();
        delList.add(bx);
        BxReq req = new BxReq();
        req.setIDList(delList);
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                bxView.onDelSuccess();
            }

            @Override
            public void onFail(Data data) {
                bxView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                bxView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                bxView.onTokenError();
            }
        });
    }

    @Override
    public void getWxEntity(String RepairID) {
        String url = UrlCons.url(UrlCons.RepairService.GET_ENTITY);
        WxReq req = new WxReq();
        req.setRepairID(RepairID);
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                Wx wx = JsonUtils.jsonToEntity(response, Wx.class);
                if (CommonUtil.isNotEmpty(wx)) {
                    wxView.onGetEntitySuccess(wx);
                }
            }

            @Override
            public void onFail(Data data) {
                wxView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                wxView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                wxView.onTokenError();
            }
        });
    }

    @Override
    public void saveWx(Wx wx) {
        String url = UrlCons.url(UrlCons.RepairService.INSERT);
        ReqData data = new ReqData(wx);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                wxView.onSaveSuccess();
            }

            @Override
            public void onFail(Data data) {
                wxView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                wxView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                wxView.onTokenError();
            }
        });
    }

    @Override
    public void delWx(Wx wx) {
        String url = UrlCons.url(UrlCons.RepairService.DEL_LIST);
        List<Wx> delList = new ArrayList<Wx>();
        delList.add(wx);
        WxReq req = new WxReq();
        req.setIDList(delList);
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                wxView.onDelSuccess();
            }

            @Override
            public void onFail(Data data) {
                wxView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                wxView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                wxView.onTokenError();
            }
        });
    }

    @Override
    public void wxPg(Pg pg) {
        String url = UrlCons.url(UrlCons.DistributionService.GET_REPAIR);
        ReqData data = new ReqData(pg);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                pgView.onPgSuccess();
            }

            @Override
            public void onFail(Data data) {
                pgView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                pgView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                pgView.onTokenError();
            }
        });
    }

    @Override
    public void ld(String DistributionID) {
        String url = UrlCons.url(UrlCons.DistributionService.GET_RECEIVE);
        PgReq req = new PgReq();
        req.setDistributionID(DistributionID);
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                ldView.onLdSuccess();
            }

            @Override
            public void onFail(Data data) {
                ldView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                ldView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                ldView.onTokenError();
            }
        });
    }

    @Override
    public void td(Apply apply) {
        String url = UrlCons.url(UrlCons.DistributionApplyService.APPLY);
        ReqData data = new ReqData(apply);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                tdView.onTdSuccess();
            }

            @Override
            public void onFail(Data data) {
                tdView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                tdView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                tdView.onTokenError();
            }
        });
    }

    @Override
    public void getCdWxEntity(String RepairID) {
        String url = UrlCons.url(UrlCons.RepairService.GET_ENTITY);
        WxReq req = new WxReq();
        req.setRepairID(RepairID);
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                Wx wx = JsonUtils.jsonToEntity(response, Wx.class);
                if (CommonUtil.isNotEmpty(wx)) {
                    cdView.onGetCdWxEntitySuccess(wx);
                }
            }

            @Override
            public void onFail(Data data) {
                cdView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                cdView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                cdView.onTokenError();
            }
        });
    }

    @Override
    public void checkIn(WorkCheck workCheck) {
        String url = UrlCons.url(UrlCons.WorkCheckService.CHECK_IN);
        ReqData data = new ReqData(workCheck);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                cdView.onCheckInSuccess();
            }

            @Override
            public void onFail(Data data) {
                cdView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                cdView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                cdView.onTokenError();
            }
        });
    }

    @Override
    public void checkOut(WorkCheck workCheck) {
        String url = UrlCons.url(UrlCons.WorkCheckService.CHECK_OUT);
        ReqData data = new ReqData(workCheck);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                cdView.onCheckOutSuccess();
            }

            @Override
            public void onFail(Data data) {
                cdView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                cdView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                cdView.onTokenError();
            }
        });
    }

    @Override
    public void getCheckHistory(String ObjectID) {
        String url = UrlCons.url(UrlCons.WorkCheckService.GET_LIST);
        WorkCheckReq req = new WorkCheckReq();
        req.setPageIndex(pageIndex);
        req.setPageSize(1000);
        req.setObjectType(WorkCheck.WX);
        req.setObjectID(ObjectID);
        req.setCheckUser(MyData.getUser(User.class).getEmployeeID());
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                WorkCheckRes res = JsonUtils.jsonToEntity(response, WorkCheckRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    cdView.onGetCheckHistorySuccess(res.getDataList());
                }
            }

            @Override
            public void onFail(Data data) {
                cdView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                cdView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                cdView.onTokenError();
            }
        });
    }
}
