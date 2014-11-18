package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.taxiforsure.commonLib.BDriver;
import com.taxiforsure.commonLib.WebDriverCommonLib;

@SuppressWarnings("unused")
public class SelectCityPage extends WebDriverCommonLib{
/*
	@FindBy(id="city")
	private WebElement city;
	
	@FindBy(xpath=".//*[@id='cm']")
	private WebElement cipyPopUp;

	@FindBy(xpath="//*[@id='cm']/ul/li/a")
	private List<WebElement> cities;
	
	
	public List<WebElement> allCities(){
		return cities;
	}
	public WebElement cityPopUp(){
		return cipyPopUp;*/
	
public SelectCityPage(WebDriver driver) {
		super(driver);
		
	}
	//	public static WebElement cityLnk() {
//		WebElement element=driver.findElement(By.id("city"));
//		return element;
//	}
	public static WebElement cityLink(){
		WebElement element=driver.findElement(By.id("city"));
		return element;
	}
	public static List<WebElement> allCities(){
		List<WebElement> element=driver.findElements(By.xpath("//*[@id='cm']/ul/li/a"));//.//*[@id='cm']/ul/li/a
		return element;
	}
	
	public static List<WebElement> allCityLinks(){
		List<WebElement> element=driver.findElements(By.xpath("//div[@class='searchCityWap']/ul/li/a"));
		
		return element;
	}
	
	public static WebElement cityPopUp(){
		WebElement element=driver.findElement(By.xpath("//div[@class='searchCityWap']"));
		return element;
	}

	}
