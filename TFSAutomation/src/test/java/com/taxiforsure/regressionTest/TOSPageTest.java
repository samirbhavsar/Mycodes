package com.taxiforsure.regressionTest;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.taxiforsure.actions.DoOperation;
import com.taxiforsure.driver.MyDriver;
import com.taxiforsure.pageObjects.CommonPage;
import com.taxiforsure.pageObjects.TFSVerifications;


public class TOSPageTest {
	
WebDriver driver;
	
	
	@BeforeClass
	public void setup()
	{
		driver = new MyDriver().getDriver();
		
		
	}
	@Test
	public void aboutUsTest() throws InvalidFormatException, IOException{
		
		new TFSVerifications(driver).verifyAboutUs();
		
		
	}
	
	

}
