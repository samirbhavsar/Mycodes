package com.taxiforsure.pageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.taxiforsure.actions.DoOperation;

public class TaxiPage {
	WebDriver driver;
	public TaxiPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/*
	@FindBy(xpath="//div[@class='stepContent']/descendant::table[@class='carList']/tbody/tr/td/input[@type='radio']")
	public List<WebElement> selectCar;
	
	@FindBy(xpath="//div[@class='stepContent']/descendant::table[@class='carList']/tbody/tr/td[2]/div/div[2]/p[1]")
	public List<WebElement> carName;*/
	
	@FindBy(xpath="//div[@class='taxiData']/p[1]")
	public List<WebElement> carList;
	
	@FindBy(xpath="//button[text()='Select My Taxi']")
	public WebElement selectTaxiBtn;
	
	
	@FindBy(xpath="//div[@class='carPrice']")
	public WebElement taxiDiv;
	
	
	@FindBy(xpath="//td[span[contains(text(),'Flat')]]/preceding-sibling::td/div[@class='taxiInfo']/div[2]/p[1]")
	public WebElement taxiWithFlat_fare;
	
	@FindBy(xpath="//td[div[@class='carCost perkm']]/preceding-sibling::td/div[@class='taxiInfo']/div[2]/p[1]")
	public WebElement taxiList_NoFare;
	
	
	
	
	public void selectTaxi(String taxiName){
		
		DoOperation.waitForWebElement(taxiDiv);
		
		//if(carList.size()>0){
			
			DoOperation.click(driver, carList, taxiName);
//		}else{
//			Assert.fail("Taxi list is missing");
//		}
		
		DoOperation.click(driver, selectTaxiBtn);
		
		//DoOperation.clickElementByText(driver, elements, text);
		
	}
	
	
	
}


