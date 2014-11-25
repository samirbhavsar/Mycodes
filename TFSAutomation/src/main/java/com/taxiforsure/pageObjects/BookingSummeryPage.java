package com.taxiforsure.pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BookingSummeryPage {
	
	WebDriver driver;
	
	 Map<String, List<String>> map = new HashMap<String, List<String>>();
	 
	  List<String> confirmedBooking = new ArrayList<String>();
	  List<String> PendingBooking = new ArrayList<String>();
	
	 public BookingSummeryPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 
	
	@FindBy(xpath = "//div[@class='cchalf']/h3")
	public WebElement bookingConfirmMsg;
	
	@FindBy(xpath = "//div[@class='ccID']/strong")
	public WebElement bookingId;
	
	@FindBy(linkText = "(Modify booking)")
	public WebElement modifyBooking;

	@FindBy(xpath = "//div[@class='buttonWapper']/a/input")
	public WebElement bookReturn;
	
	
	public void checkBookConfirm() {

		if (bookingConfirmMsg.getText().contains("Booking Confirmed")) {

			confirmedBooking.add(bookingId.getText());
			map.put("Confirmed", confirmedBooking);

		} else if (bookingConfirmMsg.getText().contains("Progress")) {
			//String getUrl = driver.getCurrentUrl();
			String[] str = driver.getCurrentUrl().split("/");
			String bookingId = str[str.length - 1];
			PendingBooking.add(bookingId);
			map.put("Pending", PendingBooking);
		} else {
			Assert.fail("Error: Booking Failed");
		}

	}

	public void showBookingId() throws IOException {

		System.out.println("================================");
		System.out.println("Total  bookings :");
		System.out.println("================================");
		if (!map.isEmpty()) {
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				String key = entry.getKey();
				List<String> values = entry.getValue();
				
				System.out.println("Status = " + key);
				System.out.println("BookingId = " + values);
			}
		}
	}
}
