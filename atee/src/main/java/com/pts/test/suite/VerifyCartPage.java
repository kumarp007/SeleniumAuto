package com.pts.test.suite;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.pts.test.core.exception.AutomationException;
import com.pts.test.fixtures.AmazonHomeFixture;
import com.pts.test.fixtures.AmazonMenuNavigationFixture;
import com.pts.test.util.BaseClass;
import com.relevantcodes.extentreports.LogStatus;


public class VerifyCartPage extends BaseClass{
	
	private static final Logger logger = LogManager.getLogger(VerifyCartPage.class);
	
	@Test(retryAnalyzer = com.pts.test.util.ReExecuteFailedTest.class)
	public void searchProduct() throws AutomationException {
		logger.info("Search Product In Amazon Home page");
		AmazonHomeFixture ah=new AmazonHomeFixture();
		AmazonMenuNavigationFixture mn= new AmazonMenuNavigationFixture();
		mn.verifyCartIcon();
		mn.navigateToCart();
		extentTest.log(LogStatus.PASS, "User able to navigate to Cart page");
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
		extentReport.endTest(extentTest);
		extentReport.flush();
	}

}  
