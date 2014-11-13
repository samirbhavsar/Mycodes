package com.taxiforsure.pageObjects;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.taxiforsure.actions.DoOperation;
import com.taxiforsure.actions.ProjectCommmonMethods;

public class SelectLocation {
	
	WebDriver driver;
	
	public SelectLocation(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//nav[@class='tab-menu']/a[1]")
	public WebElement pointToPointTag;
	
	
	

	@FindBy(id = "pickArea")
	public WebElement pickUpArea;

	@FindBy(xpath = "//ul[contains(@id,'ui-id')]/li/a")
	public List<WebElement> pikUpAreaList;

	@FindBy(id = "dropArea")
	public WebElement dropArea;

	@FindBy(xpath = "//ul[contains(@id,'ui-id')]/li/a")
	public List<WebElement> dropAreaList;
	
	@FindBy(id= "datepicker")
	public WebElement datePicker;

	@FindBy(xpath = "//div[@class='picker__header']/div[1]")
	public WebElement monthPrev;

	@FindBy(xpath = "//div[@class='picker__header']/div[2]")
	public WebElement monthNext;

	@FindBy(xpath = "//div[@class='picker__holder']/descendant::table[@class='picker__table']/tbody/tr/td/div")
	public  List<WebElement> dateSelect;

	@FindBy(id = "timepicker")
	public WebElement pickUptime;

	@FindBy(xpath = "//ul[@class='picker__list']/li")
	public List<WebElement> selectTime;

	@FindBy(xpath = "//button[@value='Find Taxi']")
	public WebElement findTaxiBtn;
	
	@FindBy(xpath="//div[@class='welcomeMsg topDiv']//b")
	public WebElement userName;
	
	
	@FindBy(className="picker__month")
	public WebElement calMonth;
	
	@FindBy(className="picker__year")
	public WebElement calYear;
	
	
	@FindBy(className="picker__table")
	public WebElement dateWidgate;

	@FindBy(id="city")
	public WebElement cityLink;
	
	@FindBy(xpath="//*[@id='cm']/ul/li/a")
	public WebElement allCities_PopUp;
	
	@FindBy(xpath="//div[@class='searchCityWap']/ul/li/a")
	public List<WebElement> city_SelectList;
	
	
	//Navigations
	@FindBy(xpath = "//a[contains(@href,'/airport-service/')]")
	private WebElement airportTransferLnk;

	@FindBy(xpath = "//a[contains(@href,'/car-rental/')]")
	private WebElement halfOrFullDayLnk;

	@FindBy(xpath = "//a[contains(@href,'/outstation/')]")
	private WebElement outstationLnk;
	
	
	
	
	@FindBy(xpath = "//div/nav[@class='toggleAirPort']/a[1]")
	private WebElement goingToAirportLnk;

	
	
	@FindBy(xpath = "//div/nav[@class='toggleAirPort']/a[2]")
	private WebElement comingFromAirportLnk;
	
	
	
	
	
	
	//package
	@FindBy(id="id_packages_name")
	private WebElement packageTxt;
	
	@FindBy(xpath="//div[@class='confirmedContain cf']/div[@class='cchalf'][1]/div[1]/div[2]")
	private WebElement getPackageTxt;
	
	@FindBy(xpath="//dl[@class='cityDrop forPackage']/dd/ul[@class='js_os_packs']/li/a")
	private List<WebElement> packageList;
	
//	@FindBy(xpath="//ul[contains(@id,'ui-id')]/li/a")
//	private List<WebElement> pickList2;
	
//	@FindBy(xpath=".//*[@id='pickArea']")
//	private WebElement pickArea2;
//	
	
	//outstation
	
	@FindBy(id = "datepicker2")
	private WebElement datePicker2;
	
	@FindBy(id="interCityOption")
	public WebElement interCityOption;
	
	@FindBy(id="interCityOneWay")
	private WebElement interCityOneWay;
	
	@FindBy(id="interCityRoundTrip")
	private WebElement interCityRoundTrip;
	
	@FindBy(id="interCity")
	private WebElement interCityTab;
	

	@FindBy(xpath = "//div[@id='returnTripDate']/descendant::div[@class='picker__nav--next']")
	private WebElement calendarNext;
	
	@FindBy(xpath="//input[@id='datepicker2']/following-sibling::div//table/descendant::div[not(contains(@class,'disabled')) and contains(@class,'infocus')]")
	public List<WebElement> datesOT;
	
	
	
	
	public void doSelectCity(String city){
		if(!cityLink.getText().equalsIgnoreCase(city)){
			DoOperation.click(driver, cityLink);
			DoOperation.click(driver, city_SelectList, city);
		}
	}
	
	public void doSelectLocationsPointToPoint(String pickupArea,
			String drpArea, String time)
			throws InterruptedException {
		//DoOperation.click(driver, pointToPointTag);
		

		DoOperation.type(driver, pickUpArea, pickupArea.split(" ")[0]);
		Thread.sleep(3000);
		DoOperation.click(driver, pikUpAreaList, pickupArea);
		
		DoOperation.type(driver, dropArea, drpArea.split(" ")[0]);
		Thread.sleep(3000);
		DoOperation.click(driver, dropAreaList, drpArea);
		
		DoOperation.click(driver, datePicker);
		
		
		//Date slection
		selectDateFromCal(DoOperation.getDate(new Date(), 15));
		/*
		((JavascriptExecutor) driver)
				.executeScript("document.getElementById ('datepicker').removeAttribute('readonly',0);");
		datePicker.clear();
		datePicker.sendKeys(date);*/
		
		
		DoOperation.click(driver, pickUptime);
		DoOperation.click(driver,selectTime,time);
		DoOperation.click(driver, findTaxiBtn);

	}
	
	public void doGoTo_AirportTransfer(String date,String time,String pickArea ) throws InterruptedException{
		
		DoOperation.click(driver, airportTransferLnk);
		DoOperation.click(driver, goingToAirportLnk);
		DoOperation.click(driver, datePicker);
		selectDateFromCal(date );
		DoOperation.click(driver, pickUptime);
		DoOperation.click(driver, selectTime, time);
		

		DoOperation.type(driver, pickUpArea, pickArea.split(" ")[0]);
		Thread.sleep(3000);
		DoOperation.click(driver, pikUpAreaList, pickArea);
		
		DoOperation.click(driver, findTaxiBtn);
		
	}
public void doFrom_AirportTransfer(String date,String time,String droparea ) throws InterruptedException{
		
		DoOperation.click(driver, airportTransferLnk);
		DoOperation.click(driver, comingFromAirportLnk);
		DoOperation.click(driver, datePicker);
		selectDateFromCal(DoOperation.getDate(new Date(), 15));
		DoOperation.click(driver, pickUptime);
		DoOperation.click(driver, selectTime, time);
		

		DoOperation.type(driver, dropArea, droparea.split(" ")[0]);
		Thread.sleep(3000);
		DoOperation.click(driver, pikUpAreaList, droparea);
		
		DoOperation.click(driver, findTaxiBtn);
		
	}


public void doSelectPackageAndLocations(String package_type,String pickUp,String date,String time) throws InterruptedException{
	
	DoOperation.click(driver, halfOrFullDayLnk);
	
	DoOperation.click(driver, packageTxt);
	DoOperation.click(driver, packageList, package_type);
	
	DoOperation.type(driver, pickUpArea, pickUp.split(" ")[0]);
	DoOperation.click(driver, pikUpAreaList, pickUp);
	
	DoOperation.click(driver, datePicker);
	selectDateFromCal(date );
	
	DoOperation.click(driver, pickUptime);
	DoOperation.click(driver, selectTime, time);
	
	DoOperation.click(driver, findTaxiBtn);
	
	
	
	
}
	

	public void doSelectLocOutstation(String trip, String toLoc)
			throws InterruptedException {

		DoOperation.click(driver, outstationLnk);
		if (trip.equalsIgnoreCase("round")
				&& DoOperation.isElementPresent(interCityTab)) {

			DoOperation.click(driver, interCityRoundTrip);
			DoOperation.type(driver, dropArea, toLoc);
			DoOperation.click(driver, dropAreaList, toLoc);
			DoOperation.click(driver, datePicker);
			
			selectDateFromCal("05/12/2014");
			
			Thread.sleep(2000);
			
			DoOperation.click(driver, datePicker2);
			//DoOperation.click(driver, calendarNext);
			selectDateFromCalOT("9/12/2014");
			//selectDate(driver,"19/11/2014");
			
			//selectDateFromCal(DoOperation.currentDatePlus(15));
			//DoOperation.typeDate(driver, "datepicker2", "10/12/2014");
			
			DoOperation.click(driver, findTaxiBtn);

		} else if (trip.equalsIgnoreCase("oneWay")
				&& DoOperation.isElementPresent(interCityTab)) {

			DoOperation.click(driver, interCityOneWay);
			DoOperation.type(driver, dropArea, toLoc);
			DoOperation.click(driver, dropAreaList, toLoc);
			DoOperation.click(driver, datePicker);
			selectDateFromCal(DoOperation.getDate(new Date(), 15));

			DoOperation.click(driver, findTaxiBtn);
		} else {
			DoOperation.type(driver, dropArea, toLoc);
			DoOperation.click(driver, dropAreaList, toLoc);
			DoOperation.click(driver, datePicker);
			selectDateFromCal(DoOperation.getDate(new Date(), 6));
			Thread.sleep(2000);
			DoOperation.click(driver, datePicker2);
			//DoOperation.click(driver, calendarNext);
			selectDateFromCalOT(DoOperation.getDate(new Date(), 10));
			
			
			DoOperation.click(driver, findTaxiBtn);

		}
	
	
}
	
	
	
	public  void selectDateFromCal(String date)
			throws InterruptedException {
		
		//System.out.println("In selectDateFromCal");

		List<String> list = Arrays.asList("January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December");
		
		String calM = null;
		String calY = null;
		boolean dateNotFound = true;
		int firstIndex = date.indexOf("/");
		int lastIndex = date.lastIndexOf("/");
		
		int  day = Integer.parseInt(date.substring(0, firstIndex));
		int month = Integer.parseInt(date.substring(firstIndex + 1, lastIndex));
		

		
		int year = Integer
				.parseInt(date.substring(lastIndex + 1, date.length()));

		
		while (dateNotFound) {
			calM = calMonth.getText();
			calY = calYear.getText();
			
			if (list.indexOf(calM) + 1 == month
					&& (year == Integer.parseInt(calY))) {
				selectDate(driver,day);
				dateNotFound = false;
			} else if (list.indexOf(calM) + 1 < month
					&& (year == Integer.parseInt(calY))
					|| year > Integer.parseInt(calY)) {
				//DoOperation.highLightElement(driver, sLoc.monthNext, "red");
			//	monthNext.click();
				DoOperation.click(driver, monthNext);
			}
		}
		Thread.sleep(3000);
	}

	public void selectDate(WebDriver driver, int date) {

		// WebElement dateWidget = dateWidgate_Box;
		List<WebElement> rows = dateWidgate.findElements(By.tagName("tr"));
		List<WebElement> columns = dateWidgate.findElements(By.tagName("div"));

		for (WebElement cell : columns) {
			// Selects Date
			int calDate = Integer.parseInt(cell.getText());
			//System.out.println(calDate);
			if (date == calDate
					&& cell.getAttribute("class").contains("infocus")
					&& !cell.getAttribute("class").contains("disabled")
					|| cell.getAttribute("class").contains("highlighted")) {
				// DoOperation.highLightElement(driver, cell, "red");
				// cell.click();
				DoOperation.click(driver, cell);
				break;
			}
		}
	}
	
	public  void selectDateFromCalOT(String date)
			throws InterruptedException {
		
		//System.out.println("In selectDateFromCal");

		List<String> list = Arrays.asList("January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December");
		
		String calM = null;
		String calY = null;
		boolean dateNotFound = true;
		int firstIndex = date.indexOf("/");
		int lastIndex = date.lastIndexOf("/");
		
		int  day = Integer.parseInt(date.substring(0, firstIndex));
		int month = Integer.parseInt(date.substring(firstIndex + 1, lastIndex));
		

		
		int year = Integer
				.parseInt(date.substring(lastIndex + 1, date.length()));

		
		while (dateNotFound) {
			calM = calMonth.getText();
			calY = calYear.getText();
			
			if (list.indexOf(calM) + 1 == month
					&& (year == Integer.parseInt(calY))) {
				selectDate(driver,day);
				dateNotFound = false;
			} else if (list.indexOf(calM) + 1 < month
					&& (year == Integer.parseInt(calY))
					|| year > Integer.parseInt(calY)) {
				//DoOperation.highLightElement(driver, sLoc.monthNext, "red");
			//	monthNext.click();
				DoOperation.click(driver, monthNext);
			}
		}
		Thread.sleep(3000);
	}
	public void selectDateOT(WebDriver driver, int date) {

		
		

		for (WebElement cell : datesOT) {
			// Selects Date
			System.out.println(cell.getText());
			int calDate = Integer.parseInt(cell.getText());
			System.out.println(calDate);
			
			if (date == calDate) {
				DoOperation.click(driver, cell);
				break;
			}
		}
	}
	
	
	

}
