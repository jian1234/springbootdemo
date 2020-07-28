/*
 * ==========================================================================================
 * $Id: ToolKit.java,v 1.4 2013/07/29 02:33:17 wwbwin7 Exp $ 创建: [2004-9-20 16:12:41] by CALM
 * ==========================================================================================
 * 
 * 项目: com.idt.EGDT.FileAdapter
 * 
 * ==========================================================================================
 * Copyright &copy; 2002-2005 IDT Software Corporation. All rights reserved.
 * ==========================================================================================
 */

package com.example.springbootdemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ShowTimeUtil
{
  
    public static void ShowTime(String str){
    	TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));//定义时区，可以避免虚拟机时间与系统时间不一致的问题  
    	SimpleDateFormat matter = new SimpleDateFormat("yyyy年MM月dd日E HH时mm分ss秒");  
        System.out.println(matter.format(new Date())+"------"+str);
    }
}

