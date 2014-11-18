package com.taxiforsure.commonLib;

import java.awt.AWTException;

import org.apache.commons.io.FileUtils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.thoughtworks.selenium.Wait;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class WebDriverCommonLib extends BaseClass {

	public WebDriverCommonLib(WebDriver driver) {
		super(driver);
		
	}

	WebElement element = null;
	
	File destPath = new File(
			"E:\\TaxiForSureProject\\TaxiForSureTest\\src\\com\\taxiforsure\\screenshots\\failure.jpg");

	public void waitForPageToLoad(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	public void waitForLinkPresent(WebDriver driver,String linkText) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.linkText(linkText)));

	}

	public void waitForWebElementFluently(WebElement webElement) {
	    new FluentWait<WebElement>(webElement).
	            withTimeout(20, TimeUnit.SECONDS).
	            pollingEvery(10, TimeUnit.MILLISECONDS).
	            ignoring(NoSuchElementException.class).
	            until(new Predicate<WebElement>() {
	            	@Override
	                public boolean apply(WebElement element) {
	                    return element.isDisplayed();
	                }
	            }
	            );
	}
	
	 public static ExpectedCondition<WebElement> visibilityOf(
		      final WebElement element) {
		    return new ExpectedCondition<WebElement>() {
		      @Override
		      public WebElement apply(WebDriver driver) {
		        return elementIfVisible(element);
		      }

		      @Override
		      public String toString() {
		        return "visibility of " + element;
		      }
		    };
		  }
	 
	 private static WebElement elementIfVisible(WebElement element) {
		    return element.isDisplayed() ? element : null;
		  }
	
	public void isWebElementEnabled(WebElement webElement) {
	    new FluentWait<WebElement>(webElement).
	            withTimeout(20, TimeUnit.SECONDS).
	            pollingEvery(10, TimeUnit.MILLISECONDS).
	            ignoring(NoSuchElementException.class).
	            until(new Predicate<WebElement>() {
	                @Override
	                public boolean apply(WebElement element) {
	                    return element.isEnabled();
	                }
	            }
	            );
	}
	
	
	public void selectTextFromOptions(WebElement element,String text){
		
		Select sel = new Select(element);
		List<WebElement> options=sel.getOptions();
		for(WebElement option:options){
			if(text.contains(option.getText())){
				sel.selectByVisibleText(text);
				break;
			}
				
		}
		
	}
	
	 /*public static ExpectedCondition<WebElement> visibilityOfElementLocated(
		      final By locator) {
		    return new ExpectedCondition<WebElement>() {
		      @Override
		      public WebElement apply(WebDriver driver) {
		        try {
		          return elementIfVisible(findElement(locator, driver));
		        } catch (StaleElementReferenceException e) {
		          return null;
		        }
		      }*/
	/*
	 * public void verifyText(String actualText, String expectedText) { try { //
	 * boolean flag = false;
	 * 
	 * String actText = driver.findElement(By.xpath(textXpath))
	 * .getText();
	 * 
	 * Assert.assertEquals(actualText, expectedText,"");
	 * 
	 * 
	 * if (expectedText.equals(actText)) { flag = true; //
	 * System.out.println(actText + " = text is verified"); } else { //
	 * System.out.println(actText + " = text is not verified"); } return flag;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	public void selectByVisibleText(WebElement selBoxXpath, String visibleText) {
		// WebElement selWb = driver.findElement(By.xpath(selBoxXpath));
		Select sel = new Select(selBoxXpath);
		sel.selectByVisibleText(visibleText);
	}

	public void selectByValue(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public void DeselectByValue(WebElement element, String value) {
		Select sel = new Select(element);
		sel.deselectByValue(value);
		
	}

	public void selectByIndex(WebElement selBoxXpath, int index) {
		// WebElement selWb = driver.findElement(By.xpath(selBoxXpath));
		Select sel = new Select(selBoxXpath);
		sel.selectByIndex(index);
	}

	public void DeselectByIndex(WebElement selBoxXpath, int index) {
		// WebElement selWb = driver.findElement(By.xpath(selBoxXpath));
		Select sel = new Select(selBoxXpath);
		sel.deselectByIndex(index);
	}

	public void deSelectByVisibleText(WebElement selBoxWb, String visibleText) {
		Select sel = new Select(selBoxWb);
		sel.deselectByVisibleText(visibleText);
	}

	public void acceptAlert() {
		Alert alt = driver.switchTo().alert();
		// System.out.println(alt.getText());
		alt.accept();
	}

	public void cancelAlert() {
		Alert alt = driver.switchTo().alert();
		// System.out.println(alt.getText());
		alt.dismiss();

	}
	public Alert alertBox(){
		Alert alert = driver.switchTo().alert();
		
		return alert;
	}
	
	public String alertText(){
		Alert alert=driver.switchTo().alert();
		String message=alert.getText();
		
		return message;
	}
 
	
	

	public boolean isElementPresent(WebElement element){
		try{
		element.isDisplayed();
			//waitForWebElementFluently(element);
		return true;
		}catch(Exception e){
			return false;
		}
		
	}
	public String GetTime() {

		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

		Date now = new Date();

		String strTime = sdfTime.format(now);

		return strTime;
	}

	// returns Machine current Date
	public String GetDate() {

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");

		Date now = new Date();

		String strDate = sdfDate.format(now);

		strDate = strDate.replace("-", "/");

		return strDate;
	}

	public boolean isAllertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}

	

	public void openReport() throws IOException {
		File openReport = new File(System.getProperty("user.dir")
				+ "\\TaxiForSureTest\\test-output\\index.html");
		Desktop.getDesktop().browse(openReport.toURI());
	}

	public void fileUpload(String filePath, String fileName)
			throws AWTException, InterruptedException {
		StringSelection stringSelection = new StringSelection(filePath
				+ fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);

		Robot robot = new Robot();
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);
	}

	public void fileDownload(String path) throws AWTException,
			InterruptedException {

		StringSelection stringSelection = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);
	}
	
	public  void handleNewTab(WebDriver driver)
	{
		Set<String> allWindowHandles = driver.getWindowHandles();
		String window0 = (String) allWindowHandles.toArray()[1];
		driver.switchTo().window(window0);
	}
	
	
	public void closeWindow(WebDriver driver){
		driver.close();
		
	}
	
	 
		public static WebElement findElementByText(List<WebElement> elements, String text) {
			
			WebElement result = null;
			for (WebElement element : elements) {
				element.getText().trim();
				if (text.equals(element.getText().trim())) {
					result = element;
					break;
				}
			}
			return result;
		}

		public static String findStringFromElements(List<WebElement> elements ,String text){
			
			String result=null;
			for(WebElement element:elements){
				element.getAttribute("value").trim();
				if(element.getAttribute("value").contains(text)){
					result=text;
					break;
				}
			}
			
			return result;
			
		}
		
	public String getDateAndHour() {

		String today;

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss_S");
		Calendar calendar = Calendar.getInstance();

		today = dateFormat.format(calendar.getTime());

		return today;

	}

	
	
	public boolean isWindowPresent(WebDriver driver){
		boolean flag=true;
		Set<String> allWindows=driver.getWindowHandles();
		if(!allWindows.isEmpty()){
			flag=true;
		}else{
			flag=false;
		}
		return flag;
	}
	public  boolean isElementPresent(final WebDriver driver, By by) {


		try {
			driver.findElement(by);

			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public List<WebElement> getOptionsFromSelectList(WebElement element){
		
	
		Select sel=new Select(element);
		List<WebElement> list=sel.getOptions();
		
		return list;
	}
	public String getSelectedOption(WebElement element){
		
		Select sel=new Select(element);
		//String value=sel.getFirstSelectedOption().getText();
		
		return sel.getFirstSelectedOption().getText();
	}
	
	
	public void highlightElement(WebDriver driver, WebElement element,String color) {
		
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].style.border='2px solid " + color + "'", element);
			
		
	}
	
	public   String getTomorrowDate(Date date, int range)
    {
          
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.add(Calendar.DAY_OF_YEAR, range);  
        //cal.add(Calendar.YEAR, 1);
        Date tomorrow = cal.getTime();  
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String formatedDate = sf.format(tomorrow);
        return formatedDate;
    }

}
