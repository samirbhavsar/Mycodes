package org.taxiforsure.notifications;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.taxiforsure.actions.DoOperation;

public class NotificationsScreen 
{
	
	WebDriver driver;
	
	public NotificationsScreen(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "city")
	private WebElement cityDropDown;
	
	@FindBy(xpath = "//input[@value='Change City']")
	private WebElement changeCityButton;
	
	public synchronized void selectCity(String city)
	{
	   new DoOperation().selectInDropDown(driver, cityDropDown, city);
	   new DoOperation().click(driver, changeCityButton);
	}
}