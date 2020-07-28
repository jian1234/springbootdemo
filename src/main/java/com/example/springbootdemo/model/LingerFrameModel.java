package com.example.springbootdemo.model;
/**
 * T_LINGER_FRAME（人员徘徊辅表）实体类 picture为传输时的流，不需要从数据库中查出
 * @author lwz
 *
 */
public class LingerFrameModel {

	private String LINGER_ID;
	
	private String MAIN_ID;
	
	private String FRAME;
	
	private String KEY_HBASE;
	
	private String PEO_ID;
	
	private String SPEEN;
	
	private String DISTANCE;
	
	private String TIME;
	
	private String SLOPE;
	
	private String PERSON_RESULT;
	
	private String LOITER_TYPE;
	
	private String CREATE_TIME;
	
	private String PICTURE;

	public String getLINGER_ID() {
		return LINGER_ID;
	}

	public void setLINGER_ID(String lINGER_ID) {
		LINGER_ID = lINGER_ID;
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

	public String getKEY_HBASE() {
		return KEY_HBASE;
	}

	public void setKEY_HBASE(String kEY_HBASE) {
		KEY_HBASE = kEY_HBASE;
	}

	public String getPEO_ID() {
		return PEO_ID;
	}

	public void setPEO_ID(String pEO_ID) {
		PEO_ID = pEO_ID;
	}

	public String getSPEEN() {
		return SPEEN;
	}

	public void setSPEEN(String sPEEN) {
		SPEEN = sPEEN;
	}

	public String getDISTANCE() {
		return DISTANCE;
	}

	public void setDISTANCE(String dISTANCE) {
		DISTANCE = dISTANCE;
	}

	public String getTIME() {
		return TIME;
	}

	public void setTIME(String tIME) {
		TIME = tIME;
	}

	public String getSLOPE() {
		return SLOPE;
	}

	public void setSLOPE(String sLOPE) {
		SLOPE = sLOPE;
	}

	public String getPERSON_RESULT() {
		return PERSON_RESULT;
	}

	public void setPERSON_RESULT(String pERSON_RESULT) {
		PERSON_RESULT = pERSON_RESULT;
	}

	public String getLOITER_TYPE() {
		return LOITER_TYPE;
	}

	public void setLOITER_TYPE(String lOITER_TYPE) {
		LOITER_TYPE = lOITER_TYPE;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getPICTURE() {
		return PICTURE;
	}
	
	public void setPICTURE(String pICTURE) {
		PICTURE = pICTURE;
	}
	
	
}
