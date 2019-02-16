package com.pts.test.util;

import com.pts.test.core.model.AmazonCentralDataObject;

public class AmazonDataObjectFactory {
	private AmazonCentralDataObject dataObject;
	
	public AmazonDataObjectFactory(){
		initialize();
	}
	
	private void initialize(){
		if(dataObject==null){
			synchronized(AmazonDataObjectFactory.class){
				if(dataObject==null){
					dataObject=new AmazonCentralDataObject();
				}
			}
			
		}
	}
	
	public AmazonCentralDataObject getDataObject(){
		return dataObject;
	}
	
	public void destroyDataObject(){
		dataObject=null;
	}
}
