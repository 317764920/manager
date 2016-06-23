package com.lltech.manager.biz.impl;

import com.android.volley.VolleyError;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ReqData;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.VolleyHttp;
import com.lltech.manager.biz.IEq;
import com.lltech.manager.entity.Req;
import com.lltech.manager.entity.eq.Eq;
import com.lltech.manager.entity.eq.EqReq;
import com.lltech.manager.entity.eq.EqRes;
import com.lltech.manager.util.MyData;
import com.lltech.manager.util.UrlCons;
import com.lltech.manager.view.EqInfoView;
import com.lltech.manager.view.EqListView;

import java.util.LinkedList;

/**
 * @ClassName(类名) : EqBiz
 * @Description(描述) : 设备档案业务
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年06月21日 16:16
 */
public class EqBiz implements IEq {
    private EqListView eqListView;
    private EqInfoView eqInfoView;
    private int pageIndex = 0;

    public EqBiz(Object object) {
        if (object instanceof EqListView) {
            this.eqListView = (EqListView) object;
        } else if (object instanceof EqInfoView) {
            this.eqInfoView = (EqInfoView) object;
        }
    }

    @Override
    public void getList(final int pageType, String reqState, String systemCode, String edit_EquipmentNo, String edit_EquipmentName) {
        switch (pageType) {
            case Req.PULL_DOWN:
                pageIndex = 0;
                break;
            case Req.PULL_UP:
                pageIndex++;
                break;
        }
        String url = UrlCons.url(UrlCons.EquipmentService.GET_LIST);
        EqReq req = new EqReq();
        req.setPageIndex(pageIndex);
        req.setProjectID(MyData.getProject().getProjectID());
        req.setSystemCode(systemCode);
        req.setStatus(reqState);
        if (CommonUtil.isNotEmpty(edit_EquipmentNo)) {
            req.setEquipmentNo(edit_EquipmentNo);
        }
        if (CommonUtil.isNotEmpty(edit_EquipmentName)) {
            req.setEquipmentName(edit_EquipmentName);
        }
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                EqRes res = JsonUtils.jsonToEntity(response, EqRes.class);
                if (CommonUtil.isNotEmpty(res)) {
                    LinkedList<Eq> list = res.getDataList();
                    switch (pageType) {
                        case Req.PULL_DOWN: {
                            eqListView.onLoad(list);
                            break;
                        }
                        case Req.PULL_UP: {
                            eqListView.onLoadMore(list);
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
                eqListView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                if (pageIndex > 0) {
                    pageIndex--;
                }
                eqListView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                eqListView.onTokenError();
            }
        });
    }

    @Override
    public void getEntity(Eq eq) {
        String url = UrlCons.url(UrlCons.EquipmentService.GET_ONE);
        EqReq req = new EqReq();
        req.setEquipmentID(eq.getEquipmentID());
        req.setEquipmentNo(eq.getEquipmentNo());
        ReqData data = new ReqData(req);
        VolleyHttp.send(VolleyHttp.POST, url, data, new VolleyHttp.OnResponse() {
            @Override
            public void onOk(Data data) {
                String response = data.getResponse().toString();
                Eq eq = JsonUtils.jsonToEntity(response, Eq.class);
                if (eq != null) {
                    eqInfoView.onSuccess(eq);
                }
            }

            @Override
            public void onFail(Data data) {
                eqInfoView.onFail(data);
            }

            @Override
            public void onError(VolleyError volleyError) {
                eqInfoView.onError(volleyError);
            }
        }, new VolleyHttp.OnTokenError() {
            @Override
            public void onTokenError() {
                eqInfoView.onTokenError();
            }
        });
    }
}
