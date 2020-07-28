package com.example.springbootdemo.dao;

import java.util.List;

import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.model.PeopleModel;





public interface PeopleCountDao {
	//根据main_id获取所有的人员聚集信息
	public List<PeopleModel> GetGroupEventEarl(String main_id);
	
	//查询100条未转换的人员徘徊的main_id 参数为每次读取多少条main_id
	public List<String> GetMainid(String num);
	
	//查询num和时间间隔
	public NumAndSleeptimeModel getNumAndSleeptime();
	
	//更新num和时间间隔
	public  int setSleepAndNum(NumAndSleeptimeModel mdoel);
	
	//停止线程
	public int StopScheduled() ;
	
	//重启线程
	public int RestartScheduled() ;
	
	
}
