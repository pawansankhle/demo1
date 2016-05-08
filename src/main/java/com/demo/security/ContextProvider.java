package com.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextProvider implements ApplicationContextAware {

	private static ApplicationContext appContext;
	
	private Logger logger = LoggerFactory.getLogger(ContextProvider.class);
	@Override
	public void setApplicationContext(ApplicationContext context){
		try{
			logger.info("@class ContextProvider @method setApplicationContext entry...");
			appContext = context;
		}catch(Exception ex){
			 ex.printStackTrace();
		}
		
	}
	
	
	public static ApplicationContext getContext()
	{
		return appContext;
	}

}
