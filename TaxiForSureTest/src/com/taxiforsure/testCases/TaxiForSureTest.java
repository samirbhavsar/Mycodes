package com.taxiforsure.testCases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.taxiforsure.commonLib.*;
import com.taxiforsure.pagelib.*;
import com.taxiforsure.projectCommonLib.*;
import com.taxiforsure.utility.*;


//@Listeners({com.taxiforsure.utility.TestNGCustom.class})
@SuppressWarnings("unused")
//@Listeners(PdfReporterListener.class)
public class TaxiForSureTest {

	WebDriver driver;
	AirportTransferPage atp;
	HalfOrFullDayPage hop;
	ReadDataFromExcel excelRead;
	WebDriverCommonLib wlib;
	ProjectCommonUtils plib;
	Login login;
	SelectCityPage selCity;
	SelectTaxiPage selTaxi;
	ConfirmBookingPage cBookPage;
	SelectLocation selLocation;
	BookingConfirmedPage bConfirmedPage;
	NavigateToPages npages;
	OutstationPage op;
	//ChoseCity cCity;
	CallBackPage cbp;
	FairChatPage fcp;
	SoftAssert softAssert=new SoftAssert();
	BookingsPage bPage;
	// @Parameters({ "city" })
	List<String> cities=Arrays.asList("Bangalore","Mysore");
	
	@BeforeClass
	public void configBeforeClass() throws Exception {
		
		driver=Drivers.OpenBrowser();
		new BaseClass(driver);
		wlib = new WebDriverCommonLib(driver);
		excelRead = new ReadDataFromExcel();
		
		plib = new ProjectCommonUtils(driver);
		// wlib = new WebDriverCommonLib();
	//	cCity = new ChoseCity();
		fcp = PageFactory.initElements(driver, FairChatPage.class);
		login = PageFactory.initElements(driver, Login.class);
		selTaxi = PageFactory
				.initElements(driver, SelectTaxiPage.class);
		cBookPage = PageFactory.initElements(driver,
				ConfirmBookingPage.class);
		selLocation = PageFactory.initElements(driver,
				SelectLocation.class);
		bConfirmedPage = PageFactory.initElements(driver,
				BookingConfirmedPage.class);
		npages = PageFactory
				.initElements(driver, NavigateToPages.class);
		atp = PageFactory.initElements(driver,
				AirportTransferPage.class);
		cbp = PageFactory.initElements(driver, CallBackPage.class);
		// String username = ExcelUtils.getExcelData("users", 1, 0);
		// String password = ExcelUtils.getExcelData("users", 0, 1);
		hop = PageFactory.initElements(driver, HalfOrFullDayPage.class);
		op = PageFactory.initElements(driver, OutstationPage.class);
		bPage = PageFactory.initElements(driver, BookingsPage.class);
		plib.openUrl();
		//login.btn_BookTaxi.click();
		// String cityName="Bangalore";
		//cCity.SelectCity("Bangalore");
//		String cityfromExcel=ReadDataFromExcel.getExcelData("p2p", 1, 0);
//		 WebDriverCommonLib.findElementByText(SelectCityPage.allCities(), cityfromExcel).click();
		 login.cityPOPupLink.click();
		plib.loginToApp();
		Thread.sleep(10000);
		wlib.waitForWebElementFluently(selLocation.showUser());

	}
	
	
	
	/*@AfterMethod
	public void am(Method method) {
		 System.out.println("Test name: " + method.getName());      
	}*/
	
	@DataProvider(name = "p2p")
	public Object[][] getData() throws Exception {
		return excelRead.testData("p2p");
	}

