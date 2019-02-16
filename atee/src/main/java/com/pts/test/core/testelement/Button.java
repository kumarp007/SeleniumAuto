package com.pts.test.core.testelement;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.pts.test.core.exception.AutomationException;
import com.pts.test.util.WaitTimeUtil;

public class Button extends UIElement {

	final static Logger logger = Logger.getLogger(Button.class);

	public void sendKeyboardEntry(WebElement element) {
		if (element != null) {
			element.sendKeys(Keys.ENTER);
			logger.info("send Keyboard Entry on Element");
		}
	}
	
	public static boolean buttonClick(WebElement element) throws AutomationException {
		boolean flag=false;
		if (element != null) {
			element.click();
			flag=true;
		}
		return flag;
	}
}
