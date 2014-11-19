package org.taxiforsure.actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;


public class DoOperation 
{
	static Logger ESS_LOGS = Logger.getLogger("DoOperations");
	
	/** 
	 * Used to type contend into edit box
	 * @param element edit box object
	 * @param text content to type
	 */
	
	
	
	public synchronized void type(WebDriver driver, WebElement element, String text)
	{
		
		ESS_LOGS.info("Typing into" + element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		element.clear();
		element.sendKeys(text);
//		((JavascriptExecutor) driver).executeScript("arguments[0].value = '"+ text +"';", element);
			
	}
	
	/** 
	 * Used to click on an element.
	 * @param element
	 */
	public synchronized void click(WebDriver driver, WebElement element)
	{
		
		ESS_LOGS.info("Clicking" + element.toString().split("->")[1].replace("]", ""));
		highLightElement(driver, element, "red");
		element.click();
	}
	
	/** 
	 * This is used to select value from a drop down. Pass the text to be selected as an argument.
	 * @param element
	 * @param valueToSelect
	 */
	
	public synchronized void selectInDropDown(WebDriver driver, WebElement element, String valueToSelect)
	{
		
		
		ESS_LOGS.info("Selecting "+ valueToSelect + " from " + element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		Select select = new Select(element);
		select.selectByVisibleText(valueToSelect);
		
	}
	
	/**
	 * This is used to select value in table drop down based on the index position rather than text. Use this if you are not sure of the 
	 * future availability of the text in the drop down. 
	 * @param dropDown
	 * @param dropDownValueToSelect
	 */
	
	public synchronized void selectInDropDownUsingIndex(WebDriver driver, WebElement dropDown, int dropDownValueToSelect)
	{
		
		
		ESS_LOGS.info("Selecting value from " + dropDown.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, dropDown, "red");
		Select select = new Select(dropDown);
		select.selectByIndex(dropDownValueToSelect);
		
	}
	
	public synchronized String selectInDropDownUsingIndextAndGetText(WebDriver driver, WebElement dropDown, int dropDownValueToSelect)
	{
		
		
		ESS_LOGS.info("Selecting value from " + dropDown.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, dropDown, "red");
		Select select = new Select(dropDown);
		select.selectByIndex(dropDownValueToSelect);
		String text =  select.getOptions().get(dropDownValueToSelect).getText();
		return text;
	}
	/** 
	 * Used to perform mouse hover operation on link.
	 * @param element
	 */
	
	public synchronized void mouseHover(WebDriver driver, WebElement element)
	{
		
		
		
		ESS_LOGS.info("Hovering on " + element.getText());
//		String javaScript = "var evObj = document.createEvent('MouseEvents');" +
//                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
//                "arguments[0].dispatchEvent(evObj);";
// 		((JavascriptExecutor)driver).executeScript(javaScript, element);
		
		highLightElement(driver, element, "red");
 		Actions action = new Actions(driver);
 		action.moveToElement(element).perform();
	}
	
	
	/** 
	 * This is used to select an item in ajax drop down list. Use it only for ajax. for regular dropdown use selectInDropdown method.
	 * @param element
	 * @param ValueToSelect
	 */
	
	public synchronized void ajaxSelect(WebDriver driver, WebElement element, String ValueToSelect)
	{
		
		
		ESS_LOGS.info("Selecting "+ ValueToSelect + " in ajax drop down for " + element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		element.click();
	}
	
	/** 
	 * This is used to click on last row of a webtable. 
	 * @param element
	 */
	

	
	/** 
	 * This is used to check a check box.
	 * @param element
	 */
	
	public synchronized void checkCheckBox(WebDriver driver, WebElement element)
	{
		
		
		highLightElement(driver, element, "red");
		boolean selected = element.isSelected();
		if(!selected)
		{
			ESS_LOGS.info("Checking the checkbox of element " + element.toString().split(":")[2].replace("]", "") + " ");
			element.click();
		}else
		{
			ESS_LOGS.info("Checkbox of " + element.toString().split(":")[2].replace("]", "") + " is already checked");
		}
	}
	
	/** 
	 * This is used to uncheck a check box.
	 * @param element
	 */
	
	public synchronized void uncheckCheckBox(WebDriver driver, WebElement element)
	{
		
		
		highLightElement(driver, element, "red");
		boolean selected = element.isSelected();
		if(selected)
		{
			ESS_LOGS.info("Unchecking the chekcbox of " + element.toString().split(":")[2].replace("]", ""));
			element.click();
		}else
		{
			ESS_LOGS.info("Checkbox of " + element.toString().split(":")[2].replace("]", "") + "is already unchekced");
		}
	}
	
	/**
	 * This is used to get text from a specified cell in the table.
	 * @param element
	 * @param rowNo
	 * @param colNo
	 * @return
	 */
	
	public synchronized String getValueFromTable(WebDriver driver, WebElement element, String rowNo, String colNo)
	{
		
		
		highLightElement(driver, element, "red");
		String text = null;
		try
		{
			text = element.findElement(By.xpath("//tr[" + rowNo +"]//td[" + colNo + "]")).getText().trim();
			ESS_LOGS.info("Getting " + text + "from " + rowNo + " and " + colNo + " of the table " +  element.toString().split(":")[2].replace("]", ""));
		}catch(Throwable t)
		{
			text = "There is no text in the specified cell";
			ESS_LOGS.info("There is not text in the " + rowNo + " and " + colNo + " of the table " + element.toString().split(":")[2].replace("]", ""));
		}
		return text;
	}
	
	/** 
	 * Used to check a check box inside a webtable.
	 * @param element provide the table id.
	 * @param rowNo the row in which check box has to be checked.
	 * @param colNo the column in which check box is present.
	 */
	
	public synchronized void selectChekboxInTable(WebDriver driver, WebElement element, String rowNo, String colNo)
	{
		
		
		ESS_LOGS.info("Checking the checkbox in " + rowNo + " and " + colNo + " of the table " +  element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		 boolean selected = element.findElement
			(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]//input")).isSelected();
		 if(!selected)
		 {
			 element.findElement
				(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]//input")).click();
		 }
	}
	
	public synchronized void uncheckChekboxInTable(WebDriver driver, WebElement element, String rowNo, String colNo)
	{
		
		
		
		ESS_LOGS.info("Checking the checkbox in " + rowNo + " and " + colNo + " of the table " +  element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		 boolean selected = element.findElement
			(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]//input")).isSelected();
		 if(selected)
		 {
			 element.findElement
				(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]//input")).click();
		 }
	}
	  
	/**
	 * This is used to set value in the webtable. Either we can select some value from a drop down or type into an edit box by
	 * specifying the row and column number. 
	 * @param table
	 * @param rowNo
	 * @param colNo
	 * @param cellType Use select for drop down and input for inputbox
	 * @param text
	 */
	
	public synchronized  void setTableCellValue(WebDriver driver, WebElement table, String rowNo, String colNo, String cellType, String text)
	{	
		
		
		
		ESS_LOGS.info("Setting the value to the cell " + rowNo + " and " + colNo + " of the table " +  table.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, table, "red");
		try
		{
			if(cellType.equalsIgnoreCase("select"))
			{
				
				WebElement selectInTable = table.findElement
						(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]//select"));
				Select select = new Select(selectInTable);
				select.selectByVisibleText(text);
			}else if(cellType.equalsIgnoreCase("input"))
			{
				table.findElement
				(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]//input")).click();
				table.findElement
				(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]//input")).clear();
				table.findElement
				(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]//input")).sendKeys(text);
			}
		}catch(NoSuchElementException e)
		{
			ESS_LOGS.info("Unable to set the data in the table " + table);
		}
	}
	
	
	/**
	 * This is used to select value in table drop down based on the index position rather than text. Use this if you are not sure of the 
	 * future availability of the text in the drop down. 
	 * @param table
	 * @param rowNo
	 * @param colNo
	 * @param dropDownValueToSelect
	 */
	
	public synchronized void selectValueInTableDropDown(WebDriver driver, WebElement table, String rowNo, String colNo, int dropDownValueToSelect)
	{
		
		ESS_LOGS.info("Selecting value in the drop down in " + rowNo + " row and " + colNo + " column of the table " +  table.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, table, "red");
		try
		{
			WebElement selectInTable = table.findElement
					(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]//select"));
			Select select = new Select(selectInTable);
			select.selectByIndex(dropDownValueToSelect);
		}catch(Throwable t)
		{
			ESS_LOGS.info("Unable to set the data in the table " + table);
		}
	}
	
	/**
	 * This is used to click ok on java script alert box.
	 */
	
	public synchronized void clickAlertOK(WebDriver driver)
	{
	
		try
		{
		   ESS_LOGS.info("Clicking OK in the java scrip alert");
		   driver.switchTo().alert().accept();
		}catch(NoAlertPresentException e)
		{
		   ESS_LOGS.info("Java script alert is not displayed");
		}
	}
	
	
	/**
	 * This is used to click cancel on java script alert box.
	 */
	
	public synchronized void clickAlertCancel(WebDriver driver)
	{
		FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
		fluent.ignoring(ElementNotVisibleException.class);
		fluent.withTimeout(10, TimeUnit.SECONDS);
		fluent.pollingEvery(2, TimeUnit.SECONDS);
		fluent.until(ExpectedConditions.alertIsPresent());
		ESS_LOGS.info("Clicking cancel in the java scrip alert");
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This is used for getting the text of alert box.
	 * @return
	 */
	
	public synchronized String getAlertText(WebDriver driver)
	{
		FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
		fluent.ignoring(ElementNotVisibleException.class);
		fluent.withTimeout(10, TimeUnit.SECONDS);
		fluent.pollingEvery(2, TimeUnit.SECONDS);
		fluent.until(ExpectedConditions.alertIsPresent());
		ESS_LOGS.info("Getting text from java script alert box");
		return driver.switchTo().alert().getText();
	}
	
	
	
	public synchronized void selectRowInTableWithData(WebDriver driver, String data,WebElement tableId,WebElement nextLinkId)
	{
		
	    	boolean quit = false;
			boolean exists = false;
			highLightElement(driver, tableId, "red");
			do 
			{
				try
				{
					exists = tableId.findElement(By.xpath("//tr//td[text()='" + data +"']")).isDisplayed();
				}catch(NoSuchElementException e)
				{
					quit = false;
				}
				if(exists)
				{
					quit = true;
					
				}
				if(quit == false)
				{
					
					if(!nextLinkId.getAttribute("class").contains("state-disabled"))
		 			{
		 				nextLinkId.click();
		 				ESS_LOGS.info("Still searching for the text " + data);
		 			}
				}
				
			} while (quit == false);
		
			if(quit == true)
			{
				ESS_LOGS.info("Found the row with text " + data + " and clicked on the row");
				tableId.findElement(By.xpath("//tr//td[text()='" + data +"']")).click();
			}else
			{
				
			}
	}
	
	/** 
	 * This method is used to find out the row number of a particular text in the table.
	 * @param tableId
	 * @param textToSearch
	 * @return
	 */
	
	 public synchronized String getRowNoInTableWithData(WebDriver driver, WebElement tableId, String textToSearch)
	 {
		
		 ESS_LOGS.info("Searching for a row in the table with the text " + textToSearch);
		 highLightElement(driver, tableId, "red");
		String rowno = null;
		try
		{
			
			rowno = tableId.findElement(By.xpath("//tr[td[text() = '"+ textToSearch +"']]/descendant::td[1]")).getText();
			 ESS_LOGS.info("Found the row in the table with the text " + textToSearch +" .The row number is " + rowno);
		}catch(Throwable t)
		{
			rowno = tableId.findElement(By.xpath("//tr[td[text() = '"+ textToSearch +"']]/descendant::td[1]")).getText();
			 ESS_LOGS.info("Found the row in the table with the text " + textToSearch +" .The row number is " + rowno);
		}
		return rowno;
		 
	 }
	 
	 
	 /**
	  * This is used to get a specified attribute from the table. 
	  * @param rowNo
	  * @param colNo
	  * @param cellType
	  * @param whichAttribute
	  * @param tableId
	  * @return
	  */
	 public synchronized String getAttributeFromTable(WebDriver driver, String rowNo, String colNo,String cellType, String whichAttribute,WebElement tableId)
		{
		 	
			
			String attributeValue = "NotFound";
			highLightElement(driver, tableId, "red");
			 ESS_LOGS.info("Getting the attribute " + whichAttribute + " from the table for the row " + rowNo + " and the column " + colNo);
			try
			{
				
				if(cellType.equalsIgnoreCase("select"))
				{
					
					attributeValue = tableId.findElement
							(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]/select[@role = 'select']")).getAttribute(whichAttribute);
				}else if(cellType.equalsIgnoreCase("input"))
				{
					attributeValue = tableId.findElement
					(By.xpath("//tr[" + rowNo +"]/td[" + colNo + "]/input")).getAttribute(whichAttribute);
				}
			}catch(NoSuchElementException e)
			{
				return attributeValue;
			}
			return attributeValue;
		}
	 
	 /** 
	  * This is used to verify if data exists in grid.
	  * @param data
	  * @param table
	  * @param nextButton
	  * @return
	  */

	 public synchronized boolean verifyDataInGrid(WebDriver driver, String data, WebElement table, WebElement nextButton)
	 {
		 
		 
	 
	 	ESS_LOGS.info("Verifying the presence of "+  data +" in the grid");
	 	highLightElement(driver, table, "red");
	 	boolean quit = false;
	 	boolean exists = false;
	 	do 
	 	{	 		
	 		try
	 		{
	 			exists = table.findElement(By.xpath("//tr//td[text()='" + data + "']")).isDisplayed();
	 		}catch(NoSuchElementException e)
	 		{
	 			quit = false;
	 		}
	 		if(exists)
	 		{
	 			quit = true;
	 			break;
	 			
	 		}
	 		if(quit == false)
	 		{
	 			FluentWait<WebDriver> fluent1 = new FluentWait<WebDriver>(driver);
	 		 	fluent1.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
	 			fluent1.withTimeout(10, TimeUnit.SECONDS);
	 		 	fluent1.pollingEvery(2, TimeUnit.SECONDS);
	 		 	fluent1.until(ExpectedConditions.visibilityOf(nextButton));
	 		 
	 			if(!nextButton.getAttribute("class").contains("state-disabled"))
	 			{
	 				nextButton.click();	
	 				
	 			}else
	 			{
	 				quit = true;
	 				break;
	 			}
	 		}
	 	
	 	} while (quit == false);

	 	return quit;
	 }	
	 
	 public synchronized int getCountOfValuesInDropDown(WebDriver driver, WebElement element)
	 {
		highLightElement(driver, element, "red");
		Select select = new Select(element);
		List<WebElement> values = select.getOptions();
		return values.size();
	 }
	 
	 public synchronized String getValueFromDropDown(WebDriver driver, WebElement element, int index)
	 {
		Select select = new Select(element);
		return select.getOptions().get(index).getText();
	 }
	 
	 public synchronized String getTheTextWhichWasSelectedInDropdown(WebDriver driver, WebElement element)
	 {
		 Select select = new Select(element);
		 return select.getFirstSelectedOption().getText();
	 }
	 
	 public synchronized void highLightElement(WebDriver driver, WebElement element, String color) 
	 {
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid " + color + "'", element);
			}
		}
}