	@Test(dataProvider = "p2p", priority = 1,enabled=true)
	public void pointToPointBookingTest(String city, String pickUpArea,
			String dropArea, String pickUpDate, String Stime, String taxiName,
			String pickUpAdd, String landMark, String phoneNo, String email)
			throws IOException, InterruptedException {

		// plib.loginToApp(username, password, cityName);
		// SelectCity.cityPopUp().click();
		// cCity.SelectCity(cityName);
		String cityName = SelectCityPage.cityLink().getText();
		if (!cityName.equalsIgnoreCase(city)) {
//			selLocation.cityLink().click();
//			cCity.SelectCity(city);
			//SelectCityPage.cityLink().click();
			//WebDriverCommonLib.findElementByText(SelectCityPage.allCityLinks(), city).click();
			DoOperation.click(driver, SelectCityPage.cityLink());
			DoOperation.click(driver, SelectCityPage.allCityLinks(), city);
			
			
			
		}
		Thread.sleep(3000);
		
		selLocation.pickUpArea().clear();
		// plib.loginToApp(username, password, cityName);
		// System.out.println("pick area before split:" + pickUpArea);
		String[] pickadd = pickUpArea.split(" ");
		// System.out.println(pickadd[0]);
		selLocation.pickUpArea().sendKeys(pickUpArea.split(" ")[0]);

		Thread.sleep(2000);
		
		
		//plib.pickUpList(pickUpArea);
		
		WebDriverCommonLib.findElementByText(selLocation.pickUpAreaList(), pickUpArea).click();
		
		
	//	plib.findSearchedElement(selLocation.pickUpAreaList(), pickUpArea).click();
		selLocation.dropArea().clear();
		String[] dropAdd = dropArea.split(" ");
		selLocation.dropArea().sendKeys(dropAdd[0]);
		Thread.sleep(3000);
		//plib.dropList(dropArea);
		WebDriverCommonLib.findElementByText(selLocation.dropAreaList(), dropArea).click();
		Thread.sleep(3000);
		//plib.findSearchedElement(selLocation.dropAreaList(), dropArea).click();
		selLocation.datePicker().click();
		
		plib.selectDateFromCal(DoOperation.getDate(new  Date(), 15), "/");
		//plib.selectDAte(pickUpDate);
		//plib.selectDateFromCal(pickUpDate, "/");
		Thread.sleep(3000);
		selLocation.clickOnTime().click();
		Thread.sleep(3000);
		plib.selectTime(Stime);
	//	WebDriverCommonLib.findElementByText(selLocation.selectTime(), Stime).click();
		// String time = selLocation.clickOnTime().getText();
		
		selLocation.findTaxiBtn().click();
		
		Thread.sleep(3000);
		wlib.waitForWebElementFluently(selTaxi.taxiDiv());
		// List<WebElement> taxiSel = selTaxi.carList();
		//
		//  
		// Assert.assertTrue(taxiSel.size()==0,
		// "Taxi information is not avalible");
		// File scr = ((TakesScreenshot) driver)
		// .getScreenshotAs(OutputType.FILE);
		// FileUtils.copyFile(scr, destPath);
		plib.selectTaxi(taxiName);
		Thread.sleep(3000);
		selTaxi.selectTaxiBtn().click();
		 
		Thread.sleep(3000);
		cBookPage.addressTxtArea().sendKeys(pickUpAdd);
		Thread.sleep(3000);
		cBookPage.landMarkTxtBox().sendKeys(landMark);
		Thread.sleep(3000);
		/*if (cBookPage.smsMobileTxtBox().getText().isEmpty()) {
			Assertions.fail("Mobile number is empty");
		}*/
		cBookPage.smsMobileTxtBox().clear();
		cBookPage.smsMobileTxtBox().sendKeys(phoneNo);
		Thread.sleep(3000);
		cBookPage.billToMailTxtBox().clear();
		/*if (cBookPage.billToMailTxtBox().getText().isEmpty()) {
			Assertions.fail("Email is empty");
		}*/
		cBookPage.billToMailTxtBox().sendKeys(email);
		Thread.sleep(3000);
		
		
		
		cBookPage.submitBookingBtn().click();
		
		
		
		Thread.sleep(3000);
		// 
		//Thread.sleep(3000);
		/*if (bConfirmedPage.bookingConfirmMsg().getText().contains("Confirmed")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {
			Assert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),
					"Booking id is not displayed");

		} else if (bConfirmedPage.bookingConfirmMsg().getText()
				.contains("Progress")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {

			System.out.println("Booking is not yet coonfirmed");

		}*/
		// plib.verifyText(bConfirmedPage.taxiNameTxt().getText(),
		// taxiName);
		
		
		
		ProjectCommonUtils.checkBookConfirm();
		
		//System.out.println(driver.getCurrentUrl());
//		 softAssert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),"Booking id is not displayed");
//		// softAssert.assertEquals(bConfirmedPage.pickUpAddressTxt().getText(),
//		 //pickUpAdd, "Pickup Address is not currect as given");
//		 softAssert.assertEquals(bConfirmedPage.dropAddressTxt().getText(),
//		 dropArea, "Drop Address is not currect as given");
		
		/*
		if (bConfirmedPage.pickUpAddressTxt().getText().contains(pickUpAdd)) {
			Assert.fail("Pickup Address is not currect as given");
		}
		if (bConfirmedPage.dropAddressTxt().getText().contains(dropArea)) {
			Assert.fail("Pickup Address is not currect as given");
		}*/

		/*
		 * if(bConfirmedPage.bookingConfirmMsg().getText().contains("Confirmed")
		 * && bConfirmedPage.bookingConfirmMsg().isDisplayed()){
		 * Assert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),
		 * "Booking id is not displayed");
		 * 
		 * } else{ System.out.println("Booking is not yet coonfirmed"); }
		 */
		 //softAssert.assertAll();
		//npages.homePageLink().click();
		npages.pointToPointLink().click();
		// plib.logOut();
		// } catch (Throwable e) {
		// e.printStackTrace();
		// mel.createScreenCaptureJPEG("fail");
		// File scr = ((TakesScreenshot) driver)
		// .getScreenshotAs(OutputType.FILE);
		// FileUtils.copyFile(scr, destPath);
		//
		// }

	}

