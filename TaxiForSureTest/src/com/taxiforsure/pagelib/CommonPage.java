package com.taxiforsure.pagelib;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.taxiforsure.commonLib.BDriver;
import com.taxiforsure.commonLib.WebDriverCommonLib;
import com.taxiforsure.projectCommonLib.AccountMouseOver;

public class CommonPage extends WebDriverCommonLib {
	public CommonPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	

	private static WebElement myAccount = driver.findElement(By
			.xpath(".//*[@id='topList']/li[3]/a/em"));

	public static void gotoBookings() {
		mouseOverAction(myAccount, "Bookings");
	}

	public static void gotoSettings() {
		mouseOverAction(myAccount, "Settings");
	}

	public static void logout() {
		mouseOverAction(myAccount, "Logout");

	}

	public static void mouseOverAction(WebElement mainElement, String subElement) {
		Actions action = new Actions(driver);
		action.moveToElement(mainElement).perform();

		if (subElement.equalsIgnoreCase("Bookings")) {
			action.moveToElement(
					driver.findElement(By
							.linkText("Bookings"))).click()
					.perform();
			// //*[@id='subList']/li/a[@href='/my-bookings/']
			// Log.info(subElement+" Displayed ");
		} else if (subElement.equalsIgnoreCase("Settings")) {
			action.moveToElement(
					driver.findElement(By
							.xpath("//ul[@id='subList']/descendant::a/em[text()='Settings']"))).click()
					.perform();
		} else if (subElement.equalsIgnoreCase("Logout")) {
			action.moveToElement(
					driver.findElement(By.linkText("Logout"))).click()
					.perform();
		} else {
			System.out.println("Invalid Selection");
		}

	}
	
	public static void getTotalBookings() throws InterruptedException{
		Actions action = new Actions(driver);
		WebElement bookings=driver.findElement(By.xpath("//*[@id='subList']/li/a[@href='/my-bookings/']"));
		action.moveToElement(myAccount).perform();
		Thread.sleep(3000);
		action.moveToElement(bookings).perform();
		
		System.out.println(bookings.getAttribute("data-val"));
	}

}
