package com.taxiforsure.projectCommonLib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.taxiforsure.commonLib.BDriver;
import com.taxiforsure.pagelib.Login;
import com.taxiforsure.pagelib.SelectCityPage;
import com.taxiforsure.pagelib.SelectCityPage;

@SuppressWarnings("unused")
public class ChoseCity  {
	 //Login loginPage = PageFactory.initElements(BDriver.driver, Login.class);
//	 SelectCity cityPage = PageFactory.initElements(BDriver.driver,
//			SelectCity.class);
	
	public   void SelectCity(String cityName) {
		List<WebElement> allCities=SelectCityPage.allCities();
		
		int count=allCities.size();
		for(int i=0;i<count;i++){
			if(allCities.get(i).getText().contains(cityName)){
				allCities.get(i).click();
				break;
			}
		}
		 /*if (cityAll.)) {
				 SelectCity.selectCityAhmedabad().click();
			} else if (cityName.equals("Bangalore")) {
				SelectCity.selectCityBangalore().click();;
			} else if (cityName.equals("Chennai")) {
				 SelectCity.selectChennai().click();
			} else if (cityName.equals("Delhi")) {
				 SelectCity.selectChennai().click();;
			} else if (cityName.equals("Hyderabad")) {
				 SelectCity.selectCityHyd().click();
			

		}else{}
		
		}*/
		
	}

}
