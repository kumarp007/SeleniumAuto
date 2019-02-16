package com.pts.test.fixtures;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.pts.test.core.exception.AutomationException;
import com.pts.test.page.AmazonHomePage;
import com.pts.test.page.AmazonPrimePage;
import com.pts.test.page.TopMenu;
import com.pts.test.util.BaseClass;
import com.pts.test.util.GlobalFactoryObjectModel;

import static com.pts.test.core.testelement.Button.*;
import static com.pts.test.core.testelement.Textbox.*;
import static com.pts.test.core.testelement.UIElement.click;


public class AmazonMenuNavigationFixture {
	
	final static Logger logger = Logger.getLogger(AmazonMenuNavigationFixture.class);
	
	private BaseClass driverFactory=new BaseClass();
	private WebDriver driver=driverFactory.getDriver();
	
	GlobalFactoryObjectModel globalFactoryObjectModel=new GlobalFactoryObjectModel(driver);
	
	private TopMenu topMenu=globalFactoryObjectModel.getTopMenu();
	private AmazonPrimePage amazonPrime = globalFactoryObjectModel.getAmazonPrimePage();
	
	public AmazonMenuNavigationFixture(){
		
	}
	
	public AmazonMenuNavigationFixture(WebDriver driver) {
		
	}
	
	public AmazonMenuNavigationFixture(GlobalFactoryObjectModel globalFactoryObjectModel) {
		this.globalFactoryObjectModel=globalFactoryObjectModel;
	}
	
	
	public void navigateToCart() throws AutomationException {
		click(topMenu.getCartIcon()); 
		logger.info("Click on topMenu.getCartIcon");
	}
	
	public void navigateToPrimePage() {
		click(topMenu.getPrimeNav());
		logger.info("Click on topMenu.getPrimeNav");
	}
	
	public void verifyCartIcon() {
		Assert.assertTrue(topMenu.getCartIcon().isDisplayed());
	}
	
	public void verifyAmazonPrimePage() {
		Assert.assertTrue(driver.getTitle().contains("Amazon Prime"));
	}
	
}
