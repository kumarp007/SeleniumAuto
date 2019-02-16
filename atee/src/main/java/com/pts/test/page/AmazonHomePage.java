package com.pts.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.pts.test.core.model.CommonConstants.*;
import static com.pts.test.util.WaitTimeUtil.*;

import java.util.List;


public class AmazonHomePage {
	WebDriver driver;
	
	public AmazonHomePage() {
	
	}
	
	public AmazonHomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(css=".nav-logo-link")
	private WebElement amazonTopLogoImg;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	private WebElement searchTextbox;
	
	@FindBy(xpath="//input[@type='submit' and @value='Go']")
	private WebElement searchBtn;
	
	@FindBy(css="#s-result-count")
	private WebElement searchResultHeader;
	
	@FindBy(css=".a-size-medium.s-inline.s-access-title.a-text-normal")
	private List<WebElement> searchResultLabel;
	
	
	
	
	public WebElement getAmazonTopLogoImg(){
//        return waitForElement(amazonTopLogoImg, TIMEOUT_PERIOD_LONG);
		return amazonTopLogoImg;
    }
	
	public WebElement getSearchTextbox(){
		return searchTextbox;
//        return waitForElement(searchTextbox, TIMEOUT_PERIOD_LONG);
    }
	
	public WebElement getsearchBtn(){
		return searchBtn;
//        return waitForElement(searchBtn, TIMEOUT_PERIOD_LONG);
    }
	
	public WebElement getSearchResultHeader(){
		return searchResultHeader;
//        return waitForElement(searchBtn, TIMEOUT_PERIOD_LONG);
    }
	
	public List<WebElement> getSearchResultLabel(){
		return searchResultLabel;
//        return waitForElement(searchBtn, TIMEOUT_PERIOD_LONG);
    }
	

}
