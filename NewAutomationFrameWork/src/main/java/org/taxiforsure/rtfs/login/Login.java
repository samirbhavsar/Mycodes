package org.taxiforsure.rtfs.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.taxiforsure.actions.DoOperation;

/**
 * 
 * @author tfs-vinay
 * @version 1.0
 * This class contains a representation of login screen of RTFS
 */

public class Login 
{ 
	WebDriver driver;
	
	public Login(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/** Username text box */
	@FindBy(id="id_username")
	private WebElement usernameTextBox;
	
	/** Password text box */
	@FindBy(id="id_password")
	private WebElement passswordTextBox;
	
	/** Login button */
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * This is used for logging into the application
	 */
	
	public synchronized void doLogin(String userName, String password)
	{
	   new DoOperation().type(driver, usernameTextBox, userName);
	   new DoOperation().type(driver, passswordTextBox, password);
	   new DoOperation().click(driver, loginButton);
	}

}