	@DataProvider(name = "gotoairport")
	public Object[][] getData1() throws Exception {
		return excelRead.testData("GOP");
	}

	@Test(dataProvider = "gotoairport", priority = 2,enabled=true )
	public void goToAirportTest(String city, String aiportName, String dateto,
			String timeT, String pickArea, String taxiname,String fare, String pickAddress,
			String landMrk, String phoneNo, String email)
			throws InvalidFormatException, IOException, InterruptedException {
		// try {
		// plib.loginToApp(userName, password, cityName);
		//  

		// atp.goingToairportLink().click();
		
		
		
		Thread.sleep(3000);
		/*String cityName = selLocation.cityLink().getText();
		if (!cityName.equals(city)) {
			selLocation.cityLink().click();
			cCity.SelectCity(city);
		}*/
		
		String cityName = SelectCityPage.cityLink().getText();
		if (!cityName.equalsIgnoreCase(city)) {
//			selLocation.cityLink().click();
//			cCity.SelectCity(city);
			SelectCityPage.cityLink().click();
			Thread.sleep(2000);
			WebDriverCommonLib.findElementByText(SelectCityPage.allCityLinks(), city).click();
			
			
		}
		
		Thread.sleep(3000);
		
		npages.airportTransferLink().click();
//		if(city.equals("Mysore")){
//			 throw new SkipException("Airport Transfer is not available for  "+city);
//		}
		
		//atp.clickOnFlightLink().click();
		//Thread.sleep(2000);
		// List<WebElement> airPrtName = atp.getAllFlightList();
		// for(int i=0;i<airPrtName.size();i++){
		// Assert.assertEquals(airPrtName.get(i).getText(),
		// aiportName,"Invalid airport name");
		// break;
		// }
		
		//plib.selectAirport(aiportName);
		// Assert.assertEquals();
//		atp.airportLink.click();
//		Thread.sleep(2000);
	//	WebDriverCommonLib.findElementByText(atp.go_toAirportList, aiportName).click();
		
		selLocation.datePicker().click();
		//plib.selectDAte(dateto);
		plib.selectDateFromCal(DoOperation.getDate(new Date(), 15), "/");
		selLocation.clickOnTime().click();
		plib.selectTime(timeT);
		// cCity.SelectCity(cityName);
		String[] pickArea1 = pickArea.split(" ");
		selLocation.pickUpArea().clear();
		selLocation.pickUpArea().sendKeys(pickArea1[0]);
		Thread.sleep(3000);

		//plib.pickUpList1(pickArea);
		WebDriverCommonLib.findElementByText(atp.pickUpList(), pickArea).click();
		Thread.sleep(4000);
		selLocation.findTaxiBtn().click();
		 
		// List<WebElement> taxiSel = selTaxi.carList();
		// if(wlib.isAllertPresent()){
		// wlib.acceptAlert();
		// }
		
		//Thread.sleep(3000);
		wlib.waitForWebElementFluently(selTaxi.taxiDiv());
		
		
		Thread.sleep(4000);
		// Assert.assertTrue(taxiSel.size()==0,
		// "Taxi information is not avalible");
		// File scr = ((TakesScreenshot) driver)
		// .getScreenshotAs(OutputType.FILE);
		// FileUtils.copyFile(scr, destPath);
		
		/*// plib.selectTaxi(taxiname);
		if(city.equalsIgnoreCase("Bangalore")){
			plib.selectTaxi(city, taxiname, fare);
		}else{*/
			plib.selectTaxi(taxiname);
			Thread.sleep(3000);
		selTaxi.selectTaxiBtn().click();
		// 
		Thread.sleep(3000);
		atp.addressTxtArea().sendKeys(pickAddress);
		Thread.sleep(3000);
		cBookPage.landMarkTxtBox().sendKeys(landMrk);
		Thread.sleep(3000);
		cBookPage.smsMobileTxtBox().clear();
		cBookPage.smsMobileTxtBox().sendKeys(phoneNo);
		Thread.sleep(3000);
		cBookPage.billToMailTxtBox().clear();
		cBookPage.billToMailTxtBox().sendKeys(email);
		Thread.sleep(3000);
		cBookPage.submitBookingBtn().click();
//		if(wlib.isAllertPresent()){
//			wlib.acceptAlert();
//		}
		 
		Thread.sleep(3000);
		ProjectCommonUtils.checkBookConfirm();
	/*	if (bConfirmedPage.bookingConfirmMsg().getText().contains("Confirmed")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {
			softAssert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),
					"Booking id is not displayed");

		} else if (bConfirmedPage.bookingConfirmMsg().getText()
				.contains("Progress")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {

			softAssert.fail("Booking is not yet coonfirmed");
		}*/
		// Assert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),"Booking id is not available");
		/*if (bConfirmedPage.pickUpAddressTxt().getText().contains(pickAddress)) {
			softAssert.fail("Pickup Address is not currect as given");
		}*/
		
		
		
		// Assert.assertEquals(bConfirmedPage.airportName().getText(),
		// aiportName, "Airport name is not currect as given");
		// plib.logOut();
		// } catch (Throwable e) {
		// e.getMessage();
		// // wlib.takeScreensort();
		// //createScreenCaptureJPEG(String filename)
		// File scr = ((TakesScreenshot) driver)
		// .getScreenshotAs(OutputType.FILE);
		// FileUtils.copyFile(scr, destPath1);
		//
		// }

	}

