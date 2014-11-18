package com.taxiforsure.pagelib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmBookingPage {

	@FindBy(xpath = "//div[@class='addressWapper']/textarea")
	private WebElement address;

	@FindBy(xpath = "//input[@class='landMark']")
	private WebElement landMark;

	@FindBy(xpath = "//*[@id='id_mobile']")
	private WebElement smsMobile;

	@FindBy(xpath = "//*[@id='id_customer_details']/div[2]/div/div/input")
	private WebElement billToEmail;

	@FindBy(id = "savAdd")
	private WebElement saveAddChkBox;

	@FindBy(id = "home")
	private WebElement homeOrOfficeEdtBox;

	@FindBy(id = "id_coupon_code")
	private WebElement couponCode;

	@FindBy(id = "submitBooking")
	private WebElement submitBookingBtn;

	@FindBy(id = "id_coupon_apply")
	private WebElement couponApplyBtn;

	public WebElement addressTxtArea() {
		return address;
	}

	public WebElement landMarkTxtBox() {
		return landMark;
	}

	public WebElement smsMobileTxtBox() {
		return smsMobile;
	}

	public WebElement billToMailTxtBox() {
		return billToEmail;
	}

	public WebElement addressChkBox() {
		return saveAddChkBox;
	}

	public WebElement homeOrOfficeTxt() {
		return homeOrOfficeEdtBox;
	}

	public WebElement couponCodeTxt() {
		return couponCode;
	}

	public WebElement couponApplyBtn() {
		return couponApplyBtn;
	}

	public WebElement submitBookingBtn() {
		return submitBookingBtn;
	}

}
