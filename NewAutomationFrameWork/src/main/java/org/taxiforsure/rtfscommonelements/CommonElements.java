package org.taxiforsure.rtfscommonelements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonElements 
{
	WebDriver driver;
	
	public CommonElements(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='submenu']/span[text()='Answer Call']")
	private WebElement answerCallInMenuLink;
	
	public void clickAnswerCallLink()
	{
//	   new DoOperation().click(driver, answerCallInMenuLink);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", answerCallInMenuLink);
	}

}
