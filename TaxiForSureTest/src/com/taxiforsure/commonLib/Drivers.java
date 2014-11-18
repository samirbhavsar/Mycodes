package com.taxiforsure.commonLib;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.taxiforsure.utility.ReadPropertiesFile;



public class Drivers {
	

	public static WebDriver driver = null;
	//static FirefoxBinary binary = new FirefoxBinary(new File("/home/tfs-santhosh/firefox/firefox"));
    static FirefoxProfile profile = new FirefoxProfile();
    
  // static FirefoxProfile profile2 = new ProfilesIni().getProfile("test");
    
	public static WebDriver OpenBrowser() throws Exception{
		DesiredCapabilities capability;
		DesiredCapabilities ieCapability;
		//DesiredCapabilities ffcapability =new DesiredCapabilities();
		ReadPropertiesFile properties=new ReadPropertiesFile();

			String browser=properties.getValue("loginInfo.properties", "browser");
			if(browser.equalsIgnoreCase("firefox")){
				profile.setAssumeUntrustedCertificateIssuer(false);
				//profile.setPreference("geo.wifi.uri", "file://C:/Users/sabyasachi.mishra/Desktop/location.json");
				
				//ffcapability.setPlatform(Platform.ANY);
				driver = new FirefoxDriver(profile);
				
			}else if(browser.equalsIgnoreCase("chrome")){
				String relativePath=System.getProperty("user.dir");
				String chromePath=relativePath+"/lib/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", chromePath);
				
				 capability = DesiredCapabilities.chrome();
				capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				
				driver=new ChromeDriver(capability);
				
			}else if(browser.equalsIgnoreCase("ie")){
				String relativePath=System.getProperty("user.dir");
				String iePath=relativePath+"/lib/IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", iePath);
				
				ieCapability = DesiredCapabilities.internetExplorer();
				ieCapability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);	
				driver=new InternetExplorerDriver(ieCapability);
			}
			
		
		
			
		
		return driver;
	}
	public static void takeScreenShot(ITestResult result, WebDriver driver)
	{
		
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.setCurrentTestResult(result);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		String relativePath=System.getProperty("user.dir");
		String destDir = relativePath+"/screenshots";
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
	
	public static void takeScreenShot(WebDriver driver)
	{
		
		
		//System.setProperty("org.uncommons.reportng.escape-output", "false");
		//Reporter.setCurrentTestResult(result);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		String relativePath=System.getProperty("user.dir");
		String destDir = relativePath+"/screenshots";
		new File(destDir).mkdirs();
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			//System.out.println("File path " + new File(destDir + "/" + destFile).getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(new File(destDir + "/" + destFile).getAbsolutePath());
//		Reporter.log("<a href = file:///" + new File(destDir + "/" + destFile).getAbsolutePath() +" target='_blank'" +"> " +
//				"<p align=\"left\", style= \"color: red; font: italic bold 12px/30px Georgia,serif;\" >Click here for screen shot </p> </a>");
		System.out.println("Taken screen shots");
	}
	

}
