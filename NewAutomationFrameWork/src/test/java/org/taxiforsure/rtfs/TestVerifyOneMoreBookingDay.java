package org.taxiforsure.rtfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.taxiforsure.answercall.AnswerCallScreen;
import org.taxiforsure.assertion.Assertion;
import org.taxiforsure.bookingdetails.BookingDetailsScreen;
import org.taxiforsure.notifications.NotificationsScreen;
import org.taxiforsure.rtfs.login.Login;
import org.taxiforsure.rtfscommonelements.CommonElements;
import org.taxiforsure.utils.ReadPropertiesFile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.taxiforsure.driver.MyDriver;

public class TestVerifyOneMoreBookingDay
{
	
	WebDriver driver;
	   String data = null;
	   String city = null;
	   String cabType = null;
	   String bookingTypeValue = null;
	   String pickUpArea;
	   String dropArea = "";
	   String setDate;
	   String priceAPIURL = "http://46.4.63.119:8393/pricingV2/api/pricingV2/postRequest";
	   String bookingType = null;
	   String customerInfoAPIURL = null;
	   String cityLimits = null;
	   String airportNameAPI= null; 
	   String cabTypeValue = null;
	   
	   Logger log = Logger.getLogger("TestVerifyDayFutureBooking");
	   
	   public TestVerifyOneMoreBookingDay(String data)
	   {
		   this.data = data;
	   }
	   
