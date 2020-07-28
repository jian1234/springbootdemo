package com.example.springbootdemo.dao;

import java.util.List;
import java.util.Map;

public interface RqjsDao {

	Map<String,Object> getNumsForPeopleCount();
	
	List<Map<String, Object>> getAlgorithm_rqjs(int num);
	
	//人群计数
	List<Map<String, Object>> getPeopleCount(String main_id);

	//修改人群计数表状态
	public int updatePeopleCount(String rqjsid);
	
}
