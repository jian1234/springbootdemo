package com.example.springbootdemo.dao;

import java.util.List;
import java.util.Map;

public interface RqhzdDao {
	Map<String,Object> getNumsForPeopleHybrid();
	
	List<Map<String, Object>> getAlgorithm_rqhzd(int num);
	
	//人群计数
	List<Map<String, Object>> getPeopleHybrid(String main_id);

	//修改人群混杂度表状态
	public int updatePeopleHybrid(String rqhzdid);
	
}
