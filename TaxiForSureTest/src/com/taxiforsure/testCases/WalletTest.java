package com.taxiforsure.testCases;



import java.math.BigDecimal;
import java.math.BigInteger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;







import com.taxiforsure.commonLib.BaseClass;
import com.taxiforsure.commonLib.DoOperation;
import com.taxiforsure.commonLib.Drivers;
import com.taxiforsure.commonLib.WebDriverCommonLib;
import com.taxiforsure.pagelib.Login;
import com.taxiforsure.pagelib.SelectCityPage;
import com.taxiforsure.pagelib.SelectLocation;
import com.taxiforsure.pagelib.WalletPage;
import com.taxiforsure.projectCommonLib.ProjectCommonUtils;

public class WalletTest {
	
	WebDriver driver;
	Login login;
	WebDriverCommonLib wlib;
	ProjectCommonUtils plib;
	SelectLocation selLocation;
	WalletPage wlp;
	
	@BeforeClass
	public void beforeClassConfig() throws Exception{
		
		driver=Drivers.OpenBrowser();
		new BaseClass(driver);
		
		wlib=new WebDriverCommonLib(driver);
		plib=new ProjectCommonUtils(driver);
		login=PageFactory.initElements(driver, Login.class);
		selLocation=PageFactory.initElements(driver, SelectLocation.class);
		wlp=PageFactory.initElements(driver, WalletPage.class);
		plib.openUrl();

		// String cityName="Bangalore";
		//cCity.SelectCity("Bangalore");
		// WebDriverCommonLib.findElementByText(SelectCityPage.allCities(), "Bangalore").click();
		DoOperation.click(driver, SelectCityPage.allCities(), "Bangalore");
		plib.loginToApp();
		Thread.sleep(10000);
		wlib.waitForWebElementFluently(selLocation.showUser());
	}
	
	@Test
	public void walletTest() throws InterruptedException{
		
		
		DoOperation.mouseOverAction(driver, login.myAccount, login.bookingsLink);
		
	//	int waletBalance=Integer.parseInt(wlp.waletBalance.getText());
		
		DoOperation.click(driver, wlp.btn_Recharge);
		DoOperation.type(driver, wlp.txt_refuelAmt, "100");
		DoOperation.click(driver, wlp.btn_Refill);
		DoOperation.type(driver, wlp.cardName, "test");
		Thread.sleep(3000);
		
		//String cardNumber=new BigDecimal().toPlainString();
		//DoOperation.type(driver, wlp.cardNumber, "5123456789012340");
		/*wlp.cardNumber.sendKeys("5123");
		wlp.cardNumber.sendKeys("4567");
		wlp.cardNumber.sendKeys("8901");
		wlp.cardNumber.sendKeys("2346");*/
		DoOperation.typeLongString(driver, wlp.cardNumber, "5123 4567 8901 2346");
		Thread.sleep(3000);
		DoOperation.click(driver,wlp.month_Expire);
		Thread.sleep(3000);
		DoOperation.click(driver, wlp.months, "May");
		Thread.sleep(3000);
		DoOperation.click(driver, wlp.expireYear);
		Thread.sleep(3000);
		DoOperation.click(driver, wlp.years, "2017");
		Thread.sleep(3000);
		DoOperation.type(driver, wlp.cvvNumber, "123");
		
		DoOperation.click(driver, wlp.btn_Pay);
		
		Thread.sleep(10000);
		/*if(DoOperation.isAllertPresent(driver)){
			DoOperation.clickAlertOK(driver);
		}else{
			//Assert.fail("There are some Errors on the page : ");
		}*/
		String rechargedBal=wlp.paidBalance.getText().split("\\.")[1];
		//Assert.assertEquals(rechargedBal.trim(), "100".trim(),"Recharged :"+"100"+" But found: "+rechargedBal);
		
		DoOperation.click(driver, wlp.btn_OK);
		
		DoOperation.click(driver, wlp.creditLink);
		Thread.sleep(2000);
		DoOperation.highLightElement(driver, wlp.currentRechargedAmt, "red");
		//Assert.assertEquals(DoOperation.getText(driver,wlp.currentRechargedAmt).trim(),rechargedBal);
		
		
		
		
		
		
	}
	
//	
//	@AfterMethod
//	public void configAfterMethod(ITestResult result){
//		
//		if(result.isSuccess()){
//			Drivers.takeScreenShot(result, driver);
//		}
//	}
		
	
	@AfterClass
	public void closeBrowser(){
		//DoOperation.mouseOverAction(driver, login.myAccount, login.logoutLink);
		driver.quit();
	}
	
		
		
	

}
