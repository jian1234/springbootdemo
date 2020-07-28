package com.example.springbootdemo.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.bytebuddy.asm.Advice.This;

public class TimeUtil {
	 /**
	 2      * 获取指定时间到格林威治时间的秒数
	 3      * UTC：格林威治时间1970年01月01日00时00分00秒（UTC+8北京时间1970年01月01日08时00分00秒）
	 4      * @param time
	 5      * @return
	 6      */
	      public static void diffSeconds(String time){
	    	  final int msInMin = 60000;  
	          final int minInHr = 60;  
	          Date date = new Date();  
	          int Hours, Minutes;  
	          DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,  
	                  DateFormat.LONG);  
	          TimeZone zone = dateFormat.getTimeZone();  
	          System.out.println("IST Time: " + dateFormat.format(date));  
	          Minutes = zone.getOffset(date.getTime()) / msInMin;  
	          Hours = Minutes / minInHr;  
	          System.out.println("GMT Time" + (Hours >= 0 ? "+" : "") + Hours + ":"  
	                  + Minutes);  
	          zone = zone.getTimeZone("GMT Time" + (Hours >= 0 ? "+" : "") + Hours  
	                  + ":" + Minutes);  
	          dateFormat.setTimeZone(zone);  
	          System.out.println("GMT: " + dateFormat.format(date));  
	     }
	      public static void main(String[] args) {
			TimeUtil time = new TimeUtil();
//			time.diffSeconds("");
			System.out.println(System.currentTimeMillis()/1000+"");
			System.out.println(new Date().getTime()/1000);
		}
}
