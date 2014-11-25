package com.taxiforsure.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.taxiforsure.actions.DoOperation;

public class CommonPage {
	
	
	WebDriver driver;
	public CommonPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//img[@alt='Book a Taxi']")
	public WebElement bookTaxi_Btn;
	
	
	
	
	
	public void goToHomePage(){
		DoOperation.click(driver, bookTaxi_Btn);
	}

}
