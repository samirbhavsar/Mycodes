package com.taxiforsure.pagelib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterUserPage {

	@FindBy(xpath = "//div[@class='popupContain']")
	private WebElement loginPopUp;

	@FindBy(linkText = "Register here")
	private WebElement registerLnk;

	@FindBy(name = "name")
	private WebElement usernameEdt;

	@FindBy(name = "email")
	private WebElement emailEdt;

	@FindBy(name = "mobile")
	private WebElement mobileEdt;

	@FindBy(name = "pawd")
	private WebElement passwordEdt;

	@FindBy(name = "confirm_pawd")
	private WebElement confirmPasswordEdt;

	@FindBy(id = "register")
	private WebElement registerBtn;

	public WebElement loginPopUp() {
		return loginPopUp;
	}

	public WebElement registerLink() {
		return registerLnk;
	}

	public WebElement userNameEdtBox() {
		return usernameEdt;
	}

	public WebElement emailEdtBox() {
		return emailEdt;
	}

	public WebElement mobileEdtBox() {
		return mobileEdt;
	}

	public WebElement passwordEdtBox() {
		return passwordEdt;
	}

	public WebElement confirmPasswordEdtBox() {
		return confirmPasswordEdt;
	}

}
