package com.example.springbootdemo.model;

public class LingerModel {
	private String uuid; //帧数
	
	private String MAIN_ID; //算法主表ID
	
	private String FRAME; //算法主表ID
	
	private String PERSON_MATCH; //算法主表ID
	
	private String CREATE_TIME; //算法主表ID

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMAIN_ID() {
		return MAIN_ID;
	}

	public void setMAIN_ID(String mAIN_ID) {
		MAIN_ID = mAIN_ID;
	}

	public String getFRAME() {
		return FRAME;
	}

	public void setFRAME(String fRAME) {
		FRAME = fRAME;
	}

	public String getPERSON_MATCH() {
		return PERSON_MATCH;
	}

	public void setPERSON_MATCH(String pERSON_MATCH) {
		PERSON_MATCH = pERSON_MATCH;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	
	
}
