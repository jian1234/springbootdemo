package com.example.springbootdemo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springbootdemo.Scheduled.ScheduledTask_Linger;
import com.example.springbootdemo.Scheduled.ScheduledTask_PeopleCount;
import com.example.springbootdemo.Scheduled.ScheduledTask_PeopleFace;
import com.example.springbootdemo.Scheduled.ScheduledTask_Remnant;
import com.example.springbootdemo.dao.PublicDao;
import com.example.springbootdemo.model.CompleteModel;
import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.model.ScheduledModel;
import com.example.springbootdemo.service.LingerServer;
import com.example.springbootdemo.service.PeopleCountService;
import com.example.springbootdemo.service.PeopleFaceService;
import com.example.springbootdemo.service.RemnantService;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
public class ScheduledController {
	
	 @Autowired
	 private ScheduledTask_Linger scheduledTask_Linger;
	 
	 @Autowired
	 private ScheduledTask_Remnant scheduledTask_Remnant;
	 
	 @Autowired
	 private ScheduledTask_PeopleCount scheduledTask_PeopleCount;
	 @Autowired
	 private ScheduledTask_PeopleFace ScheduledTask_PeopleFace;
	 @Autowired
	 private LingerServer LingerServer;
	 @Autowired
	 private PeopleCountService PeopleCountService;
	 @Autowired
	 private RemnantService RemnantService;
	 @Autowired
	 private PublicDao PublicDao;
	 @Autowired
	 private PeopleFaceService peoplefaceService;
	 
	 @RequestMapping(value = "/updateScheduledTask")
	 @ResponseBody
	 public String updateScheduledTask(@RequestParam("cron") String cron,@RequestParam("isstop")  String iSSTOP,
			 @RequestParam("num") String num,@RequestParam("name") String name,@RequestParam("uuid") String uuid) {
		 	System.err.println("进入更新controller");
			NumAndSleeptimeModel model = new NumAndSleeptimeModel();
			model.setNUM(num);
			model.setSLEEPTIME(cron);
			model.setISSTOP(iSSTOP);
			model.setSCHEDULEDNAME(name);
			model.setUuid(uuid);
		 	int updateSchedule = PublicDao.updateSchedule(model);
		 	System.err.println(updateSchedule);
	        return "success";
	    }
	 
	 @ResponseBody
	 @RequestMapping("selectScheduled")
	 public Map<String, Object> selectScheduled(@RequestParam("uuid") String uuid ){
		ScheduledModel scheduledModel = PublicDao.selectScheduled(uuid);
		Map<String, Object> map = new HashMap<String, Object>();
     	map.put("cron", scheduledModel.getSLEEPTIME());
     	map.put("is_stop", scheduledModel.getISSTOP().equals("1")?"启动":"未启动");
     	map.put("num", scheduledModel.getNUM());
     	map.put("ScheduledName", scheduledModel.getSCHEDULEDNAME());
     	map.put("ScheduledFlag", scheduledModel.getFLAG());
     	map.put("uuid", scheduledModel.getUuid());
		return map;
	 }
	 
	 @RequestMapping(value = "/stop_scheduled")
	 public String stopScheduledTask(@RequestParam("flag") String flag) {
//		String cron = "0 0 0 0 12 ? 2099-2099";
		if ("1".equals(flag)) {
			scheduledTask_PeopleCount.setStop_type("0");
			PeopleCountService.stopScheduled();
		}else if ("2".equals(flag)) {
			scheduledTask_Linger.setStop_type("0");
			LingerServer.stopScheduled();
		}else if ("3".equals(flag)) {
			scheduledTask_Remnant.setStop_type("0");
			RemnantService.stopScheduled();
		}else if ("4".equals(flag)) {
			ScheduledTask_PeopleFace.setStop_type("0");
			peoplefaceService.stopScheduled();
		}
		return "success";
	 }
	 
