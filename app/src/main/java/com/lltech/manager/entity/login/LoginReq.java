package com.lltech.manager.entity.login;

import com.lltech.manager.entity.Req;

public class LoginReq extends Req {

	private static final long serialVersionUID = 1L;

	private String LoginID;
	private String UserPwd;
	private String UserID;

	public String getLoginID() {
		return LoginID;
	}

	public void setLoginID(String loginID) {
		LoginID = loginID;
	}

	public String getUserPwd() {
		return UserPwd;
	}

	public void setUserPwd(String userPwd) {
		UserPwd = userPwd;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

}
