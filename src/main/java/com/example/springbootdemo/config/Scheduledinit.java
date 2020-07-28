package com.example.springbootdemo.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbootdemo.Scheduled.ScheduledTask_Linger;
import com.example.springbootdemo.Scheduled.ScheduledTask_PeopleCount;
import com.example.springbootdemo.Scheduled.ScheduledTask_PeopleFace;
import com.example.springbootdemo.Scheduled.ScheduledTask_Remnant;
import com.example.springbootdemo.Scheduled.ScheduledTask_rlsb;
import com.example.springbootdemo.Scheduled.ScheduledTask_rqhzd;
import com.example.springbootdemo.Scheduled.ScheduledTask_rqjs;
import com.example.springbootdemo.Scheduled.ScheduledTask_ryph;
import com.example.springbootdemo.Scheduled.ScheduledTask_sxt;
import com.example.springbootdemo.Scheduled.ScheduledTask_tf;
import com.example.springbootdemo.Scheduled.ScheduledTask_ylw;
import com.example.springbootdemo.dao.PublicDao;
import com.example.springbootdemo.model.ScheduledModel;
@Component
public class Scheduledinit   {
	
//	@Autowired
//	private  PublicDao publicDao;
	@Autowired
	private  ScheduledTask_Linger scheduledTask_Linger;
	 
	@Autowired
	private  ScheduledTask_Remnant scheduledTask_Remnant;
	 
	@Autowired
	private  ScheduledTask_PeopleCount scheduledTask_PeopleCount;
	
	@Autowired
	private ScheduledTask_PeopleFace ScheduledTask_PeopleFace;
	
	/**
	  *     人员徘徊
	 */
	@Autowired
	private ScheduledTask_ryph ScheduledTask_ryph;
	/**
	 *  人群计数
	 */
	@Autowired
	private ScheduledTask_rqjs ScheduledTask_rqjs;
	/**
	 * 人群混杂度
	 */
	@Autowired
	private ScheduledTask_rqhzd ScheduledTask_rqhzd;
	/**
	 * 条幅
	 */
	@Autowired
	private ScheduledTask_tf scheduledTask_tf;
	/**
	 *  人脸识别
	 */
	@Autowired
	private ScheduledTask_rlsb ScheduledTask_rlsb;
	/**
	 * 遗留物
	 */
	@Autowired
	private ScheduledTask_ylw ScheduledTask_ylw;
	/**
	 * 摄像头
	 */
	@Autowired
	private ScheduledTask_sxt ScheduledTask_sxt;
//	
	@PostConstruct
    public void init(){
//		 ScheduledModel model_peo = publicDao.getCron("1");
//		 ScheduledModel model_lin = publicDao.getCron("2");
//		 ScheduledModel model_Rem = publicDao.getCron("3");
//		 ScheduledModel model_face = publicDao.getCron("4");
//
//		 ScheduledModel model_ryph = publicDao.getCron("5");
//		 ScheduledModel model_rqjs = publicDao.getCron("1");
//		 ScheduledModel model_rqhzd = publicDao.getCron("7"); //浑浊度
//		 ScheduledModel model_tf = publicDao.getCron("8");   //条幅
//		 ScheduledModel model_rlsb = publicDao.getCron("4"); //人脸识别
//		 ScheduledModel model_ylw = publicDao.getCron("3"); //可疑物遗留
//		 ScheduledModel model_sxt = publicDao.getCron("9"); //摄像头
//
//		scheduledTask_PeopleCount.setCron(model_peo.getSLEEPTIME());
//		scheduledTask_PeopleCount.setStop_type(model_peo.getISSTOP());
//		scheduledTask_Linger.setCron(model_lin.getSLEEPTIME());
//		scheduledTask_Linger.setStop_type(model_lin.getISSTOP());
//		scheduledTask_Remnant.setCron(model_Rem.getSLEEPTIME());
//		scheduledTask_Remnant.setStop_type(model_Rem.getISSTOP());
//		ScheduledTask_PeopleFace.setCron(model_face.getSLEEPTIME());
//		ScheduledTask_PeopleFace.setStop_type(model_face.getISSTOP());
//
//		//新写人员徘徊
//		ScheduledTask_ryph.setCron(model_ryph.getSLEEPTIME());
//		ScheduledTask_ryph.setStop_type(model_ryph.getISSTOP());
//		//人群计数
//		ScheduledTask_rqjs.setCron(model_rqjs.getSLEEPTIME());
//		ScheduledTask_rqjs.setStop_type(model_rqjs.getISSTOP());
//		//混杂度
//		ScheduledTask_rqhzd.setCron(model_rqhzd.getSLEEPTIME());
//		ScheduledTask_rqhzd.setStop_type(model_rqhzd.getISSTOP());
//		//条幅
//		scheduledTask_tf.setCron(model_tf.getSLEEPTIME());
//		scheduledTask_tf.setStop_type(model_tf.getISSTOP());
//		//人脸识别
//		ScheduledTask_rlsb.setCron(model_rlsb.getSLEEPTIME());
//		ScheduledTask_rlsb.setStop_type(model_rlsb.getISSTOP());
//		//可疑物遗留
//		ScheduledTask_ylw.setCron(model_ylw.getSLEEPTIME());
//		ScheduledTask_ylw.setStop_type(model_ylw.getISSTOP());
//		//摄像头
//		ScheduledTask_sxt.setCron(model_sxt.getSLEEPTIME());
//		ScheduledTask_sxt.setStop_type(model_sxt.getISSTOP());
    }

//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		
//		
//		
//		
//		
//		
//		System.err.println("启动后执行");
////		ServiceLocator serviceLocator = new ServiceLocator();
////		PublicDao publicDao = (PublicDao) serviceLocator.getBean("PublicDao");
////		ScheduledTask_Linger scheduledTask_Linger = (ScheduledTask_Linger) serviceLocator.getBean("ScheduledTask_Linger");
////		ScheduledTask_Remnant scheduledTask_Remnant = (ScheduledTask_Remnant) serviceLocator.getBean("ScheduledTask_Remnant");
////		ScheduledTask_PeopleCount scheduledTask_PeopleCount = (ScheduledTask_PeopleCount) serviceLocator.getBean("ScheduledTask_PeopleCount");
//		System.out.println(publicDao.getCron("1"));
//		
//		
////		String cron_peo = publicDao.getCron("1");
////		System.out.println("]]]]]]]]]]]]]]");
////		String cron_rem = publicDao.getCron("2");
////		String cron_lin = publicDao.getCron("3");
////		String cron_peo = "0 0 0 0 12 ? 2099-2099";
////		System.out.println("]]]]]]]]]]]]]]");
////		String cron_rem = "0 0 0 0 12 ? 2099-2099";
////		String cron_lin = "0 0 0 0 12 ? 2099-2099";
////		scheduledTask_PeopleCount.setCron(cron_peo);
////		scheduledTask_Linger.setCron(cron_lin);
////		scheduledTask_Remnant.setCron(cron_rem);
//	}

	
	

//	@Override
//	public void onApplicationEvent(ApplicationEvent event) {
//		String cron_peo = publicDao.getCron("1");
//		String cron_rem = publicDao.getCron("2");
//		String cron_lin = publicDao.getCron("3");
//		scheduledTask_PeopleCount.setCron(cron_peo);
//		scheduledTask_Linger.setCron(cron_lin);
//		scheduledTask_Remnant.setCron(cron_rem);
//		
//	}
	
	

}
