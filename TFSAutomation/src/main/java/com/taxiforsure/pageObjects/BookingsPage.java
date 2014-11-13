package com.taxiforsure.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.taxiforsure.actions.DoOperation;



public class BookingsPage {
	
	WebDriver driver;
	public BookingsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	} 
	
	
	@FindBy(linkText="Active")
	private WebElement active_Btn;
	
	@FindBy(linkText="Past")
	private WebElement past_Btn;
	
	@FindBy(xpath="//span[@class='myTripsTab abs']/following-sibling::i")
	private WebElement expand_Ikon;
	

	@FindBy(linkText="Book Now")
	private WebElement bookNow_Btn;
	
	
	@FindBy(id="xpandProfile")
	private WebElement xpandProfile_Ikon;
	

	@FindBy(id="searchBooking")
	private WebElement searchBookingTxtBox;
	
	@FindBy(id="currentBookingData")
	private WebElement currentBookingData;
	
	
	@FindBy(id="b_count")
	private WebElement bookingsLink;
	
	@FindBy(xpath="//div[text()='BOOKING ID']/preceding-sibling::div")
	private WebElement booking_Id;
	
	@FindBy(xpath="//span[text()='CANCEL']")
	private WebElement cancel_Ikon;
	
	
	
	public void cancelBookings(String bookingId){
		Login login=DoOperation.initPage(driver, Login.class);
		DoOperation.mouseOverAction(driver, login.myAccount, bookingsLink);
		DoOperation.click(driver, expand_Ikon);
		DoOperation.click(driver, active_Btn);
		
		if(DoOperation.isElementPresent(bookNow_Btn)){
			Assert.fail("No booking present to cancel");
		}
	
		
		DoOperation.type(driver, searchBookingTxtBox, bookingId);
		DoOperation.waitForPageLoad(driver);
		
		//Assert.assertEquals( booking_Id.getText(),bookingId);
		DoOperation.click(driver, cancel_Ikon);
		if(DoOperation.isAllertPresent(driver)){
			DoOperation.clickAlertOK(driver);
			//DoOperation.waitForAlertPresent(driver);
			//Assert.assertTrue(DoOperation.getAlertText(driver).contains(bookingId));
			//Assert.assertTrue(DoOperation.getAlertText(driver).contains("canceled"));
			DoOperation.clickAlertOK(driver);
		}else{
			Assert.fail("No alert is displayed.Cancel booking failed");
		}
			
	}
	public void verifyBookingCancelled(String bookingId){
		try{
		DoOperation.click(driver, past_Btn);
		DoOperation.type(driver, searchBookingTxtBox, bookingId);
		DoOperation.waitForPageLoad(driver);
		Assert.assertEquals(DoOperation.getTextFromElement(driver, "//div[text()='", "']", bookingId),bookingId);
		}catch(Exception e){
			Assert.fail("Booking id: "+bookingId+" is not present in past booking section");
		}
	}

}