	 @RequestMapping(value = "/restart_scheduled")
	 public String RestartScheduledTask(@RequestParam("flag") String flag) {
		 String cron ;
		 if ("1".equals(flag)) {
			 	NumAndSleeptimeModel model = PeopleCountService.getNumAndSleeptimeModel();
			 	cron  = model.getSLEEPTIME();
			 	PeopleCountService.RestartScheduled();
				scheduledTask_PeopleCount.setStop_type("1");
			}else if ("2".equals(flag)) {
				NumAndSleeptimeModel model = LingerServer.getNumAndSleeptimeModel();
				cron = model.getSLEEPTIME();
				LingerServer.RestartScheduled();
				scheduledTask_Linger.setStop_type("1");
			}else if ("3".equals(flag)) {
				NumAndSleeptimeModel model =RemnantService.getNumAndSleeptimeModel();
				cron = model.getSLEEPTIME();
				RemnantService.RestartScheduled();
				scheduledTask_Remnant.setStop_type("1");
			}else if ("4".equals(flag)) {
				NumAndSleeptimeModel model = PeopleCountService.getNumAndSleeptimeModel();
				cron = model.getSLEEPTIME();
				peoplefaceService.RestartScheduled();
				ScheduledTask_PeopleFace.setStop_type("1");
			}
		return "success";
	}
	 
	 	@RequestMapping(value = { "/initScheduledTable.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	    @ResponseBody
	    public Map<String, Object> queryFormmodel_layui(HttpServletRequest request, 
	        HttpServletResponse response) throws IOException {
		 	System.err.println("进入controller");
	    	// 当前页码
			String page = request.getParameter("page")==null?"":request.getParameter("page");
			// 每页行数
			String limit = request.getParameter("limit")==null?"":request.getParameter("limit");
	        //检索规则
	        String searchrule= request.getParameter("searchrule");
	        String type=request.getParameter("type");
	        String sql="";
	       
	           
//	        int count = commonDao.countPages(sql.toString(), null);
//	        PageUtil pageUtil = new PageUtil(limit, page, count, "1");
//			List<Map<String, Object>> list = commonDao.getPagesList(pageUtil.getStart(), pageUtil.getEnd(), sql.toString(), null);
	        List<ScheduledModel> initScheduledTable = PublicDao.initScheduledTable();
	        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	        for (int i = 0; i < initScheduledTable.size(); i++) {
	        	ScheduledModel scheduledModel = initScheduledTable.get(i);
	        	Map<String, Object> map = new HashMap<String, Object>();
	        	map.put("cron", scheduledModel.getSLEEPTIME());
	        	map.put("is_stop", scheduledModel.getISSTOP().equals("1")?"启动":"未启动");
	        	map.put("num", scheduledModel.getNUM());
	        	map.put("ScheduledName", scheduledModel.getSCHEDULEDNAME());
	        	map.put("ScheduledFlag", scheduledModel.getFLAG());
	        	map.put("uuid", scheduledModel.getUuid());
	        	list.add(map);
			}
	        Map<String, Object> jsonMap = new HashMap<String, Object>();
	  		jsonMap.put("code", 0);
	  		jsonMap.put("msg", "");
	  		jsonMap.put("count", 3);
	  		jsonMap.put("data", list);
	       return jsonMap;
	    }
	 	
	 	//完成未完成数量查询
	 	@RequestMapping(value = { "/GetComplete.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	    @ResponseBody
	 	public Map<String,Object> GetComplete(HttpServletRequest request, 
		        HttpServletResponse response){
	 			CompleteModel getComplete = PublicDao.GetComplete();
	 			Map<String, Object> map = new HashMap<String, Object>();
	 			map.put("peo_wc", getComplete.getPeo_wc());
	 			map.put("peo_wwc", getComplete.getPeo_wwc());
	 			map.put("lin_wc", getComplete.getLin_wc());
	 			map.put("lin_wwc", getComplete.getLin_wwc());
	 			map.put("rem_wc", getComplete.getRem_wc());
	 			map.put("rem_wwc", getComplete.getRem_wwc());
	 			map.put("face_wc", getComplete.getFace_wc());
	 			map.put("face_wwc", getComplete.getFace_wwc());
			return map;
	 		
	 	}
}
