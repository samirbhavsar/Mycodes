package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class AirportTransferPage {

	@FindBy(xpath = "//div/nav[@class='toggleAirPort']/a[1]")
	private WebElement goingToAirportLnk;

	@FindBy(xpath="//td[span[@class='flatFare']]/preceding-sibling::td/div/div[2]/p[1]")
	private List<WebElement> taxiListFlat;
	
	@FindBy(xpath = "//div/nav[@class='toggleAirPort']/a[2]")
	private WebElement comingFromAirportLnk;

	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div/div[1]/form/div[2]/div[1]/div[1]/div/div[2]/div/dl/dd/ul/li/a")
	private List<WebElement> flightsContainerLnk;

	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div/div[1]/form/div[2]/div[1]/div[1]/div/div[1]/div/dl/dt/a")
	private WebElement clickOnFlightLnk;

	@FindBy(xpath="//div[@class='inputWapper airWap goingAirSelect']/descendant::ul[@class='js_at_ul']/li/a")
	public List<WebElement> go_toAirportList;
	
	
	@FindBy(xpath="//div[@class='inputWapper airWap comingAirSelect']/descendant::ul[@class='js_at_ul']/li/a")
	public List<WebElement> coming_AirportList;
	
	
	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div/div[1]/form/div[2]/div[1]/div[1]/div/div[2]/div/dl/dt/a")
	private WebElement clickOnFlightLnk2;
	
	@FindBy(name="airport_span")
	public WebElement airportLink;

	@FindBy(xpath = "//div/input[@id='flight']")
	private WebElement flightNumberTxt;
	@FindBy(xpath = "//ul[contains(@id,'ui-id')]/li/a")
	private List<WebElement> pickUpList;

	@FindBy(xpath = "//ul[contains(@id,'ui-id')]/li/a")
	private List<WebElement> dropList;

	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div/div[1]/form/div[4]/div[1]/div[2]/div/div[1]/div[2]/textarea")
	private WebElement addressTextArea;

	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div[1]/div/div/div[2]/div[2]/div[4]/div[2]")
	private WebElement airportName;

	@FindBy(xpath="//td[div[@class='carCost perkm']]/preceding-sibling::td/div/descendant::p[1]")
	private List<WebElement> taxiNoFlat;
	
	public List<WebElement> taxiListNoFlatFare(){
		return taxiNoFlat;
	}
	
	
	
	
	public WebElement flightLink2() {

		return clickOnFlightLnk2;

	}
	public List<WebElement> taxiflatFare(){
		return taxiListFlat;
	}

	public List<WebElement> dropList1() {

		return dropList;

	}

	public WebElement addressTxtArea() {
		return addressTextArea;
	}

	public WebElement goingToairportLink() {
		return goingToAirportLnk;
	}

	public List<WebElement> pickUpList() {
		return pickUpList;
	}

	public WebElement comingFrmAirportLink() {
		return comingFromAirportLnk;
	}

	public WebElement clickOnFlightLink() {
		return clickOnFlightLnk;
	}

	public List<WebElement> getAllFlightList() {
		return flightsContainerLnk;
	}

	public WebElement flightNumbTxt() {
		return flightNumberTxt;
	}
}
