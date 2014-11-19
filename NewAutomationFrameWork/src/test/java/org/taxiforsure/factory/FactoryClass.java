package org.taxiforsure.factory;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.taxiforsure.bookingtypes.BookingTypes;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import com.taxiforsure.driver.MyDriver;


public class FactoryClass
{

   @Factory(dataProvider = "getTestData")
   public Object[] createInstance(String testData)
   {
	  ArrayList<Object> ar = new ArrayList<Object>();
	  Object[] res = null;

	  ar.add(new org.taxiforsure.rtfs.TestVerifyNightFutureBooking(testData));
	  res = new Object[ar.size()];
	  res = ar.toArray();
	  return res;
   }

   @DataProvider()
   static public Object[][] getTestData()
   {
	  System.out.println("Inside data");
	  WebDriver driver = new MyDriver().getDriver();
	  Object obj[][] = new BookingTypes().getAllBookingTypesForCity(driver, "Aurangabad");
	  new MyDriver().quitBrowser(driver);

	  return obj;
   }
}
