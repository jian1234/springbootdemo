package com.example.springbootdemo.model;
/**
 * T_ALGORITHM_MAIN 算法主表实体类， OUTPUTVIDEO不需要从数据库查出
 * @author lwz
 *
 */
public class AlgorithmMainModel {
	
	private String UUID;
	
	private String INPUTPATH;
	
	private String OUTPUTPATH;
	
	private String TABLENAME;
	
	private String COLUMNNAME;
	
	private String STATUS;
	
	private String CREATE_TIME;
	
	private String T_MONITORVIDEO_ID;
	
	private String OUTPUTVIDEO;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getINPUTPATH() {
		return INPUTPATH;
	}

	public void setINPUTPATH(String iNPUTPATH) {
		INPUTPATH = iNPUTPATH;
	}

	public String getOUTPUTPATH() {
		return OUTPUTPATH;
	}

	public void setOUTPUTPATH(String oUTPUTPATH) {
		OUTPUTPATH = oUTPUTPATH;
	}

	public String getTABLENAME() {
		return TABLENAME;
	}

	public void setTABLENAME(String tABLENAME) {
		TABLENAME = tABLENAME;
	}

	public String getCOLUMNNAME() {
		return COLUMNNAME;
	}

	public void setCOLUMNNAME(String cOLUMNNAME) {
		COLUMNNAME = cOLUMNNAME;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getT_MONITORVIDEO_ID() {
		return T_MONITORVIDEO_ID;
	}

	public void setT_MONITORVIDEO_ID(String t_MONITORVIDEO_ID) {
		T_MONITORVIDEO_ID = t_MONITORVIDEO_ID;
	}

	public String getOUTPUTVIDEO() {
		return OUTPUTVIDEO;
	}

	public void setOUTPUTVIDEO(String oUTPUTVIDEO) {
		OUTPUTVIDEO = oUTPUTVIDEO;
	}
	

}
