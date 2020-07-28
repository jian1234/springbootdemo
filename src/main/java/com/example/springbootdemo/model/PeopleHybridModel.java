package com.example.springbootdemo.model;
/**
 * PEOPLEHYBRID 人群混杂程度表实体类  PICTURE不需要从数据库中查出
 * @author lwz
 *
 */
public class PeopleHybridModel {
	
	private String UUID;
	
	private String MAIN_ID;
	
	private String ENTROPY;
	
	private String HYBRID;
	
	private String FRAME;
	
	private String CREATE_TIME;
	
	private String KEY_HBASE;
	
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

	public String getENTROPY() {
		return ENTROPY;
	}

	public void setENTROPY(String eNTROPY) {
		ENTROPY = eNTROPY;
	}

	public String getHYBRID() {
		return HYBRID;
	}

	public void setHYBRID(String hYBRID) {
		HYBRID = hYBRID;
	}

	public String getFRAME() {
		return FRAME;
	}

	public void setFRAME(String fRAME) {
		FRAME = fRAME;
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

	public String getPICTURE() {
		return PICTURE;
	}

	public void setPICTURE(String pICTURE) {
		PICTURE = pICTURE;
	}
	
}
