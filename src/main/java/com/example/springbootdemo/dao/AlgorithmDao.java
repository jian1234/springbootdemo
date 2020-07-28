package com.example.springbootdemo.dao;

import java.util.List;
import java.util.Map;

public interface AlgorithmDao {
	
	List<Map<String, Object>> getAlgorithm(int nums);
	
	List<Map<String, Object>> getMonitorvideo(String t_monitorvideo_id); //参数t_monitorvideo_id 是T_ALGORITHM_MAIN 的t_monitorvideo_id
	
	List<Map<String, Object>> getLinger(String main_id);  //参数mian_id 是T_ALGORITHM_MAIN 的主键
	
	List<Map<String, Object>> getLinger_Frame(String main_id); //参数mian_id 是T_ALGORITHM_MAIN 的主键
	
	Map<String,Object> getNumsForLinger();
	
	// 发送成功修改标is_trans=“1”
	public int updateForMonitorvideo(String id);
	
	public int updateForLinger(String id);
	
	public int updateForLinger_Frame(String id);
	
	public int updateForAlgorithm(String id);
}
