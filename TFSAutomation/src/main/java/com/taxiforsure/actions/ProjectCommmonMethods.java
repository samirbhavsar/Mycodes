package com.taxiforsure.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.taxiforsure.driver.MyDriver;
import com.taxiforsure.pageObjects.BookingSummeryPage;
import com.taxiforsure.pageObjects.SelectLocation;

public class ProjectCommmonMethods {
	
	
	Map<String, List<String>> map = new HashMap<String, List<String>>();
	  List<String> confirmedBooking = new ArrayList<String>();
	  List<String> PendingBooking = new ArrayList<String>();
	
	public  void selectDateFromCal(WebDriver driver,String date)
			throws InterruptedException {

		List<String> list = Arrays.asList("January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December");
		
		SelectLocation sLoc=DoOperation.initPage(driver, SelectLocation.class);
		
		
		String calM = null;
		String calY = null;
		boolean dateNotFound = true;
		int firstIndex = date.indexOf("/");
		int lastIndex = date.lastIndexOf("/");
		
		String day = date.substring(0, firstIndex);
		int month = Integer.parseInt(date.substring(firstIndex + 1, lastIndex));
		List<WebElement> rows = sLoc.dateWidgate.findElements(By.tagName("tr"));
		List<WebElement> columns = sLoc.dateWidgate.findElements(By.tagName("div"));

		
		int year = Integer
				.parseInt(date.substring(lastIndex + 1, date.length()));

		
		while (dateNotFound) {
			calM = sLoc.calMonth.getText();
			calY = sLoc.calYear.getText();
			
			if (list.indexOf(calM) + 1 == month
					&& (year == Integer.parseInt(calY))) {
			//	selectDate(day);
				

				for (WebElement cell : columns) {
					// Selects Date
					if (cell.getText().equals(date)&& cell.getAttribute("class").contains("infocus") && 
							!cell.getAttribute("class").contains("disabled")) {
						//DoOperation.highLightElement(driver, cell, "red");
						//cell.click();
						DoOperation.click(driver, cell);
						break;
					}
				}
				
				
				dateNotFound = false;
			} else if (list.indexOf(calM) + 1 < month
					&& (year == Integer.parseInt(calY))
					|| year > Integer.parseInt(calY)) {
				//DoOperation.highLightElement(driver, sLoc.monthNext, "red");
				//sLoc.monthNext.click();
				DoOperation.click(driver, sLoc.monthNext);
				
			}
		}
		Thread.sleep(3000);
	}

	/*public  void selectDate(String date) {

		//WebElement dateWidget = dateWidgate_Box;
		List<WebElement> rows = sLoc.dateWidgate.findElements(By.tagName("tr"));
		List<WebElement> columns = sLoc.dateWidgate.findElements(By.tagName("div"));

		for (WebElement cell : columns) {
			// Selects Date
			if (cell.getText().equals(date)&& cell.getAttribute("class").contains("infocus") && 
					!cell.getAttribute("class").contains("disabled")) {
				//DoOperation.highLightElement(driver, cell, "red");
				//cell.click();
				DoOperation.click(driver, cell);
				break;
			}
		}
	}*/
	
	public void checkBookConfirm(WebDriver driver) {
		BookingSummeryPage bPage=DoOperation.initPage(driver, BookingSummeryPage.class);
		

		if (bPage.bookingConfirmMsg.getText().contains("Booking Confirmed")) {

			confirmedBooking.add(bPage.bookingId.getText());
			map.put("Confirmed", confirmedBooking);

		} else if (bPage.bookingConfirmMsg.getText().contains("Progress")) {
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