	@DataProvider(name = "commingFrmAirport")
	public Object[][] getTestData() throws Exception {
		return excelRead.testData("CFA");
	}

	@Test(dataProvider = "commingFrmAirport", priority = 3,enabled=true )
	public void commingFromAirportTest(String city, String aiportName,
			String dateto, String time, String dropArea, String taxiname,String fare,
			String phoneNo, String email) throws InvalidFormatException,
			IOException, InterruptedException {
		// try {
		// npages.airportTransferLink().click();
		// plib.loginToApp(userName, password, cityName);
		 
		
		Thread.sleep(3000);
		/*String cityName = selLocation.cityLink().getText();
		if (!cityName.equals(city)) {
			selLocation.cityLink().click();
			cCity.SelectCity(city);
		}*/
		
		String cityName = SelectCityPage.cityLink().getText();
		if (!cityName.equalsIgnoreCase(city)) {
//			selLocation.cityLink().click();
//			cCity.SelectCity(city);
			SelectCityPage.cityLink().click();
			Thread.sleep(2000);
			WebDriverCommonLib.findElementByText(SelectCityPage.allCityLinks(), city).click();	
		}
		
		
		
		Thread.sleep(3000);
		
		npages.airportTransferLink().click();
//		if(city.equals("Mysore")){
//			 throw new SkipException("Airport Transfer is not available for  "+city);
//		}
		Thread.sleep(3000);
		atp.comingFrmAirportLink().click();
		Thread.sleep(3000);
		//atp.flightLink2().click();
		//plib.selectAirport(aiportName);
		selLocation.datePicker().click();
		//plib.selectDAte(dateto);
		plib.selectDateFromCal(DoOperation.getDate(new Date(), 15), "/");
		selLocation.clickOnTime().click();
		plib.selectTime(time);
		String[] dropArea1 = dropArea.split(" ");
		selLocation.dropArea().clear();
		selLocation.dropArea().sendKeys(dropArea1[0]);
		Thread.sleep(3000);
		//plib.dropList1(dropArea);
		WebDriverCommonLib.findElementByText(atp.dropList1(), dropArea).click();
		Thread.sleep(4000);
		selLocation.findTaxiBtn().click();
		
		wlib.waitForWebElementFluently(selTaxi.taxiDiv());
		 
		Thread.sleep(5000);
		
		 
		 assertThat("Taxilist is missing ",!selTaxi.carList().isEmpty());
		 
		 
		 
		 
		 
		 /*if(taxiSel.size()==0){
			 softAssert.fail("Taxi list is not displayed");
		 }*/
		// if(wlib.isAllertPresent()){
		// wlib.acceptAlert();
		// }
		//  
		// Assert.assertTrue(taxiSel.size()==0,
		// "Taxi information is not avalible");
		// File scr = ((TakesScreenshot) driver)
		// .getScreenshotAs(OutputType.FILE);
		// FileUtils.copyFile(scr, destPath);
		/*if(city.equalsIgnoreCase("Bangalore")){
			plib.selectTaxi(city, taxiname, fare);
		}else{*/
			plib.selectTaxi(taxiname);
		Thread.sleep(3000);
		
		selTaxi.selectTaxiBtn().click();
		 
		Thread.sleep(3000);
		cBookPage.smsMobileTxtBox().sendKeys(phoneNo);
		cBookPage.billToMailTxtBox().clear();
		Thread.sleep(3000);
		cBookPage.billToMailTxtBox().sendKeys(email);
		Thread.sleep(3000);
		// atp.flightNumbTxt().sendKeys(flightNo);
		cBookPage.submitBookingBtn().click();
//		if(wlib.isAllertPresent()){
//			wlib.acceptAlert();
//		}
		 
		// plib.logOut(); npages.airportTransferLink().click();
		// atp.comingFrmAirportLink().click();

		/*if (bConfirmedPage.bookingConfirmMsg().getText().contains("Confirmed")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {
			Assert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),
					"Booking id is not displayed");

		} else if (bConfirmedPage.bookingConfirmMsg().getText()
				.contains("Progress")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {

			System.out.println("Booking is not yet coonfirmed");
		}

		if (bConfirmedPage.pickUpAddressTxt().getText().contains(dropArea)) {
			Assert.fail("Pickup Address is not currect as given");
		}
		// Assert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),"Booking id is not displayed");
		// Assert.assertEquals(bConfirmedPage.pickUpAddressTxt().getText(),
		// dropArea, "drop Address is not currect as given");
		if (bConfirmedPage.airportName().getText().contains(aiportName)) {
			// Assert.assertEquals(bConfirmedPage.airportName().getText(),aiportName,
			// "Airport name is not currect as given");
			Assert.fail("Pickup Address is not currect as given");
		}*/
		Thread.sleep(3000);
		ProjectCommonUtils.checkBookConfirm();
		Thread.sleep(2000);
		npages.airportTransferLink().click();
		// } catch (Throwable e) {
		// e.printStackTrace();
		//
		// File scr = ((TakesScreenshot) driver)
		// .getScreenshotAs(OutputType.FILE);
		// FileUtils.copyFile(scr, destPath2);
		//
		// }
	}

