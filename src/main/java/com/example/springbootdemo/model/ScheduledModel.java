package com.example.springbootdemo.model;

public class ScheduledModel {
	
	private String SLEEPTIME;
	
	private String NUM;
	
	private String FLAG;
	
	private String ISSTOP;
	
	private String SCHEDULEDNAME; //任务名称
	
	public String getSCHEDULEDNAME() {
		return SCHEDULEDNAME;
	}

	public void setSCHEDULEDNAME(String sCHEDULEDNAME) {
		SCHEDULEDNAME = sCHEDULEDNAME;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	private String uuid;

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

	public String getFLAG() {
		return FLAG;
	}

	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}

	public String getISSTOP() {
		return ISSTOP;
	}

	public void setISSTOP(String iSSTOP) {
		ISSTOP = iSSTOP;
	}
	
	
}
