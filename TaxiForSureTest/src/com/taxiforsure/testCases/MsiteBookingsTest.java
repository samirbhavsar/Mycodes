package com.taxiforsure.testCases;





import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.taxiforsure.commonLib.BaseClass;
import com.taxiforsure.commonLib.DoOperation;
import com.taxiforsure.commonLib.Drivers;
import com.taxiforsure.commonLib.WebDriverCommonLib;
import com.taxiforsure.pagelib.TfsMsitePage;
import com.taxiforsure.projectCommonLib.ProjectCommonUtils;
import com.taxiforsure.utility.ReadDataFromExcel;
import com.taxiforsure.utility.ReadPropertiesFile;

public class MsiteBookingsTest {

	//Properties prop = new Properties();;
	WebDriver driver;
	WebDriverCommonLib wlib;
	ProjectCommonUtils plib;
	TfsMsitePage tms;
	Actions action;
	ReadDataFromExcel excelRead;
	ReadPropertiesFile prop=new ReadPropertiesFile();
	String allertMsg="cancelled";
	@BeforeClass
	public void beforeClass() throws Exception {

		// String
		// mapFile=System.getProperty("user.dir")+"//msiteObjectRepo.properties";
		

		
		driver= Drivers.OpenBrowser();
		new BaseClass(driver);
		wlib=new WebDriverCommonLib(driver);
		plib=new ProjectCommonUtils(driver);
		tms=PageFactory.initElements(driver, TfsMsitePage.class);
		driver.get(prop.getValue("loginInfo.properties", "msiteUrlProd"));
		excelRead=new ReadDataFromExcel();
		action=new Actions(driver);
		Thread.sleep(3000);
		DoOperation.shareLocation();
//		WebElement closeBtn=driver.findElement(By.xpath("//div[1]/div[4]/div[1]/div/span"));
//		closeBtn.click();
		Thread.sleep(3000);
		String username=prop.getValue("loginInfo.properties", "username");
		String password=prop.getValue("loginInfo.properties", "password");
		
		tms.loginToMsite(username, password);
		
		Thread.sleep(5000);
	}
	
	/*@BeforeMethod
	public void beforeMethodConfig() throws InterruptedException{
		
		
	}*/

	@DataProvider(name = "TC1")
	public Object[][] getData() throws Exception {
		return excelRead.testData("MsiteTC_1");
	}
	
	@Test(dataProvider="TC1", priority=1,enabled=false,timeOut=300)
	public void mSiteBookingNext60MinsTest(String city,String picLoc,String dropLoc,String mobileNo) throws Exception {

		if(!wlib.getSelectedOption(tms.city).equalsIgnoreCase(city)){
			wlib.selectByVisibleText(tms.city, city);
			}
	
		
	
		tms.pickUpLoc.clear();
		tms.pickUpLoc.sendKeys(picLoc);
		Thread.sleep(3000);
		tms.pickUpLoc.sendKeys(Keys.ARROW_DOWN);
		
		Thread.sleep(3000);
		tms.pickUpLoc.sendKeys(Keys.RETURN);
		//tms.click_Loc.click();
		Thread.sleep(3000);
		tms.DropLoc.clear();
		tms.DropLoc.sendKeys(dropLoc);
		tms.DropLoc.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		tms.DropLoc.sendKeys(Keys.RETURN);
		Thread.sleep(5000);
		tms.now.click();
		
		wlib.selectByIndex(tms.selectCar, 2);
		//tms.now.click();
		Thread.sleep(5000);
		tms.customer_number.sendKeys(mobileNo);
		Thread.sleep(3000);
		tms.bookTaxiBtn.click();
		
		 
		Thread.sleep(3000);
		 if(DoOperation.isAllertPresent(driver)){
			 DoOperation.clickAlertCancel(driver);
		 }
		 
		
		 Thread.sleep(3000);
		 /* Thread.sleep(3000);
		 tms.cancel_BookingIkon.click();
		 if(DoOperation.isAllertPresent(driver)){
			 DoOperation.clickAlertOK(driver);
			 Thread.sleep(2000);
			Assert.assertTrue(allertMsg.contains(DoOperation.getAlertText(driver)));
			 Thread.sleep(2000);
			DoOperation.clickAlertOK(driver);
			 
			 
		 }*/
	}

