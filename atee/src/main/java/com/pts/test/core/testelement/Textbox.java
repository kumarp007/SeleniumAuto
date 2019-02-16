package com.pts.test.core.testelement;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.pts.test.core.exception.AutomationException;

public class Textbox {
	
final static Logger logger = Logger.getLogger(Textbox.class);
	
	public static void setText(WebElement element, String val) throws AutomationException {
        if ((element != null)&&(val!=null)) {
            element.sendKeys(val);
        }
    }
	
}