	   @BeforeMethod
	   public void setup()
	   {
		  driver = new MyDriver().getDriver();
	   }
	   
	   
	   @Test()
	   public void testVerifyOneMoreBookingDay()
	   {
		  String individualData[] = data.split("_");
		  
		  this.city = "Aurangabad"; //individualData[0];
		  bookingType = "Point to Point"; //individualData[1];
		  bookingTypeValue = "p2p"; //individualData[2];
		  cabTypeValue =  "Hatchback"; //individualData[3];
		  
		  String customerNameFromAnsCal = new AnswerCallScreen(driver).getCustomerName();
		  String custPhNo = new AnswerCallScreen(driver).getCustomerPhoneNo();
		  String pickUpAddress = new AnswerCallScreen(driver).getPickUpAddress();
		  
		  
		  log.info("Starting the test for the city " + city + " ,the cab type " + cabType + " and the booking type " + bookingType);
		  
		  /** This sets booking type for railway tranfer and changes booking type value got from the application from rt to p2p */
		  if (bookingType.contains("Railway Transfer"))
		  {
			 bookingTypeValue = "p2p";
		  }

		  String dropCity = null;

		  /** Logging into the application and navigating to answer call page */
		  new Login(driver).doLogin("vinay.b", "vin_1984");
		  new NotificationsScreen(driver).selectCity(city);
		  new CommonElements(driver).clickAnswerCallLink();

		  /** Setting phone num, booking type, city, night travel, cab type */
		  new AnswerCallScreen(driver).setPhoneNumber(custPhNo);
		  new AnswerCallScreen(driver).selectBookingType(bookingType);
		  
		  /** Getting pick up and drop locality that will be set in answer call screen */
		  pickUpArea = new AnswerCallScreen(driver).getpickUpLocality(city, bookingType);
		  dropArea = new AnswerCallScreen(driver).getDropLocality(city, bookingType);
		  

		  /** Setting day travel */
		  setDate= new AnswerCallScreen(driver).setDayTravelAndGetTheSetDate("08:00");
		  

		  /** Selecting the trip type */
		  if (bookingType.equalsIgnoreCase("Outstation"))
		  {
			 new AnswerCallScreen(driver).setDataForOutstationTravel(city, pickUpArea, 1);
			 new AnswerCallScreen(driver).setDayTravelAndGetTheSetDate("08:00");
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
				new AnswerCallScreen(driver).setDayTravelAndGetTheSetDate("08:00");
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

				setDate = new AnswerCallScreen(driver).setDayTravelAndGetTheSetDate("08:00");
				

				if (bookingType.equalsIgnoreCase("Outstation"))
				{
				   new AnswerCallScreen(driver).setDataForOutstationTravel(pickUpArea);
				   new AnswerCallScreen(driver).setDayTravelAndGetTheSetDate("08:00");
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
		 
		  

		  /** Setting proper parameters for price api*/
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
			 stringForPriceAPI = new AnswerCallScreen(driver).getPriceApiString(setCityShortName, cabTypeValue, bookingTypeValue, setDate, "08:00", pickUpArea, dropArea, cityLimits);
		  } else if (!(bookingType.equalsIgnoreCase("Intercity")))
		  {
			 stringForPriceAPI = new AnswerCallScreen(driver).getPriceApiString(setCityShortName, cabTypeValue, bookingTypeValue, setDate, "08:00", pickUpArea, dropArea, cityLimits);
			

		  } else if (bookingType.equalsIgnoreCase("Intercity"))
		  {
			 stringForIntercityPriceAPI = new AnswerCallScreen(driver).getPriceAPIForIntercity(setCityShortName, cabTypeValue, bookingTypeValue, setDate, "08:00", city, dropCity, cityLimits);
		  }

		  /** Getting the id of the tariff table and extra km fare */
		  List<Object> id = null;
		  List<Object> ekf = null;
		  if (!(bookingType.equalsIgnoreCase("Intercity")))
		  {
			 id = new AnswerCallScreen(driver).getValuesFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "id");
			 ekf = new AnswerCallScreen(driver).getValuesFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "extraKmFare");
		  } else if (bookingType.equalsIgnoreCase("Intercity"))
		  {
			 id = new AnswerCallScreen(driver).getValuesFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "id");
			 ekf = new AnswerCallScreen(driver).getValuesFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "extraKmFare");
		  }

		  /** Verifying if the price from the api and the tariff table matches */
		  boolean isFareMatching = new AnswerCallScreen(driver).isPriceMatching(id, ekf, bookingTypeValue, city);
		  new Assertion().assertTrue(isFareMatching, "The price from API does not match with price in the application");
		  
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
		  String bookingIdFromDB = new BookingDetailsScreen(driver).getBookingIdFromDB(custPhNo);

		  /** Asserting booking id */
		  new Assertion().assertString(bookingIdFromDB, bookingIdFromApplication, "The expected booking id from application " + "'" + bookingIdFromApplication + "'"
				+ " does not match with booking id from db " + "'" + bookingIdFromDB + "'");
		  customerInfoAPIURL = null;
		  customerInfoAPIURL = new ReadPropertiesFile().getValue("Config.properties", "url");
		  customerInfoAPIURL = customerInfoAPIURL + "/api/consumer-app/booking-info/?booking_id=" + bookingIdFromDB;
		  
		  String customerInfoJson = new BookingDetailsScreen(driver).getBookingDetailsJson(customerInfoAPIURL);

		  /** Asserting pick up time */
		  String pickUpTimeInConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "pickup_time");
		  new Assertion().assertString(pickUpTimeInConfPage, "08:00", "The pick time in answer call page '9 a.m.' does not match with booking time in confirmation page " + "'" + pickUpTimeInConfPage
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

		  /** Asserting base Kms */
		  if(bookingType.contains("Intercity"))
		  {
			 Integer baseKMFromAPI = new Integer(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "baseKm", city, cabTypeValue));
			  Integer baseKMFromConfPage = (Integer) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_km");
			  new Assertion().assertInt(baseKMFromAPI, baseKMFromConfPage, "The base fare in confirmaiton page " + "'" + baseKMFromConfPage + "'" + " does not match with base price from api "
					+ "'" + baseKMFromAPI + "'");
		  }else
		  {
			 Integer baseKMFromAPI = new Integer(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "baseKm", city, cabTypeValue));
			  Integer baseKMFromConfPage = (Integer) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_km");
			  new Assertion().assertInt(baseKMFromAPI, baseKMFromConfPage, "The base fare in confirmaiton page " + "'" + baseKMFromConfPage + "'" + " does not match with base price from api "
					+ "'" + baseKMFromAPI + "'");
		  }
		 
		  
		  /** Asserting base fare */
		  if (!(bookingType.contains("Outstation") | bookingType.equalsIgnoreCase("Intercity")))
		  {
			 Double baseFareFromAPI = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "baseFare", city, cabTypeValue));
			 Double baseFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_fare");
			 new Assertion().assertDouble(baseFareFromConfPage, baseFareFromAPI, "The base fare in confirmaiton page " + "'" + baseFareFromConfPage + "'" + " does not match with base price from api "
				   + "'" + baseFareFromAPI + "'");
		  }

		  if (bookingType.equalsIgnoreCase("Intercity"))
		  {
			 Double baseFareFromAPI = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "baseFare", city, cabTypeValue));
			 Double baseFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_fare");
			 new Assertion().assertDouble(baseFareFromConfPage, baseFareFromAPI, "The base fare in confirmaiton page " + "'" + baseFareFromConfPage + "'" + " does not match with base price from api "
				   + "'" + baseFareFromAPI + "'");
		  }

		  /** Asserting extra km fare */
		  if (!(bookingType.contains("Outstation") | bookingType.equalsIgnoreCase("Intercity")))
		  {
			 Double extraKmFareFromApi = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "extraKmFare", city, cabTypeValue));
			 Double extraKmFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "extra_km_fare");
			 new Assertion().assertDouble(extraKmFareFromConfPage, extraKmFareFromApi, "The extra km fare in confirmation page " + "'" + extraKmFareFromConfPage + "'"
				   + " does not match with extra km fare from api " + "'" + extraKmFareFromApi + "'");
		  }

		  if (bookingType.equalsIgnoreCase("Intercity"))
		  {
			 Double extraKmFareFromApi = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "extraKmFare", city, cabTypeValue));
			 Double extraKmFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "extra_km_fare");
			 new Assertion().assertDouble(extraKmFareFromConfPage, extraKmFareFromApi, "The extra km fare in confirmation page " + "'" + extraKmFareFromConfPage + "'"
				   + " does not match with extra km fare from api " + "'" + extraKmFareFromApi + "'");
		  }
		  
		                                                           /* 1st time */

		/** Clicking on create once more booking button*/
		  new BookingDetailsScreen(driver).clickOneMoreBookingButton();
		  
		  /** Asserting if all the values which were set during booking creation is pre populated */
		  
		  /** Asserting the phone number */
		  String customerPhoneNoWhichWasSet = new AnswerCallScreen(driver).getTheCustPhNumberWhichWasSet();
		  new Assertion().assertString(custPhNo, customerPhoneNoWhichWasSet, "The customer phone number which was set during booking creation " + custPhNo + " does not match " + customerPhoneNoWhichWasSet);
		
		  /** Asserting the booking type */
		  String bookingTypeThatWasSet = new AnswerCallScreen(driver).getTheSelectedBookingType();
		  new Assertion().assertString(bookingTypeThatWasSet, bookingType, "The booking type selected during booking " + bookingType + " does not match " + bookingTypeThatWasSet);
		  
		  /** Asserting the pick up area */
		  String pickUpAreaThatWasSet = new AnswerCallScreen(driver).getThePickUpAreaWhichWasSet();
		  new Assertion().assertString(pickUpAreaThatWasSet, pickUpArea, "The pick up area that was set " + pickUpArea + " does not match " + pickUpAreaThatWasSet);
		  
		  /** Asserting the drop area */
		  String dropAreaThatWasSet = new AnswerCallScreen(driver).getTheDropAreaWhichWasSet();
		  new Assertion().assertString(dropAreaThatWasSet, dropArea, "The drop area which was set " + dropArea + " does not match " + dropAreaThatWasSet) ;
		  
		  /**Asserting if the cab type selected is still selected */
		  boolean isTheSelectedCabStillSelected = new AnswerCallScreen(driver).isTheChosenCabStillSelected(cabTypeValue);
		  new Assertion().assertTrue(isTheSelectedCabStillSelected, "The selected cab during booking is no more selected");
		  
		  /** Setting time to take one more booking night time*/
		  new AnswerCallScreen(driver).setDayTravelAndGetTheSetDate("08:15");
		  
		  /** Clicking on take booking button */
		 
		  new AnswerCallScreen(driver).clickTakeBookingButton();
		  
		  /** Ensuring cab is available for the selected trip type and locations for one more booking option */
		  cabIsAvaialable = false;
		  do
		  {
			 try
			 {
				cabIsAvaialable = new AnswerCallScreen(driver).checkForCabAvailability(bookingType, pickUpArea, cabTypeValue);
			 }catch(Throwable t){}
			 if (cabIsAvaialable == true)
			 {
				 new AnswerCallScreen(driver).clickTakeBookingButton();
				 new AnswerCallScreen(driver).setPickUpAdress(pickUpAddress);
				 new AnswerCallScreen(driver).setLandMark();
				 new AnswerCallScreen(driver).setCustomerName(customerNameFromAnsCal);
				 new AnswerCallScreen(driver).setEmail();
				 new AnswerCallScreen(driver).selectFoundUsThrough();
				 new AnswerCallScreen(driver).selectGender();
				break;
			 }

			 if (cabIsAvaialable == false)
			 {
				new AnswerCallScreen(driver).setPhoneNumber(custPhNo);
				new AnswerCallScreen(driver).selectBookingType(bookingType);

				setDate = new AnswerCallScreen(driver).setDayTravelAndGetTheSetDate("08:15");
				
				if (bookingType.equalsIgnoreCase("Outstation"))
				{
				   new AnswerCallScreen(driver).setDataForOutstationTravel(pickUpArea);
				   new AnswerCallScreen(driver).setDayTravelAndGetTheSetDate("08:15");
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
				if(cabIsAvaialable == true)
				{
					new AnswerCallScreen(driver).clickTakeBookingButton();
					new AnswerCallScreen(driver).setPickUpAdress(pickUpAddress);
					new AnswerCallScreen(driver).setLandMark();
					new AnswerCallScreen(driver).setCustomerName(customerNameFromAnsCal);
					new AnswerCallScreen(driver).setEmail();
					new AnswerCallScreen(driver).selectFoundUsThrough();
					new AnswerCallScreen(driver).selectGender();
				}
			 }// End of iscabavailable
		  } while (cabIsAvaialable == false);
		  
		  new AnswerCallScreen(driver).clickConfrimButton();
		  
		  /** Getting the booking id from the application and Data base for one more booking option */
		  bookingIdFromApplication = new BookingDetailsScreen(driver).getBookingID();
		  bookingIdFromDB = new BookingDetailsScreen(driver).getBookingIdFromDB(custPhNo);

		  /** Asserting booking id for one more booking option*/
		  new Assertion().assertString(bookingIdFromDB, bookingIdFromApplication, "The expected booking id from application " + "'" + bookingIdFromApplication + "'"
				+ " does not match with booking id from db " + "'" + bookingIdFromDB + "'");
		  customerInfoAPIURL = null;
		  customerInfoAPIURL = new ReadPropertiesFile().getValue("Config.properties", "url");
		  customerInfoAPIURL = customerInfoAPIURL + "/api/consumer-app/booking-info/?booking_id=" + bookingIdFromDB;
		  
		  customerInfoJson = new BookingDetailsScreen(driver).getBookingDetailsJson(customerInfoAPIURL);
		  
		  /** Asserting pick up time for one more booking option*/
		  pickUpTimeInConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "pickup_time");
		  new Assertion().assertString(pickUpTimeInConfPage, "08:15", "The pick time in answer call page '08:15 a.m.' does not match with booking time in confirmation page " + "'" + pickUpTimeInConfPage
				+ "'");

		  /** Asserting pick up area for one more booking option*/
		  pickUpAreaInConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "pickup_area");
		  new Assertion().assertString(pickUpAreaInConfPage, pickUpArea, "The pick up area in confirmation page " + "'" + pickUpAreaInConfPage + "'"
				+ " does not match with pick up area in answer call page " + "'" + pickUpArea + "'");

		  /** Asserting drop area for one more booking option*/
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

		  /** Asserting base Kms for one more booking option*/
		  if(bookingType.contains("Intercity"))
		  {
			 Integer baseKMFromAPI = new Integer(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "baseKm", city, cabTypeValue));
			  Integer baseKMFromConfPage = (Integer) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_km");
			  new Assertion().assertInt(baseKMFromAPI, baseKMFromConfPage, "The base fare in confirmaiton page " + "'" + baseKMFromConfPage + "'" + " does not match with base price from api "
					+ "'" + baseKMFromAPI + "'");
		  }else
		  {
			 Integer baseKMFromAPI = new Integer(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "baseKm", city, cabTypeValue));
			  Integer baseKMFromConfPage = (Integer) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_km");
			  new Assertion().assertInt(baseKMFromAPI, baseKMFromConfPage, "The base fare in confirmaiton page " + "'" + baseKMFromConfPage + "'" + " does not match with base price from api "
					+ "'" + baseKMFromAPI + "'");
		  }
		 
		  
		  /** Asserting base Kms */
		  if(bookingType.contains("Intercity"))
		  {
			 Integer baseKMFromAPI = new Integer(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "baseKm", city, cabTypeValue));
			  Integer baseKMFromConfPage = (Integer) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_km");
			  new Assertion().assertInt(baseKMFromAPI, baseKMFromConfPage, "The base fare in confirmaiton page " + "'" + baseKMFromConfPage + "'" + " does not match with base price from api "
					+ "'" + baseKMFromAPI + "'");
		  }else
		  {
			 Integer baseKMFromAPI = new Integer(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "baseKm", city, cabTypeValue));
			  Integer baseKMFromConfPage = (Integer) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_km");
			  new Assertion().assertInt(baseKMFromAPI, baseKMFromConfPage, "The base fare in confirmaiton page " + "'" + baseKMFromConfPage + "'" + " does not match with base price from api "
					+ "'" + baseKMFromAPI + "'");
		  }
		 
		  
		  /** Asserting base fare */
		  if (!(bookingType.contains("Outstation") | bookingType.equalsIgnoreCase("Intercity")))
		  {
			 Double baseFareFromAPI = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "baseFare", city, cabTypeValue));
			 Double baseFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_fare");
			 new Assertion().assertDouble(baseFareFromConfPage, baseFareFromAPI, "The base fare in confirmaiton page " + "'" + baseFareFromConfPage + "'" + " does not match with base price from api "
				   + "'" + baseFareFromAPI + "'");
		  }

		  if (bookingType.equalsIgnoreCase("Intercity"))
		  {
			 Double baseFareFromAPI = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "baseFare", city, cabTypeValue));
			 Double baseFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_fare");
			 new Assertion().assertDouble(baseFareFromConfPage, baseFareFromAPI, "The base fare in confirmaiton page " + "'" + baseFareFromConfPage + "'" + " does not match with base price from api "
				   + "'" + baseFareFromAPI + "'");
		  }

		  /** Asserting extra km fare */
		  if (!(bookingType.contains("Outstation") | bookingType.equalsIgnoreCase("Intercity")))
		  {
			 Double extraKmFareFromApi = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "extraKmFare", city, cabTypeValue));
			 Double extraKmFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "extra_km_fare");
			 new Assertion().assertDouble(extraKmFareFromConfPage, extraKmFareFromApi, "The extra km fare in confirmation page " + "'" + extraKmFareFromConfPage + "'"
				   + " does not match with extra km fare from api " + "'" + extraKmFareFromApi + "'");
		  }

		  if (bookingType.equalsIgnoreCase("Intercity"))
		  {
			 Double extraKmFareFromApi = new Double(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "extraKmFare", city, cabTypeValue));
			 Double extraKmFareFromConfPage = (Double) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "extra_km_fare");
			 new Assertion().assertDouble(extraKmFareFromConfPage, extraKmFareFromApi, "The extra km fare in confirmation page " + "'" + extraKmFareFromConfPage + "'"
				   + " does not match with extra km fare from api " + "'" + extraKmFareFromApi + "'");
		  }
		  
		  																/* 2nd time */
		  
		  /** Clicking on create once more booking button*/
		  new BookingDetailsScreen(driver).clickOneMoreBookingButton();
		  
		  /** Asserting if all the values which were set during booking creation is pre populated */
		  
		  /** Asserting the phone number */
		  customerPhoneNoWhichWasSet = new AnswerCallScreen(driver).getTheCustPhNumberWhichWasSet();
		  new Assertion().assertString(custPhNo, customerPhoneNoWhichWasSet, "The customer phone number which was set during booking creation " + custPhNo + " does not match " + customerPhoneNoWhichWasSet);
		
		  /** Asserting the booking type */
		  bookingTypeThatWasSet = new AnswerCallScreen(driver).getTheSelectedBookingType();
		  new Assertion().assertString(bookingTypeThatWasSet, bookingType, "The booking type selected during booking " + bookingType + " does not match " + bookingTypeThatWasSet);
		  
		  /** Asserting the pick up area */
		  pickUpAreaThatWasSet = new AnswerCallScreen(driver).getThePickUpAreaWhichWasSet();
		  new Assertion().assertString(pickUpAreaThatWasSet, pickUpArea, "The pick up area that was set " + pickUpArea + " does not match " + pickUpAreaThatWasSet);
		  
		  /** Asserting the drop area */
		  dropAreaThatWasSet = new AnswerCallScreen(driver).getTheDropAreaWhichWasSet();
		  new Assertion().assertString(dropAreaThatWasSet, dropArea, "The drop area which was set " + dropArea + " does not match " + dropAreaThatWasSet) ;
		  
		  /**Asserting if the cab type selected is still selected */
		  isTheSelectedCabStillSelected = new AnswerCallScreen(driver).isTheChosenCabStillSelected(cabTypeValue);
		  new Assertion().assertTrue(isTheSelectedCabStillSelected, "The selected cab during booking is no more selected");
		  
		  /** Setting time to take one more booking */
		  new AnswerCallScreen(driver).setNightTravelAndGetTheSetDate("11:00");
		  
		  /** Clicking on take booking button */
		  
		  new AnswerCallScreen(driver).clickTakeBookingButton();
		  
		  /** Ensuring cab is available for the selected trip type and locations for one more booking option */
		  cabIsAvaialable = false;
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
				new AnswerCallScreen(driver).clickTakeBookingButton();
				  new AnswerCallScreen(driver).setPickUpAdress(pickUpAddress);
				  new AnswerCallScreen(driver).setLandMark();
				  new AnswerCallScreen(driver).setCustomerName(customerNameFromAnsCal);
				  new AnswerCallScreen(driver).setEmail();
				  new AnswerCallScreen(driver).selectFoundUsThrough();
				  new AnswerCallScreen(driver).selectGender();
			 }// End of iscabavailable
		  } while (cabIsAvaialable == false);
		  
		  new AnswerCallScreen(driver).clickConfrimButton();
		  
		  /** Getting the booking id from the application and Data base for one more booking option */
		  bookingIdFromApplication = new BookingDetailsScreen(driver).getBookingID();
		  bookingIdFromDB = new BookingDetailsScreen(driver).getBookingIdFromDB(custPhNo);

		  /** Asserting booking id for one more booking option*/
		  new Assertion().assertString(bookingIdFromDB, bookingIdFromApplication, "The expected booking id from application " + "'" + bookingIdFromApplication + "'"
				+ " does not match with booking id from db " + "'" + bookingIdFromDB + "'");
		  customerInfoAPIURL = null;
		  customerInfoAPIURL = new ReadPropertiesFile().getValue("Config.properties", "url");
		  customerInfoAPIURL = customerInfoAPIURL + "/api/consumer-app/booking-info/?booking_id=" + bookingIdFromDB;
		  
		  customerInfoJson = new BookingDetailsScreen(driver).getBookingDetailsJson(customerInfoAPIURL);
		  
		  /** Asserting pick up time for one more booking option*/
		  pickUpTimeInConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "pickup_time");
		  new Assertion().assertString(pickUpTimeInConfPage, "23:00", "The pick time in answer call page '11:00 p.m.' does not match with booking time in confirmation page " + "'" + pickUpTimeInConfPage
				+ "'");

		  /** Asserting pick up area for one more booking option*/
		  pickUpAreaInConfPage = (String) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "pickup_area");
		  new Assertion().assertString(pickUpAreaInConfPage, pickUpArea, "The pick up area in confirmation page " + "'" + pickUpAreaInConfPage + "'"
				+ " does not match with pick up area in answer call page " + "'" + pickUpArea + "'");

		  /** Asserting drop area for one more booking option*/
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

		  /** Asserting base Kms for one more booking option*/
		  if(bookingType.contains("Intercity"))
		  {
			 Integer baseKMFromAPI = new Integer(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForIntercityPriceAPI, "baseKm", city, cabTypeValue));
			  Integer baseKMFromConfPage = (Integer) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_km");
			  new Assertion().assertInt(baseKMFromAPI, baseKMFromConfPage, "The base fare in confirmaiton page " + "'" + baseKMFromConfPage + "'" + " does not match with base price from api "
					+ "'" + baseKMFromAPI + "'");
		  }else
		  {
			 Integer baseKMFromAPI = new Integer(new BookingDetailsScreen(driver).getValueFromPriceAPI(priceAPIURL, "441ea2bea63ef43d14d9aecde490ad1", stringForPriceAPI, "baseKm", city, cabTypeValue));
			  Integer baseKMFromConfPage = (Integer) new BookingDetailsScreen(driver).getValueFromBookingInfoAPI(customerInfoJson, "base_km");
			  new Assertion().assertInt(baseKMFromAPI, baseKMFromConfPage, "The base fare in confirmaiton page " + "'" + baseKMFromConfPage + "'" + " does not match with base price from api "
					+ "'" + baseKMFromAPI + "'");
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
		  
		  
		  
		  log.info("Ending the test for the city " + city + " ,the cab type " + cabType + " and the booking type " + bookingType);
	   }

}
