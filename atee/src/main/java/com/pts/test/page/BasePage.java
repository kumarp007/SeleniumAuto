package com.pts.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	WebDriver driver;
	
	@FindBy(xpath="//img[@alt='Me']")
	private WebElement profileIcone;
	
	@FindBy(css=".gridicon.gridicons-create >g>path")
	private WebElement createANewPost;
	
	@FindBy(xpath="//span[text()='My Site']")
	private WebElement mySite;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyLogin()
	{
		return profileIcone.isDisplayed();
	}
	
		
}
