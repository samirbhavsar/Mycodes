package com.taxiforsure.projectCommonLib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.taxiforsure.commonLib.BDriver;
import com.taxiforsure.commonLib.WebDriverCommonLib;


@SuppressWarnings("unused")
public class AccountMouseOver extends WebDriverCommonLib {

	public AccountMouseOver(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void mouseOverAction(WebElement mainElement, String subElement) {
		Actions action = new Actions(driver);
		action.moveToElement(mainElement).perform();

		if (subElement.equals("Bookings")) {
			action.moveToElement(
					driver.findElement(By
							.xpath("//*[@id='subList']/li[1]/a"))).click()
					.perform();
			//Log.info(subElement+" Displayed ");
		} else if (subElement.equals("Settings")) {
			action.moveToElement(
					driver.findElement(By
							.xpath("//*[@id='subList']/li[2]/a"))).click()
					.perform();
		} else if (subElement.equals("Logout")) {
			action.moveToElement(
					driver.findElement(By
							.xpath(".//*[@id='subList']/li[3]/a"))).click()
					.perform();
		} else {
			System.out.println("Invalid Selection");
		}

	}

}
