package org.taxiforsure.answercall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.taxiforsure.actions.DoOperation;
import org.taxiforsure.utils.DataBaseUtils;
import org.taxiforsure.utils.ParseAPIAndGetValue;
import org.taxiforsure.utils.ReadPropertiesFile;

public class AnswerCallScreen
{
   /**
    * @author tfs-vinay
    * @Version 1.0
    * This class contains a representation of the answer call screen.
    */

   WebDriver driver;
   private Logger log = Logger.getLogger("Answer Call Screen");
   private ArrayList<String> localities;

   public AnswerCallScreen(WebDriver driver)
   {
	  this.driver = driver;
	  PageFactory.initElements(driver, this);
   }

   

   /* Customer number text field */
   @FindBy(id = "id_customer_number")
   private WebElement phoneNumberTextField;

   /* Booking type drop down */
   @FindBy(id = "id_booking_type")
   private WebElement bookingTypeDropDown;

   /* Pick up area text box */
   @FindBy(id="id_pickup_area")
   private WebElement pickUpAreaTextBox;

   /* Pick up area drop down list */
   @FindBy(xpath = "//div[input[@id='id_pickup_area']]/descendant::ul[@class='typeahead dropdown-menu']")
   private WebElement pickUpAreaDropDownList;

   /* Drop area text box */
   @FindBy(id = "id_drop_area")
   private WebElement dropAreaTextBox;

   /* Drop area drop down list */
   @FindBy(xpath = "//div[input[@id='id_drop_area']]/descendant::ul[@class='typeahead dropdown-menu']")
   private WebElement dropAreaDropDownList;

   /* Pick up date text field */
   @FindBy(id="id_pickup_date")
   private WebElement pickupDateField;

   /* Pick up time drop down */
   @FindBy(id = "id_pickup_time")
   private WebElement pickUpTimeDropDown;

   /* Meridiem drop down AM/PM */
   @FindBy(id = "id_pickup_time_meridian")
   private WebElement meridiemDropDown;

   /* Future date and time tool tip */
   @FindBy(xpath = "//div[text()='Please select a future date and time']")
   private WebElement futureDateAndTimeToolTip;

   /* Pick up time tool tip */
   @FindBy(id = "id_pickup_time_warning")
   private WebElement pickUpTimeWarningMessage;

   /* Car section */
   @FindBy(id = "car")
   private WebElement car;

   /* Take booking button */
   @FindBy(id="id_should_take_booking")
   private WebElement takeBookingButton;

   /* Pick up address text box */
   @FindBy(id = "id_pickup_address")
   private WebElement pickUpAdresssTextArea;

   /* Land mark text box */
   @FindBy(id="id_landmark")
   private WebElement landmarkTextBox;

   /* Customer name text box */
   @FindBy(id="id_customer_name")
   private WebElement customerNameTextField;

   /* Email id text box */
   @FindBy(id = "id_customer_email")
   private WebElement emailIdTextField;

   /* Found us drop down */
   @FindBy(id = "id_found_us_through")
   private WebElement foundUsThroughDropDown;

   /* Gender radio button */
   @FindBy(xpath = "//div[label[text()='Gender']]")
   private WebElement genderSection;

   /* Confirm button */
   @FindBy(id = "id_confirmed_booking")
   private WebElement confirmButton;

   /* Tariff table */
   @FindBy(id = "id_tariff_table")
   private WebElement tariffTable;

   /* Cab Availability message area */
   @FindBy(id="check_cap_availability_response")
   private WebElement checkCabAvailabilty;

   /* This is displayed when we select Outstation value */
   @FindBy(id = "id_return_date")
   private WebElement returnDateForOutstation;

   /* This message area is displayed when we select an area where cab service is not available */
   @FindBy(xpath = "//div[@id= 'tariff']/p/strong[text()='No Pickup and Drop Service available.']")
   private WebElement noPickUpAndDropServiceSection;

   /* This is drop down displayed to select a city  when we select Intercity */
   @FindBy(id = "id_drop_city")
   private WebElement dropCityDropDown;




   /**
    * 
    * @return
    * This returns a boolean value depending on if no service message is displayed
    */
   public synchronized boolean isNoPickUpAndDropServiceElementDisplayed()
   {
	  boolean displayed = true;
	  log.info("Verifying if no pick and drop service message is displayed");
	  try
	  {
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 noPickUpAndDropServiceSection.isDisplayed();
		 log.info("No pick and drop service message, is displayed");
	  }catch(Throwable t)
	  {
		 log.info("No pick and drop service message is not displayed");
		 displayed = false;
	  }finally
	  {
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  }
	  return displayed;
   }

   /**
    * This is used for creating and setting the mobile number. This will create random 10 numbers 
    * and type into mobile number field
    */
   public synchronized void setPhoneNumber(String phoneNo)
   {
	 
	  log.info("Setting customer phone number " + phoneNo);
	  new DoOperation().type(driver, phoneNumberTextField, phoneNo);
   }

