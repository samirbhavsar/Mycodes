package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.taxiforsure.commonLib.WebDriverCommonLib;

public class TfsMsitePage {
	
	@FindBy(xpath="//div[1]/div[4]/div[1]/div/span")
	public WebElement closeBtn;
	
	@FindBy(id="city")
	public WebElement city;
	
@FindBy(xpath="html/body/div[3]/div/span[2]/span")
public WebElement click_Loc;

	@FindBy(id="pick")
	public WebElement pickUpLoc;
	
	@FindBy(id="drop")
	public WebElement DropLoc;
	
	@FindBy(xpath="//div/div/div/div/div/div[4]/div[1]/div/div/ul/li[1]")
	public WebElement next60;
	@FindBy(xpath="//li[text()='After 60 mins']")
	public WebElement after60;
	

	@FindBy(id="datepicker")
	public WebElement datePicker;
	
	@FindBy(id="timepicker")
	public WebElement timePicker;
	
	@FindBy(xpath="//button[contains(text(),'Book')]")
	public WebElement bookTaxiBtn;
	
	
	@FindBy(xpath="//ul[@class='picker__list']/li")
	public List<WebElement> times;
	
	@FindBy(id="customer_number")
	public WebElement customer_number;
	
	/*@FindBy(xpath="html/body/form/div/header/div[1]/a/img")
	public WebElement ImgTfs;*/
	
	
	@FindBy(xpath="//table[@class='picker__table']/descendant::div[@class='picker__day picker__day--infocus' or contains(@class,'picker__day--today')]")
	public List<WebElement> dates;
	
	@FindBy(xpath="//div[@class='logoWap']/a/img")
	public WebElement img_HomePage;
	
	@FindBy(css="i.imenu")
	public WebElement loginIkon;
	
	@FindBy(linkText="LOGIN")
	public WebElement loginLink;
	
	@FindBy(id="login_id")
	public WebElement user_Name;
	
	@FindBy(id="login_password")
	public WebElement Password;
	
	@FindBy(xpath="//button[text()='Login']")
	public WebElement btn_Login;
	
	@FindBy(linkText="LOGOUT")
	public WebElement logOutLink;
	
	@FindBy(id="jSelectCar")
	public WebElement selectCar;
	
	@FindBy(xpath="//div[@class='atYou']/strong")
	public WebElement confirmMsg;
	
	@FindBy(className="abdClose")
	public WebElement cancel_BookingIkon;
	
	@FindBy(css="span.cbDay")
	public WebElement travelDate;
	
	@FindBy(className="cbHr")
	public WebElement travel_Time;
	
	@FindBy(className="cbCabNo")
	public WebElement booking_Id;
	
	@FindBy(xpath="//a[@href='/m/bookings/']")
	public WebElement myBookings;
	
	@FindBy(xpath = "//li[text()='Now']")
	public WebElement now;
	
	@FindBy(xpath="//div[@class='picker__nav--next']")
	public WebElement calNext;
	
	public void loginToMsite(String username,String password){
		
		loginIkon.click();
		loginLink.click();
		user_Name.sendKeys(username);
		Password.sendKeys(password);
		btn_Login.click();
		
	}
	
	public void logoutFromApp(){
		
			img_HomePage.click();
			loginIkon.click();
		
			logOutLink.click();
	}
	
	
	
	
}




