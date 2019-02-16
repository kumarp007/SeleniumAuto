package com.pts.test.core.model;

public class AmazonCentralDataObject {
	public String productName;
	
	public AmazonCentralDataObject(){
		
	}
	
	
	public AmazonCentralDataObject(String productName){
		super();
		this.productName=productName;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