   /**
    * @return
    * This returns the set customer phone number
    */
   public synchronized String getCustomerPhoneNo()
   {
	  return   "9" +RandomStringUtils.randomNumeric(9);
   }

   /**
    * @param typeOfBooking
    * This is used for selecting the type of booking from the drop down
    */
   public synchronized void selectBookingType(String typeOfBooking)
   {
	  log.info("Selecting the booking type - " + typeOfBooking);
	  new DoOperation().selectInDropDown(driver, bookingTypeDropDown, typeOfBooking);
   }

   /**
    * This is used for clicking on take booking button
    */
   public synchronized void clickTakeBookingButton()
   {
	  	FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
		fluent.ignoring(Throwable.class);
		fluent.withTimeout(10, TimeUnit.SECONDS);
		fluent.pollingEvery(2, TimeUnit.SECONDS);
		fluent.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("id_tariff_table"))));
	  new DoOperation().click(driver, takeBookingButton);
   }

   /**
    * This is used for typing pick up address. This creates and types random 15 letters
    */
   public synchronized void setPickUpAdress(String pickupAddress)
   {
	  
	  log.info("Setting the cusotmer pick up address as " + pickupAddress);
	  new DoOperation().type(driver, pickUpAdresssTextArea, pickupAddress);
   }

   /**
    * This returns the pick up address text 
    */
   public synchronized String getPickUpAddress()
   {
	  return RandomStringUtils.randomAlphabetic(15);
   }

   /**
    * This is used for typing a random string into land mark text box
    */
   public synchronized void setLandMark()
   {
	  log.info("Setting the land mark");
	  new DoOperation().type(driver, landmarkTextBox, RandomStringUtils.randomAlphabetic(10));
   }

   /**
    * This is used to set the customer name. It will type the customer name as Taxi
    */
   public synchronized void setCustomerName(String customerName)
   {
	  
	  log.info("Setting the customer name " + customerName);
	  new DoOperation().type(driver, customerNameTextField, customerName);
   }

   /**
    * @return
    * This is used to get the set customer name 
    */
   public synchronized String getCustomerName()
   {
	  return "vinay";
   }

   /**
    * This is used to type the email id. Always will type abc@mail.com
    */
   public synchronized void setEmail()
   { 
	  log.info("Setting customer email id " + getCustomerEmail());
	  new DoOperation().type(driver, emailIdTextField, getCustomerEmail());
   }

   /**
    * @return
    * This will return the set customer email
    */
   public synchronized String getCustomerEmail()
   {
	  return "abc@mail.com";
   }

   /**
    * This is used to select a value in found us through drop down. It will always select the second option
    */
   public synchronized void selectFoundUsThrough()
   {
	  log.info("Selecting found us through option");
	  new DoOperation().selectInDropDownUsingIndex(driver, foundUsThroughDropDown, 2);
   }

   /**
    * This is for clicking on confirm button
    */
   public synchronized void clickConfrimButton()
   {
	  new DoOperation().click(driver, confirmButton);
   }

   /**
    * This is used for selecting a gender. Will select either male or female.
    */
   public void selectGender()
   {
	  int i = new Random().nextInt((2-1) +1) + 1;
	  WebElement gender;
	  if(i == 1)
	  {
		 gender = genderSection.findElement(By.xpath("//label[contains(.,'Male')]"));
		 log.info("Clicking on gender Male" );
	  }else
	  {
		 gender = genderSection.findElement(By.xpath("//label[contains(.,'Female')]"));
		 log.info("Clicking on gender Female" );
	  }
	 
	  new DoOperation().click(driver, gender);
   }

   /**
    * This method is used for setting up day travel. This method will set 8:00 AM as the time which is assumed
    * as day travel across all cities. Also this ensures if the time for the set date has moved past 8:00 AM
    * then the next date is set in the date field.
    */
   public synchronized String setDayTravelAndGetTheSetDate(String time)
   {
	  String dateToSet;
	  log.info("Setting day travel");
	  dateToSet = getTodayDate();
	  log.info("Setting the date as " + dateToSet);
	  new DoOperation().type(driver, pickupDateField, dateToSet);
	  new DoOperation().selectInDropDown(driver, pickUpTimeDropDown, time);
	  new DoOperation().selectInDropDown(driver, meridiemDropDown, "Morning");
	  if(isFutureDateAndTimeMessageDispalyed() == true  || isPickUpWarningMessageDisplayed() == true)
	  {
		 log.info("Resetting the date since Future date and time message or pick up warning message was displayed");
		 Date date = new Date();
		 dateToSet = getFutureDate(date, 1);
		 new DoOperation().type(driver, pickupDateField, dateToSet);
		 new DoOperation().selectInDropDown(driver, meridiemDropDown, "Morning");
	  }
	  return dateToSet;
   }

   public synchronized String setNightTravelAndGetTheSetDate(String time)
   {
	  String dateToSet;
	  log.info("Setting day travel");
	  dateToSet = getTodayDate();
	  log.info("Setting the date as " + dateToSet);
	  new DoOperation().type(driver, pickupDateField, dateToSet);
	  new DoOperation().selectInDropDown(driver, pickUpTimeDropDown, time);
	  new DoOperation().selectInDropDown(driver, meridiemDropDown, "Night");
	  if(isFutureDateAndTimeMessageDispalyed() == true  || isPickUpWarningMessageDisplayed() == true)
	  {
		 log.info("Resetting the date since Future date and time message or pick up warning message was displayed");
		 Date date = new Date();
		 dateToSet = getFutureDate(date, 1);
		 new DoOperation().type(driver, pickupDateField, dateToSet);
		 new DoOperation().selectInDropDown(driver, meridiemDropDown, "Night");
	  }
	  return dateToSet;
   }

   /**
    * @param city
    * @param bookingType
    * @param pickUpLocale
    * @param dropLocale
    * Overloaded method used when we have no cab available message. Once booking page is reloaded then this method will set the same pick up
    * and drop area
    */
   public synchronized void setPickUpAndDropArea(String city, String bookingType, String pickUpLocale, String dropLocale)
   {
	  log.info("Setting the pick up location as " + pickUpLocale);
	  new DoOperation().type(driver, pickUpAreaTextBox, pickUpLocale);
	  try
	  {
		 Thread.sleep(1000);
	  } catch (InterruptedException e)
	  {}
	  Actions act = new Actions(driver);
	  log.info("Click on Tab button");
	  act.sendKeys(Keys.TAB).build().perform();

	  if(bookingType.contains("hrs") & city.contains("Bangalore") == true)
	  {
		 log.info("Not setting drop area since city is Bangalore and selected trip type has Hrs");
	  }else if(bookingType.contains("Outstation"))
	  {
		 log.info("Not setting drop area since the selected trip type is outstation");
	  }else if(bookingType.contains("Intercity"))
	  {
		 log.info("Not setting drop area since the selected trip type is intercity");
	  }else
	  {
		 log.info("Setting the drop as " + dropLocale);
		 new DoOperation().type(driver, dropAreaTextBox, dropLocale);
		 try
		 {
			Thread.sleep(1000);
		 } catch (InterruptedException e)
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 Actions action = new Actions(driver);
		 log.info("Click on Tab button");
		 action.sendKeys(Keys.TAB).build().perform();
	  }
   }
   /**
    * @param city
    * This method sets the values in pick up and drop area. Ensures there are two different values in pick up
    * and drop area 
    */
   public synchronized void setPickUpAndDropArea(String city, String pickUpLocality, String dropLocality, String bookingType, String pickUpDate)
   {
	 
	  new DoOperation().type(driver, pickUpAreaTextBox, pickUpLocality);
	  try
	  {
		 Thread.sleep(1000);
	  } catch (InterruptedException e)
	  {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
	  }
	  Actions act = new Actions(driver);
	  log.info("Pressing Tab");
	  act.sendKeys(Keys.TAB).build().perform();

	  if(bookingType.contains("hrs") & city.contains("Bangalore") == true)
	  {
		 log.info("Not setting drop area since city is Bangalore and selected trip type has Hrs");
	  }else if(bookingType.contains("Outstation"))
	  {
		 log.info("Not setting drop area since the selected trip type is outstation");
	  }else if(bookingType.contains("Intercity"))
	  {
		 log.info("Not setting drop area since the selected trip type is intercity");
	  }else
	  {
		 log.info("Setting the drop as " + dropLocality);
		 new DoOperation().type(driver, dropAreaTextBox, dropLocality);
		 try
		 {
			Thread.sleep(1000);
		 } catch (InterruptedException e)
		 {
		 }
		 Actions action = new Actions(driver);
		 log.info("Click on Tab button");
		 action.sendKeys(Keys.TAB).build().perform();
		 
	  }

   }

   /**
    * @param city
    * @param carType 
    * This is used for selecting a particular car type Takes the city name and selects a car based on the city
    * name. 
    */
   public synchronized void selectCabType(String cabTypeValue)
   {
//	  String cabValueForPriceAPI = null;
//	  try
//	  {
//		 if(city.equalsIgnoreCase("Bangalore") & carType.equalsIgnoreCase("Hatchback"))
//		 {
//			WebElement hatchBack = car.findElement(By.xpath("//button[text()='Indica']"));
//			cabValueForPriceAPI = hatchBack.getAttribute("value");
//			new DoOperation().click(driver, hatchBack);
//		 }
//		 else if(city.equalsIgnoreCase("Mysore") & carType.equalsIgnoreCase("Hatchback"))
//		 {
//			WebElement hatchBack = car.findElement(By.xpath("//button[text()='Indica']"));
//			cabValueForPriceAPI = hatchBack.getAttribute("value");
//			new DoOperation().click(driver, hatchBack);
//		 }else if(carType.equalsIgnoreCase("HatchBack"))
//		 {
//			WebElement hatchBack = car.findElement(By.xpath("//button[text()='Hatchback']"));
//			cabValueForPriceAPI = hatchBack.getAttribute("value");
//			new DoOperation().click(driver, hatchBack);		
//		 }else if(carType.equalsIgnoreCase("Sedan"))
//		 {
//			WebElement sedan = car.findElement(By.xpath("//button[text()='Sedan']"));
//			cabValueForPriceAPI = sedan.getAttribute("value");
//			new DoOperation().click(driver, sedan);
//		 }else if(city.equalsIgnoreCase("Bangalore") && carType.equalsIgnoreCase("SUV"))
//		 {
//			WebElement suv = car.findElement(By.xpath("//button[text()='Innova(6+1)']"));
//			cabValueForPriceAPI = suv.getAttribute("value");
//			new DoOperation().click(driver, suv);		
//		 }else if(carType.equalsIgnoreCase("suv"))
//		 {
//			WebElement suv = car.findElement(By.xpath("//button[text()='SUV']"));
//			cabValueForPriceAPI = suv.getAttribute("value");
//			new DoOperation().click(driver, suv);		
//		 }
//	  }catch(ElementNotVisibleException e)
//	  {
//		 throw new RuntimeException("Skipping the test as the selected cab type " + "'" + carType + "'" + " is not valid for the city " + "'" + city  + "'");
//	  }
//	  return cabValueForPriceAPI;
	  log.info("Selecting the cab type " + cabTypeValue);
	  WebElement cab = car.findElement(By.xpath("//button[@value='"+cabTypeValue+"']"));
	  new DoOperation().click(driver, cab);
   }

   /**
    * @param max
    * @param min
    * @return
    * This is used for generating random numbers between a range of values
    */
   Random random = new Random();
   private synchronized int generateRandomNumber(int max, int min)
   {
	  System.out.println(random.nextInt(max));
	  return random.nextInt(max);
   }

   /**
    * @param city
    * @return
    * This executes a query which returns a list of localities for a specified city.
    */
   private synchronized ArrayList<String> getAllLocalities(String city)
   {
	  String query = new ReadPropertiesFile().getValue("RTFSQueries.properties", "getAreasForACity");
	  String executeQuery = query.replace("cityName", city);

	  ArrayList<Object> l = new DataBaseUtils().executeQuery(executeQuery, "name");
	  ArrayList<String> localities = new ArrayList<String>();
	  for(int i=0;i<l.size();i++)
	  {
		 localities.add((String)l.get(i));
	  }
	  return localities;
   }

   /**
    * @return
    * This is used to verify if the future date and time message is displayed. This is used for setting
    * a next date if the selected time has elapsed in the selected day.
    */
   private synchronized boolean isFutureDateAndTimeMessageDispalyed()
   {
	  boolean messageIsDispalyed = true;
	  try
	  {
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 futureDateAndTimeToolTip.isDisplayed();
	  }catch(Throwable t)
	  {
		 messageIsDispalyed = false;
	  }finally
	  {
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  }
	  return messageIsDispalyed;
   }

   /**
    * @param date
    * @return
    * This returns the next date by taking a date object
    */
   private synchronized String getFutureDate(Date date, int daysToBeAdded)
   {
	  log.info("Getting the future date");
	  Calendar cal = Calendar.getInstance();  
	  cal.setTime(date);  
	  cal.add(Calendar.DAY_OF_YEAR, daysToBeAdded);   
	  Date tomorrow = cal.getTime();  
	  SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	  String formatedDate = sf.format(tomorrow);
	  return formatedDate;
   }

   /**
    * @return
    * This returns a String representation of the current date.
    */
   private synchronized String getTodayDate()
   {
	  log.info("Getting current date");
	  Date now = new Date();  
	  SimpleDateFormat sf = new SimpleDateFormat("dd/MM/YYYY");
	  String formatedDate = sf.format(now);
	  return formatedDate;
   }

   /**
    * 
    * @return
    * This returns a boolean value based on whether pick up warning message is displayed. This message is 
    * displayed when we select wrong date for pick up
    */
   private synchronized boolean isPickUpWarningMessageDisplayed()
   {
	  boolean messageIsDispalyed = true;
	  try
	  {
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 pickUpTimeWarningMessage.isDisplayed();
	  }catch(Throwable t)
	  {
		 messageIsDispalyed = false;
	  }finally
	  {
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  }
	  return messageIsDispalyed;
   }

   /**
    * @param apikey
    * @param input
    * @param jsonkey
    * @return
    * This takes a json key value and returns the corresponding json value. If the api does not a key then
    * we can enter a null value
    */
   public synchronized List<Object> getValuesFromPriceAPI(String URL, String apikey, String input, String jsonkey)
   {
	  log.info("Getting the value from price api of the key " + apikey);
	  return new ParseAPIAndGetValue().getPostJSONValue(URL, apikey, input, jsonkey);
   }
   
   

   /**
    * @return
    * This is used for getting the set pick up locality
    */
   public synchronized String getpickUpLocality(String city, String tripType)
   {
	  
	  String pickUpLocality = null;
	  if(tripType.contains("Airport Transfer"))
	  {
		 log.info("Since trip type is of airport transfer ignoring the pick up locality since pick up locality is already pre populate");
		 pickUpLocality = pickUpAreaTextBox.getAttribute("value");
	  }else
	  {
		 log.info("Getting the pick up locality which is set for the trip " + tripType);
		 localities = getAllLocalities(city);
		 int pickUp = generateRandomNumber(localities.size(), 0);
		 pickUpLocality = localities.get(pickUp);
		
		 
		 log.info("The pick up locality is " + pickUp);
	  }
	  return pickUpLocality;
   }

   /**
    * @return
    * This is for getting the set drop locality 
    */
   public synchronized String getDropLocality(String city, String tripType)
   {
	  String dropLocality = null;
	  log.info("Getting the drop up locality which is set for the trip " + tripType);
	  if(tripType.contains("Airport Transfer"))
	  {
		 log.info("Since trip type is of airport transfer ignoring the drop locality since pick up locality is already pre populate");
		 dropLocality = pickUpAreaTextBox.getAttribute("value");
	  }else
	  {
		 localities = getAllLocalities(city);
		 int drop = generateRandomNumber(localities.size(), 0);
		 dropLocality = localities.get(drop); 
		 
		 log.info("Setting the drop locality as " + dropLocality);
	  }
	  return dropLocality;
   }

   

   /**
    * @param city
    * @return
    * This returns a list which has to be narrowed down to get the short name of the selected city
    */
   public synchronized ArrayList<Object> getCityShortName(String city)
   {
	  String query = new ReadPropertiesFile().getValue("RTFSQueries.properties", "getCityShortName");
	  log.debug("Executing query to get the city short for the city " + city);
	  String executeQuery = query.replace("cityName", city);
	  return new DataBaseUtils().executeQuery(executeQuery, "city_short_code");
   }

   /**
    * @param id
    * @param ekf
    * @return
    * This method returns a boolean value and is used for comparing the displayed price. This gets the price
    * from pricing api and can be used to checking the price displayed in front end
    */
   public synchronized boolean isPriceMatching(List<Object> id, List<Object> ekf, String bookingTypeValue, String city)
   {
	  log.info("Asseerting prices match");
	  boolean priceMatches = false;
	  WebDriverWait w = new WebDriverWait(driver, 5);
	  w.ignoring(StaleElementReferenceException.class);
	  w.until(ExpectedConditions.visibilityOf(tariffTable));
	  for(int i=0;i<id.size();i++)
	  {
		 String price;
		 if(bookingTypeValue.contains("km") && !(bookingTypeValue.contains("at-km")))
		 {
			log.info("Getting the price from fare table for trip type " + bookingTypeValue);
			price = driver.findElement(By.xpath("//tr[td[@id='"+ (String)id.get(i) +"']]/descendant::td[5]")).getText();
		 }else if(bookingTypeValue.equals("os"))
		 {
			log.info("Getting the price from fare table for trip type " + bookingTypeValue);
			price = driver.findElement(By.xpath("//tr[td[@id='"+ (String)id.get(i) +"']]/descendant::td[3]")).getText();

		 }else if((bookingTypeValue.equals("op")) & (city.equalsIgnoreCase("Bangalore")))
		 {
			log.info("Getting the price from fare table for trip type " + bookingTypeValue);
			price = driver.findElement(By.xpath("//tr[td[@id='"+ (String)id.get(i) +"']]["+ (i+1) + "]/descendant::td[6]")).getText();
		 }else if(bookingTypeValue.equals("op"))
		 {
			log.info("Getting the price from fare table for trip type " + bookingTypeValue);
			price = driver.findElement(By.xpath("//tr[td[@id='"+ (String)id.get(i) +"']]/descendant::td[6]")).getText();
		 }else if(bookingTypeValue.contains("at-km"))
		 {
			log.info("Getting the price from fare table for trip type " + bookingTypeValue);
			price = driver.findElement(By.xpath("//tr[td[@id='"+ (String)id.get(i) +"']]/descendant::td[4]")).getText();
		 }
		 else
		 {
			log.info("Getting the price from fare table for trip type " + bookingTypeValue);
			price = driver.findElement(By.xpath("//tr[td[@id='"+ (String)id.get(i) +"']]/descendant::td[4]")).getText();
		 }

		 Double extraKMFareInApplication = Double.parseDouble(price);
		 log.info("The extra km fare displayed in application is " + extraKMFareInApplication);
		 Double extraKMFareInAPI = Double.parseDouble((String) ekf.get(i));
		 log.info("The extra km fare displayed in api is " + extraKMFareInApplication);
		
		 if(extraKMFareInApplication.compareTo(extraKMFareInAPI) == 0)
		 {
			priceMatches = true;
			break;
		 }
	  }
	  return priceMatches;
   }

   /**
    * @param city
    * @return
    * This returns the area limits for a city. It is based on city limit the price for a trip is displayed in 
    * the front end. Based on the highest values between from and to locality the trip price will be decided.
    */
   public synchronized String getCityLimit(String city, String pickUpLocality, String dropLocality, String tripType)
   {
	  log.info("Getting the city limit");
	  List<Object> cityLimit = null;
	  cityLimit = new ArrayList<Object>();
	  List<Integer> in = null;
	  in = new ArrayList<Integer>();
	  String query = null;

	  query = new ReadPropertiesFile().getValue("RTFSQueries.properties", "getCityLimits");

	  String getFromLocality = query.replace("area", pickUpLocality);
	  String executeQueryForFromLocality = getFromLocality.replace("cityName", city);
	  cityLimit.add(new DataBaseUtils().executeQuery(executeQueryForFromLocality, "p2p_cityLimits"));

	  query = new ReadPropertiesFile().getValue("RTFSQueries.properties", "getCityLimits");
	  String getToLocality = query.replace("area", dropLocality);
	  String executeQueryForToLocality = getToLocality.replace("cityName", city);
	  cityLimit.add(new DataBaseUtils().executeQuery(executeQueryForToLocality, "p2p_cityLimits"));

	  for(int i=0;i<cityLimit.size();i++)
	  {
		 Object o = cityLimit.get(i);
		 
		 in.add(Integer.parseInt(String.valueOf(o).replace("[", "").replace("]", "")));
	  }

	  String str = String.valueOf(Collections.max(in));
	  return str;

   }
   
   
   /**
    * @param city
    * This is used to verify if cab is actually available for the selected date and time. If not then this 
    * method will select two other drop locations and sets the next date and then verifies if cab is available.
    * This will happen a max of 6 times. Beyond this the test case is failed stating unavailibity of cabs.
    */
   public synchronized boolean checkForCabAvailability(String tripType, String fromLocation, String cabType)
   {
	  log.info("Verifying if cab is available for the pick up locality " + fromLocation);
	  boolean cabIsAvaiable = true;

	  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(10, TimeUnit.SECONDS)
			.pollingEvery(2, TimeUnit.SECONDS)
			.ignoring(Throwable.class);
	  wait.until(ExpectedConditions.visibilityOf(checkCabAvailabilty));
	  String isCabAvailable = checkCabAvailabilty.findElement(By.xpath("//span[contains(@class,'lead')]")).getText();
	  if(isCabAvailable.contains("Cab is available"))
	  {
		 log.info("Hurray cab is avaliable...");
	  }
	  else if(isCabAvailable.contains("Cab not available"))
	  {
		 log.info("Cab not available message is displayed");
		 cabIsAvaiable = false;
		 String appUrl = new ReadPropertiesFile().getValue("Config.properties", "url");
		 log.info("Navigating to cab request screen");
		 driver.get(appUrl + "/call_center/agents-cab-request-list/");
		 new DoOperation().clickAlertOK(driver);
		 log.info("Clicking Accept in cab request screen");
		 WebElement element = driver.findElement(By.xpath("//tr[td[contains(text(),'"+fromLocation+"')] and td[text()='"+cabType+"']]/descendant::td/form/input[@value='Accept']"));
		 new DoOperation().click(driver, element);
		 try
		 {
			Thread.sleep(2000);
		 } catch (InterruptedException e)
		 {}
		 log.info("Navigating back to answer call screen");
		 driver.get(appUrl + "/answer-call/");
	  }else if(isCabAvailable.contains("Your request has been recorded"))
	  {
		 log.info("Your request has been recorded message is displayed");
		 cabIsAvaiable = false;
		 String appUrl = new ReadPropertiesFile().getValue("Config.properties", "url");
		 log.info("Navigating to cab request screen");
		 driver.get(appUrl + "/call_center/agents-cab-request-list/");
		 new DoOperation().clickAlertOK(driver);
		 WebElement element = driver.findElement(By.xpath("//tr[td[contains(text(),'"+fromLocation+"')]]/descendant::td/form/input[@value='Accept']"));
		 new DoOperation().click(driver, element);
		 try
		 {
			Thread.sleep(2000);
		 } catch (InterruptedException e)
		 {}
		 log.info("Navigating back to answer call screen");
		 driver.get(appUrl + "/answer-call/");
		 
	  } 
	  return cabIsAvaiable;
   }

   /**
    * 
    * @param cityShortName
    * @param cabtype
    * @param dateSet
    * @param pickupT
    * @param pickUpA
    * @param dropA
    * @param cityL
    * @return
    * This method returns a sting representation of the pricing api
    */
   public synchronized String getPriceApiString(String cityShortName, String cabtype,String bookingType,String dateSet, String pickupT, String pickUpA, String dropA, String cityL)
   {
	  String priceAPI = new ReadPropertiesFile().getValue("APIString.properties", "priceApi");
	  
	  priceAPI  = priceAPI.replace("setCityShortName", cityShortName).replace("cab", cabtype).
			replace("setDate", dateSet).replace("booking_Type", bookingType).replace("PickUpTime", pickupT).replace("PickUpArea", pickUpA).
			replace("DropArea", dropA).replace("CityLimits", cityL).replace("\"+", "");
	  log.info("Got the price api string " + priceAPI);
	  return priceAPI;
   }

   
   /**
    * @param city
    * @param pickUpDate
    * Method used to set the data for out station trip type
    */
   public synchronized void setDataForOutstationTravel(String city, String pickUpLocality, int i)
   {
	  log.info("Setting data for outsation travel");
	  new DoOperation().type(driver, pickUpAreaTextBox, pickUpLocality);
	  try
	  {
		 Thread.sleep(1000);
	  } catch (InterruptedException e)
	  {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
	  }
	  Actions act = new Actions(driver);
	  act.sendKeys(Keys.TAB).build().perform();
	  Date date = new Date();
	  String returnDate = getFutureDate(date, 7);
	  new DoOperation().type(driver, returnDateForOutstation, returnDate);
   }

   /**
    * @param city
    * @param pickUpDate
    * @param pickUpArea
    * Overloaded outstation trip type. Used in case there is no cab available message is displayed.
    */
   public synchronized void setDataForOutstationTravel(String pickUpArea)
   {
	  log.info("ReSetting data for outsation travel since there was no cab available");
	  new DoOperation().type(driver, pickUpAreaTextBox, pickUpArea);
	  try
	  {
		 Thread.sleep(2000);
	  } catch (InterruptedException e)
	  {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
	  }
	  Actions act = new Actions(driver);
	  act.sendKeys(Keys.TAB).build().perform();
	  Date date = new Date();
	  String returnDate = getFutureDate(date, 7);
	  new DoOperation().type(driver, returnDateForOutstation, returnDate);
   }

   /**
    * 
    * @param city
    * @param pickUpDate
    * @return
    * Method used to set the data for intercity travel, This will also return the first option under intercity travel drop down
    */
   public synchronized String setDataForInterCity(String pickUpLocality, int i)
   {
	  log.info("Setting data for Intercity travel");
	  new DoOperation().type(driver, pickUpAreaTextBox, pickUpLocality);
	  try
	  {
		 Thread.sleep(2000);
	  } catch (InterruptedException e)
	  {}
	  Actions act = new Actions(driver);
	  act.sendKeys(Keys.TAB).build().perform();
	  new DoOperation().selectInDropDownUsingIndex(driver, dropCityDropDown, 1);
	  return new DoOperation().getValueFromDropDown(driver, dropCityDropDown, 1);
   }

   /**
    * 
    * @param city
    * @param pickUpDate
    * @param pickUpArea
    * @return
    * Overloaded data for inter city method. This is used in case of no cab available message.
    */
   public synchronized String setDataForInterCity(String pickUpArea)
   {
	  log.info("ReSetting data for intercity travel since there was no cab available");
	  new DoOperation().type(driver, pickUpAreaTextBox, pickUpArea);
	  try
	  {
		 Thread.sleep(2000);
	  } catch (InterruptedException e)
	  {}
	  Actions act = new Actions(driver);
	  act.sendKeys(Keys.TAB).build().perform();
	  new DoOperation().selectInDropDownUsingIndex(driver, dropCityDropDown, 1);
	  return new DoOperation().getValueFromDropDown(driver, dropCityDropDown, 1);
   }

   /**
    * 
    * @param cityShortName
    * @param cabtype
    * @param bookingType
    * @param dateSet
    * @param pickupT
    * @param PickUpCity
    * @param DropCity
    * @param cityL
    * @return
    * This method returns a String value which is used to get information from pricing api for intercity. In intercity
    * we use pickupcity and drop city instead of pick up area and drop area.
    */
   public synchronized String getPriceAPIForIntercity(String cityShortName, String cabtype,String bookingType,String dateSet, String pickupT, String PickUpCity, String DropCity, String cityL)
   {
	  String priceAPI = new ReadPropertiesFile().getValue("APIString.properties", "priceApiForInterCity");
	  priceAPI =  priceAPI.replace("setCityShortName", cityShortName).replace("cab", cabtype).
			replace("setDate", dateSet).replace("booking_Type", bookingType).replace("PickUpTime", pickupT).replace("pickUP_City", PickUpCity).
			replace("drop_City", DropCity).replace("CityLimits", cityL).replace("\"+", "");
	  log.info("Getting the price api for intercity travel " + priceAPI);
	  return priceAPI;
   }

   
   static List<Object> list = new ArrayList<Object>();;
   /**
    * 
    * @param city
    * @return
    * This method gets all the types of booking option available for a city along with the cab types for each booking
    */
   public synchronized List<Object> getAllBookingOptions(String city)
   {
	  log.info("Getting all the booking type for " + city + " along with the cab type combinations");
	  Select sel = new Select(bookingTypeDropDown);
	  
	  List<WebElement> options = sel.getOptions();
	  for(int j =0;j<options.size(); j++) 
	  {
		 new DoOperation().selectInDropDown(driver, bookingTypeDropDown, options.get(j).getText());
		 List<WebElement> cab = car.findElements(By.tagName("button"));
			for(WebElement w : cab)
			{
			  
			   if(!(w.getAttribute("style").contains("display: none;")))
			   {
				  if(!(w.getAttribute("value").contains("ANY")))
			   	  {
					 String cabType = w.getAttribute("value");
					 list.add(city + "_" + options.get(j).getText() +  "_" + options.get(j).getAttribute("value") + "_" + cabType);
			   	  }
			   }
			}
	  }
	  return list;
   }
   
  /**
   * 
   * @param url
   * @return
   * Used to check if any value is displayed in pick up and drop area if airport transfer is selected as trip type
   */
   public synchronized boolean checkAirportNameDispalyed(String url)
   {
	  log.info("Verifying if airport name is displayed in pick up and drop area");
	  boolean airportNameMatches = false;
	  List<String> airportNameToBeDisplayed = new ParseAPIAndGetValue().getAirportName(url);
	  
	  if(airportNameToBeDisplayed.contains(pickUpAreaTextBox.getAttribute("value")) && airportNameToBeDisplayed.contains(dropAreaTextBox.getAttribute("value")))
	  {
		 log.info("The pick up and drop area contains " + airportNameToBeDisplayed);
		 airportNameMatches = true;
	  }else
	  {
		 log.info("The airport names displayed in the application does not match any of the airports displayed int airport api");
	  }
	  return airportNameMatches;
   }
   
   /** Get airpotName api string */
   public synchronized String getAirportNameAPIURL(String cityName)
   {
	  log.debug("Getting the sting for fetching the airport names from airport api");
	  String query = new ReadPropertiesFile().getValue("RTFSQueries.properties", "getCityID").replace("cityname", cityName);
	  String cityid = (String) new DataBaseUtils().executeQuery(query, "city_id").get(0);
	  return new ReadPropertiesFile().getValue("APIString.properties", "airportName").replace("cityID", cityid);
   }
   
   /** 
    * @return phoneNumber
    * This returns the phone number which was set for the customer. This is used when we create one more booking for the same customer
    */
   public synchronized String getTheCustPhNumberWhichWasSet()
   {
	   return phoneNumberTextField.getAttribute("value");
   }
   
   /**
    * @return
    * This returns the trip type that was selected for the first booking. Use this only for create one more booking flow.
    */
   public synchronized String getTheSelectedBookingType()
   {
	   return new DoOperation().getTheTextWhichWasSelectedInDropdown(driver, bookingTypeDropDown);
   }
   
   /**
    * @return
    * This returns the pick up are which was selected when creating the first booking. Use this in create one more booking flow
    */
   public synchronized String getThePickUpAreaWhichWasSet()
   {
	   return pickUpAreaTextBox.getAttribute("value");
   }
   
   
   /**
    * @return
    * This returns the drop area which was set when creating a new booking. Use this in create one more booking flow.
    */
   public synchronized String getTheDropAreaWhichWasSet()
   {
	   return dropAreaTextBox.getAttribute("value");
   }
   
   
   /**
    * @param cabTypeValue
    * @return
    * This method returns a boolean value depending on if the selected cab type is selected again for create one more booking flow
    */
   public synchronized boolean isTheChosenCabStillSelected(String cabTypeValue)
   {
	   boolean cabIsSelected = false;
	   String selectedCarClassAttribute = car.findElement(By.xpath("//button[@value='"+cabTypeValue+"']")).getAttribute("class");
	   if(selectedCarClassAttribute.contains("active"))
	   {
		   cabIsSelected = true;
	   }
	   return cabIsSelected;
   }
   
   
   
   
}

