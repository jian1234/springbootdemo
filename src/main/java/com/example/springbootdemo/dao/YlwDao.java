package com.example.springbootdemo.dao;

import java.util.List;
import java.util.Map;

public interface YlwDao {
	Map<String,Object> getNumsForRemnant();
	
	List<Map<String, Object>> getAlgorithm_Ylw(int num);
	
	//遗留物
	List<Map<String, Object>> getRemnant(String main_id);

	//遗留物
	List<Map<String, Object>> getRemnant_Sp(String main_id);
	
	//修改遗留物状态
	public int updateRemnant(String ylwid);
	//修改遗留物视频状态
	public int updateRemnant_video(String ylwspid);
}
