package com.pts.test.core.exception;

import org.apache.log4j.*;

public class AutomationException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = Logger.getLogger(AutomationException.class);
	
	Object objError;
	
	public AutomationException() {
		super();
	}
	
	public AutomationException(String msessage) {
		super(msessage);
	}
	
	public AutomationException(Object objError) {
		super();
		this.objError = objError;
		logger.info(objError);
	}
	
	public AutomationException(String message, Object objError) {
		super(message);
		this.objError = objError;
		logger.info(message);
		if(objError!=null) {
			logger.info(objError);
		}		
	}
	
	public AutomationException(String message, Exception exception) {
		super(message, exception);
		this.objError = objError;
		logger.info(message);
		logger.info(exception.getMessage(), exception);
	}
}