	@DataProvider(name = "HalfFull")
	public Object[][] getData2() throws Exception {
		return excelRead.testData("HoF");
	}

	@Test(dataProvider = "HalfFull", priority = 4, enabled = true)
	public void HalfDayOrFullDayTest(String city, String packageType,
			String pickArea,String dropArea, String dateto, String time, String taxiName,
			String pickAddress, String landMark, String mobileNo, String email)
			throws InterruptedException, IOException, InvalidFormatException {
		// try {
		
	//	Thread.sleep(3000);
		/*String cityName = selLocation.cityLink().getText();
		if (!cityName.equals(city)) {
			selLocation.cityLink().click();
			cCity.SelectCity(city);
		}*/
		//Added 
		//String cityName = SelectCityPage.cityLink().getText();
		if (!SelectCityPage.cityLink().getText().equalsIgnoreCase(city)) {
//			selLocation.cityLink().click();
//			cCity.SelectCity(city);
			SelectCityPage.cityLink().click();
			Thread.sleep(2000);
			WebDriverCommonLib.findElementByText(SelectCityPage.allCityLinks(), city).click();	
		}
		Thread.sleep(2000);
		npages.halfOrFullDayLink().click();
		Thread.sleep(3000);
		assertThat("Package type is not displayed",wlib.isElementPresent(hop.packageTxtBox()));
		hop.packageTxtBox().click();
		//Thread.sleep(3000);
		//WebDriverCommonLib.findElementByText(hop.getPackageList(),packageType);
		Thread.sleep(3000);
		plib.selectPackage(packageType);
		String[] piCkar = pickArea.split(" ");
		
		//softAssert.assertTrue(hop.pickHalfFullArea().isDisplayed(), "PickUp textbox is not displayed");
		assertThat("PickUp textbox is not displayed", hop.pickHalfFullArea().isDisplayed());
		hop.pickHalfFullArea().clear();
		hop.pickHalfFullArea().sendKeys(piCkar[0]);
		Thread.sleep(3000);
		
		
		//plib.pickUpList(pickArea);
		WebDriverCommonLib.findElementByText(selLocation.pickUpAreaList(), pickArea).click();;
		Thread.sleep(3000);
//		selLocation.dropArea().clear();
//		String[] dropAdd = dropArea.split(" ");
		
	//	softAssert.assertTrue(selLocation.dropArea().isDisplayed(), "Drop area is not displayed");
		//assertThat("DropArea textbox is not displayed", selLocation.dropArea().isDisplayed());
	//	selLocation.dropArea().sendKeys(dropAdd[0]);
		Thread.sleep(3000);
		//plib.dropList(dropArea);

		selLocation.datePicker().click();
		//plib.selectDAte(dateto);
		plib.selectDateFromCal(DoOperation.getDate(new Date(), 15), "/");
		selLocation.clickOnTime().click();
		Thread.sleep(3000);
		plib.selectTime(time);
		selLocation.findTaxiBtn().click();
		wlib.waitForWebElementFluently(selTaxi.taxiDiv());
		
		 
		// if(wlib.isAllertPresent()){
		// wlib.acceptAlert();
		// }
//		 List<WebElement> taxiSel = selTaxi.carName();
		 //System.out.println(taxiSel.size());
		 assertThat("Taxilist is missing ",!selTaxi.carName().isEmpty());
		 
		 /*if(taxiSel.size()==0){
			 softAssert.fail("Taxilist is not displayed");
		 }*/
		//
		//  
		//
		//
		// Assert.assertTrue(taxiSel.size()==0,
		// "Taxi information is not avalible");
		//
		plib.selectTaxi(taxiName);
		Thread.sleep(3000);
		selTaxi.selectTaxiBtn().click();
		Thread.sleep(3000);
		 
		cBookPage.addressTxtArea().sendKeys(pickAddress);
		Thread.sleep(3000);
		cBookPage.landMarkTxtBox().sendKeys(landMark);
		Thread.sleep(3000);
		cBookPage.smsMobileTxtBox().clear();
		Thread.sleep(3000);
		cBookPage.smsMobileTxtBox().sendKeys(mobileNo);
		cBookPage.billToMailTxtBox().clear();
		
		cBookPage.billToMailTxtBox().sendKeys(email);
		Thread.sleep(3000);
		if (!selLocation.showUser().isDisplayed()) {
			bConfirmedPage.askLoginBtn().click();
			plib.loginToApp();

		}
		
		
		 
		Thread.sleep(4000);
		cBookPage.submitBookingBtn().click();
		 
		ProjectCommonUtils.checkBookConfirm();
		
		// wlib.verifyText(actualText, expectedText);
		// plib.verifyText(bConfirmedPage.pickUpAddressTxt().getText(),
		// pickArea);

		/*if (bConfirmedPage.bookingConfirmMsg().getText().contains("Confirmed")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {
			Assert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),
					"Booking id is not displayed");

		} else if (bConfirmedPage.bookingConfirmMsg().getText()
				.contains("Progress")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {

			System.out.println("Booking is not yet coonfirmed");
		}
		if (bConfirmedPage.pickUpAddressTxt().getText().contains(pickAddress)) {
			Assert.fail("Pickup Address is not currect as given");
		}*/
		// Assert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),"Booking id is not displayed");
		// Assert.assertEquals(bConfirmedPage.pickUpAddressTxt().getText(),
		// pickAddress, "Pickup Address is not currect as given");
		npages.halfOrFullDayLink().click();

	}

