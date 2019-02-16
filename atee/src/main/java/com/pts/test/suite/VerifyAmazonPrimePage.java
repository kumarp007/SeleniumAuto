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


public class VerifyAmazonPrimePage extends BaseClass{
	
	private static final Logger logger = LogManager.getLogger(VerifyAmazonPrimePage.class);
	
	@Test(retryAnalyzer = com.pts.test.util.ReExecuteFailedTest.class)
	public void verifyAmazonPrime() throws AutomationException, InterruptedException {
		logger.info("Verify Amazon Prime");
		AmazonMenuNavigationFixture mn=new AmazonMenuNavigationFixture();
		mn.navigateToPrimePage();
		Thread.sleep(5000);
		System.out.println("Browser Title :"+driver.getTitle());
		mn.verifyAmazonPrimePage();
		extentTest.log(LogStatus.PASS, "User able to navigate to AmazonPrimePage");
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
		extentReport.endTest(extentTest);
		extentReport.flush();
	}

}  
