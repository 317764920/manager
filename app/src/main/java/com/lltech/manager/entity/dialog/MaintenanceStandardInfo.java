package com.lltech.manager.entity.dialog;

import java.io.Serializable;

public class MaintenanceStandardInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	private String MaintenanceStandards;
	private String MaintenanceStandardName;

	public String getMaintenanceStandards() {
		return MaintenanceStandards;
	}

	public void setMaintenanceStandards(String maintenanceStandards) {
		MaintenanceStandards = maintenanceStandards;
	}

	public String getMaintenanceStandardName() {
		return MaintenanceStandardName;
	}

	public void setMaintenanceStandardName(String maintenanceStandardName) {
		MaintenanceStandardName = maintenanceStandardName;
	}

}
