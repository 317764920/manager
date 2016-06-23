package com.lcx.mysdk.entity;

import java.io.Serializable;
/**
 *
 * @Description(功能描述)    : 错误信息
 * @author(作者)         ：liuchunxu
 * @date (开发日期)      ：2016-04-06 13:43
 *
 */
public class ErrorInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorText;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}
