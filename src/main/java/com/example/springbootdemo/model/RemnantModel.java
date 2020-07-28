package com.example.springbootdemo.model;

/**
 * 可疑物品遗留表实体类 PICTURE_SUSP、PICTURE_OBJ两个字段为传输时的流，不需从数据库查出
 * @author lwz
 *
 */
public class RemnantModel {
	private String FRAME; //帧数
	
	private String MAIN_ID; 
	
	private String SUSP_ID; //嫌疑人ID
	
	private String ABADON_VID; //遗弃视频存储地址
	
	private String KEY_HBASE; 
	
	private String OBJ_ID; //遗留物ID-一个main_id对应多个物品分析
	
	private String ROI; //视频可疑区域记录，同一遗留物可能对应多帧数据
	
	private String CREATE_TIME	; //插入时间
	
	private String PICTURE_SUSP; //遗留者图片
	
	private String PICTURE_OBJ; //遗留物图片

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

	public String getSUSP_ID() {
		return SUSP_ID;
	}

	public void setSUSP_ID(String sUSP_ID) {
		SUSP_ID = sUSP_ID;
	}

	public String getABADON_VID() {
		return ABADON_VID;
	}

	public void setABADON_VID(String aBADON_VID) {
		ABADON_VID = aBADON_VID;
	}

	public String getKEY_HBASE() {
		return KEY_HBASE;
	}

	public void setKEY_HBASE(String kEY_HBASE) {
		KEY_HBASE = kEY_HBASE;
	}

	public String getOBJ_ID() {
		return OBJ_ID;
	}

	public void setOBJ_ID(String oBJ_ID) {
		OBJ_ID = oBJ_ID;
	}

	public String getROI() {
		return ROI;
	}

	public void setROI(String rOI) {
		ROI = rOI;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getPICTURE_SUSP() {
		return PICTURE_SUSP;
	}

	public void setPICTURE_SUSP(String pICTURE_SUSP) {
		PICTURE_SUSP = pICTURE_SUSP;
	}

	public String getPICTURE_OBJ() {
		return PICTURE_OBJ;
	}

	public void setPICTURE_OBJ(String pICTURE_OBJ) {
		PICTURE_OBJ = pICTURE_OBJ;
	}
	
	
}
