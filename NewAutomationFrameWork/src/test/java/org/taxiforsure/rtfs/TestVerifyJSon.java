package org.taxiforsure.rtfs;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.taxiforsure.actions.DoOperation;
import org.taxiforsure.answercall.AnswerCallScreen;
import org.taxiforsure.notifications.NotificationsScreen;
import org.taxiforsure.rtfs.login.Login;
import org.taxiforsure.rtfscommonelements.CommonElements;
import org.taxiforsure.utils.DataBaseUtils;
import org.taxiforsure.utils.ReadPropertiesFile;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.taxiforsure.driver.MyDriver;

public class TestVerifyJSon
{
   WebDriver driver;
   String city = "Aurangabad" ;
   String priceAPIURL = "http://api1.rtfs.in/pricingV2/api/pricingV2/postRequest";
   String customerInfoAPIURL = new ReadPropertiesFile().getValue("Config.properties", "url");
   String bookingType = null;


 

   @Test() 
   public void testVerifyHatchBackDay()
   {
	 ArrayList<Object> s = new DataBaseUtils().executeQuery("select booking_id from taxi_dispatch.tfs_booking where customer_number = '9123456788' order by booking_id desc;", "booking_id");
	 System.out.println("value " +s.get(0));
   }






  


}
