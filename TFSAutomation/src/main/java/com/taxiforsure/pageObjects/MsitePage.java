package com.taxiforsure.pageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.taxiforsure.actions.DoOperation;

public class MsitePage {
	WebDriver driver;
	 public MsitePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Close']")
	public WebElement closeBtn;

	@FindBy(id = "city")
	public WebElement city;
	
	@FindBy(id = "pick")
	public WebElement pickUpLoc;

	@FindBy(id = "drop")
	public WebElement DropLoc;

	@FindBy(xpath = "//li[text()='Now']")
	public WebElement now;
	
	@FindBy(xpath = "//li[text()='After 60 mins']")
	public WebElement after60;

	@FindBy(id = "datepicker")
	public WebElement datePicker;

	@FindBy(id = "timepicker")
	public WebElement timePicker;

	@FindBy(className = "bookTaxi")
	public WebElement bookTaxiBtn;

	@FindBy(xpath = "//ul[@class='picker__list']/li")
	public List<WebElement> times;

	@FindBy(id = "customer_number")
	public WebElement customer_number;

	@FindBy(xpath = "//table[@class='picker__table']/descendant::div[@class='picker__day picker__day--infocus' or contains(@class,'picker__day--today')]")
	public List<WebElement> dates;

	@FindBy(xpath = "//div[@class='logoWap']/a/img")
	public WebElement img_HomePage;

	@FindBy(css = "i.imenu")
	public WebElement loginIkon;

	@FindBy(linkText = "LOGIN")
	public WebElement loginLink;

	@FindBy(id = "login_id")
	public WebElement user_Name;

	@FindBy(id = "login_password")
	public WebElement Password;

	@FindBy(xpath = "//button[text()='Login']")
	public WebElement btn_Login;

	@FindBy(linkText = "LOGOUT")
	public WebElement logOutLink;

	@FindBy(id = "jSelectCar")
	public WebElement selectCar;

	@FindBy(xpath = "//div[@class='atYou']/strong")
	public WebElement confirmMsg;

	@FindBy(className = "abdClose")
	public WebElement cancel_BookingIkon;

	@FindBy(css = "span.cbDay")
	public WebElement travelDate;

	@FindBy(className = "cbHr")
	public WebElement travel_Time;

	@FindBy(className = "cbCabNo")
	public WebElement booking_Id;

	@FindBy(xpath = "//a[@href='/m/bookings/']")
	public WebElement myBookings;

	public void selectLocationMsite_After60(String city_Name, String picLoc,
			String dropLoc, String date, String time,String mobile_No)
			throws InterruptedException {

		DoOperation.click(driver, closeBtn);

		if (!DoOperation.getFirstSelectedOption(driver, city).equalsIgnoreCase(
				city_Name)) {
			
			DoOperation.selectInDropDown(driver, city, city_Name);
		}
		DoOperation.type(driver, pickUpLoc, picLoc);
		DoOperation.sendKeystrokes(pickUpLoc, Keys.ARROW_DOWN);
		Thread.sleep(2000);
		DoOperation.sendKeystrokes(pickUpLoc, Keys.RETURN);
		Thread.sleep(2000);
		
		DoOperation.type(driver, DropLoc, dropLoc);
		DoOperation.sendKeystrokes(DropLoc, Keys.ARROW_DOWN);
		Thread.sleep(2000);
		DoOperation.sendKeystrokes(DropLoc, Keys.RETURN);

		DoOperation.click(driver, after60);
		Thread.sleep(4000);
		DoOperation.typeDate(driver, "datepicker",date);
		DoOperation.click(driver, datePicker);
		DoOperation.click(driver, dates, "30");
		
		//DoOperation.typeDate(driver, "timepicker", time);
		
		DoOperation.click(driver, timePicker);
		DoOperation.click(driver, times, "9:45 PM");
		
		DoOperation.type(driver, customer_number, mobile_No);
		
		//DoOperation.click(driver, bookTaxiBtn);

	}
}
