package com.example.springbootdemo.dao;

import java.util.List;
import java.util.Map;

public interface RlsbDao {

	//获取人脸识别定时任务执行的条数
	Map<String, Object> getNumsForRlsb();
	
	//根据条数取数据
	List<Map<String, Object>> getRlsbList(int nums);
	
	//修改状态
	int updateForRlsb(String uuid);
	
}
