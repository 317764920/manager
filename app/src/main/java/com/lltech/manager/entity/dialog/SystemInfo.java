package com.lltech.manager.entity.dialog;

import java.io.Serializable;

public class SystemInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	private String SystemCode;
	private String SystemName;

	public String getSystemCode() {
		return SystemCode;
	}

	public void setSystemCode(String systemCode) {
		SystemCode = systemCode;
	}

	public String getSystemName() {
		return SystemName;
	}

	public void setSystemName(String systemName) {
		SystemName = systemName;
	}

}
