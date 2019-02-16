package com.pts.test.util;

import static com.pts.test.core.model.Capability.CHROME_BROWSER;
import static com.pts.test.core.model.Capability.FIREFOX_BROWSER;
import static com.pts.test.core.model.Capability.IE_DRIVER;
import static com.pts.test.core.model.CommonConstants.DRIVER_PATH;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.LogStatus;

public class WebDriverSelector {
	final static Logger logger = Logger.getLogger(WebDriverSelector.class.getName());
	public static WebDriver driver;

	protected static WebDriver getLocalWebDriver() {
		System.out.println("SYS Path :" + System.getProperty("user.dir"));
		String downloadPath = "c:\\";
		String browserName = TestConfig.getAppConfigProperty("browser");
		String url = TestConfig.getAppConfigProperty("url");
		logger.info("Browser selected : " + browserName);
		/*if (null != browserName) {
			if (browserName.contains(" ")) {
				browserName = browserName.split(" ")[0];
			}
		}*/

		String processName = null;
		/*
		 * switch (browserName.toUpperCase()) { case FIREFOX_BROWSER: processName =
		 * "geckodriver.exe"; break; case CHROME_BROWSER: processName =
		 * "chromedriver.exe"; break; case IE_DRIVER: processName =
		 * "IEDriverServer.exe"; break; default: logger.error("Invalid Process Name"); }
		 */

//		processName = "chromedriver.exe";

		/*try {
			Utilities.cleanDrivers(processName);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		if (browserName.equalsIgnoreCase("FF") || browserName.equalsIgnoreCase("FIREFOX")) {

			System.setProperty("webdriver.gecko.driver", DRIVER_PATH + "geckodriver.exe");
			FirefoxProfile ffprofile = new FirefoxProfile();
			ffprofile.setAcceptUntrustedCertificates(true);
			ffprofile.setAssumeUntrustedCertificateIssuer(false);
			ffprofile.setPreference("accessbility.force_disabled", 1);
			ffprofile.setPreference("browser.download.folderList", 2);
			ffprofile.setPreference("browser.download.dir", downloadPath);

			FirefoxOptions ffo = new FirefoxOptions();
			ffo.setProfile(ffprofile);
			
//			driver = new FirefoxDriver(ffo);
			driver = new FirefoxDriver();
			logger.info("Browser Started >>>>>>> ");
		} else if (browserName.equalsIgnoreCase("IE") || browserName.equalsIgnoreCase("INTRENETEXPLORE")) {
			System.setProperty("webdriver.ie.driver", DRIVER_PATH + "IEDriverServer.exe");

			DesiredCapabilities dcap = DesiredCapabilities.internetExplorer();
			dcap.setCapability("ignoreProtectedModeSettings", true);
			dcap.setCapability("ignoreZoomSettings", true);

			InternetExplorerOptions ieo = new InternetExplorerOptions(dcap);
			ieo.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieo.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
			ieo.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			ieo.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			ieo.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ieo.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			ieo.setCapability(CapabilityType.VERSION, "7.0");
			driver = new InternetExplorerDriver(ieo);

		} else if (browserName.equalsIgnoreCase("GC") || browserName.equalsIgnoreCase("CHROME")
				|| browserName.equalsIgnoreCase("GOOGLECHROME")) {
			System.setProperty("webdriver.chrome.driver", DRIVER_PATH + "chromedriver.exe");

			String downloadFilepath = "C:\\downloadFile\\";
			/*HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);*/
//			ChromeOptions options = new ChromeOptions();
//			options.setExperimentalOption("prefs", chromePrefs);
//			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

//			driver = new ChromeDriver(options);
			driver = new ChromeDriver();
			logger.info("Browser Started >>>>>>> ");
		}

		logger.info("Browser selection done");

		driver.manage().deleteAllCookies();
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			logger.error("Error occured during window maximize - " + e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		EventFiringWebDriver eventHandler = new EventFiringWebDriver(driver);
		WebEventListener eCapture = new WebEventListener();
		// Registering with EventFiringWebDriver
		// Register method allows to register our implementation of
		// WebDriverEventListner to listen to the WebDriver events
		eventHandler.register(eCapture);

		driver.get(url);

		return driver;
	}

}
