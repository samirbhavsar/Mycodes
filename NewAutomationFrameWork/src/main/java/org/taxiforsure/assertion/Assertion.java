package org.taxiforsure.assertion;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;


  
  /**
   * This class is used for asserting. We have used soft assert
   * 
   * @author vinay.prasanna
   *	
   */			
public class Assertion
{
   Logger log = Logger.getLogger("Assertion");


   /**
    * This method is used to assert a string value
    * 
    * @param actual
    * @param expected
    * @param message
    */
   public void assertString(String actual, String expected, String message)
   {
	  Assert.assertEquals(actual, expected, message);
	  log.info("The actual " + actual + " and expected " + expected + " matches");
   }

   /**
    * This is used to assert if web element exists or not
    * 
    * @param element
    * @param message
    */
   public void assertWebElementExists(WebDriver driver, WebElement element, String message)
   {
	  FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
	  fluent.ignoring(ElementNotVisibleException.class);
	  fluent.withTimeout(10, TimeUnit.SECONDS);
	  fluent.pollingEvery(2, TimeUnit.SECONDS);
	  fluent.until(ExpectedConditions.visibilityOf(element));

	  boolean elementStatus = false;
	  if (element.isDisplayed())
	  {
		 elementStatus = true;
	  }
	  Assert.assertEquals(elementStatus, true, message);
	  log.info("The element " + element + " is dispalyed");
   }

   /**
    * This is used to assert true condition
    * 
    * @param expected
    * @param message
    */
   public void assertTrue(boolean expected, String message)
   {
	  Assert.assertTrue(expected, message);
	  log.info("The expected condition matches with the actual condition");
   }

   /**
    * This is used to assert false condition
    * 
    * @param expected
    * @param message
    */
   public void assertFalse(boolean expected, String message)
   {
	  Assert.assertFalse(expected, message);
	  log.info("The expected condition matches with the actual condition");
   }

   /**
    * This is used for asserting intiger values
    * 
    * @param actual
    * @param expected
    * @param message
    */
   public void assertInt(int actual, int expected, String message)
   {
	  Assert.assertEquals(actual, expected, message);
	  log.info("The expected integer '" + expected + "' matches with the '" + actual + "' actual integer");
   }

   /**
    * This is used to assert doubles
    * @param actual
    * @param expected
    * @param message
    */
   public void assertDouble(double actual, double expected, String message)
   {
	  Assert.assertEquals(actual, expected, message);
	  log.info("The expected double '" + expected + "' matches with the '" + actual + "' actual double");
   }

}
