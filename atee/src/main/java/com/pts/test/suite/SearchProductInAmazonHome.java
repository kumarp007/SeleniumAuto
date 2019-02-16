package com.pts.test.suite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pts.test.core.exception.AutomationException;
import com.pts.test.core.model.AmazonCentralDataObject;
import com.pts.test.fixtures.AmazonHomeFixture;
import com.pts.test.util.AmazonDataObjectFactory;
import com.pts.test.util.BaseClass;
import com.relevantcodes.extentreports.LogStatus;


public class SearchProductInAmazonHome extends BaseClass{
	private static final Logger logger = LogManager.getLogger(SearchProductInAmazonHome.class);
	
	private AmazonDataObjectFactory amazonDataObjectFactory=new AmazonDataObjectFactory();
	private AmazonCentralDataObject amazonDataObject=amazonDataObjectFactory.getDataObject();
	
	
	@Test(retryAnalyzer = com.pts.test.util.ReExecuteFailedTest.class)
	public void searchProduct() throws AutomationException {
		logger.info("Search Product In Amazon Home page");
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		try {
			amazonDataObject=mapper.readerForUpdating(amazonDataObject).readValue(new FileInputStream(".\\src\\main\\resources\\testdata\\Product.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("Product Name -> "+amazonDataObject.productName);
		
		AmazonHomeFixture ah=new AmazonHomeFixture();
		ah.searchProduct("Computer UPC");
		extentTest.log(LogStatus.PASS, "User able view all product in search result page");
		ah.verifyProductSearchReasult();
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
		extentReport.endTest(extentTest);
		extentReport.flush();
	}

}  
