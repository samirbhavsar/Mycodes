package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FairChatPage {

	@FindBy(linkText = "Fare Chart")
	public WebElement fareChartLink;

	@FindBy(xpath = "//*[@id='x']/dt/a/span")
	public WebElement cityDropDown;

	@FindBy(xpath = "//*[@id='x']/dd/ul/li/a")
	public List<WebElement> cityList;

	@FindBy(xpath="//div[@class='dayNightWap']/ul/li[1]/a")
	public WebElement dayIcon;
	
	@FindBy(xpath="//div[@class='dayNightWap']/ul/li[2]/a")
	public WebElement nightIkon;
	
	@FindBy(xpath="//div[4]/div[3]/div[9]/div/div[1]/div/div[2]/span")
	public WebElement closeBtn;
	
	@FindBy(xpath="//div[4]/div[3]/div[9]/div/div[1]/div/div[1]/div/dl/dt/a/span")
	public WebElement cityName;
	
	
	@FindBy(xpath="// input[@id='tabday1']/following-sibling::label")
	public WebElement pToPdayTab;
	
	@FindBy(id="tabnight1")
	public WebElement pToPnightTab;
	
	@FindBy(xpath="//div[@id='nightFare']/ul/li[1]/label[1]/following-sibling::div/descendant::p")
	public List<WebElement> pToPTaxilistNight;
	
	@FindBy(xpath="//div[@id='dayFare']/ul/li[1]/label[1]/following-sibling::div/descendant::p")
	public List<WebElement> pToPTaxilistDay;
	// //div[@id='dayFare']/ul/li[1]/label[1]/following-sibling::div/descendant::div[@class='carCost perkm']
	
	@FindBy(xpath="//div[@id='dayFare']/ul/li[1]/label[1]/following-sibling::div/descendant::div[@class='carCost perkm']")
	public List<WebElement> pToPCarCostDay;
	
	@FindBy(xpath="//div[@id='nightFare']/ul/li[1]/label[1]/following-sibling::div/descendant::div[@class='carCost perkm']")
	public List<WebElement>  pToPCarCostNight;
	
	
	@FindBy(id="tabday2")
	public WebElement airportDayTab;
	
	@FindBy(id="tabnight2")
	public WebElement airportNightTab;
	
	
	

	@FindBy(id="tabday3")
	public WebElement Day_halfDayFullDayTab;
	
	@FindBy(id="tabnight3")
	public WebElement Night_halfDayFullDayTab;
	
	

	@FindBy(id="tabday4")
	public WebElement Day_outstationTab;
	
	@FindBy(id="tabnight4")
	public WebElement Night_outstationTab;
	
	
	@FindBy(xpath="//div[@id='dayFare']/ul/descendant::table[@class='carList']/tbody[@class='4h40km']/tr/td[2]/div/div/p")
	public List<WebElement> halfDayFullDay440CarList ;
	
	@FindBy(xpath="//div[@id='dayFare']/ul/descendant::table[@class='carList']/tbody[@class='8h80km']/tr/td[2]/div/div/p")
	public List<WebElement> Day_halfDayFullDay880CarList ;
	
	

	@FindBy(xpath="//div[@id='nightFare']/ul/descendant::table[@class='carList']/tbody[@class='8h80km']/tr/td[2]/div/div/p")
	public List<WebElement> Night_halfDayFullDay880CarList ;
	
	

	@FindBy(xpath="//div[@id='nightFare']/ul/descendant::table[@class='carList']/tbody[@class='4h40km']/tr/td[2]/div/div/p")
	public List<WebElement> Night_halfDayFullDay440CarList ;
	
	@FindBy(xpath="//div[@id='dayFare']/ul/descendant::table[@class='carList']/tbody[@class='4h40km']/tr/td[3]/div[1]")
	public List<WebElement> Day_dayfare440Price;
	
	
	@FindBy(xpath="//div[@id='nightFare']/ul/descendant::table[@class='carList']/tbody[@class='4h40km']/tr/td[3]/div[1]")
	public List<WebElement> Night_dayfare440Price;
	
	
	

	@FindBy(xpath="//div[@id='dayFare']/ul/descendant::table[@class='carList']/tbody[@class='8h80km']/tr/td[3]/div[1]")
	public List<WebElement> Day_dayfare880Price;
	
	
	@FindBy(xpath="//div[@id='nightFare']/ul/descendant::table[@class='carList']/tbody[@class='8h80km']/tr/td[3]/div[1]")
	public List<WebElement> Night_dayfare880Price;
	
	

	@FindBy(xpath="//div[@id='dayFare']/ul/descendant::table[@class='carList']/tbody[@class='outstation']/tr/td[2]/div/div/p")
	public List<WebElement> Day_outstationCarList;
	
	
	@FindBy(xpath="//div[@id='nightFare']/ul/descendant::table[@class='carList']/tbody[@class='outstation']/tr/td[2]/div/div/p")
	public List<WebElement> Night_outstationCarList;
	
	
	

	@FindBy(xpath="//div[@id='dayFare']/ul/descendant::table[@class='carList']/tbody[@class='outstation']/tr/td[4]/span")
	public List<WebElement> Day_outstationPrice;
	

	@FindBy(xpath="//div[@id='nightFare']/ul/descendant::table[@class='carList']/tbody[@class='outstation']/tr/td[4]/span")
	public List<WebElement> Night_outstationPrice;
	
	
}
