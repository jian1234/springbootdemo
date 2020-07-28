package com.example.springbootdemo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.service.LingerServer;
import com.example.springbootdemo.serviceImpl.LingerServerImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "LingerController", tags={"人群计数API"})
@RequestMapping(value="/Linger")
public class LingerController {
	@Autowired
	private LingerServer lingerServer;
	
	@ApiOperation(value="启动线程",notes="启动线程1")
	@ApiImplicitParams({
	      @ApiImplicitParam(name = "Authorization",value = "Token验证信息",required = true,paramType = "form",dataType = "string")
	    })
	@RequestMapping("LingerStart.do")
	public String LingerStart(HttpServletRequest request,HttpServletResponse response){
		NumAndSleeptimeModel numAndSleeptimeModel = lingerServer.getNumAndSleeptimeModel();
		String num = numAndSleeptimeModel.getNUM();
		lingerServer.TranLingerDate();
		return "success";
	}
	@RequestMapping("SetLingerSLeepAndNum.do")
	public String SetSLeepAndNum(HttpServletRequest request,HttpServletResponse response){
		String sleep = request.getParameter("sleep")==null?"":request.getParameter("sleep");
		String num = request.getParameter("num")==null?"":request.getParameter("num");
		NumAndSleeptimeModel model = new NumAndSleeptimeModel();
		model.setSLEEPTIME(sleep);
		model.setNUM(num);
		boolean setSleepAndNum = lingerServer.SetSleepAndNum(model);
		if (setSleepAndNum) {
			return "success";
		}else {
			return "false";
		}
		
	}
}
