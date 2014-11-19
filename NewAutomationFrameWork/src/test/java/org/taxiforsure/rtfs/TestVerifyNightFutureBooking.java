package org.taxiforsure.rtfs;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.taxiforsure.answercall.AnswerCallScreen;
import org.taxiforsure.assertion.Assertion;
import org.taxiforsure.bookingdetails.BookingDetailsScreen;
import org.taxiforsure.bookingtypes.BookingTypes;
import org.taxiforsure.notifications.NotificationsScreen;
import org.taxiforsure.rtfs.login.Login;
import org.taxiforsure.rtfscommonelements.CommonElements;
import org.taxiforsure.utils.ReadPropertiesFile;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.taxiforsure.driver.MyDriver;

public class TestVerifyNightFutureBooking
{

   WebDriver driver;
   String city = null;
   String cabType = null;
   String bookingTypeValue = null;
   String pickUpArea;
   String dropArea = "";
   String setDate;
   String priceAPIURL = "http://api1.rtfs.in/pricingV2/api/pricingV2/postRequest";
   String bookingType = null;
   String customerInfoAPIURL = null;
   String cityLimits = null;
   String dateForDynamicCabs = null;
   String dropCity = null;
   String cabTypeValue = null;
   String data = null;
   
   public TestVerifyNightFutureBooking(String data)
   {
	   this.data = data;
   }
   
   @BeforeMethod
   public void setup()
   {
	  driver = new MyDriver().getDriver();
   }

