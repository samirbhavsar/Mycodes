package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectLocation {

	@FindBy(xpath = "//nav[@class='tab-menu']/a[1]")
	private WebElement pointToPointTag;
	
	@FindBy(id="city")
	private WebElement cityLink;

	@FindBy(id = "pickArea")
	private WebElement pickUpArea;

	@FindBy(xpath = "//ul[contains(@id,'ui-id')]/li/a")
	private List<WebElement> pikUpAreaList;

	@FindBy(id = "dropArea")
	private WebElement dropArea;

	@FindBy(xpath = "//ul[contains(@id,'ui-id')]/li/a")
	private List<WebElement> dropAreaList;
	
	@FindBy(id= "datepicker")
	private WebElement datePicker;

	@FindBy(xpath = "//div[@class='picker__header']/div[1]")
	private WebElement monthPrev;

	@FindBy(xpath = "//div[@class='picker__header']/div[2]")
	private WebElement monthNext;

	@FindBy(xpath = "//div[@class='picker__holder']/descendant::table[@class='picker__table']/tbody/tr/td/div")
	private List<WebElement> dateSelect;

	@FindBy(id = "timepicker")
	private WebElement pickUptime;

	@FindBy(xpath = "//ul[@class='picker__list']/li")
	private List<WebElement> selectTime;

	@FindBy(xpath = "//button[@value='Find Taxi']")
	private WebElement findTaxiBtn;
	
	@FindBy(xpath="//div[@class='welcomeMsg topDiv']/p/b")
	private WebElement userName;
	
	
	@FindBy(xpath="//div[@class='picker__month']")
	private WebElement calMonth;
	
	@FindBy(xpath="//div[@class='picker__year']")
	private WebElement calYear;
	
	
	@FindBy(xpath="//table[@class='picker__table']")
	private WebElement dateWidgate;
	
	public WebElement dateWidgate_Box(){
		return dateWidgate;
	}
	
	public WebElement calMonth(){
		return calMonth;
	}
	public WebElement calYear(){
		return calYear;
	}
	
	
	
	public WebElement cityLink(){
		return cityLink;
	}
	public  WebElement showUser(){
		return userName;
	}

	public WebElement pointToPointNav() {
		return pointToPointTag;
	}

	public WebElement pickUpArea() {
		return pickUpArea;
	}

	public WebElement dropArea() {
		return dropArea;
	}

	public WebElement datePicker() {
		return datePicker;
	}

	public WebElement monthPrev() {
		return monthPrev;
	}

	public WebElement monthNext() {
		return monthNext;
	}

	public List<WebElement> dateSelect() {
		return dateSelect;
	}

	public WebElement clickOnTime() {
		return pickUptime;
	}

	public List<WebElement> selectTime() {
		return selectTime;
	}

	public List< WebElement> pickUpAreaList() {
		return pikUpAreaList;
	}
	public WebElement findTaxiBtn() {
		return findTaxiBtn;
	}
	public List<WebElement> dropAreaList() {
		return dropAreaList ;
	}
}
