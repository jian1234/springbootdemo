package com.example.springbootdemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.service.RemnantService;

import io.swagger.annotations.Api;

@Controller
@Api(value = "RemnantController", tags={"可疑物遗留API"})
@RequestMapping(value="/api")
public class RemnantController {
	@Autowired
	private RemnantService remnantService;
	
	@RequestMapping("RemnantStart.do")
	public String RemnantStart(HttpServletRequest request,HttpServletResponse response) {
		NumAndSleeptimeModel numAndSleeptimeModel = remnantService.getNumAndSleeptimeModel();
		String num = numAndSleeptimeModel.getNUM();
		remnantService.TranRemnantDate();
		return null;
	}
	
	@RequestMapping("SetRemnantSLeepAndNum.do")
	public String SetSLeepAndNum(HttpServletRequest request,HttpServletResponse response){
		String sleep = request.getParameter("sleep")==null?"":request.getParameter("sleep");
		String num = request.getParameter("num")==null?"":request.getParameter("num");
		NumAndSleeptimeModel model = new NumAndSleeptimeModel();
		model.setSLEEPTIME(sleep);
		model.setNUM(num);
		boolean setSleepAndNum = remnantService.SetSleepAndNum(model);
		if (setSleepAndNum) {
			return "success";
		}else {
			return "false";
		}
		
	}
}