   @Test()
   public void testVerifyHatchBackNight()
   {
	  
	  String individualData[] = data.split("_");

	  this.city = individualData[0];
	  bookingType = individualData[1];
	  bookingTypeValue = individualData[2];
	  cabTypeValue = individualData[3];
	
	  String customerNameFromAnsCal = new AnswerCallScreen(driver).getCustomerName();
	  String custPhNo = new AnswerCallScreen(driver).getCustomerPhoneNo();
	  String pickUpAddress = new AnswerCallScreen(driver).getPickUpAddress();
	  
	  if(bookingType.equals("Airport Transfer"))
	  {
		 throw new SkipException("Skipping the flow since airport transfer is not yet impletmented");
	  }

	  /** This sets booking type for railway tranfer and changes booking type value got from the application from rt to p2p */
	  if (bookingType.contains("Railway Transfer"))
	  {
		 bookingTypeValue = "p2p";
	  }

	  /** Logging into the application and navigating to answer call page */
	  new Login(driver).doLogin("vinay.b", "vin_1984");
	  new NotificationsScreen(driver).selectCity(city);
	  new CommonElements(driver).clickAnswerCallLink();

	  /** Setting phone num, booking type, city, night travel, cab type */
	  new AnswerCallScreen(driver).setPhoneNumber(custPhNo);
	  new AnswerCallScreen(driver).selectBookingType(bookingType);
	  
	  pickUpArea = new AnswerCallScreen(driver).getpickUpLocality(city, bookingType);
	  dropArea = new AnswerCallScreen(driver).getDropLocality(city, bookingType);

	  /** Verifying if airport pick up and drop locations are displayed */
	  if (bookingType.contains("Airport Transfer"))
	  {
		 String url = new AnswerCallScreen(driver).getAirportNameAPIURL(city);
		 boolean airportNameIsDisplayed = new AnswerCallScreen(driver).checkAirportNameDispalyed(url);
		 new Assertion().assertTrue(airportNameIsDisplayed, "The airport name displayed in the application does not match the airport name in the airport api");
	  }
	  
	  /** Setting night travel */
	  setDate = new AnswerCallScreen(driver).setNightTravelAndGetTheSetDate("11:00");

	  /** Selecting the trip type */
	  if (bookingType.equalsIgnoreCase("Outstation"))
	  {
		new AnswerCallScreen(driver).setDataForOutstationTravel(city, pickUpArea, 1);
		new AnswerCallScreen(driver).setNightTravelAndGetTheSetDate("11:00");
	  } else if (bookingType.equalsIgnoreCase("Intercity"))
	  {
		 dropCity = new AnswerCallScreen(driver).setDataForInterCity(pickUpArea,1);
	  }else if(bookingType.contains("Airport Transfer"))
	  {
		 
   	  }else
	  {
   		new AnswerCallScreen(driver).setPickUpAndDropArea(city, bookingType, pickUpArea, dropArea);

	  }

	  /** Selecting the cab type for the trip */
	  new AnswerCallScreen(driver).selectCabType(cabTypeValue);

	  /** Ensuring there is a cab available even if no pick up message is displayed */
	  if (new AnswerCallScreen(driver).isNoPickUpAndDropServiceElementDisplayed())
	  {
		 if (bookingType.equalsIgnoreCase("Outstation"))
		 {
			new AnswerCallScreen(driver).setDataForOutstationTravel(city, pickUpArea, 1);
			new AnswerCallScreen(driver).setNightTravelAndGetTheSetDate("11:00");
		 } else if (bookingType.equalsIgnoreCase("Intercity"))
		 {
			 dropCity = new AnswerCallScreen(driver).setDataForInterCity(pickUpArea,1);
		 } else
		 {
			new AnswerCallScreen(driver).setPickUpAndDropArea(city, bookingType, pickUpArea, dropArea);
		 }
		 new AnswerCallScreen(driver).selectCabType(cabTypeValue);
	  }

	  /** Attempting to book cab */
	  new AnswerCallScreen(driver).clickTakeBookingButton();


	  /** Ensuring cab is available for the selected trip type and locations */
	  boolean cabIsAvaialable = false;
	  do
	  {
		 try
		 {
			cabIsAvaialable = new AnswerCallScreen(driver).checkForCabAvailability(bookingType, pickUpArea, cabTypeValue);
		 }catch(Throwable t){}
		 if (cabIsAvaialable == true)
		 {
			break;
		 }

		 if (cabIsAvaialable == false)
		 {
			new AnswerCallScreen(driver).setPhoneNumber(custPhNo);
			new AnswerCallScreen(driver).selectBookingType(bookingType);

			setDate = new AnswerCallScreen(driver).setNightTravelAndGetTheSetDate("11:00");
			

			if (bookingType.equalsIgnoreCase("Outstation"))
			{
			   new AnswerCallScreen(driver).setDataForOutstationTravel(pickUpArea);
			   new AnswerCallScreen(driver).setNightTravelAndGetTheSetDate("11:00");
			} else if (bookingType.equalsIgnoreCase("Intercity"))
			{
			   dropCity = new AnswerCallScreen(driver).setDataForInterCity(pickUpArea);

			} else
			{
			   new AnswerCallScreen(driver).setPickUpAndDropArea(city, bookingType, pickUpArea, dropArea);

			}
			new AnswerCallScreen(driver).selectCabType(cabTypeValue);
			new AnswerCallScreen(driver).clickTakeBookingButton();
			cabIsAvaialable = new AnswerCallScreen(driver).checkForCabAvailability(bookingType, pickUpArea, cabTypeValue);

		 }// End of iscabavailable
	  } while (cabIsAvaialable == false);


	  /** Getting the cab type, city short name which has to be passed as an argument for pricing api */
	  
	  ArrayList<Object> cityShortName = new AnswerCallScreen(driver).getCityShortName(city);
	  String setCityShortName = (String) cityShortName.get(0);

	  String stringForPriceAPI = null;
	  String stringForIntercityPriceAPI = null;

	  /** Getting the pick up area, drop area and the set date to be passed in price api */
	

	  /** Setting proper parameters for price api string */
	  if (bookingType.equalsIgnoreCase("Outstation"))
	  {
		 cityLimits = "1";
		 dropArea = " ";

	  } else if (bookingType.equalsIgnoreCase("Intercity"))
	  {
		 cityLimits = "1";
	  } else
	  {
		 cityLimits = new AnswerCallScreen(driver).getCityLimit(city, pickUpArea, dropArea, bookingType);
	  }

	  /** Building the final price api string */
	  if (bookingType.contains("hrs") & city.contains("Bangalore"))
	  {
		 dropArea = "";
		 stringForPriceAPI = new AnswerCallScreen(driver).getPriceApiString(setCityShortName, cabTypeValue, bookingTypeValue, setDate, "23:00", pickUpArea, dropArea, cityLimits);
		 System.out.println(stringForPriceAPI);
	  } else if (!(bookingType.equalsIgnoreCase("Intercity")))
	  {
		 stringForPriceAPI = new AnswerCallScreen(driver).getPriceApiString(setCityShortName, cabTypeValue, bookingTypeValue, setDate, "23:00", pickUpArea, dropArea, cityLimits);
		 System.out.println(stringForPriceAPI);

	  } else if (bookingType.equalsIgnoreCase("Intercity"))
	  {
		 stringForIntercityPriceAPI = new AnswerCallScreen(driver).getPriceAPIForIntercity(setCityShortName, cabTypeValue, bookingTypeValue, setDate, "23:00", city, dropCity, cityLimits);
	  }

	  /** Getting the id of the tariff table and extra km fare */
	  List<Object> id = null;
	  List<Object> ekf = null;
	  if (!(bookingType.equalsIgnoreCase("Intercity")))
	  {
		 id = new AnswerCallScreen(driver).getValuesFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "id");
		 ekf = new AnswerCallScreen(driver).getValuesFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "nightExtraKmFare");
	  } else if (bookingType.equalsIgnoreCase("Intercity"))
	  {
		 id = new AnswerCallScreen(driver).getValuesFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "id");
		 ekf = new AnswerCallScreen(driver).getValuesFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "nightExtraKmFare");
	  }

	  /** Verifying if the price from the api and the tariff table matches */
	  boolean isFareMatching = new AnswerCallScreen(driver).isPriceMatching(id, ekf, bookingTypeValue, city);
	  new Assertion().assertTrue(isFareMatching, "The price from API does not match with price in the application");

	  /** Getting pick up locality for the final time even if the pick up area changes */
	 
	  
	  /** Taking the booking by taking final inputs */
	  new AnswerCallScreen(driver).clickTakeBookingButton();
	  new AnswerCallScreen(driver).setPickUpAdress(pickUpAddress);
	  new AnswerCallScreen(driver).setLandMark();
	  new AnswerCallScreen(driver).setCustomerName(customerNameFromAnsCal);
	  new AnswerCallScreen(driver).setEmail();
	  new AnswerCallScreen(driver).selectFoundUsThrough();
	  new AnswerCallScreen(driver).selectGender();
	  new AnswerCallScreen(driver).clickConfrimButton();

	  /** Getting the booking id from the application and Data base */
	  String bookingIdFromApplication = new BookingDetailsScreen(driver).getBookingID();
	  System.out.println(city + " _" + bookingIdFromApplication);
	  String bookingIdFromDB = new BookingDetailsScreen(driver).getBookingIdFromDB(custPhNo);

	  /** Asserting booking id */
	  new Assertion().assertString(bookingIdFromDB, bookingIdFromApplication, "The expected booking id from application " + "'" + bookingIdFromApplication + "'"
			+ " does not match with booking id from db " + "'" + bookingIdFromDB + "'");
	  customerInfoAPIURL = null;
	  customerInfoAPIURL = new ReadPropertiesFile().getValue("Config.properties", "url");
	  customerInfoAPIURL = customerInfoAPIURL + "/api/consumer-app/booking-info/?booking_id=" + bookingIdFromDB;
	  
	  String customerInfoJson = new BookingDetailsScreen(driver).getBookingDetailsJson(customerInfoAPIURL);

	  /** Asserting customer name */
	 
	  String customerNameFromConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "customer_name");

	  new Assertion().assertString(customerNameFromAnsCal, customerNameFromConfPage, "The customer name in answer call page " + "'" + customerNameFromAnsCal + "'"
			+ " does not match with customer name from confirmation page " + "'" + customerNameFromConfPage + "'");

	  /** Asserting pick up address */
	 
	  String pickUpAddressFromConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "pickup_address");

	  new Assertion().assertString(pickUpAddress, pickUpAddressFromConfPage, "The pick up addresss from answer call page " + "'" + pickUpAddress + "'"
			+ " does not match with pick up address in confrimation page " + "'" + pickUpAddressFromConfPage + "'");

	  /** Asserting booking type */
	  String bookingTypeFromConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "booking_type");
	  
	  /** Setting the booking type to rt for railway transfer. This has to be done since booking api returns rt and not p2p*/
	  if(bookingType.contains("Railway Transfer"))
	  {
		 bookingTypeValue = "rt";
	  }
	  new Assertion().assertString(bookingTypeValue, bookingTypeFromConfPage, "The booking type in answer call page " + "'" + bookingType + "'"
			+ " does not match with booking type in confirmation page " + "'" + bookingTypeFromConfPage + "'");


	  /** Asserting pick up time */
	  String pickUpTimeInConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "pickup_time");
	  new Assertion().assertString(pickUpTimeInConfPage, "23:00", "The pick time in answer call page '11:00 p.m.' does not match with booking time in confirmation page " + "'" + pickUpTimeInConfPage
			+ "'");

	  /** Asserting pick up area */
	  String pickUpAreaInConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "pickup_area");
	  new Assertion().assertString(pickUpAreaInConfPage, pickUpArea, "The pick up area in confirmation page " + "'" + pickUpAreaInConfPage + "'"
			+ " does not match with pick up area in answer call page " + "'" + pickUpArea + "'");

	  /** Asserting drop area */
	  if (bookingType.contains("hrs") && city.contains("Bangalore"))
	  {

	  } else if (bookingType.contains("Outstation"))
	  {

	  } else if (bookingType.contains("Intercity"))
	  {

	  } else
	  {
		 String dropAreaInConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "drop_area");
		 new Assertion().assertString(dropAreaInConfPage, dropArea, "The drop area in confirmation page " + "'" + dropAreaInConfPage + "'" + " does not match with drop area in answer call page "
			   + "'" + dropArea + "'");
	  }

	  if (bookingType.equalsIgnoreCase("Intercity"))
	  {
		 String dropAreaInConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "drop_area");
		 new Assertion().assertString(dropAreaInConfPage, dropCity, "The drop city in confirmation page " + "'" + dropAreaInConfPage + "'" + " does not match with drop city in answer call page "
			   + "'" + dropCity + "'");
	  }

	  /** Asserting base fare */
	  if (!(bookingType.contains("Outstation") | bookingType.equalsIgnoreCase("Intercity")))
	  {
		 Double baseFareFromAPI = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "nightBaseFare", city, cabTypeValue));
		 Double baseFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_fare");
		 new Assertion().assertDouble(baseFareFromConfPage, baseFareFromAPI, "The base fare in confirmaiton page " + "'" + baseFareFromConfPage + "'" + " does not match with base price from api "
			   + "'" + baseFareFromAPI + "'");
	  }

	  if (bookingType.equalsIgnoreCase("Intercity"))
	  {
		 Double baseFareFromAPI = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "nightBaseFare", city, cabTypeValue));
		 Double baseFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_fare");
		 new Assertion().assertDouble(baseFareFromConfPage, baseFareFromAPI, "The base fare in confirmaiton page " + "'" + baseFareFromConfPage + "'" + " does not match with base price from api "
			   + "'" + baseFareFromAPI + "'");
	  }

	  /** Asserting extra km fare */
	  if (!(bookingType.contains("Outstation") | bookingType.equalsIgnoreCase("Intercity")))
	  {
		 Double extraKmFareFromApi = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "nightExtraKmFare", city, cabTypeValue));
		 Double extraKmFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "extra_km_fare");
		 new Assertion().assertDouble(extraKmFareFromConfPage, extraKmFareFromApi, "The extra km fare in confirmation page " + "'" + extraKmFareFromConfPage + "'"
			   + " does not match with extra km fare from api " + "'" + extraKmFareFromApi + "'");
	  }

	  if (bookingType.equalsIgnoreCase("Intercity"))
	  {
		 Double extraKmFareFromApi = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "nightExtraKmFare", city, cabTypeValue));
		 Double extraKmFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "extra_km_fare");
		 new Assertion().assertDouble(extraKmFareFromConfPage, extraKmFareFromApi, "The extra km fare in confirmation page " + "'" + extraKmFareFromConfPage + "'"
			   + " does not match with extra km fare from api " + "'" + extraKmFareFromApi + "'");
	  }

	  /** Asserting customer phone number */
	  String customerPhNoFromConfPage = new BookingDetailsScreen(driver).getCustomerInfoFromDB("customer_number", bookingIdFromApplication);
	  new Assertion().assertString(custPhNo, customerPhNoFromConfPage, "The customer phone number in answer call page " + "'" + custPhNo + "'"
			+ " does not match with customer phone from confirmation page " + "'" + customerPhNoFromConfPage + "'");

	  /** Asserting customer email id */
	  String customerEmailFromAnsCal = new AnswerCallScreen(driver).getCustomerEmail();
	  String customerEmailFromConfPage = new BookingDetailsScreen(driver).getCustomerInfoFromDB("customer_email", bookingIdFromApplication);
	  new Assertion().assertString(customerEmailFromAnsCal, customerEmailFromConfPage, "The customer email in answer call page " + "'" + customerEmailFromAnsCal + "'"
			+ " does not match with customer email from confirmation page " + "'" + customerEmailFromConfPage + "'");
   }


   @AfterMethod
   public void cleanUp(ITestResult result)
   {
	  if(!result.isSuccess())
	  {
		 System.setProperty("org.uncommons.reportng.escape-output", "false");
		 Reporter.setCurrentTestResult(result);
		 File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		 String destDir = "target/surefire-reports/screenshots";
		 new File(destDir).mkdirs();
		 String destFile = dateFormat.format(new Date()) + ".png";

		 try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		 } catch (IOException e) {
			e.printStackTrace();
		 }
		 System.out.println(new File(destDir + "/" + destFile).getAbsolutePath());
		 Reporter.log("<a href = file:///" + new File(destDir + "/" + destFile).getAbsolutePath() +" target='_blank'" +"> " +
			   "<p align=\"left\", style= \"color: red; font: italic bold 12px/30px Georgia,serif;\" >Click here for screen shot of "+ city + " " + bookingType +"</p> </a>");
		 System.out.println("Taken screen shots");
	  }else
	  {
		  System.setProperty("org.uncommons.reportng.escape-output", "false");
		  Reporter.setCurrentTestResult(result);
		  Reporter.log("Test passed for advanced booking night for the combination " + city + "_" + cabTypeValue + "_" + bookingType);
	  }
	  new MyDriver().quitBrowser(driver);
   }

   @DataProvider(name = "cityName")
   public Object[][] getCityNames()
   {
	  driver = new MyDriver().getDriver();
	  Object obj[][] = new BookingTypes().getAllBookingTypesForCity(driver, "Rajkot", "Surat", "Aurangabad");
	  System.out.println(obj[0].length);
	  System.out.println(obj.length);
	  new MyDriver().quitBrowser(driver);
	 
	  return obj;

   }

}
