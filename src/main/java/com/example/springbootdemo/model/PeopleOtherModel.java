package com.example.springbootdemo.model;

/**
 *T_PEOPLEOTHER（条幅表）实体类 PICTURE 不需要重数据库中查出
 * @author lwz
 *
 */
public class PeopleOtherModel {
	
	private String UUID;
	
	private String MAIN_ID;
	
	private String BANNER;
	
	private String SEGMENTATION1;
	
	private String SKELETON;
	
	private String SEGMENTATION2;
	
	private String CREATE_TIME;
	
	private String KEY_HBASE;
	
	private String FRAME;
	
	private String PICTURE;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getMAIN_ID() {
		return MAIN_ID;
	}

	public void setMAIN_ID(String mAIN_ID) {
		MAIN_ID = mAIN_ID;
	}

	public String getBANNER() {
		return BANNER;
	}

	public void setBANNER(String bANNER) {
		BANNER = bANNER;
	}

	public String getSEGMENTATION1() {
		return SEGMENTATION1;
	}

	public void setSEGMENTATION1(String sEGMENTATION1) {
		SEGMENTATION1 = sEGMENTATION1;
	}

	public String getSKELETON() {
		return SKELETON;
	}

	public void setSKELETON(String sKELETON) {
		SKELETON = sKELETON;
	}

	public String getSEGMENTATION2() {
		return SEGMENTATION2;
	}

	public void setSEGMENTATION2(String sEGMENTATION2) {
		SEGMENTATION2 = sEGMENTATION2;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getKEY_HBASE() {
		return KEY_HBASE;
	}

	public void setKEY_HBASE(String kEY_HBASE) {
		KEY_HBASE = kEY_HBASE;
	}

	public String getFRAME() {
		return FRAME;
	}

	public void setFRAME(String fRAME) {
		FRAME = fRAME;
	}

	public String getPICTURE() {
		return PICTURE;
	}

	public void setPICTURE(String pICTURE) {
		PICTURE = pICTURE;
	}
	
	
	
}
