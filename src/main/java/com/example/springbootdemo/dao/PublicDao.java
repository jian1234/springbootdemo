package com.example.springbootdemo.dao;

import java.util.List;

import com.example.springbootdemo.model.CompleteModel;
import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.model.PicAndVideoModel;
import com.example.springbootdemo.model.ScheduledModel;

public interface PublicDao {

	PicAndVideoModel getPicAndVideoModel(String main_id);
	
	void updateTrans(String main_id);
	
	//获取数据库中存储的cron表达式
	public ScheduledModel getCron(String flag);
	
	public List<ScheduledModel> initScheduledTable();
	
	public CompleteModel GetComplete();
	
	public int updateSchedule(NumAndSleeptimeModel model);
	
	public ScheduledModel selectScheduled(String uuid);
	
}
