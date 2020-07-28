package com.example.springbootdemo.dao;

import java.util.List;

import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.model.PeopleFaceModel;




public interface PeopleFaceDao {
	//根据main_id获取所有的徘徊人员信息
	public PeopleFaceModel GetpeoFace(String uuid);
	
	//查询num条未转换的人群聚集的main_id 参数为每次读取多少条main_id
	public List<String> GetMainid(String num);
	
	//查询num和时间间隔
	public NumAndSleeptimeModel getNumAndSleeptime();
	
	//更新num和时间间隔
	public  int setSleepAndNum(NumAndSleeptimeModel mdoel);
	
	//停止线程
	public int StopScheduled() ;
	//重启线程
	public int RestartScheduled() ;
	//更新转换状态
	public int updateTRANS(String uuid);
	
	
}
