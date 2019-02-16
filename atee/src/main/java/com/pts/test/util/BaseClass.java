package com.pts.test.util;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.OutputType;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.pts.test.core.model.CommonConstants;
import com.pts.test.core.model.Test1;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass extends WebDriverSelector{
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public static String result;
	final static Logger logger = Logger.getLogger(BaseClass.class.getName());
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
	
	@BeforeClass
	public void setup() {
		logger.info("BASE CLASS BEFORE_CLASS");
		String className = this.getClass().getSimpleName();
		String browserName = TestConfig.getAppConfigProperty("browser");
		String url = TestConfig.getAppConfigProperty("url");
		extentReport.addSystemInfo("Browser", browserName);
		extentTest = extentReport.startTest(className);
		extentTest.assignCategory("Support Portal Regression Test on Browser - " + browserName);
	}

	@BeforeTest
	public void loadLog4j() {
		String log4jConfigPath = System.getProperty("user.dir") + "/log4j.properties";
		PropertyConfigurator.configure(log4jConfigPath);
	}

	@BeforeSuite
	public void preSuit() {
		logger.info("BASE CLASS BEFORE_SUITE");
		extentReport = ExtentReportManager.Instance();
	}

	@AfterSuite
	public void postSuit() {
		logger.info("BASE CLASS AFTER_SUITE");
		extentReport.close();
		logger.info("Extent report Closed");
		String reportFlag = TestConfig.getAppConfigProperty("openReportAfterExecution");
		// String user =System.getProperty("user.name");
		// String user ="hnaikx";

		// Changes made for 36 server
		File jenkinsPath = new File(
				"E:/Automation/Jenkins_Home/.jenkins/jobs/SalesForce_Regression/workspace/Extent_Report/");

		File ReportPath = new File(
				"E:/Automation/Jenkins_Home/.jenkins/jobs/SalesForce_Regression/workspace/Extent_Report/Automation_Report_SalesForce.html");

		if (ReportPath.exists()) {
			ReportPath.delete();
		}
		try {
			FileUtils.copyFileToDirectory(ExtentReportManager.pjtPath, jenkinsPath);

			File src = FileUtils.getFile(jenkinsPath + "\\Automation_Report_" + ExtentReportManager.strDate + ".html");
			File des = FileUtils.getFile(jenkinsPath + "\\Automation_Report_SalesForce.html");
			logger.info(src);
			logger.info(des);
			FileUtils.moveFile(src, des);
			logger.info("Move report file success");
		} catch (Throwable e) {
			logger.info(e);
		}
		if (CommonConstants.Y.toUpperCase().equalsIgnoreCase(reportFlag)) {
			openReportAfterExecution();
		}
		
	}

	@BeforeMethod
	public void getWebDriver() {
		String isRemortEnabled = TestConfig.getAppConfigProperty("isRemoteEnabled");
		String isSauceEnabled = TestConfig.getAppConfigProperty("isSauceEnabled");
		String browserName = TestConfig.getAppConfigProperty("browser");
		String url = TestConfig.getAppConfigProperty("url");
		String envName = TestConfig.getAppConfigProperty("environmentName");
		
		extentTest.log(LogStatus.INFO, "Environment Name :"+envName +", Test URL :"+url);
		
		if (CommonConstants.Y.toUpperCase().equalsIgnoreCase(isRemortEnabled)) {

		} else if (CommonConstants.Y.toUpperCase().equalsIgnoreCase(isSauceEnabled)) {

		} else {
			System.out.println("qATEST :"+Test1.ONE);
			getLocalWebDriver();
		}
	}

	@AfterMethod
	public void postCondition(ITestResult result) {
		
		logger.info("BASE CLASS AFTER_METHOD");
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.info(result.getMethod().getMethodName() + " -- TEST SCENARIO FAILED");
			// String screenshot_path=Utility.captureScreenshot(driver, result.getName());

			String base64img = "";
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				String scnShot = ts.getScreenshotAs(OutputType.BASE64);
				base64img = "data:image/jpg;base64, " + scnShot;
				logger.info("Screenshot taken");
			} catch (Throwable e) {

				logger.info("Exception while taking screenshot " + e.getMessage());
			}

			String image = extentTest.addBase64ScreenShot(base64img);
			logger.info("Screenshot attached");
			extentTest.log(LogStatus.FAIL, result.getMethod().getMethodName() + " -- TEST FAILED", image);
			extentTest.log(LogStatus.FAIL, result.getMethod().getMethodName() + " -- TEST SCENARIO FAILED");

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.info(result.getMethod().getMethodName() + " -- TEST SCENARIO PASSED");
			extentTest.log(LogStatus.PASS, result.getMethod().getMethodName() + " -- TEST SCENARIO PASSED");
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.info(result.getMethod().getMethodName() + " -- TEST SCENARIO SKIPPED");

			String base64img = "";
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				String scnShot = ts.getScreenshotAs(OutputType.BASE64);
				base64img = "data:image/jpg;base64, " + scnShot;
				logger.info("Screenshot taken");
			} catch (Throwable e) {

				logger.info("Exception while taking screenshot " + e.getMessage());
			}

			String image = extentTest.addBase64ScreenShot(base64img);
			logger.info("Screenshot attached");
			extentTest.log(LogStatus.INFO, result.getMethod().getMethodName() + " -- TEST SKIPPED", image);
			extentTest.log(LogStatus.INFO, result.getMethod().getMethodName() + " -- TEST SCENARIO SKIPPED");
		}
		
		
		//My old code
		/*if (ITestResult.FAILURE == result.getStatus()) {
			CommonLib.captureScreenshot(driver, result.getMethod().getMethodName());
		}
		if (driver != null) {
			driver.quit();
		}*/
	}

	public WebDriver getDriver() {
		return driver;
	}

	public static String getDriverSessionId() {
		if (driver != null && driver instanceof RemoteWebDriver) {
			return ((RemoteWebDriver) driver).getSessionId().toString();
		}
		return null;
	}
	
	private void openReportAfterExecution() {
		// open report in browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String url = ExtentReportManager.Path;
		driver.get(url);
	}
}
