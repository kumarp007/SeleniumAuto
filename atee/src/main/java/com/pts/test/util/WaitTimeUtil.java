package com.pts.test.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.log4j.*;

import org.openqa.selenium.*;

public class WaitTimeUtil {
	final static Logger logger = Logger.getLogger(WaitTimeUtil.class);
	final static int TIMEOUT_IN_SECONDS=120;
	final static int POLL_INTERVAL_IN_SECONDS=2;
	
	public static int getDynamicWaitTime() {
//		int waitTime=Integer.parseInt(s);
		return 1;
	}

	/*public static WebElement waitForElement(WebElement element, long timeout) {
		WebElement myElement = null;
		try {
			myElement = new WebDriverWait(BaseClass.getDriver(), timeout)
					.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException toe) {

		} finally {
			logger.info("Unable to find the WebElement in the webpage by using its locator: " + element
					+ " within the timeout: " + timeout);
		}

		return myElement;
	}*/

}
