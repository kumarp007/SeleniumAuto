package com.pts.test.util;

import org.openqa.selenium.WebDriver;

import com.pts.test.fixtures.AmazonHomeFixture;
import com.pts.test.page.AmazonHomePage;
import com.pts.test.page.AmazonPrimePage;
import com.pts.test.page.BasePage;
import com.pts.test.page.TopMenu;



public class GlobalFactoryObjectModel {

	private static GlobalFactoryObjectModel globalFactoryObjectModel;
	private WebDriver driver;
	private AmazonHomeFixture amazonHome;

	/* Web Page Object */
	private AmazonHomePage amazonHomePage;
	private BasePage basePageObject;
	private TopMenu topMenu;
	private AmazonPrimePage amazonPrimePage;



	/*-------------initialize driver for Page----------------*/

	public AmazonHomePage getAmazonHomePage() {
		if (null == amazonHomePage) {
			return amazonHomePage = new AmazonHomePage(driver);
		}
		return amazonHomePage;
	}

	
	public BasePage getBasePage() {
		if (null == basePageObject) {
			return basePageObject = new BasePage(driver);
		}
		return basePageObject;
	}

	public TopMenu getTopMenu() {
		if (null == topMenu) {
			return topMenu = new TopMenu(driver);
		}
		return topMenu;
	}
	
	public AmazonPrimePage getAmazonPrimePage() {
		if (null == amazonPrimePage) {
			return amazonPrimePage = new AmazonPrimePage(driver);
		}
		return amazonPrimePage;
	}

	/*--------------------------------------------------------------*/

	public AmazonHomeFixture getAmazonHomeFixture() {
		if (null == amazonHome) {
			return amazonHome = new AmazonHomeFixture(driver);
		}
		return amazonHome;
	}
	
	
	public GlobalFactoryObjectModel() {
		if (globalFactoryObjectModel == null)
			synchronized (GlobalFactoryObjectModel.class) {
				if (globalFactoryObjectModel == null) {
					globalFactoryObjectModel = new GlobalFactoryObjectModel();
				}
			}
	}

	public GlobalFactoryObjectModel(WebDriver driver) {
		this.driver = driver;
	}

	public GlobalFactoryObjectModel(WebDriver driver, GlobalFactoryObjectModel globalFactoryObjectModel) {
		this.driver = driver;
		this.globalFactoryObjectModel = globalFactoryObjectModel;
	}

	

}
