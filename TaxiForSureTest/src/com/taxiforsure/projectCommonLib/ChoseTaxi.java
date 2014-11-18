package com.taxiforsure.projectCommonLib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.taxiforsure.commonLib.BDriver;
import com.taxiforsure.pagelib.SelectTaxiPage;

public class ChoseTaxi {
	static SelectTaxiPage selTaxi=PageFactory.initElements(BDriver.driver, SelectTaxiPage.class);
	
	
	public static void selectTaxi(String taxiName){
		
		List<WebElement> taxiSel= selTaxi.carList();
		for(WebElement nTaxi:taxiSel){
			if(nTaxi.getText().equals(taxiName)){
				//if(!nTaxi.isSelected())
				nTaxi.click();
				break;
			}
		}
		
	}

}
