package com.example.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.springbootdemo.Scheduled.ScheduledTask_Linger;
import com.example.springbootdemo.Scheduled.ScheduledTask_PeopleCount;
import com.example.springbootdemo.Scheduled.ScheduledTask_Remnant;
import com.example.springbootdemo.dao.PublicDao;
import com.example.springbootdemo.util.ServiceLocator;


@SpringBootApplication
@MapperScan("com.example.springbootdemo.dao")   //配置扫描路径
@EnableScheduling
public class SpringbootdemoApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
		
			
		}
	
	
	
}
