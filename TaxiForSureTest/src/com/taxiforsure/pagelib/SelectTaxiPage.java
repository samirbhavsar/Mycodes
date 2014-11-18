package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectTaxiPage {

	@FindBy(xpath="//div[@class='stepContent']/descendant::table[@class='carList']/tbody/tr/td/input[@type='radio']")
	private List<WebElement> selectCar;
	
	@FindBy(xpath="//div[@class='stepContent']/descendant::table[@class='carList']/tbody/tr/td[2]/div/div[2]/p[1]")
	private List<WebElement> carName;
	
	@FindBy(xpath="//div[@class='taxiData']/p[1]")
	private List<WebElement> carList;
	
	@FindBy(xpath="//div[4]/div[1]/div/div[3]/div[1]/div/div[1]/form/div[3]/div[2]/div/div/button")
	private WebElement selectTaxiBtn;
	
	
	////div[4]/div[1]/div/div[3]/div[1]/div/div[1]/form/div[3]/div[2]/div/div/button
	
	@FindBy(xpath="//div[@class='carPrice']")
	private WebElement taxiDiv;
	
	
	@FindBy(xpath="//td[div[@class='carCost perkm']]/preceding-sibling::td/div[@class='taxiInfo']/div[2]/p[1]")
	private WebElement taxiList_NoFare;
	
	
	public WebElement taxiList_NoFare(){
		return taxiList_NoFare;
	}
	
	
	
	public WebElement taxiDiv(){
		return taxiDiv;
	}
	public List<WebElement> selectCar(){
		return selectCar;
	}
	public WebElement selectTaxiBtn(){
		return selectTaxiBtn;
	}
	public List<WebElement> carList(){
		return carList;
	}
	
	public List<WebElement> carName(){
		return carName;
	}
	
	
	
}
