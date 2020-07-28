package com.example.springbootdemo.model;

/**
 * T_FACEALARM 人脸实别表实体模型
 * PICTURE为传输过程中的数据流，不需从数据库中读取
 * @author lwz
 *
 */
public class FacealarmModel {

	private String UUID;
	
	private String PERSON_ID;
	
	private String CHARACTER_ID;
	
	private String LIBID;
	
	private String SIMILARITY;
	
	private String IMGPATH;
	
	private String POSITION_RECT;
	
	private String POSITION_P5S;
	
	private String TASKID;
	
	private String CREATE_TIME;
	
	private String PLATFORM_ID;
	
	private String DATA_ID;
	
	private String PICTURE;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getPERSON_ID() {
		return PERSON_ID;
	}

	public void setPERSON_ID(String pERSON_ID) {
		PERSON_ID = pERSON_ID;
	}

	public String getCHARACTER_ID() {
		return CHARACTER_ID;
	}

	public void setCHARACTER_ID(String cHARACTER_ID) {
		CHARACTER_ID = cHARACTER_ID;
	}

	public String getLIBID() {
		return LIBID;
	}

	public void setLIBID(String lIBID) {
		LIBID = lIBID;
	}

	public String getSIMILARITY() {
		return SIMILARITY;
	}

	public void setSIMILARITY(String sIMILARITY) {
		SIMILARITY = sIMILARITY;
	}

	public String getIMGPATH() {
		return IMGPATH;
	}

	public void setIMGPATH(String iMGPATH) {
		IMGPATH = iMGPATH;
	}

	public String getPOSITION_RECT() {
		return POSITION_RECT;
	}

	public void setPOSITION_RECT(String pOSITION_RECT) {
		POSITION_RECT = pOSITION_RECT;
	}

	public String getPOSITION_P5S() {
		return POSITION_P5S;
	}

	public void setPOSITION_P5S(String pOSITION_P5S) {
		POSITION_P5S = pOSITION_P5S;
	}

	public String getTASKID() {
		return TASKID;
	}

	public void setTASKID(String tASKID) {
		TASKID = tASKID;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getPLATFORM_ID() {
		return PLATFORM_ID;
	}

	public void setPLATFORM_ID(String pLATFORM_ID) {
		PLATFORM_ID = pLATFORM_ID;
	}

	public String getDATA_ID() {
		return DATA_ID;
	}

	public void setDATA_ID(String dATA_ID) {
		DATA_ID = dATA_ID;
	}

	public String getPICTURE() {
		return PICTURE;
	}

	public void setPICTURE(String pICTURE) {
		PICTURE = pICTURE;
	}
	
	
}
