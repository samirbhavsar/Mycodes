package org.taxiforsure.bookingdetails;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.taxiforsure.actions.DoOperation;
import org.taxiforsure.utils.DataBaseUtils;
import org.taxiforsure.utils.ParseAPIAndGetValue;
import org.taxiforsure.utils.ReadPropertiesFile;

/**
 * 
 * @author tfs-vinay
 * @version 1.0
 * This class will be used to perform actions with booking details screen
 */

public class BookingDetailsScreen 
{
	
	WebDriver driver;
	private static Object bookingInfoAPIJson = null;
	
	public BookingDetailsScreen(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cnt_main']")
	private WebElement bookingTable;
	
	@FindBy(xpath = "//button[text()='Create One More Booking']")
	private WebElement createOneMoreBookingButton;
	
	/* This will return the booking id from booking details screen */
	public synchronized String getBookingID()
	{
		return driver.findElement(By.xpath("//th[text()='Booking ID']/following-sibling::td")).getText();
	}
	
	/* This will return the booking id from data base */
	public synchronized String getBookingIdFromDB(String phoneNo)
	{
		String query = new ReadPropertiesFile().getValue("RTFSQueries.properties", "getBookingID");
		ArrayList<Object> l = new DataBaseUtils().executeQuery(query.replace("phoneNo", phoneNo), "booking_id");
		return (String)l.get(0);
	}
	
	public synchronized String getCustomerInfoFromDB(String customerInfo, String bookingID)
	{
	   String query = new ReadPropertiesFile().getValue("RTFSQueries.properties", "getCustomerInfo");
	   query = query.replace("customerInformation", customerInfo).replace("bookingId", bookingID);
	   
	   return (String)new DataBaseUtils().executeQuery(query, customerInfo).get(0);
	   
	}
	

	
	/**
	 * 
	 * @param URL
	 * @param apikey
	 * @param input
	 * @param jsonkey
	 * @param city
	 * @return
	 * This modifies the values got from pricing API and returns a string representation of the specified api key
	 */
	
	public synchronized String getValueFromPriceAPI(String URL,String apikey, String input, String jsonkey, String city, String cabType)
	{
		String value = null;
		try
		{
      		List<Object> l = getValueFromPriceAPI(URL, apikey,input,jsonkey);
      		if(city.equalsIgnoreCase("Bangalore") & !(cabType.contains("Sedan")))
      		{
      			value =  (String)l.get(1);
      		}else
      		{
      			value =  (String)l.get(0);
      		}
		}catch(NullPointerException n)
		{
		   throw new RuntimeException("There is some problem with the returned price API and the returned API was - " + input);
		}
		return value;
	}
	
	/**
	 *  
	 * @param URL
	 * @param apikey
	 * @param input
	 * @param jsonkey
	 * @return
	 * This returns a list of values from pricing API 
	 */
	private synchronized List<Object> getValueFromPriceAPI(String URL, String apikey, String input, String jsonkey)
	{
		return new ParseAPIAndGetValue().getPostJSONValue(URL, apikey, input, jsonkey);
	}
	
	public synchronized String getBookingDetailsJson(String URL)
	{
	  return  new ParseAPIAndGetValue().getBookingDetailsRequest(URL);
	}
	
	public synchronized Object getValueFromBookingInfoAPI(String jasonResponse, String jsonkey)
	{
	  
		  bookingInfoAPIJson =  new ParseAPIAndGetValue().getBookingDetailsFromJSON(jasonResponse, jsonkey);	   
		  return bookingInfoAPIJson;
	}
	
	public synchronized void clickOneMoreBookingButton()
	{
		new DoOperation().click(driver, createOneMoreBookingButton);
	}
	
	
}