	@DataProvider(name = "outstation")
	public Object[][] getData3() throws Exception {
		return excelRead.testData("outstation");
	}

	@Test(dataProvider = "outstation", priority = 5,enabled=true)
	public void outstationBookingTest(String city, String toPlace,
			String departDate,String trip, String returnDate, String taxiName,String time,
			String pickAddress, String landMrk, String mobileNo, String email)
			throws InterruptedException, IOException {

		// try {
		
		Thread.sleep(3000);
		/*String cityName = selLocation.cityLink().getText();
		if (cityName != city) {
			selLocation.cityLink().click();
			cCity.SelectCity(city);
		}*/
		
		if (!SelectCityPage.cityLink().getText().equals(city)) {
//			selLocation.cityLink().click();
//			cCity.SelectCity(city);
			SelectCityPage.cityLink().click();
			Thread.sleep(2000);
			WebDriverCommonLib.findElementByText(SelectCityPage.allCityLinks(), city).click();	
		}
		Thread.sleep(3000);
		npages.outstationLink().click();
		Thread.sleep(3000);
		//plib.selectTrip(trip, returnDate, city);
		plib.selectTripOutStation(trip, DoOperation.currentDatePlus(15));
		Thread.sleep(3000);
		String[] drpArea = toPlace.split(",");
		selLocation.dropArea().clear();
		selLocation.dropArea().sendKeys(drpArea[0]);
		Thread.sleep(3000);

	
		
		WebDriverCommonLib.findElementByText(selLocation.dropAreaList(), toPlace).click();
		Thread.sleep(3000);
		
		selLocation.datePicker().click();
		//plib.selectDAte(departDate);
		plib.selectDateFromCal(departDate, "/");
		Thread.sleep(2000);
		
		//Added for one way and round trip
		
		/*for(int i=0;i<cities.size();i++){
			if(cities.get(i).equalsIgnoreCase(cityName)){
				op.interCityOneWay().click();
			}
		}
		
		op.datePicker2().click();
		op.calendarNxt().click();
		plib.selectDAte(returnDate);*/
		
		
		Thread.sleep(3000);
		selLocation.findTaxiBtn().click();
		wlib.waitForWebElementFluently(selTaxi.taxiDiv());
		
		Thread.sleep(3000);
		
		selTaxi.selectTaxiBtn().click();
		Thread.sleep(3000);
		selLocation.clickOnTime().click();
		// plib.selectTime();
		 WebDriverCommonLib.findElementByText(selLocation.selectTime(), time).click();
		cBookPage.addressTxtArea().sendKeys(pickAddress);
		Thread.sleep(3000);
		// cBookPage.landMarkTxtBox().sendKeys(landMrk);
		op.landMarkOutstation().sendKeys(landMrk);
		Thread.sleep(3000);
		cBookPage.smsMobileTxtBox().clear();
		cBookPage.smsMobileTxtBox().sendKeys(mobileNo);
		Thread.sleep(3000);
		cBookPage.billToMailTxtBox().clear();
		cBookPage.billToMailTxtBox().sendKeys(email);
		Thread.sleep(3000);
		
		cBookPage.submitBookingBtn().click();
		 
		Thread.sleep(3000);
		ProjectCommonUtils.checkBookConfirm();
		
		/*
		if (bConfirmedPage.bookingConfirmMsg().getText().contains("Confirmed")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {
			Assert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),
					"Booking id is not displayed");

		} else if (bConfirmedPage.bookingConfirmMsg().getText()
				.contains("Progress")
				&& bConfirmedPage.bookingConfirmMsg().isDisplayed()) {

			System.out.println("Booking is not yet coonfirmed");
		}
		// Assert.assertTrue(bConfirmedPage.bookingIdTxt().isDisplayed(),"Booking id is not displayed");
		if (bConfirmedPage.pickUpAddressTxt().getText().contains(pickAddress)) {
			Assert.fail("Pickup Address is not currect as given");
		}*/
		// wlib.verifyText(op.dropArea().getText(), dropPlace);

		npages.outstationLink().click();
		

	}

	
	
