package com.example.springbootdemo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springbootdemo.service.AlgorithmService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "AlgorithmController", tags={"算法"})
@Controller
public class AlgorithmController {
	
	@Autowired
	private AlgorithmService algorithmService;
	
	//获取算法表数据
	@ApiOperation(value="获取算发表",notes="获取数据库表名，无需参数")
	@RequestMapping(value="getAlgorithm.do",method=RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getAlgorithm() throws UnsupportedEncodingException, Exception{
		
		return algorithmService.getAlgorithm();
	}
}
