package com.taxiforsure.commonLib;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.mysql.jdbc.Driver;

public class BDriver {
	
	 //static FirefoxProfile profile;
//	static String chromePath="G:\\Browser Drivers\\chromedriver.exe";
//	static String iePath="G:\\Browser Drivers\\IEDriverServer.exe";
	 
	

	
	public static WebDriver driver = new FirefoxDriver();
	
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
		 
        //Convert web driver object to TakeScreenshot
 
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
 
        //Call getScreenshotAs method to create image file
 
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
 
            //Move image file to new destination
 
                File DestFile=new File(fileWithPath);
 
                //Copy file at destination
 
                FileUtils.copyFile(SrcFile, DestFile);
 
             
 
    }
	
	
	

}
