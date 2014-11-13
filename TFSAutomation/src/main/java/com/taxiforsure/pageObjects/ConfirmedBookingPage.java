package com.taxiforsure.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.taxiforsure.actions.DoOperation;

public class ConfirmedBookingPage {

	WebDriver driver;

	public ConfirmedBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "pickup_address")
	public WebElement address;

	@FindBy(className = "textAreaAddress")
	public WebElement txt_Address_Pkg;

	@FindBy(xpath = "//input[@class='landMark']")
	public WebElement landMark;

	@FindBy(xpath = "//*[@id='id_mobile']")
	public WebElement smsMobile;

	@FindBy(xpath = "//*[@id='id_customer_details']/div[2]/div/div/input")
	public WebElement billToEmail;

	@FindBy(id = "savAdd")
	public WebElement saveAddChkBox;

	@FindBy(id = "home")
	public WebElement homeOrOfficeEdtBox;

	@FindBy(id = "id_coupon_code")
	public WebElement couponCode;

	@FindBy(id = "submitBooking")
	public WebElement submitBookingBtn;

	@FindBy(id = "id_coupon_apply")
	public WebElement couponApplyBtn;

	@FindBy(name = "address")
	public WebElement addressForAirport;

	public void doTypeAddress(String mobileNo,
			String billTo) {

		DoOperation.type(driver, address, DoOperation.getRandomString(5));
		DoOperation.type(driver, landMark, DoOperation.getRandomString(5));
		DoOperation.type(driver, smsMobile, mobileNo);
		DoOperation.type(driver, billToEmail, billTo);

		DoOperation.click(driver, submitBookingBtn);
		

	}

	public void doTypeAddressPkg(
			String mobileNo, String billTo) {

		DoOperation.type(driver, txt_Address_Pkg, DoOperation.getRandomString(5));
		DoOperation.type(driver, landMark, DoOperation.getRandomString(5));
		DoOperation.type(driver, smsMobile, mobileNo);
		DoOperation.type(driver, billToEmail, billTo);

		DoOperation.click(driver, submitBookingBtn);

	}

	public void doTypeAddressToAirport(
			String mobileNo, String billTo) {

		DoOperation.type(driver, addressForAirport, DoOperation.getRandomString(5));
		DoOperation.type(driver, landMark, DoOperation.getRandomString(5));
		DoOperation.type(driver, smsMobile, mobileNo);
		DoOperation.type(driver, billToEmail, billTo);

		DoOperation.click(driver, submitBookingBtn);

	}

	public void doTypeAddressFromAirport(String mobileNo, String billTo) {

		// Operation.type(driver, addressForAirport, aDdress);
		// DoOperation.type(driver, landMark, landmark);
		DoOperation.type(driver, smsMobile, mobileNo);
		DoOperation.type(driver, billToEmail, billTo);

		DoOperation.click(driver, submitBookingBtn);

	}
	
	

}
