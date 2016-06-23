package com.lltech.manager.entity.badge;

import com.lltech.manager.entity.Req;

public class BadgeReq extends Req{

	private static final long serialVersionUID = 1L;
	private String CreateUserID;
	private String QueryEmployeeID;
	public String getCreateUserID() {
		return CreateUserID;
	}
	public void setCreateUserID(String createUserID) {
		CreateUserID = createUserID;
	}
	public String getQueryEmployeeID() {
		return QueryEmployeeID;
	}
	public void setQueryEmployeeID(String queryEmployeeID) {
		QueryEmployeeID = queryEmployeeID;
	}
}
