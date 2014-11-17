package com.taxiforsure.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class ExecuteInGrid 
{


/**
 * This creates a single thread of driver. No other thread running the grid can access this driver and this is better than
 * static webdriver instance.	
 */
	
private static ThreadLocal<RemoteWebDriver> driver = null;
public static WebDriver currentDriver;
	

	
	/** 
	 * Pass the browse parameter in the xml file and based on the parameter a particular browser will be invoked
	 * @param browser
	 * @throws MalformedURLException
	 */

	@BeforeMethod
	@Parameters("browser") 
	
	public void setUp(@Optional ("firefox") String browser) throws MalformedURLException
	{
		System.out.println(browser);
		driver=new ThreadLocal<RemoteWebDriver>();
		DesiredCapabilities dc = new DesiredCapabilities();
		if(browser.equalsIgnoreCase("ie"))
		{
		dc.setBrowserName("internet explorer");
		dc.setPlatform(Platform.WINDOWS);
		}else if(browser.equalsIgnoreCase("firefox"))
		{

		dc.setBrowserName("firefox");
		dc.setPlatform(Platform.ANY);
		dc.setCapability(FirefoxDriver.PROFILE, MyDriver.getFirefoxProfile());
		}else if(browser.equalsIgnoreCase("chrome"))
		{
			dc.setBrowserName("chrome");
			dc.setPlatform(Platform.ANY);
			dc.setCapability(ChromeOptions.CAPABILITY, MyDriver.getChromeProfile());
		}
		currentDriver = driver.get();
		
        if(currentDriver==null)
        {
        	try
        	{
        		
        		driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc));
        		System.out.println("Setting driver");
        		driver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        		getDriver().get("url");
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        		currentDriver.quit();
        	}
        	
        }	
	}
	
	public WebDriver getDriver()
	{
		return driver.get();
	}
	
	@AfterMethod
	public void cleaUp()
	{
		System.out.println("Closing driver");
		currentDriver = driver.get();
		if (currentDriver != null) {
			currentDriver.quit();
			driver.set(null);
		}
	}
	

	
	

}
