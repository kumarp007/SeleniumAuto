package com.pts.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import static com.pts.test.core.model.CommonConstants.*;
import com.pts.test.core.exception.AutomationException;


public class TestConfig {

	final static Logger logger = Logger.getLogger(TestConfig.class);

	private static Properties properties = new Properties();
	private static Properties elementConfig;

	static {
		try {
			File file = null;
			FileInputStream in = null;
			ClassLoader classLoader = TestConfig.class.getClassLoader();
			URL url = classLoader.getResource("application.properties");

			if (url != null) {
				file = new File(url.getFile());
				if (file != null && file.exists()) {
					in = new FileInputStream(file);
					properties.load(in);
					in.close();
				}
			}

			/*url = classLoader.getResource("atee.properties");

			if (url != null) {
				file = new File(url.getFile());
				if (file != null && file.exists()) {
					in = new FileInputStream(file);
					properties.load(in);
					in.close();
				}
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		String value = null;
		if (System.getProperty(key) != null) {
			value = System.getProperty(key);
		} else {
			value = properties.getProperty(key).trim();
		}

		return value;
	}

	/*public static String getElementConfig(String key) {
		String value = null;
		if (elementConfig == null) {
			// initializeElementConfig();
		}
		value = elementConfig.getProperty(key);
		return value;
	}*/

	/*public static void initializeElementConfig() throws AutomationException {
		if(elementConfig == null) {
			elementConfig=new Properties();
			
			try {
				ClassLoader classLoader = TestConfig.class.getClassLoader();
				File file = new File(classLoader.getResource("elementConfig.properties").getFile());
				
				FileInputStream in = new FileInputStream(file);
				elementConfig.load(in);
				
				TestElementType testElementType = null;
				for(String propertyName : elementConfig.stringPropertyNames()) {
				try {
					testElementType = TestElementType.
					
				} catch(IllegalArgumentException e) {
				logger.debug(message); 
				}
				
				}
				} catch(Exception e) {
					throw new AutomationException("Error occure while loading element configuration");
				}
			
			
			
			} 
		}*/
	

	public static String readProperties(String filepath,String key)
	{
		String value=null;
		File f=new File(filepath);
		FileInputStream fis;
		
		try
		{
			fis=new FileInputStream(f);
			Properties prop=new Properties();
			prop.load(fis);
			value=prop.getProperty(key);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return value;
	}
	
	public static String getAppConfigProperty(String key) {
		return readProperties(APPLICATION_CONFIG_PATH, key); 
	}
	
	}


