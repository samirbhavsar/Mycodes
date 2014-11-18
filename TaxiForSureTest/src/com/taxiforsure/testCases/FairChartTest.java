package com.taxiforsure.testCases;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import com.taxiforsure.commonLib.BaseClass;
import com.taxiforsure.commonLib.Drivers;
import com.taxiforsure.commonLib.WebDriverCommonLib;
import com.taxiforsure.pagelib.FairChatPage;
import com.taxiforsure.pagelib.SelectCityPage;
import com.taxiforsure.projectCommonLib.ProjectCommonUtils;
import com.taxiforsure.utility.ReadDataFromExcel;



public class FairChartTest {
	WebDriver driver;
	FairChatPage fcp;
	SoftAssert softAssert=new SoftAssert();
	ReadDataFromExcel excelRead;
	WebDriverCommonLib wlib;
	ProjectCommonUtils plib;
	
	@BeforeClass
	public void initConfig() throws Exception{
		driver=Drivers.OpenBrowser();
		new BaseClass(driver);
		wlib = new WebDriverCommonLib(driver);
		plib = new ProjectCommonUtils(driver);
		excelRead = new ReadDataFromExcel();
		
		fcp=PageFactory.initElements(driver, FairChatPage.class);
		plib.openUrl();
		
		 WebDriverCommonLib.findElementByText(SelectCityPage.allCities(), "Bangalore").click();
		 fcp.fareChartLink.click();
		String xpath1="//div[@id='dayFare']/ul/li[1]/label[1]/following-sibling::div/descendant::td[div[@class='taxiInfo']/div/p[text()='";
		String xpath2="']]/following-sibling::td/div[1]";
	}
	
	@DataProvider(name = "fairChartDay")
	public Object[][] getData() throws Exception {
		return excelRead.testData("FCDayPointToPoint");
	}
	
	
	@Test(dataProvider="fairChartDay")//String city,String type,String carname,String time,String fare
	public void fairChartPointToPointDayTest(String city,String type,String carname,String time,String fare) throws InterruptedException, InvalidFormatException, IOException{
		
		Thread.sleep(3000);
		
		if(time.equals("day-fare")&& type.contains("Point to Point")){
			fcp.dayIcon.click();
			fcp.pToPdayTab.click();
			
		}
		
		if(!city.equals(fcp.cityName.getText())){
			fcp.cityDropDown.click();
			 WebDriverCommonLib.findElementByText(fcp.cityList,city).click();
		}
		
		
		// Thread.sleep(3000);
	//	fcp.pToPdayTab.click();
		Thread.sleep(3000);
		
		Assert.assertEquals(
				WebDriverCommonLib.findElementByText(fcp.pToPTaxilistDay,
						carname	).getText(), carname);
		Assert.assertEquals(plib.getcarCostDay(carname),fare);
		
		//System.out.println(plib.getcarCostDay(carname));
		
//		System.out.println(WebDriverCommonLib.findElementByText(fcp.pToPTaxilistDay,
//						"Tata Indica Non-AC").getText());
//		
		
		
	}
	@Test
	
	@AfterTest
	public void tearDown(){
		
		fcp.closeBtn.click();
		driver.close();
	}
	

}
