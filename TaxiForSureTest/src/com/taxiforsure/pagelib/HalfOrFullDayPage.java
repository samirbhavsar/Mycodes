package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HalfOrFullDayPage {

	@FindBy(xpath=".//*[@id='id_packages_name']")
	private WebElement packageTxt;
	
	@FindBy(xpath="//div[@class='confirmedContain cf']/div[@class='cchalf'][1]/div[1]/div[2]")
	private WebElement getPackageTxt;
	
	@FindBy(xpath="//dl[@class='cityDrop forPackage']/dd/ul[@class='js_os_packs']/li/a")
	private List<WebElement> packageList;
	
	@FindBy(xpath="//ul[contains(@id,'ui-id')]/li/a")
	private List<WebElement> pickList2;
	
	@FindBy(xpath=".//*[@id='pickArea']")
	private WebElement pickArea2;
	
	public WebElement pickHalfFullArea(){
		return pickArea2;
	}
	public WebElement packageTxtBox(){
		return  packageTxt;
	}
	public WebElement getPkgTxt(){
		return getPackageTxt;
	}
	public  List<WebElement>getPackageList(){
		return packageList;
	}
	public List<WebElement> pickUpAreaList() {
		// TODO Auto-generated method stub
		return pickList2;
	}
}
