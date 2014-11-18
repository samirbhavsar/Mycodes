package com.taxiforsure.pagelib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigateToPages {

	@FindBy(xpath = "html/body/div[4]/div[1]/div/div[3]/div[1]/div[1]/nav/a[1]")
	private WebElement pointTopointLnk;
	@FindBy(xpath = "//a[contains(@href,'/airport-service/')]")
	private WebElement airportTransferLnk;

	@FindBy(xpath = "//a[contains(@href,'/car-rental/')]")
	private WebElement halfOrFullDayLnk;

	@FindBy(xpath = "//a[contains(@href,'/outstation/')]")
	private WebElement outstationLnk;
	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/a[1]")
	private WebElement homePageLnk;

	@FindBy(xpath="//div[4]/div[1]/div/div[3]/div[1]/div/nav/a[1]")
	private WebElement pTopLink;
	
	public WebElement pToPLink(){
		return pTopLink;
	}
	public WebElement pointToPointLink() {
		return pointTopointLnk;
	}

	public WebElement airportTransferLink() {
		return airportTransferLnk;

	}

	public WebElement halfOrFullDayLink() {
		return halfOrFullDayLnk;
	}

	public WebElement outstationLink() {
		return outstationLnk;
	}

	public WebElement homePageLink() {
		return homePageLnk;
	}

}
