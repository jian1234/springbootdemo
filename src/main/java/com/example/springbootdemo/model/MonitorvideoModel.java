package com.example.springbootdemo.model;

/**
 * T_MONITORVIDEO（原始视频表）实体类
 * @author lwz
 *
 */
public class MonitorvideoModel {

	private String ID;
	
	private String MODELNAME;
	
	private String FORMAT;
	
	private String FILESIZE;
	
	private String CAMERAID;
	
	private String ADDRESS;
	
	private String RESOLUTIONRATE;
	
	private String LENGTH_WEIGHT_SCALE;
	
	private String INPUTVIDEO;// 获取视频后转的视频流，从数据库重查询时没有该字段

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMODELNAME() {
		return MODELNAME;
	}

	public void setMODELNAME(String mODELNAME) {
		MODELNAME = mODELNAME;
	}

	public String getFORMAT() {
		return FORMAT;
	}

	public void setFORMAT(String fORMAT) {
		FORMAT = fORMAT;
	}

	public String getFILESIZE() {
		return FILESIZE;
	}

	public void setFILESIZE(String fILESIZE) {
		FILESIZE = fILESIZE;
	}

	public String getCAMERAID() {
		return CAMERAID;
	}

	public void setCAMERAID(String cAMERAID) {
		CAMERAID = cAMERAID;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getRESOLUTIONRATE() {
		return RESOLUTIONRATE;
	}

	public void setRESOLUTIONRATE(String rESOLUTIONRATE) {
		RESOLUTIONRATE = rESOLUTIONRATE;
	}

	public String getLENGTH_WEIGHT_SCALE() {
		return LENGTH_WEIGHT_SCALE;
	}

	public void setLENGTH_WEIGHT_SCALE(String lENGTH_WEIGHT_SCALE) {
		LENGTH_WEIGHT_SCALE = lENGTH_WEIGHT_SCALE;
	}

	public String getINPUTVIDEO() {
		return INPUTVIDEO;
	}

	public void setINPUTVIDEO(String iNPUTVIDEO) {
		INPUTVIDEO = iNPUTVIDEO;
	}
	
}
