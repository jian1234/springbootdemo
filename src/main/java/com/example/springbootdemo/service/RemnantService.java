package com.example.springbootdemo.service;

import com.example.springbootdemo.model.NumAndSleeptimeModel;

public interface RemnantService {

	boolean TranRemnantDate();
	
	boolean SetSleepAndNum(NumAndSleeptimeModel model);
	
	NumAndSleeptimeModel getNumAndSleeptimeModel();
	
	void stopScheduled();
	
	void RestartScheduled();
}
