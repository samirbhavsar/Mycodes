package com.taxiforsure.pagelib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {

	
	@FindBy(linkText = "Login/Register")
	private WebElement loginLnk;

	@FindBy(xpath = "//div[@class='popupContain']")
	private WebElement loginPopUp;

	@FindBy(linkText = "Forgot your password ?")
	private WebElement forgotPasswordLnk;
	
	@FindBy(name="mobile_number")
	private WebElement mobileNumberEdt;
	
	@FindBy(id="forgot_password")
	private WebElement forgotPasswordBtn;

	@FindBy(name = "username")
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(id = "login")
	private WebElement loginBtn;

	@FindBy(xpath="//*[@id='topList']/li[3]/a/em")
	public WebElement myAccount;
	
	@FindBy(id="b_count")
	public WebElement bookingsLink;
	
	@FindBy(id="citySelect")
	public WebElement city_SearchBox;
	
	@FindBy(xpath="//a[@href='/logout/']/em")
	public WebElement logoutLink;
	
	
	@FindBy(xpath="//img[@alt='Book a Taxi']")
	public WebElement btn_BookTaxi;
	
	@FindBy(xpath="//div[@class='footerCity']/a[text()='Bangalore']")
	public WebElement cityPOPupLink;
	public WebElement loginLink() {
		return loginLnk;
	}

	public WebElement loginPopup() {

		return loginPopUp;
	}

	public WebElement forgotPasswordLink() {
		return forgotPasswordLnk;
	}
	public WebElement mobileNumberEdtBox(){
		return mobileNumberEdt;
	}
	public WebElement forGotPasswordBtn(){
		return forgotPasswordBtn;
	}

	public WebElement getUserNameEdt() {
		return userName;
	}

	public WebElement getPasswordEdt() {
		return password;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	public void login(String username,String password){
		this.loginLnk.click();
		this.userName.sendKeys(username);
		this.password.sendKeys(password);
		this.loginBtn.click();
	}

}
