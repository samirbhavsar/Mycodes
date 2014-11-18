package com.taxiforsure.actions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Predicate;

public class DoOperation
// devpinoyLogger
{

	public static Logger ESS_LOGS = Logger.getLogger("devpinoyLogger");;

	// public static Logger APP_LOGS;

	/**
	 * Used to type contend into edit box
	 * 
	 * @param <T>
	 * @param element
	 *            edit box object
	 * @param text
	 *            content to type
	 */

	// Generic method for PageFactory
	public static <T> T initPage(WebDriver driver, Class<T> clazz) {

		T page = PageFactory.initElements(driver, clazz);
		return page;
	}
	
	public static void waitForAjaxToFinish(WebDriver driver) {
        int timeout = 0;
        while(timeout<400) {
            boolean ajaxWorking = (boolean) ((JavascriptExecutor) driver)
                    .executeScript("return jQuery.active == 0");
            if(ajaxWorking) {
                break;
            }
            try{
                timeout++;
                Thread.sleep(500);
            } catch(Exception e) {
 
            }
        }
    }

	public static void waitForPageLoad(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public static void ScrollToElement(WebDriver driver, WebElement element) {
		ESS_LOGS.debug("Scrolling to element: ");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		/*
		 * int x = element.getLocation().getX(); int y =
		 * element.getLocation().getY(); ((JavascriptExecutor)
		 * driver).executeScript("window.scrollBy("+x+","+y+")", "");
		 */

	}

	public static void type(WebDriver driver, WebElement element, String text) {
		// DOMConfigurator.configure("devpinoyLogger.xml");
		// PropertyConfigurator.configure("log4j.properties");
		// ESS_LOGS = Logger.getLogger("devpinoyLogger");
		ESS_LOGS.debug("Typing into"
				+ element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		element.clear();
		element.sendKeys(text);

	}

	public static void sendKeystrokes(WebElement element, Keys key) {

		ESS_LOGS.info("Sending keystrokes"
				+ element.toString().split(":")[2].replace("]", ""));
		// highLightElement(driver, element, "red");
		element.sendKeys(key);

	}

	public static String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public static String getRandomString(int length) {
		SecureRandom random = new SecureRandom();
		String result = new BigInteger(Long.SIZE * length, random).toString(32);
		return result.substring(0, length);
	}

	/**
	 * Used to click on an element.
	 * 
	 * @param element
	 */
	public static void click(WebDriver driver, WebElement element) {
		// PropertyConfigurator.configure("log4j.properties");
		// DOMConfigurator.configure("devpinoyLogger.xml");
		ESS_LOGS.debug("Clicking"
				+ element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		element.click();
	}

	public static void click(WebDriver driver, List<WebElement> elements,
			String text) {
		// DOMConfigurator.configure("devpinoyLogger.xml");

		for (WebElement element : elements) {
			// element.getText().trim();
			if (text.equals(element.getText().trim())) {
				highLightElement(driver, element, "red");
				ESS_LOGS.info("Clicking"
						+ element.toString().split(":")[2].replace("]", ""));
				element.click();
				break;
			}
		}

	}

	public static int getResponseCode(String urlString) throws IOException {
		try {
			URL u = new URL(urlString);
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			return h.getResponseCode();

		} catch (MalformedURLException e) {
			return -1;
		}
	}

	public static String getTextFromElement(WebDriver driver, String xpath1,
			String xpath2, String text) {

		String completeXpath = xpath1 + text + xpath2;
		return driver.findElement(By.xpath(completeXpath)).getText();
	}

	public static boolean isElementPresent(WebElement element) {
		try {
			element.isDisplayed();
			// waitForWebElementFluently(element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isAllertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}

	public static boolean isElementPresent(final WebDriver driver, By by) {

		try {
			driver.findElement(by);

			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static String getDate(Date date, int range) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, range);
		// cal.add(Calendar.YEAR, 1);
		Date tomorrow = cal.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("dd/mm/yyyy");
		String formatedDate = sf.format(tomorrow);
		return formatedDate;
	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println(DoOperation.getDate(new Date(), 10).split("/")[2]); }
	 */

	public static void typeDate(WebDriver driver, String locator, String date) {
		((JavascriptExecutor) driver)
				.executeScript("document.getElementById ('" + locator
						+ "').removeAttribute('readonly',0);");

		WebElement date_Picker = driver.findElement(By.id(locator));

		date_Picker.clear();

		date_Picker.sendKeys(date);
	}

	/*
	 * public static String currentDatePlus(int numberOfDays) { Calendar c =
	 * Calendar.getInstance(); c.add(Calendar.DATE, numberOfDays); return new
	 * SimpleDateFormat("dd/MM/yyyy").format(c.getTime()); }
	 */

	/*
	 * public static void main(String[] args) {
	 * System.out.println(DoOperation.currentDatePlus(15));
	 * System.out.println(DoOperation.currentDatePlus(20)); }
	 */
	/**
	 * This is used to select value from a drop down. Pass the text to be
	 * selected as an argument.
	 * 
	 * @param element
	 * @param valueToSelect
	 */

	public static void selectInDropDown(WebDriver driver, WebElement element,
			String valueToSelect) {
		// /PropertyConfigurator.configure("log4j.properties");
		// DOMConfigurator.configure("devpinoyLogger.xml");
		ESS_LOGS.info("Selecting " + valueToSelect + " from "
				+ element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		Select select = new Select(element);
		select.selectByVisibleText(valueToSelect);

	}

	/**
	 * This is used to select value in table drop down based on the index
	 * position rather than text. Use this if you are not sure of the future
	 * availability of the text in the drop down.
	 * 
	 * @param dropDown
	 * @param dropDownValueToSelect
	 */

	public static void selectInDropDownUsingIndex(WebDriver driver,
			WebElement dropDown, int dropDownValueToSelect) {
		// /PropertyConfigurator.configure("log4j.properties");
		// DOMConfigurator.configure("devpinoyLogger.xml");
		ESS_LOGS.info("Selecting value from "
				+ dropDown.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, dropDown, "red");
		Select select = new Select(dropDown);
		select.selectByIndex(dropDownValueToSelect);

	}

	public static String selectInDropDownUsingIndextAndGetText(
			WebDriver driver, WebElement dropDown, int dropDownValueToSelect) {
		// PropertyConfigurator.configure("log4j.properties");

		ESS_LOGS.debug("Selecting value from "
				+ dropDown.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, dropDown, "red");
		Select select = new Select(dropDown);
		select.selectByIndex(dropDownValueToSelect);
		String text = select.getOptions().get(dropDownValueToSelect).getText();
		return text;
	}

	public static String getFirstSelectedOption(WebDriver driver,
			WebElement dropDown) {
		highLightElement(driver, dropDown, "red");
		Select select = new Select(dropDown);
		return select.getFirstSelectedOption().getText();
	}

	/**
	 * Used to perform mouse hover operation on link.
	 * 
	 * @param element
	 */

	public static void mouseHover(WebDriver driver, WebElement element) {

		// PropertyConfigurator.configure("log4j.properties");

		ESS_LOGS.debug("Hovering on " + element.getText());
		// String javaScript =
		// "var evObj = document.createEvent('MouseEvents');" +
		// "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
		// +
		// "arguments[0].dispatchEvent(evObj);";
		// ((JavascriptExecutor)driver).executeScript(javaScript, element);

		highLightElement(driver, element, "red");
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public static void mouseOverAction(WebDriver driver,
			WebElement mainElement, WebElement subElement) {
		// PropertyConfigurator.configure("log4j.properties");
		ESS_LOGS.debug("Hovering on " + mainElement.getText());
		highLightElement(driver, mainElement, "red");
		Actions action = new Actions(driver);
		action.moveToElement(mainElement).perform();
		ESS_LOGS.debug("Hovering on " + subElement.getText());
		highLightElement(driver, subElement, "red");
		action.moveToElement(subElement).click().perform();

	}
	 

	public static void dragAndDropAction(WebDriver driver, WebElement source,
			WebElement target) {
		highLightElement(driver, source, "red");
		(new Actions(driver)).dragAndDrop(source, target).perform();

		highLightElement(driver, target, "red");
	}

	public static void waitForWebElement(WebElement webElement) {
		// PropertyConfigurator.configure("log4j.properties");
		new FluentWait<WebElement>(webElement)
				.withTimeout(50, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.until(new Predicate<WebElement>() {

					public boolean apply(WebElement element) {
						return element.isDisplayed();
					}
				});
	}

	/**
	 * This is used to select an item in ajax drop down list. Use it only for
	 * ajax. for regular dropdown use selectInDropdown method.
	 * 
	 * @param element
	 * @param ValueToSelect
	 */

	public static void ajaxSelect(WebDriver driver, WebElement element,
			String ValueToSelect) {

		// PropertyConfigurator.configure("log4j.properties");
		ESS_LOGS.debug("Selecting " + ValueToSelect + " in ajax drop down for "
				+ element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		element.click();
	}

	/**
	 * This is used to click on last row of a webtable.
	 * 
	 * @param element
	 */

	/**
	 * This is used to check a check box.
	 * 
	 * @param element
	 */

	public static void checkCheckBox(WebDriver driver, WebElement element) {
		// PropertyConfigurator.configure("log4j.properties");

		highLightElement(driver, element, "red");
		boolean selected = element.isSelected();
		if (!selected) {
			ESS_LOGS.debug("Checking the checkbox of element "
					+ element.toString().split(":")[2].replace("]", "") + " ");
			element.click();
		} else {
			ESS_LOGS.debug("Checkbox of "
					+ element.toString().split(":")[2].replace("]", "")
					+ " is already checked");
		}
	}

	/**
	 * This is used to uncheck a check box.
	 * 
	 * @param element
	 */

	public static void uncheckCheckBox(WebDriver driver, WebElement element) {
		// PropertyConfigurator.configure("log4j.properties");

		highLightElement(driver, element, "red");
		boolean selected = element.isSelected();
		if (selected) {
			ESS_LOGS.debug("Unchecking the chekcbox of "
					+ element.toString().split(":")[2].replace("]", ""));
			element.click();
		} else {
			ESS_LOGS.debug("Checkbox of "
					+ element.toString().split(":")[2].replace("]", "")
					+ "is already unchekced");
		}
	}

	public static void selectSearchDropdown(WebDriver driver, By locator,
			String value) {
		driver.findElement(locator).click();
		driver.findElement(locator).sendKeys(value);
		driver.findElement(locator).sendKeys(Keys.TAB);
		driver.findElement(locator).sendKeys(Keys.RETURN);
	}

	public static void selectSearchDropdown(WebDriver driver,
			WebElement element, String value) {
		// element.click();
		element.sendKeys(value);
		element.sendKeys(Keys.TAB);
		element.sendKeys(Keys.RETURN);
	}

	public static void uploadFile(WebDriver driver, By locator, String path) {
		driver.findElement(locator).sendKeys(path);
	}

	public static void uploadFile(WebDriver driver, WebElement element,
			String path) {
		element.sendKeys(path);
	}

	/**
	 * This is used to get text from a specified cell in the table.
	 * 
	 * @param element
	 * @param rowNo
	 * @param colNo
	 * @return
	 */

	public static String getValueFromTable(WebDriver driver,
			WebElement element, String rowNo, String colNo) {

		highLightElement(driver, element, "red");
		String text = null;
		try {
			text = element
					.findElement(
							By.xpath("//tr[" + rowNo + "]//td[" + colNo + "]"))
					.getText().trim();
			ESS_LOGS.debug("Getting " + text + "from " + rowNo + " and "
					+ colNo + " of the table "
					+ element.toString().split(":")[2].replace("]", ""));
		} catch (Throwable t) {
			text = "There is no text in the specified cell";
			ESS_LOGS.debug("There is not text in the " + rowNo + " and "
					+ colNo + " of the table "
					+ element.toString().split(":")[2].replace("]", ""));
		}
		return text;
	}

	/**
	 * Used to check a check box inside a webtable.
	 * 
	 * @param element
	 *            provide the table id.
	 * @param rowNo
	 *            the row in which check box has to be checked.
	 * @param colNo
	 *            the column in which check box is present.
	 */

	public static void selectChekboxInTable(WebDriver driver,
			WebElement element, String rowNo, String colNo) {

		ESS_LOGS.debug("Checking the checkbox in " + rowNo + " and " + colNo
				+ " of the table "
				+ element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		boolean selected = element.findElement(
				By.xpath("//tr[" + rowNo + "]/td[" + colNo + "]//input"))
				.isSelected();
		if (!selected) {
			element.findElement(
					By.xpath("//tr[" + rowNo + "]/td[" + colNo + "]//input"))
					.click();
		}
	}

	public static void uncheckChekboxInTable(WebDriver driver,
			WebElement element, String rowNo, String colNo) {

		ESS_LOGS.debug("Checking the checkbox in " + rowNo + " and " + colNo
				+ " of the table "
				+ element.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, element, "red");
		boolean selected = element.findElement(
				By.xpath("//tr[" + rowNo + "]/td[" + colNo + "]//input"))
				.isSelected();
		if (selected) {
			element.findElement(
					By.xpath("//tr[" + rowNo + "]/td[" + colNo + "]//input"))
					.click();
		}
	}

	/**
	 * This is used to set value in the webtable. Either we can select some
	 * value from a drop down or type into an edit box by specifying the row and
	 * column number.
	 * 
	 * @param table
	 * @param rowNo
	 * @param colNo
	 * @param cellType
	 *            Use select for drop down and input for inputbox
	 * @param text
	 */

	public static void setTableCellValue(WebDriver driver, WebElement table,
			String rowNo, String colNo, String cellType, String text) {

		ESS_LOGS.debug("Setting the value to the cell " + rowNo + " and "
				+ colNo + " of the table "
				+ table.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, table, "red");
		try {
			if (cellType.equalsIgnoreCase("select")) {

				WebElement selectInTable = table.findElement(By.xpath("//tr["
						+ rowNo + "]/td[" + colNo + "]//select"));
				Select select = new Select(selectInTable);
				select.selectByVisibleText(text);
			} else if (cellType.equalsIgnoreCase("input")) {
				table.findElement(
						By.xpath("//tr[" + rowNo + "]/td[" + colNo + "]//input"))
						.click();
				table.findElement(
						By.xpath("//tr[" + rowNo + "]/td[" + colNo + "]//input"))
						.clear();
				table.findElement(
						By.xpath("//tr[" + rowNo + "]/td[" + colNo + "]//input"))
						.sendKeys(text);
			}
		} catch (NoSuchElementException e) {
			ESS_LOGS.debug("Unable to set the data in the table " + table);
		}
	}

	/**
	 * This is used to select value in table drop down based on the index
	 * position rather than text. Use this if you are not sure of the future
	 * availability of the text in the drop down.
	 * 
	 * @param table
	 * @param rowNo
	 * @param colNo
	 * @param dropDownValueToSelect
	 */

	public static void selectValueInTableDropDown(WebDriver driver,
			WebElement table, String rowNo, String colNo,
			int dropDownValueToSelect) {

		ESS_LOGS.debug("Selecting value in the drop down in " + rowNo
				+ " row and " + colNo + " column of the table "
				+ table.toString().split(":")[2].replace("]", ""));
		highLightElement(driver, table, "red");
		try {
			WebElement selectInTable = table.findElement(By.xpath("//tr["
					+ rowNo + "]/td[" + colNo + "]//select"));
			Select select = new Select(selectInTable);
			select.selectByIndex(dropDownValueToSelect);
		} catch (Throwable t) {
			ESS_LOGS.debug("Unable to set the data in the table " + table);
		}
	}

	/**
	 * This is used to click ok on java script alert box.
	 */

	public static void clickAlertOK(WebDriver driver) {
		FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
		fluent.ignoring(ElementNotVisibleException.class);
		fluent.withTimeout(10, TimeUnit.SECONDS);
		fluent.pollingEvery(2, TimeUnit.SECONDS);
		fluent.until(ExpectedConditions.alertIsPresent());
		ESS_LOGS.debug("Clicking ok in the java scrip alert");
		driver.switchTo().alert().accept();
	}

	/**
	 * This is used to click cancel on java script alert box.
	 */
	public static void waitForAlertPresent(WebDriver driver) {
		FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
		fluent.ignoring(ElementNotVisibleException.class);
		fluent.withTimeout(10, TimeUnit.SECONDS);
		fluent.pollingEvery(2, TimeUnit.SECONDS);
		fluent.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert();
	}

	public static void clickAlertCancel(WebDriver driver) {
		FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
		fluent.ignoring(ElementNotVisibleException.class);
		fluent.withTimeout(10, TimeUnit.SECONDS);
		fluent.pollingEvery(2, TimeUnit.SECONDS);
		fluent.until(ExpectedConditions.alertIsPresent());
		ESS_LOGS.debug("Clicking cancel in the java scrip alert");
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This is used for getting the text of alert box.
	 * 
	 * @return
	 */

	public static String getAlertText(WebDriver driver) {
		FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
		fluent.ignoring(ElementNotVisibleException.class);
		fluent.withTimeout(10, TimeUnit.SECONDS);
		fluent.pollingEvery(2, TimeUnit.SECONDS);
		fluent.until(ExpectedConditions.alertIsPresent());
		ESS_LOGS.debug("Getting text from java script alert box");
		return driver.switchTo().alert().getText();
	}

	public static void selectRowInTableWithData(WebDriver driver, String data,
			WebElement tableId, WebElement nextLinkId) {

		boolean quit = false;
		boolean exists = false;
		highLightElement(driver, tableId, "red");
		do {
			try {
				exists = tableId.findElement(
						By.xpath("//tr//td[text()='" + data + "']"))
						.isDisplayed();
			} catch (NoSuchElementException e) {
				quit = false;
			}
			if (exists) {
				quit = true;

			}
			if (quit == false) {

				if (!nextLinkId.getAttribute("class")
						.contains("state-disabled")) {
					nextLinkId.click();
					ESS_LOGS.debug("Still searching for the text " + data);
				}
			}

		} while (quit == false);

		if (quit == true) {
			ESS_LOGS.debug("Found the row with text " + data
					+ " and clicked on the row");
			tableId.findElement(By.xpath("//tr//td[text()='" + data + "']"))
					.click();
		} else {

		}
	}

	/**
	 * This method is used to find out the row number of a particular text in
	 * the table.
	 * 
	 * @param tableId
	 * @param textToSearch
	 * @return
	 */

	public static String getRowNoInTableWithData(WebDriver driver,
			WebElement tableId, String textToSearch) {

		ESS_LOGS.debug("Searching for a row in the table with the text "
				+ textToSearch);
		highLightElement(driver, tableId, "red");
		String rowno = null;
		try {

			rowno = tableId.findElement(
					By.xpath("//tr[td[text() = '" + textToSearch
							+ "']]/descendant::td[1]")).getText();
			ESS_LOGS.debug("Found the row in the table with the text "
					+ textToSearch + " .The row number is " + rowno);
		} catch (Throwable t) {
			rowno = tableId.findElement(
					By.xpath("//tr[td[text() = '" + textToSearch
							+ "']]/descendant::td[1]")).getText();
			ESS_LOGS.debug("Found the row in the table with the text "
					+ textToSearch + " .The row number is " + rowno);
		}
		return rowno;

	}

	/**
	 * This is used to get a specified attribute from the table.
	 * 
	 * @param rowNo
	 * @param colNo
	 * @param cellType
	 * @param whichAttribute
	 * @param tableId
	 * @return
	 */
	public static String getAttributeFromTable(WebDriver driver, String rowNo,
			String colNo, String cellType, String whichAttribute,
			WebElement tableId) {

		String attributeValue = "NotFound";
		highLightElement(driver, tableId, "red");
		ESS_LOGS.debug("Getting the attribute " + whichAttribute
				+ " from the table for the row " + rowNo + " and the column "
				+ colNo);
		try {

			if (cellType.equalsIgnoreCase("select")) {

				attributeValue = tableId.findElement(
						By.xpath("//tr[" + rowNo + "]/td[" + colNo
								+ "]/select[@role = 'select']")).getAttribute(
						whichAttribute);
			} else if (cellType.equalsIgnoreCase("input")) {
				attributeValue = tableId
						.findElement(
								By.xpath("//tr[" + rowNo + "]/td[" + colNo
										+ "]/input")).getAttribute(
								whichAttribute);
			}
		} catch (NoSuchElementException e) {
			return attributeValue;
		}
		return attributeValue;
	}

	/**
	 * This is used to verify if data exists in grid.
	 * 
	 * @param data
	 * @param table
	 * @param nextButton
	 * @return
	 */

	public static boolean verifyDataInGrid(WebDriver driver, String data,
			WebElement table, WebElement nextButton) {

		ESS_LOGS.debug("Verifying the presence of " + data + " in the grid");
		highLightElement(driver, table, "red");
		boolean quit = false;
		boolean exists = false;
		do {
			try {
				exists = table.findElement(
						By.xpath("//tr//td[text()='" + data + "']"))
						.isDisplayed();
			} catch (NoSuchElementException e) {
				quit = false;
			}
			if (exists) {
				quit = true;
				break;

			}
			if (quit == false) {
				FluentWait<WebDriver> fluent1 = new FluentWait<WebDriver>(
						driver);
				fluent1.ignoring(NoSuchElementException.class,
						StaleElementReferenceException.class);
				fluent1.withTimeout(10, TimeUnit.SECONDS);
				fluent1.pollingEvery(2, TimeUnit.SECONDS);
				fluent1.until(ExpectedConditions.visibilityOf(nextButton));

				if (!nextButton.getAttribute("class")
						.contains("state-disabled")) {
					nextButton.click();

				} else {
					quit = true;
					break;
				}
			}

		} while (quit == false);

		return quit;
	}

	public static void highLightElement(WebDriver driver, WebElement element,
			String color) {
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].style.border='2px solid " + color + "'",
					element);
		}
	}

	public static void shareLocation() throws AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(4000);
	}

}