	@Test( priority=6,enabled=false)
	public void cityCheckTest() throws Exception{
		List<String>  cities=ReadDataFromExcel.getExcelData("citylist");
		cbp.callBackLnk().click();
		
		cbp.cityDropDown().click();
		List<WebElement> cityList=cbp.cityList();
		
		for(int i=0;i<cityList.size();i++){
			
			if(cityList.get(i).getText().equals(cityList.get(i))){
				System.out.println(cityList.get(i)+" Present");
			}else{
				softAssert.fail(cityList.get(i)+" not present");
			}
	
//			softAssert.assertEquals(cityList.get(i).getText(),cities.get(i),"Found "+cityList.get(i).getText()+" Expected "+cities.get(i));
//			softAssert.assertAll();
			//assertThat(cityList.get(i).getText(),is(equalTo(cities.get(i))));
			
			//assertThat(cityList.get(i).getText(), is(cities.get(i));
			
		}
		cbp.closeWindow().click();
		
		
		
	}
	/*
	@Test(priority=7,enabled=false)
	public void fairChartTest() throws InvalidFormatException, IOException{
		
		
		List<String> citiList=ReadDataFromExcel.getExcelData("citylist");
		fcp.fareChartLink().click();
		fcp.cityDropDwon().click();
		List<WebElement> cities=fcp.cityList();
		for(int i=0;i<cities.size();i++){
			
//			softAssert.assertEquals(cityList.get(i).getText(),cities.get(i),"Found "+cityList.get(i).getText()+" Expected "+cities.get(i));
//			softAssert.assertAll();
			//assertThat(cities.get(i).getText(),is(equalTo(citiList.get(i))));
			
			if(cities.get(i).getText().equals(citiList.get(i))){
				System.out.println(citiList.get(i)+" is present ");
			}else{
				softAssert.fail(citiList.get(i)+"not present");
			}
			
			//assertThat(cityList.get(i).getText(), is(cities.get(i));
			
		}
		fcp.closeWindow().click();
		
	}*/
	
	
	
	
	@Test(priority=8,enabled=false)
	public void bookingsTest() throws InvalidFormatException, IOException{
		
		CommonPage.gotoBookings();
		plib.confirmedBookings();
		//plib.inProgressBookings();
	}
	@Test(enabled=false)
	public void cancelBookingTest(){
		
		CommonPage.gotoBookings();
		//bPage.txtBx_SearchBooking().sendKeys("TFS-IC-C3067845");
		
		wlib.waitForWebElementFluently(bPage.bookingDiv());
		bPage.cancelBookingIcon().click();
		if(wlib.isAllertPresent()){
			wlib.acceptAlert();
			wlib.acceptAlert();
		}
		
		
	}
	
