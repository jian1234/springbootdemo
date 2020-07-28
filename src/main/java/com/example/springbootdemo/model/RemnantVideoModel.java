package com.example.springbootdemo.model;

/**
 * T_REMNANT_VIDEO（可疑物品遗留视频表）实体类
 * @author lwz
 *
 */
public class RemnantVideoModel {

	private String MAIN_ID;
	
	private String OBJ_ID;
	
	private String VIDEOPATH;
	
	private String CREATE_TIME;
	
	private String VIDEO;

	public String getMAIN_ID() {
		return MAIN_ID;
	}

	public void setMAIN_ID(String mAIN_ID) {
		MAIN_ID = mAIN_ID;
	}

	public String getOBJ_ID() {
		return OBJ_ID;
	}

	public void setOBJ_ID(String oBJ_ID) {
		OBJ_ID = oBJ_ID;
	}

	public String getVIDEOPATH() {
		return VIDEOPATH;
	}

	public void setVIDEOPATH(String vIDEOPATH) {
		VIDEOPATH = vIDEOPATH;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getVIDEO() {
		return VIDEO;
	}

	public void setVIDEO(String vIDEO) {
		VIDEO = vIDEO;
	}
	
	
}
