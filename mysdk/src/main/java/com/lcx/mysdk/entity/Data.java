package com.lcx.mysdk.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName(类名) : Data
 * @Description(描述) : 请求返回数据对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月24日 上午11:09:10
 *
 */
public class Data implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 状态码：ok-成功；fail-失败
	 */
	private String result;
	/**
	 * 数据
	 */
	private Object Response;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getResponse() {
		return Response;
	}

	public void setResponse(Object response) {
		this.Response = response;
	}
}
