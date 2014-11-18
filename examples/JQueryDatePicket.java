import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JQueryDatePicket {
	WebDriver driver;
	WebElement dateWidget;
	List<WebElement> rows;
	List<WebElement> columns;
	List<String> list = Arrays.asList("January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December");
	// Expected Date, Month and Year
	int expMonth;
	int expYear;
	String expDate = null;
	// Calendar Month and Year
	String calMonth = null;
	String calYear = null;
	boolean dateNotFound;

	@BeforeTest
	public void start() {
		driver = new FirefoxDriver();
	}

	@Test
	public void testJQueryDatePicket() throws InterruptedException {

		driver.get("http://jqueryui.com/datepicker/");
		driver.switchTo().frame(0);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// Click on textbox of Date so that datepicker will appear
		driver.findElement(By.id("datepicker")).click();
		dateNotFound = true;
		expMonth = 03;
		expYear = 2015;
		expDate = "05";
		while (dateNotFound) {

			calMonth = driver.findElement(By.className("ui-datepicker-month"))
					.getText();
			calYear = driver.findElement(By.className("ui-datepicker-year"))
					.getText();
			if (list.indexOf(calMonth) + 1 == expMonth
					&& (expYear == Integer.parseInt(calYear))) {
				selectDate(expDate);
				dateNotFound = false;
			} else if (list.indexOf(calMonth) + 1 < expMonth
					&& (expYear == Integer.parseInt(calYear))
					|| expYear > Integer.parseInt(calYear)) {
				driver.findElement(
						By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span"))
						.click();
			} else if (list.indexOf(calMonth) + 1 > expMonth
					&& (expYear == Integer.parseInt(calYear))
					|| expYear < Integer.parseInt(calYear)) {
				driver.findElement(
						By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span"))
						.click();
			}
		}
		Thread.sleep(3000);
	}

	public void selectDate(String date) {
		dateWidget = driver.findElement(By.id("ui-datepicker-div"));
		rows = dateWidget.findElements(By.tagName("tr"));
		columns = dateWidget.findElements(By.tagName("td"));

		for (WebElement cell : columns) {
			// Selects Date
			if (cell.getText().equals(date)) {
				cell.findElement(By.linkText(date)).click();
				break;
			}
		}
	}

	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}