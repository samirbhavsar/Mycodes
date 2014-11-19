package org.taxiforsure.bookingtypes;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.taxiforsure.actions.DoOperation;
import org.taxiforsure.answercall.TestAC;
import org.taxiforsure.notifications.NotificationsScreen;
import org.taxiforsure.rtfs.login.Login;
import org.taxiforsure.rtfscommonelements.CommonElements;

public class BookingTypes
{

   public synchronized Object[][] getAllBookingTypesForCity(WebDriver driver, String... city)
   {
	  new Login(driver).doLogin("vinay.b", "vin_1984");
	  List<Object> list = new ArrayList<Object>();
	 
	  for(int i=0;i<city.length;i++)
	  {
		 new NotificationsScreen(driver).selectCity(city[i]);
		 new CommonElements(driver).clickAnswerCallLink();
		 list = new TestAC(driver).getAllBookingOptions(city[i]);
	
		 driver.navigate().back();
		 
		 new DoOperation().clickAlertOK(driver);
		 driver.navigate().refresh();
	  }
	  
	  Object[][] o = new Object[list.size()][1];
	  for(int row =0;row<list.size();row++)
	  {
		 for(int col =0; col<1; col++)
		 {
			
				  o[row][col] = list.get(row);
				  
			   
		 }
	  }
	  return o;
   }
   
 
}
