package com.taxiforsure.regressionTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.taxiforsure.actions.DoOperation;
import com.taxiforsure.actions.ProjectCommmonMethods;
import com.taxiforsure.driver.MyDriver;
import com.taxiforsure.pageObjects.BookingSummeryPage;
import com.taxiforsure.pageObjects.ConfirmedBookingPage;
import com.taxiforsure.pageObjects.Login;
import com.taxiforsure.pageObjects.SelectLocation;
import com.taxiforsure.pageObjects.TaxiPage;
import com.taxiforsure.utils.ExcelUtils;
import com.taxiforsure.utils.ReadPropertiesFile;

public class TaxiforSureBookingsTest {
	WebDriver driver;
	
	ProjectCommmonMethods pcm=new ProjectCommmonMethods();
	// ProjectCommmonMethods pcm;
	@BeforeClass
	public void setup() {
		driver = new MyDriver().getDriver();
		
		// pcm=new ProjectCommmonMethods(driver);
	}



	@Test(priority = 1,enabled=false)
	public void loginTest() throws InterruptedException {

		new Login(driver).doLogin();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
	
	@DataProvider(name = "p2p")
	public Object[][] getData2() throws Exception {
		return ExcelUtils.testData("P2P");
	}
	
	@Test(priority = 2, enabled = true,dataProvider="p2p")
	public void pointToPointTest(String city_Name, String pick_Area,
			String dropArea, String time, String taxi_Name,
			 String contactNo,
			String email) throws InterruptedException {

		new SelectLocation(driver).doSelectCity(city_Name);
		new SelectLocation(driver).doSelectLocationsPointToPoint(
				pick_Area, dropArea,
				 time);
		new TaxiPage(driver).selectTaxi(taxi_Name);
		new ConfirmedBookingPage(driver).doTypeAddress(contactNo,
				email);
		
		

	}

	@Test(priority = 3, enabled = false)
	public void goToAirportTest() throws InterruptedException {

		new SelectLocation(driver).doSelectCity("Bangalore");
		new SelectLocation(driver).doGoTo_AirportTransfer(DoOperation.getDate(new Date(), 15), "8:30 p.m.","Kumara Park East");
		new TaxiPage(driver).selectTaxi("Sedan");
		new ConfirmedBookingPage(driver).doTypeAddressToAirport("9999999999", "sabyasachi2189@gmail.com");
	}

	@Test(priority = 4, enabled = false)
	public void fromAirportTest() throws InterruptedException {

		new SelectLocation(driver).doSelectCity("Bangalore");
		new SelectLocation(driver).doFrom_AirportTransfer(
				DoOperation.getDate(new Date(), 15), "8:30 p.m.","Kumara Park East");
		new TaxiPage(driver).selectTaxi("Sedan");
		new ConfirmedBookingPage(driver).doTypeAddressFromAirport("9999999999","sabyasachi2189@gmail.com");
		pcm.checkBookConfirm(driver);
	}

	@Test(priority = 5, enabled = false)
	public void packageTest() throws InterruptedException {
		new SelectLocation(driver).doSelectCity("Bangalore");
		new SelectLocation(driver).doSelectPackageAndLocations("8hrs 80Kms",
				"Kumara Park East", DoOperation.getDate(new Date(), 15),
				"8:30 p.m.");
		new TaxiPage(driver).selectTaxi("Sedan");
		new ConfirmedBookingPage(driver).doTypeAddressPkg(
				"9999999999", "sabyasachi2189@gmail.com");

		pcm.checkBookConfirm(driver);

	}

	@Test(priority = 5,enabled=false)
	public void outstationTest() throws InterruptedException {

		new SelectLocation(driver).doSelectCity("Bangalore");
		new SelectLocation(driver).doSelectLocOutstation("round","Coorg,Karnataka");
		new TaxiPage(driver).selectTaxi("Sedan");
		new ConfirmedBookingPage(driver).doTypeAddressPkg("9999999999", "sabyasachi2189@gmail.com");
		pcm.checkBookConfirm(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void onTestFailure(ITestResult result) {

		if (!result.isSuccess()) {
			new MyDriver().takeScreenShot(result, driver);
		}
	}

	@AfterClass
	public void tearDown() throws IOException {
		 pcm.showBookingId();

		// new Login(driver).doLogout();

		// new MyDriver().quitBrowser(driver);
	}

}
