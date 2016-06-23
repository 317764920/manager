package com.lltech.manager.entity.badge;

import com.lltech.manager.entity.Req;

public class Badge extends Req{

	private static final long serialVersionUID = 1L;
	private String CreateUserID;
	private String AllMegCount;
	private String PersonalMegCount;
	private String WarningCount;
	private String DBCount;
	public String getCreateUserID() {
		return CreateUserID;
	}
	public void setCreateUserID(String createUserID) {
		CreateUserID = createUserID;
	}
	public String getAllMegCount() {
		return AllMegCount;
	}
	public void setAllMegCount(String allMegCount) {
		AllMegCount = allMegCount;
	}
	public String getPersonalMegCount() {
		return PersonalMegCount;
	}
	public void setPersonalMegCount(String personalMegCount) {
		PersonalMegCount = personalMegCount;
	}
	public String getWarningCount() {
		return WarningCount;
	}
	public void setWarningCount(String warningCount) {
		WarningCount = warningCount;
	}
	public String getDBCount() {
		return DBCount;
	}
	public void setDBCount(String dBCount) {
		DBCount = dBCount;
	}
}
