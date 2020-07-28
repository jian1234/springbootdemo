package com.example.springbootdemo.dao;

import java.util.List;
import java.util.Map;

public interface TfDao {
	Map<String,Object> getNumsForPeopleOther();
	
	List<Map<String, Object>> getAlgorithm_Tf(int num);
	
	//条幅
	List<Map<String, Object>> PeopleOther(String main_id);

	//修改条幅检测表状态
	public int updatePeopleOther(String rqhzdid);
	
}
