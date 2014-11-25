package com.taxiforsure.regressionTest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.taxiforsure.actions.ProjectCommmonMethods;
import com.taxiforsure.driver.MyDriver;
import com.taxiforsure.pageObjects.BookingsPage;
import com.taxiforsure.pageObjects.Login;
import com.taxiforsure.utils.ReadPropertiesFile;

public class CancelBookingTest {
	WebDriver driver;
	ReadPropertiesFile prop = new ReadPropertiesFile();
	String username = prop.getValue("Config.properties", "username");
	String password = prop.getValue("Config.properties", "password");
	ProjectCommmonMethods pcm=new ProjectCommmonMethods();
	
	
	
	@BeforeClass
	public void setup() throws InterruptedException {
		driver = new MyDriver().getDriver();
		
		
		
	}
	
	@Test
	public void cancelBookingsTest() throws InterruptedException{
		
		new Login(driver).doLogin(username,password);
		
		new BookingsPage(driver).cancelBookings("TFS-PP-C3072843");
		//new BookingsPage(driver).verifyBookingCancelled("TFS-AT-C3072844");
	
	}
	
	@AfterMethod
	public void onTestFailure(ITestResult result){
		if(!result.isSuccess()){
			new MyDriver().takeScreenShot(result, driver);
		}
	}


}
