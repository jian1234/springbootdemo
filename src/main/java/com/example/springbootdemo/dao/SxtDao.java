package com.example.springbootdemo.dao;

import java.util.List;
import java.util.Map;

public interface SxtDao {

	//获取人摄像头管理定时任务执行的条数
	Map<String, Object> getNumsForSxt();
	
	//根据条数取数据
	List<Map<String, Object>> getSxtList(int nums);
	
	//修改状态
	int updateForSxt(String uuid);
	
}
