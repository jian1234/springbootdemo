package com.example.springbootdemo.model;

public class PeopleModel {

	private String FRAME; //帧数
	
	private String MAIN_ID; //算法主表ID
	
	private String KEY_HBASE; 
	
	private String CSRA_COUNT; //A算法人数
	
	private String CSRB_COUNT; //B算法人数
	
	private String SEG_COUNT; //语义分隔人数
	
	private String CREATE_TIME; //插入时间
	
	private String PEOPLE_COUNT; //综合人数
	
	private String DENSITY_MAP; //密度map
	
	private String PICTURE;//图片流

	public String getPEOPLE_COUNT() {
		return PEOPLE_COUNT;
	}

	public void setPEOPLE_COUNT(String pEOPLE_COUNT) {
		PEOPLE_COUNT = pEOPLE_COUNT;
	}

	public String getDENSITY_MAP() {
		return DENSITY_MAP;
	}

	public void setDENSITY_MAP(String dENSITY_MAP) {
		DENSITY_MAP = dENSITY_MAP;
	}

	public String getPICTURE() {
		return PICTURE;
	}

	public void setPICTURE(String pICTURE) {
		PICTURE = pICTURE;
	}

	public String getFRAME() {
		return FRAME;
	}	
	
	public void setFRAME(String fRAME) {
		FRAME = fRAME;
	}

	public String getMAIN_ID() {
		return MAIN_ID;
	}

	public void setMAIN_ID(String mAIN_ID) {
		MAIN_ID = mAIN_ID;
	}

	public String getKEY_HBASE() {
		return KEY_HBASE;
	}

	public void setKEY_HBASE(String kEY_HBASE) {
		KEY_HBASE = kEY_HBASE;
	}

	public String getCSRA_COUNT() {
		return CSRA_COUNT;
	}

	public void setCSRA_COUNT(String cSRA_COUNT) {
		CSRA_COUNT = cSRA_COUNT;
	}

	public String getCSRB_COUNT() {
		return CSRB_COUNT;
	}

	public void setCSRB_COUNT(String cSRB_COUNT) {
		CSRB_COUNT = cSRB_COUNT;
	}

	public String getSEG_COUNT() {
		return SEG_COUNT;
	}

	public void setSEG_COUNT(String sEG_COUNT) {
		SEG_COUNT = sEG_COUNT;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	
	
}
