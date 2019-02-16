package com.pts.test.fixtures;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.pts.test.core.exception.AutomationException;
import com.pts.test.page.AmazonHomePage;
import com.pts.test.util.BaseClass;
import com.pts.test.util.GlobalFactoryObjectModel;

import static com.pts.test.core.testelement.Button.*;
import static com.pts.test.core.testelement.Textbox.*;


public class AmazonHomeFixture {
	
	final static Logger logger = Logger.getLogger(AmazonHomeFixture.class);
	
	private BaseClass driverFactory=new BaseClass();
	private WebDriver driver=driverFactory.getDriver();
	
	GlobalFactoryObjectModel globalFactoryObjectModel=new GlobalFactoryObjectModel(driver);
	
	private AmazonHomePage amazonHomePage=globalFactoryObjectModel.getAmazonHomePage();

	
	public AmazonHomeFixture(){
		
	}
	
	public AmazonHomeFixture(WebDriver driver) {
		
	}
	
	public AmazonHomeFixture(GlobalFactoryObjectModel globalFactoryObjectModel) {
		this.globalFactoryObjectModel=globalFactoryObjectModel;
	}
	

	public void enterProductName(String val) throws AutomationException {
		setText(amazonHomePage.getSearchTextbox(),val);
	}
	
	public void searchProduct(String productName) throws AutomationException {
		enterProductName(productName);
		logger.info("productName :"+productName);
		click(amazonHomePage.getsearchBtn()); 
			logger.info("Click on amazonHomePage.searchBtn");

	}
	
	public void printSerchProductResult() {
		for(WebElement o:amazonHomePage.getSearchResultLabel()) {
			System.out.println("Label :"+o.getText());
		}
	}
	
	public void verifyProductSearchReasult() {
		Assert.assertTrue(amazonHomePage.getSearchResultHeader().getText().trim().contains("results for"));
		printSerchProductResult();
	}
	

	

}
