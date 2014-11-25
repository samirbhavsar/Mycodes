package com.taxiforsure.pageObjects;



import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.taxiforsure.actions.DoOperation;
import com.taxiforsure.utils.ExcelUtils;

public class TFSVerifications {
	
	WebDriver driver;
	
	public TFSVerifications(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='/about-us/']")
	public WebElement aboutUs_Link;
	
	@FindBy(linkText="Our Story")
	public WebElement ourStory_Link;
	
	
	@FindBy(linkText="Team")
	public WebElement team_Link;
	
	@FindBy(linkText="Testimonials")
	public WebElement testimonials_Link;
	
	
	@FindBy(xpath="//div[@class='aboutContainHead']/P")
	public WebElement aboutContain_Header;
	
	@FindBy(linkText="Our Partners")
	public WebElement ourPartner_Link;
	
	
	@FindBy(linkText="Contact Us")
	public WebElement contactUs_Link;
	
	@FindBy(xpath="//a[@href='/tos/']")
	public WebElement terms_Of_Services_Link;
	
	@FindBy(xpath="//a[@href='/our-partners/']")
	public WebElement our_partners_Link;
	
	
	
	
	
	public void verifyAboutUs() throws InvalidFormatException, IOException {

		CommonPage cPage = DoOperation.initPage(driver, CommonPage.class);
		Login login = DoOperation.initPage(driver, Login.class);

		cPage.goToHomePage();
		DoOperation.click(driver, login.cityList, "Bangalore");
		DoOperation.ScrollToElement(driver, aboutUs_Link);
		DoOperation.click(driver, aboutUs_Link);
		//System.out.println(DoOperation.getPageSource(driver));
		Assert.assertTrue(!DoOperation.getPageSource(driver).isEmpty());
		Assert.assertTrue(
				DoOperation.getPageSource(driver).contains(
						ExcelUtils.getExcelData("messages", 1, 0)), "Text"
						+ ExcelUtils.getExcelData("Sheet1", 1, 0)
						+ " is not present in current the content");

	}
	
	
	

}
