package com.taxiforsure.commonLib;

import org.openqa.selenium.WebDriver;

public class BaseClass {
	public static WebDriver driver;
	
	public  BaseClass(WebDriver driver){
		BaseClass.driver = driver;
		
	}

}
