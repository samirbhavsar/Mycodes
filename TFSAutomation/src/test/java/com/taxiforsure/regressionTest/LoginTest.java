package com.taxiforsure.regressionTest;

import org.openqa.selenium.WebDriver;

import com.taxiforsure.pageObjects.Login;
import com.taxiforsure.utils.ReadPropertiesFile;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.taxiforsure.actions.DoOperation;
import com.taxiforsure.driver.MyDriver;
public class LoginTest {
	
	
	WebDriver driver;
	
	ReadPropertiesFile prop = new ReadPropertiesFile();
	String username=prop.getValue("Config.properties", "username");
	String password=prop.getValue("Config.properties", "password");
	
	@BeforeClass
	public void setup()
	{
		driver = new MyDriver().getDriver();
		
	}
	
	@Test
	public void loginTest() throws InterruptedException
	{
		
		new Login(driver).doLogin(username,password);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		new Login(driver).doLogout();
	}
	
	
	
	@AfterMethod
	public void onTestFailure(ITestResult result){
		
		if(!result.isSuccess()){
			new MyDriver().takeScreenShot(result, driver);
		}
		//new Login(driver).doLogout();
		
	}
	@AfterClass
	public void tearDown(){
		new MyDriver().quitBrowser(driver);
		
		
		
	}
	

}
