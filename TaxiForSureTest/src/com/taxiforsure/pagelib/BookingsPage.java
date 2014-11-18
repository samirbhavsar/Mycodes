package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingsPage {

	@FindBy(xpath = "//div[div[div[div[@class='atYou']/strong[contains(text(),'Confirmed')]]]]/preceding-sibling::div[@class='bdrowWap'][2]/div[2]/div[@class='bdCabModel']/div[1]")
	private List<WebElement> confirmedId;

	@FindBy(xpath = "//div[div[div[@class='bdCabModel']/div[contains(text(),'progress.')]]]/preceding-sibling::div[@class='bdrowWap'][2]/div[2]/div[@class='bdCabModel']/div[1]")
	private List<WebElement> inProgressId;

	@FindBy(xpath = "//div[@class='bdCabModel']/div[contains(text(),'BOOKING ID')]/preceding-sibling::div")
	private List<WebElement> pastBookingId;

	@FindBy(id = "searchBooking")
	private WebElement txtBx_searchBooking;

	@FindBy(xpath = "//div[@id='bookingControls']/ul/li[1]/span[1]")
	private WebElement cal_popUp;

	@FindBy(xpath = "//div[@id='bookingControls']/ul/li[2]/span[1]")
	private WebElement filterBox;

	@FindBy(id = "clrall")
	private WebElement btn_clearAll;

	@FindBy(id = "dateFrom")
	private WebElement dateFrom;

	@FindBy(id = "dateTo")
	private WebElement dateTo;

	@FindBy(xpath = "//div[@class='dropText allTrip']/ul/li")
	private List<WebElement> bookTypes;

	@FindBy(id = "all_trips")
	public WebElement clickAllTrips;

	@FindBy(xpath = "//div[@class='dropText allTripTypes']/ul/li")
	private List<WebElement> allTripTypes;

	@FindBy(xpath="//div[4]/div[1]/div/div[3]/div/div/div[1]/div[5]/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div[1]/a/button")
	private WebElement btn_ViewBill;
	
	public WebElement btn_ViewBill(){
		return btn_ViewBill;
	}
	
	public List<WebElement> allTripTypes() {
		return allTripTypes;
	}

	public WebElement clickAllTrips() {
		return clickAllTrips;
	}

	@FindBy(id = "all_trip_type")
	private WebElement clickAllTripsTypes;

	public WebElement clickAllTripsTypes() {
		return clickAllTripsTypes;
	}
	
	@FindBy(xpath="//li[@class='bdDel js_can_taxi']/span/i")
	private List<WebElement> cancel_Booking_Btn;

	@FindBy(xpath="//input[@class='show-more-past']")
	private WebElement showMoreResults;
	
	@FindBy(xpath="//div[@class='bookingDetailWap']")
	private WebElement bookingDiv;
	
	
	@FindBy(xpath="//span[@title='Cancel']/i")
	private WebElement cancelBookingIcon;
	
	@FindBy(xpath="//div[4]/div[1]/div/div[3]/div/div/div[1]/div[4]/div/div[1]/div/div/div[2]/div[6]/div/div/ul/li[1]/a/span/i")
	public WebElement editBookingIkon;
	
	@FindBy(className="no-results")
	private WebElement resultMsg;
	
	public WebElement resultMsg(){
		return resultMsg;
	}
	
	public WebElement cancelBookingIcon(){
		return cancelBookingIcon;
	}
	
	public WebElement bookingDiv(){
		return bookingDiv;
	}
	
	public WebElement showMoreResults(){
		return showMoreResults;
	}
	
	public List<WebElement> allTrips() {
		return bookTypes;
	}
public List<WebElement> cancel_Booking_Btn(){
	return cancel_Booking_Btn;
}
	
	@FindBy(xpath="//div[@class='bookingStats']/ul/li/span[contains(text(),'Past')]")
	private WebElement btn_pastBooking;
	
	public WebElement btn_PastBooking(){
		return btn_pastBooking;
	}
	public WebElement date_To() {
		return dateTo;
	}

	public WebElement btn_ClearAll() {
		return btn_clearAll;
	}

	public WebElement filterBox() {
		return filterBox;
	}

	public WebElement cal_PopUp() {
		return cal_popUp;
	}

	public WebElement txtBx_SearchBooking() {
		return txtBx_searchBooking;
	}

	public List<WebElement> inProgressBookingIds() {
		return inProgressId;
	}

	public List<WebElement> pastBookingIds() {
		return pastBookingId;
	}

	public List<WebElement> confirmedBookingId() {
		return confirmedId;
	}

}
