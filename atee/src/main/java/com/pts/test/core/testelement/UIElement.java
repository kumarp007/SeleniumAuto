package com.pts.test.core.testelement;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.openqa.selenium.JavascriptExecutor;
import com.pts.test.core.exception.AutomationException;
import com.pts.test.util.BaseClass;

public class UIElement {
	final static Logger logger = Logger.getLogger(UIElement.class);

	
	
	public static void isEnabled(WebElement element) throws AutomationException{
		if(element.isEnabled()) {
			Assert.assertTrue(element.isEnabled());
		} else {
			Assert.assertTrue(element.isEnabled(),"Element is disabled");
		}
	}
	
	public static void isDisabled(WebElement element) throws AutomationException {
		if(!element.isEnabled()) {
			Assert.assertTrue(element.isEnabled());
		} else {
			Assert.assertFalse(element.isEnabled(),"Element is enabled");
		}
	}
	
	public static void click(WebElement element) {
		element.click();
	}
	
	/*public static void fireEventElement(String sEventAction) throws AutomationException {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) WebDriverSelector.getDriver();
			jse.executeScript("if (document.createEvent){var vMouseEventElement=document.createEvent('MouseEvent');vMouseEventElement.initEvent('" +sEventAction +"',true,false);arguments[0].dispatchEvent(vMouseEventElement);}" else if(document.createEventObject) {arguments[0].fireEvent('"+sEventAction +"');}",webElement);
		}catch(Exception e) {
			
		}
	}*/
}
