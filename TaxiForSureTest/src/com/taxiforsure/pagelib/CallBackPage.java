package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CallBackPage {
	
	@FindBy(linkText="Call back")
	private WebElement callBackLink;
	
	@FindBy(xpath="//div[4]/div[3]/div[8]/div[2]/div[1]/div/div/dl/dt/a/span")
	private WebElement cityDropDown;
	
	@FindBy(xpath="//ul[@class='js_ccall_city']/li/a")
	private List<WebElement> cityList;
	
	@FindBy(id="cc-number")
	private WebElement cellNo;
	
	@FindBy(id="cc-submit")
	private WebElement submitCallBack;
	
	@FindBy(xpath="//div[4]/div[3]/div[8]/div[1]/div/div/span")
	private WebElement closeWindow;
	
	public WebElement closeWindow(){
		return closeWindow;
	}
	
	public WebElement callBackLnk(){
		return callBackLink;
	}
	public WebElement cityDropDown(){
		return cityDropDown;
	}
	public List<WebElement> cityList(){
		return cityList;
	}
	
}
