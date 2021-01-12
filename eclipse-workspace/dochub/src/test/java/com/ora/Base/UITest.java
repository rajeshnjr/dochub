package com.ora.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.log4j.Logger;

public class UITest {
	protected Logger logger = Logger.getLogger(this.getClass());
	WebDriver driver;
	
	public String getGeckoDriverProperty() {
		return "webdriver.gecko.driver";
	}
	
	public String getGeckoDriver() {
		return System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe";
	}
	
	public void SetGeckoDriverProperties() {
		logger.info("Setting UP Gecko driver properties");
		System.setProperty(getGeckoDriverProperty(),
				getGeckoDriver());
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
	}
	
	public void SetChromeDriverProperties() {
		logger.info("Setting UP Chrome driver properties");
	}
	public WebDriver SetDriverProperties(String sBrowser,String sURL) {
		if(sBrowser.equals("firefox")) {
			SetGeckoDriverProperties();
			driver = new FirefoxDriver();
		}
		if(sBrowser.equals("chrome")) {
			SetChromeDriverProperties();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.get(sURL);
		return driver;
	}
	
	public WebDriver CreateDriver(String sBrowser,String sURL) {
		return SetDriverProperties(sBrowser,sURL);
	}
}
