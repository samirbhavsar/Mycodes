package com.taxiforsure.pageObjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.taxiforsure.actions.DoOperation;
import com.taxiforsure.assertions.Assertion;

public class Login {
	
WebDriver driver;
Logger log = Logger.getLogger("Login");
	
	public Login(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//*[@id='id_login']/em")
	public WebElement loginLnk;

	
	@FindBy(name="username")
	public WebElement usernameTextBox;
	
	@FindBy(name="password")
	public WebElement passswordTextBox;
	
	@FindBy(id="login")
	public WebElement loginButton;
	
	@FindBy(xpath="//li[@class='menu']/a//em")
	public WebElement myAccount;
	
	
	
	@FindBy(linkText="Logout")
	public WebElement logoutLink;
	
	@FindBy(xpath="//ul[@class='cityUL']/li/a")
	public List<WebElement> cityList;
	
	@FindBy(xpath="//div[@class='welcomeMsg topDiv']/descendant::b")
	public WebElement username;
	
	@FindBy(xpath="//img[@alt='Book a Taxi']")
	public WebElement btn_BookTaxi;
	
	@FindBy(xpath="//div[@class='footerCity']/a[text()='Bangalore']")
	public WebElement cityPOPupLink;
	
	public void doLogin(String userName, String password) throws InterruptedException
	{
		DoOperation.click(driver, cityPOPupLink);
		//DoOperation.click(driver, btn_BookTaxi);
		DoOperation.click(driver, cityList, "Bangalore");
		DoOperation.click(driver, loginLnk);
		Thread.sleep(3000);
	//	Assert.assertTrue(new Login(driver).usernameTextBox.isDisplayed(),"Not present");
		DoOperation.type(driver, usernameTextBox, userName);
		DoOperation.type(driver, passswordTextBox, password);
		DoOperation.click(driver, loginButton);
		try{
		DoOperation.waitForWebElement(username);
		}catch(Exception e){
			Assert.fail("Login unsuccessfull");
		}
		
	
	}
	
	public void doLogout() throws InterruptedException{
		DoOperation.mouseOverAction(driver,myAccount,logoutLink);
		//DoOperation.mouseHover(driver, myAccount);
		Thread.sleep(3000);
		
		
	}

	public void setUsername(String username){
		try{
			usernameTextBox.clear();
			usernameTextBox.sendKeys(username);
			log.info("UserName field exists");
		}catch(Throwable t){
			log.error("username field doesnot exist");
			Assert.fail("Username doesnot exists or not visible");
			
		}
	}
}