	@Test(enabled=false)
	public void editBookingTest(String city, String pickUpArea,
			String dropArea, String pickUpDate, String Stime, String taxiName,
			String pickUpAdd, String landMark, String phoneNo, String email) throws InterruptedException{
		
		DoOperation.mouseOverAction(driver, login.myAccount, login.bookingsLink);
		
		Thread.sleep(3000);
		
		DoOperation.click(driver, bPage.editBookingIkon);
		Thread.sleep(3000);
		
		DoOperation.type(driver, selLocation.pickUpArea(), pickUpArea.split(" ")[0]);
		Thread.sleep(3000);
		DoOperation.click(driver, selLocation.pickUpAreaList(), pickUpArea);
		Thread.sleep(3000);
		DoOperation.type(driver, selLocation.pickUpArea(), dropArea.split(" ")[0]);
		Thread.sleep(3000);
		DoOperation.click(driver, selLocation.dropAreaList(), dropArea);
		Thread.sleep(3000);
		plib.selectDateFromCal(pickUpDate, "/");
		Thread.sleep(3000);
		
		DoOperation.click(driver, selLocation.findTaxiBtn());
		wlib.waitForWebElementFluently(selTaxi.taxiDiv());
		DoOperation.click(driver, selTaxi.selectTaxiBtn());
		Thread.sleep(3000);
		DoOperation.click(driver, cBookPage.submitBookingBtn());
	}
	
	@Test(enabled=false)
	public void viewBillTest() throws InterruptedException{
		
		CommonPage.gotoBookings();
		bPage.btn_PastBooking().click();
		bPage.filterBox().click();
		bPage.clickAllTrips().click();
		Thread.sleep(3000);
		WebDriverCommonLib.findElementByText(bPage.allTrips(), "Completed").click();
		Thread.sleep(3000);
			
				bPage.btn_ViewBill().click();
				
			
			
			Set<String> allWindows=driver.getWindowHandles();
			Iterator<String> it=allWindows.iterator();
			String parentWindow=it.next();
			String childWindow=it.next();
			if(!allWindows.isEmpty()){
			driver.switchTo().window(childWindow);
			Thread.sleep(5000);
			
			System.out.println(driver.getTitle());
			driver.close();
			driver.switchTo().window(parentWindow);
			
			
		}
	}
	
	@Test(dependsOnMethods={"pointToPointBookingTest"})
	public void bookReturnPointToPoint(){
		DoOperation.click(driver, bConfirmedPage.btn_BookReturn);
		
	}
	
	@Test(enabled=false)
	public void getTotalBookingsTest() throws InterruptedException{
		
	CommonPage.gotoBookings();
	bPage.btn_PastBooking().click();
	int count=0;
	while(bPage.showMoreResults().isDisplayed()){
		bPage.showMoreResults().click();
		count=bPage.pastBookingIds().size();
		
	}
	
	System.out.println(count);
	
	}
	/*
	@AfterMethod
	public void onTestFailure(ITestResult result){
		if(!result.isSuccess()){
		Drivers.takeScreenShot(result, driver);
		}
	}*/
	 
	@AfterClass
	public void showBookings() throws IOException{
		plib.viewBookingId();
	}
	
	@AfterSuite
	public void afterClassConfig() throws IOException {
		
		CommonPage.logout();
		//driver.quit();
		
		//CommonPage.logout();
		//plib.logOut();
		//driver.quit();
		//wlib.openReport();

	}

}
