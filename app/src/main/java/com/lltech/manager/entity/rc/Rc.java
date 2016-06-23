package com.lltech.manager.entity.rc;

import java.io.Serializable;

public class Rc implements Serializable {

	private static final long serialVersionUID = -1L;

	private String ScheduleID;
	private String ScheduleName;
	private String StartTime;
	private String EndTime;
	private String ExecutUser;
	private String ScheduleDesc;
	private String State;
	private String SubmitProgress;
	private String SubmitWorkContent;
	private String SubmitTime;
	private String SubmitUser;
	private String SortCode;
	private String EmployeeID;
	private String Leve;

	public String getScheduleID() {
		return ScheduleID;
	}

	public void setScheduleID(String scheduleID) {
		ScheduleID = scheduleID;
	}

	public String getScheduleName() {
		return ScheduleName;
	}

	public void setScheduleName(String scheduleName) {
		ScheduleName = scheduleName;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getExecutUser() {
		return ExecutUser;
	}

	public void setExecutUser(String executUser) {
		ExecutUser = executUser;
	}

	public String getScheduleDesc() {
		return ScheduleDesc;
	}

	public void setScheduleDesc(String scheduleDesc) {
		ScheduleDesc = scheduleDesc;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getSubmitProgress() {
		return SubmitProgress;
	}

	public void setSubmitProgress(String submitProgress) {
		SubmitProgress = submitProgress;
	}

	public String getSubmitWorkContent() {
		return SubmitWorkContent;
	}

	public void setSubmitWorkContent(String submitWorkContent) {
		SubmitWorkContent = submitWorkContent;
	}

	public String getSubmitTime() {
		return SubmitTime;
	}

	public void setSubmitTime(String submitTime) {
		SubmitTime = submitTime;
	}

	public String getSubmitUser() {
		return SubmitUser;
	}

	public void setSubmitUser(String submitUser) {
		SubmitUser = submitUser;
	}

	public String getSortCode() {
		return SortCode;
	}

	public void setSortCode(String sortCode) {
		SortCode = sortCode;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getLeve() {
		return Leve;
	}

	public void setLeve(String leve) {
		Leve = leve;
	}
}
