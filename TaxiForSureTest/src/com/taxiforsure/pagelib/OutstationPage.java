package com.taxiforsure.pagelib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OutstationPage {

	
	@FindBy(xpath = "//div[@id='returnTripDate']/descendant::div[@class='picker__nav--next']")
	private WebElement calendarNext;
	
	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div[1]/div/div/div[2]/div[2]/div[1]/div[2]")
	private WebElement pickUpAddress;
	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div[1]/div/div/div[2]/div[2]/div[2]/div[2]")
	private WebElement pickUpArea;
	@FindBy(xpath = "//div[4]/div[1]/div/div[3]/div[1]/div[1]/div/div/div[2]/div[2]/div[3]/div[2]")
	private WebElement dropArea;

	@FindBy(xpath = "//*[@id='os_form']/div[4]/div[1]/div[2]/div/div[1]/div[3]/input")
	private WebElement outLandmrk;
	

	@FindBy(id = "datepicker2")
	private WebElement datePicker2;
	
	@FindBy(id="interCityOption")
	public WebElement interCityOption;
	
	@FindBy(id="interCityOneWay")
	private WebElement interCityOneWay;
	
	@FindBy(id="interCityRoundTrip")
	private WebElement interCityRoundTrip;
	
	@FindBy(id="interCity")
	private WebElement interCityBlock;
	
	
	
	public WebElement interCityBlock(){
		return interCityBlock;
	}
	
	public WebElement interCityOneWay(){
		return interCityOneWay;
	}
	public WebElement interCityRoundTrip(){
		return interCityRoundTrip;
	}

	public WebElement landMarkOutstation() {
		return outLandmrk;
	}

	public WebElement dropArea() {
		return dropArea;
	}

	public WebElement pickUpArea() {
		return pickUpArea;
	}

	public WebElement pickUpaddress() {
		return pickUpAddress;
	}

	public WebElement datePicker2() {
		return datePicker2;
	}

	public WebElement calendarNxt() {
		return calendarNext;
	}

}
