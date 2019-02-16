package com.pts.test.page;

import static com.pts.test.core.model.CommonConstants.TIMEOUT_PERIOD_LONG;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopMenu {
	
	WebDriver driver;
	
	public TopMenu() {
		
	}
	
	public TopMenu(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="nav-link-shopall") 
	private WebElement shopByCategoryBtn;
	
	@FindBy(xpath="//div[@id='nav-tools']//span[@class='nav-line-2' and contains(text(),'Prime')]")
	private WebElement primeNav;
	
	@FindBy(id="nav-cart")
	private WebElement cartIcon;
	
	public WebElement getShopByCategoryBtn(){
//        return waitForElement(shopByCategoryBtn, TIMEOUT_PERIOD_LONG);
        return shopByCategoryBtn;
    }
	
	public WebElement getPrimeNav() {
		return primeNav;
	}
	public WebElement getCartIcon(){
		return cartIcon;
//        return waitForElement(searchBtn, TIMEOUT_PERIOD_LONG);
    }

}
