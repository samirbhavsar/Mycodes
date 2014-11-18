package com.taxiforsure.pagelib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingConfirmedPage {

	@FindBy(xpath = "//div[@class='cchalf']/h3")
	private WebElement bookingConfirmMsg;
	
	@FindBy(xpath = "//div[@class='ccID']/strong")
	private WebElement bookingId;

	@FindBy(xpath = "//div[contains(text(),'Taxi')]/following-sibling::div[1]")
	private WebElement taxiName;

	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div[1]/div/div/div[2]/div[1]/div[2]/div[2]")
	private WebElement dateAndTime;

	@FindBy(xpath = "//div[@class='ccTitle' and text()='ADDRESS:']/following-sibling::div[@class='ccDetail']")
	private WebElement pickUpAddress;

	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div[1]/div/div/div[2]/div[2]/div[2]/div[2]")
	private WebElement dropAddress;

	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div[1]/div/div/div[2]/div[2]/div[3]/div[2]")
	private WebElement landMark;

	@FindBy(linkText = "(Modify booking)")
	private WebElement modifyBooking;

	@FindBy(xpath = "//div[@class='buttonWapper']/a/input")
	private WebElement bookReturn;

	@FindBy(id = "askLogin")
	private WebElement askLoginBtn;

	@FindBy(xpath = "//div[contains(text(),'AIRPORT')]/following-sibling::div[1]")
	private WebElement airportName;

	@FindBy(xpath="//input[@value='Book Return']")
	public WebElement btn_BookReturn;
	
	public WebElement bookingConfirmMsg() {
		return bookingConfirmMsg;
	}

	public WebElement airportName() {
		return airportName;
	}

	public WebElement askLoginBtn() {
		return askLoginBtn;
	}

	public WebElement bookingIdTxt() {
		return bookingId;
	}

	public WebElement taxiNameTxt() {
		return taxiName;
	}

	public WebElement pickUpAddressTxt() {
		return pickUpAddress;
	}

	public WebElement dropAddressTxt() {
		return dropAddress;
	}

	public WebElement landMarkTxt() {
		return landMark;
	}

	public WebElement dateAndTimeTxt() {
		return dateAndTime;
	}

	public WebElement modifyBookingLnk() {
		return modifyBooking;
	}

	public WebElement bookReturnBtn() {
		return bookReturn;
	}
}
