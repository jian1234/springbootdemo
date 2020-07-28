package com.example.springbootdemo.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.example.springbootdemo.service.LingerServer;
import com.example.springbootdemo.service.PeopleCountService;
import com.example.springbootdemo.service.PeopleFaceService;

@Component
public class ScheduledTask_PeopleFace implements SchedulingConfigurer {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	private PeopleFaceService peopleFaceService;
	
	
	private static final String DEFAULT_CRON = "0/5 * * * * ?";
//	private static final String DEFAULT_CRON = "0 0 0 0 12 ? 2099-2099";
    private String cron = DEFAULT_CRON;
    private static final String  isstop = "1";
    private String stop_type = isstop;
    
	
    public void setStop_type(String stop_type) {
		this.stop_type = stop_type;
	}
    public void setCron(String cron) {
        System.out.println("当前cron="+this.cron+"->将被改变为："+cron);
        this.cron = cron;
    }
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
		taskRegistrar.addTriggerTask(() -> {
			if (stop_type.equals(isstop)) {
//				peopleFaceService.TranPeopleCOuntDate();
		        System.out.println("动态修改定时任务cron参数_peopleface，当前时间：" + dateFormat.format(new Date()));
			}
        // 定时任务的业务逻辑

    }, (triggerContext) -> {
        // 定时任务触发，可修改定时任务的执行周期
        CronTrigger trigger = new CronTrigger(cron);
        Date nextExecDate = trigger.nextExecutionTime(triggerContext);
        return nextExecDate;
    });
		
	}

}
