package com.lltech.manager.entity;

public class OrderCode extends Req{
	
	public static final String BX = "01";
	public static final String PG = "02";
	public static final String WX = "03";
	public static final String EQ = "04";
	public static final String PRE = "05";
	public static final String PRE_IN = "06";
	public static final String PRE_OUT = "07";
	public static final String BY = "08";

	private static final long serialVersionUID = 1L;
	private String SerialNumber;
	private String AutomaticNo;
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getAutomaticNo() {
		return AutomaticNo;
	}
	public void setAutomaticNo(String automaticNo) {
		AutomaticNo = automaticNo;
	}
}
