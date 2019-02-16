package com.pts.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonPrimePage {
	
	WebDriver driver;
	
	public AmazonPrimePage() {
		
	}
	
	public AmazonPrimePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="a-autoid-0-announce")
	private WebElement loginToJoinPrimeBtn;
	
	
	public WebElement getAmazonPrimeBtn(){
		return loginToJoinPrimeBtn;
    }

}
