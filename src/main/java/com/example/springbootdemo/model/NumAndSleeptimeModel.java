package com.example.springbootdemo.model;

public class NumAndSleeptimeModel {
	
	private String SLEEPTIME; //时间间隔
	
	private String NUM;		 //单次读取次数
	
	private String SCHEDULEDNAME; //任务名称
	
	private String ISSTOP; //是否停止s
	
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSCHEDULEDNAME() {
		return SCHEDULEDNAME;
	}

	public void setSCHEDULEDNAME(String sCHEDULEDNAME) {
		SCHEDULEDNAME = sCHEDULEDNAME;
	}

	public String getISSTOP() {
		return ISSTOP;
	}

	public void setISSTOP(String iSSTOP) {
		ISSTOP = iSSTOP;
	}

	public String getSLEEPTIME() {
		return SLEEPTIME;
	}

	public void setSLEEPTIME(String sLEEPTIME) {
		SLEEPTIME = sLEEPTIME;
	}

	public String getNUM() {
		return NUM;
	}

	public void setNUM(String nUM) {
		NUM = nUM;
	}

}
