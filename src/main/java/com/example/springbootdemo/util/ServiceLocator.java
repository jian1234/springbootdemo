package com.example.springbootdemo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Bean定位类
 * @author freechan
 *
 */
@Component
public class ServiceLocator implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext = null;  
	
	@Override
	public void setApplicationContext(org.springframework.context.ApplicationContext context)
			throws BeansException {
		 if(applicationContext == null){  
			 applicationContext  = context;  
	     }
	}
	
	/**
	 * 获取applicationContext  
	 * @return ApplicationContext
	 */
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }
    
	/**
	 * 通过name获取 Bean
	 * @param name String bean名称
	 * @return Object
	 */
    public static Object getBean(String name){  
        return getApplicationContext().getBean(name);  
  
    }  
  
    /**
     * 通过class获取Bean  
     * @param clazz Class Bean类型
     * @return T
     */
    public static <T> T getBean(Class<T> clazz){  
        return getApplicationContext().getBean(clazz);  
    }
}
