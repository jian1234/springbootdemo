package com.example.springbootdemo.model;

public class PeopleFaceModel {
	//主键
	private String uuid;
	//返回的符合特征的人脸ID
	private String PERSON_ID;
	//摄像头返回的实时id
	private String CHARACTER_ID;
	//特征库ID
	private String LIBID;
	//相似度
	private String SIMILARITY;
	//图片存放路径(前面需要加url地址，即可返回图片流)
	private String IMGPATH;
	
	private String POSITION_RECT;
	
	private String POSITION_P5S;
	//任务ID
	private String TASKID;
	//插入时间
	private String CREATE_TIME;
	//主板ID
	private String PLATFORM_ID;
	//确定为同一条数据
	private String DATA_ID;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	
}
