package com.lltech.manager.entity.me;

import java.io.Serializable;
import java.util.LinkedList;

public class EmployeeRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Employee> DataList;

	public LinkedList<Employee> getDataList() {
		return DataList;
	}

	public void setDataList(LinkedList<Employee> dataList) {
		DataList = dataList;
	}
	
}
