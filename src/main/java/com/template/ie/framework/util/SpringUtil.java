package com.template.ie.framework.util;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil {
	
	private static ApplicationContext applicationContext = null;
	
	public static void setApplicationContext(ApplicationContext arg0) {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = arg0;
		}
	}
	
	 // 获取applicationContext
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	// 通过name获取 Bean.
	public static Object getBean(String name) {
		 return getApplicationContext().getBean(name);
	}
	
	// 通过class获取Bean.
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}
	
	// 通过name,以及Clazz返回指定的Bean
	 public static <T> T getBean(String name, Class<T> clazz) {
		 return getApplicationContext().getBean(name, clazz);
	 }
}