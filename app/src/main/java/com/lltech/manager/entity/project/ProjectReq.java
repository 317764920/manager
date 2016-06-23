package com.lltech.manager.entity.project;

import com.lltech.manager.entity.Req;

/**
 * 
 * @ClassName(类名) : ProjectReq
 * @Description(描述) : 项目请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 *
 */
public class ProjectReq extends Req {

	private static final long serialVersionUID = 1L;

	private String UserID;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String UserID) {
		this.UserID = UserID;
	}
}