	@DataProvider(name = "TC2")
	public Object[][] getTestData() throws Exception {
		return excelRead.testData("MsiteTC2");
	}

	@Test(dataProvider = "TC2", priority = 2, enabled = true)
	public void mSiteBookingAfter60MinsTest(String city, String picLoc,
			String dropLoc, String date, String time, String mobileNo)
			throws Exception {

		if (!wlib.getSelectedOption(tms.city).equalsIgnoreCase(city)) {
			wlib.selectByVisibleText(tms.city, city);
		}
		/*
		 * tms.pickUpLoc.sendKeys("Koramangala Layout, Bangalore, Karnataka");
		 * Thread.sleep(3000); tms.click_Loc.click(); Thread.sleep(3000);
		 * tms.DropLoc.sendKeys("Marathahalli, Bangalore, Karnataka");
		 * Thread.sleep(3000);
		 * driver.findElement(By.xpath("html/body/div[4]/div/span[2]/span"
		 * )).click(); Thread.sleep(5000);
		 */

		// System.out.println(wlib.getSelectedOption(tms.city));
		tms.pickUpLoc.clear();
	//	wlib.highlightElement(driver, tms.pickUpLoc,"red");
		Thread.sleep(4000);
		tms.pickUpLoc.sendKeys(picLoc);
		//System.out.println(DoOperation);
		Thread.sleep(3000);
		tms.pickUpLoc.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		tms.pickUpLoc.sendKeys(Keys.RETURN);
		// tms.click_Loc.click();
		Thread.sleep(3000);
		tms.DropLoc.clear();
		tms.DropLoc.sendKeys(dropLoc);
		tms.DropLoc.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		tms.DropLoc.sendKeys(Keys.RETURN);
		Thread.sleep(3000);
		tms.after60.click();
		Thread.sleep(10000);
		tms.datePicker.click();
		tms.calNext.click();
		WebDriverCommonLib.findElementByText(tms.dates, date).click();
		Thread.sleep(5000);
		tms.timePicker.click();
		WebDriverCommonLib.findElementByText(tms.times, time).click();
		Thread.sleep(5000);
		wlib.selectByIndex(tms.selectCar, 2);
		tms.customer_number.clear();
		tms.customer_number.sendKeys(mobileNo);
		Thread.sleep(3000);

		// tms.loginIkon.click();

		// driver.findElement(By.cssSelector(selector))
		 tms.bookTaxiBtn.click();
		 
		Thread.sleep(3000);
		 if(DoOperation.isAllertPresent(driver)){
			 DoOperation.clickAlertCancel(driver);
		 }
		 
		 Drivers.takeScreenShot(driver);
		 
		// tms.img_HomePage.click();
		 
		 Thread.sleep(3000);
		 tms.cancel_BookingIkon.click();
		 if(DoOperation.isAllertPresent(driver)){
			 DoOperation.clickAlertOK(driver);
			 Thread.sleep(2000);
			//Assert.assertTrue(allertMsg.contains(DoOperation.getAlertText(driver)));
			 System.out.println(DoOperation.getAlertText(driver).split(" ")[3]);
			 Thread.sleep(2000);
			DoOperation.clickAlertOK(driver);
			 
			 
		 }
	}

	/*@Test(priority=3,enabled=false)
		
	public void cancelBookingTest(){
		tms.goToBookings();
		
		
		
	}*/
	



	@AfterClass
	public void afterClass() {
		
	//	driver.quit();
		
		//tms.logoutFromApp();
		
	}

}
