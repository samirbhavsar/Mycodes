package com.taxiforsure.driver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.taxiforsure.utils.ReadPropertiesFile;
import org.testng.ITestResult;
import org.testng.Reporter;

public class MyDriver 
{

	Logger log = Logger.getLogger("MyDriver");


	/**
	 * This method will get the url if we pass it as an argument in cmd
	 * mvn test appurl="ururl"
	 */

	public String geturl()
	{
		System.out.println("url " + System.getProperty("appurl"));
		return System.getProperty("appurl");
	}

	WebDriver driver = null;
	public WebDriver getDriver() 
	{
		ReadPropertiesFile prop = new ReadPropertiesFile();
		String browser = prop.getValue("Config.properties", "browser");
		String runOnGrid = prop.getValue("Config.properties", "grid");
		String gridBrowser = prop.getValue("Config.properties", "gridbrowser");
		String remoteWebDriverURL = prop.getValue("Config.properties", "remoteWebDriverURL");
		
		DesiredCapabilities dc = new DesiredCapabilities();
		if(runOnGrid.equals("true") & gridBrowser.equals("firefox"))
		{
			dc.setBrowserName("firefox");
			dc = getDesiredCapabilities("firefox");
		}
		
		if(browser.equals("firefox"))
		{
			if(runOnGrid.equals("false"))
			{
				driver = new FirefoxDriver();
				System.out.println("Inside firefox");
			}
		}else if(browser.equals("ie"))
		{
			String relativePath = System.getProperty("user.dir");
			String ieDriverPath = relativePath + "\\lib\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			driver = new InternetExplorerDriver();
		}else if(browser.equals("chrome"))
		{
			String relativePath = System.getProperty("user.dir");
			String chromeDriverPath = relativePath + "\\lib\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		}else
		{
			System.out.println("Something is wrong with browser settings. The browser name set was " + browser + " .Exiting build execution");
			System.exit(1);
		}
		
		log.info("Starting browser");
		try
		{
			driver = new RemoteWebDriver(new URL(remoteWebDriverURL), dc);
			
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		
		String applicationurl = prop.getValue("Config.properties", "url");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get(applicationurl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}

	public void takeScreenShot(ITestResult result, WebDriver driver)
	{


		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.setCurrentTestResult(result);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		String destDir = "target/surefire-reports/screenshots";
		new File(destDir).mkdirs();
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			System.out.println("File path " + new File(destDir + "/" + destFile).getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(new File(destDir + "/" + destFile).getAbsolutePath());
		Reporter.log("<a href = file:///" + new File(destDir + "/" + destFile).getAbsolutePath() +" target='_blank'" +"> " +
				"<p align=\"left\", style= \"color: red; font: italic bold 12px/30px Georgia,serif;\" >Click here for screen shot </p> </a>");
		System.out.println("Taken screen shots");
	}
	
	private DesiredCapabilities getDesiredCapabilities(String browser)
	{
		DesiredCapabilities dc = new DesiredCapabilities();
		if(browser.equals("firefox"))
		{
			System.out.println("Inside grid");
			dc = DesiredCapabilities.firefox();
			dc.setBrowserName("firefox");
//			dc.setCapability("browser", "firefox");
			dc.setCapability("version", "32");
			dc.setCapability("platform", Platform.LINUX);
//			dc.setCapability("os_version", "7");
//			dc.setCapability("resolution", "1400x800"); 
//			dc.setCapability("browserstack.debug", "true"); 
			return dc;
		}
		return null;
		
		
	}
}
