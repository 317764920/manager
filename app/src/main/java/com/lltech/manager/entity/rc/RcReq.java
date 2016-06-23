package com.lltech.manager.entity.rc;

import com.lltech.manager.entity.Req;

/**
 * 
 * @ClassName(类名) : RcReq
 * @Description(描述) : 日程请求对象
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月28日 下午1:35:17
 *
 */
public class RcReq extends Req {

	private static final long serialVersionUID = 1L;

	private String EmployeeID;

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}
}
