package org.taxiforsure.rtfs;

import org.openqa.selenium.WebDriver;
import org.taxiforsure.notifications.NotificationsScreen;
import org.taxiforsure.rtfs.login.Login;
import org.taxiforsure.rtfscommonelements.CommonElements;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.taxiforsure.driver.MyDriver;

public class TestGrid
{
   WebDriver driver ;
   
   @BeforeMethod
   public void setup()
   {
	  System.out.println("Inside before");
	  driver = new MyDriver().getDriver(); 
   }
   
   
   @AfterMethod
   public void teardown()
   {
	  System.out.println("Inside after");
//	  new MyDriver().quitBrowser(driver);
	  
   }
   
   @DataProvider(name = "test", parallel = true)
   public Object[][] getData()
   {
	  System.out.println("Inside data");
	  Object obj[][] = new Object[2][1];
	  obj[0][0] = 1;
	  obj[1][0] = 2;
	  return obj;
   }
   
   @Test(dataProvider = "test")
   public void testing(int i)
   {
	  System.out.println(i);
	  new Login(driver).doLogin("vinay.b", "tfs123");
	  new NotificationsScreen(driver).selectCity("Bangalore");
	  System.out.println("Banglore");
	  new CommonElements(driver).clickAnswerCallLink();
	  
   }
   
  

}
