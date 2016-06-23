package com.lltech.manager.entity.project;

import java.io.Serializable;

public class Project implements Serializable {

	private static final long serialVersionUID = -1L;

	private String ProjectID;
	private String ProjectName;
	private String ProjectDesc;
	public String getProjectID() {
		return ProjectID;
	}
	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getProjectDesc() {
		return ProjectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		ProjectDesc = projectDesc;
	}

}
