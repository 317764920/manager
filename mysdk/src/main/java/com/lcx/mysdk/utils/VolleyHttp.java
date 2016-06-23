package com.lcx.mysdk.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.lcx.mysdk.application.BaseApplication;
import com.lcx.mysdk.entity.Data;
import com.lcx.mysdk.entity.ErrorInfo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName(类名) : VolleyHttp
 * @Description(描述) : volley请求
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年04月08日 12:06
 */
public class VolleyHttp {
    public static final int POST = Request.Method.POST;
    public static final int GET = Request.Method.GET;
    public static final String OK = "ok";
    public static final String FAIL = "fail";
    public static final String TOKEN_ERROR = "token_error";
    private static String TAG;
    private static VolleyHttp mInstance;
    private GsonRequest<Data> request;

    private VolleyHttp() {
        TAG = getClass().getSimpleName();
    }

    /**
     * @Description(功能描述) : 请求回调接口
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-08 13:45
     */
    public interface OnResponse {
        /**
         * @Description(功能描述) : 请求成功---状态码ok
         * @author(作者) ：liuchunxu
         * @date (开发日期)      ：2016-04-08 13:27
         */
        void onOk(Data data);

        /**
         * @Description(功能描述) : 请求成功---状态码fail
         * @author(作者) ：liuchunxu
         * @date (开发日期)      ：2016-04-08 13:27
         */
        void onFail(Data data);

        /**
         * @Description(功能描述) : 请求失败
         * @author(作者) ：liuchunxu
         * @date (开发日期)      ：2016-04-08 13:27
         */
        void onError(VolleyError volleyError);
    }

    /**
     * @Description(功能描述) : token验证回调接口
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-08 13:45
     */
    public interface OnTokenError {
        /**
         * @Description(功能描述) : token错误
         * @author(作者) ：liuchunxu
         * @date (开发日期)      ：2016-04-08 13:27
         */
        void onTokenError();
    }

    private GsonRequest<Data> setRequest(int method, String url, Object params, final OnResponse listener, final OnTokenError tokenListener) {
        request = new GsonRequest<Data>(method, url, params, Data.class, new Response.Listener<Data>() {
            @Override
            public void onResponse(Data data) {
                if (OK.equalsIgnoreCase(data.getResult())) {
                    listener.onOk(data);
                } else if (FAIL.equalsIgnoreCase(data.getResult())) {
                    listener.onFail(data);
                } else if (TOKEN_ERROR.equalsIgnoreCase(data.getResult())) {
                    if (tokenListener != null) {
                        tokenListener.onTokenError();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError);
            }
        });
        return request;
    }

    private static VolleyHttp getInstance() {
        if (mInstance == null) {
            synchronized (VolleyHttp.class) {
                if (mInstance == null) {
                    mInstance = new VolleyHttp();
                }
            }
        }
        return mInstance;
    }

    private static GsonRequest<Data> getRequest(int method, String url, Object params, OnResponse listener, OnTokenError tokenListener) {
        return getInstance().setRequest(method, url, params, listener, tokenListener);
    }

    /**
     * @Description(功能描述) : 发送请求
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-08 13:14
     */
    public static void send(int method, String url, Object params, OnResponse listener) {
        BaseApplication.getApplication().getQueue().add(getRequest(method, url, params, listener, null));
    }

    /**
     * @Description(功能描述) : 发送请求
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-08 13:14
     */
    public static void send(int method, String url, Object params, OnResponse listener, OnTokenError tokenListener) {
        BaseApplication.getApplication().getQueue().add(getRequest(method, url, params, listener, tokenListener));
    }

    /**
     * @Description(功能描述) : 获取错误信息
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016-04-19 14:39
     */
    public static String errorInfo(Data data) {
        ErrorInfo errorInfo = JsonUtils.jsonToEntity(data.getResponse().toString(), ErrorInfo.class);
        return errorInfo == null ? "服务端错误" : errorInfo.getErrorText();
    }

    /**
     * @param <T>
     * @ClassName(类名) : GsonRequest
     * @Description(描述) : 网络请求返回对象
     * @author(作者) ：liuchunxu
     * @date (开发日期) ：2015年8月18日 下午1:47:27
     */
    private class GsonRequest<T> extends Request<T> {

        private final Response.Listener<T> mListener;

        private Class<T> mClass;

        private Object mparams;

        public GsonRequest(int method, String url, Object params, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
            super(method, url, errorListener);
            Log.d(TAG, url);
            mClass = clazz;
            mListener = listener;
            mparams = params;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Response<T> parseNetworkResponse(NetworkResponse response) {
            try {
                String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                Log.d(TAG, json);
                T jsonToEntity = JsonUtils.jsonToEntity(json, mClass);
                if (CommonUtil.isEmpty(jsonToEntity)) {
                    Data data = new Data();
                    data.setResult(FAIL);
                    jsonToEntity = (T) data;
                }
                return Response.success(jsonToEntity, HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            }
        }

        @Override
        protected void deliverResponse(T response) {
            mListener.onResponse(response);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            CommonUtil.bean2Map(mparams, params);
            Log.d(TAG, params.toString());
            return params;
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("t", AppData.getToken());
            Log.d(TAG, headers.toString());
            return headers;
        }

        @Override
        public void setRetryPolicy(RetryPolicy retryPolicy) {
            super.setRetryPolicy(new DefaultRetryPolicy(10000, 0, 0));
            // setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 0,
            // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
    }
}
