package com.example.springbootdemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.service.PeopleCountService;

@Controller
public class PeopleCountController {

	@Autowired
	private PeopleCountService PeopleCountService;
	
	
	@RequestMapping("PeopleCountStart.do")
	public String LingerStart(HttpServletRequest request,HttpServletResponse response){
		NumAndSleeptimeModel numAndSleeptimeModel = PeopleCountService.getNumAndSleeptimeModel();
		String num = numAndSleeptimeModel.getNUM();
		PeopleCountService.TranPeopleCOuntDate();
		return "success";
	}
	
	@RequestMapping("SetPeopleCountSLeepAndNum.do")
	public String SetSLeepAndNum(HttpServletRequest request,HttpServletResponse response){
		String sleep = request.getParameter("sleep")==null?"":request.getParameter("sleep");
		String num = request.getParameter("num")==null?"":request.getParameter("num");
		NumAndSleeptimeModel model = new NumAndSleeptimeModel();
		model.setSLEEPTIME(sleep);
		model.setNUM(num);
		boolean setSleepAndNum = PeopleCountService.SetSleepAndNum(model);
		if (setSleepAndNum) {
			return "success";
		}else {
			return "false";
		}
		
	}
	
}
