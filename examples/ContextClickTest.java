import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


public class ContextClickTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver =new FirefoxDriver();
		driver.get("http://google.com");
		
		WebElement element=driver.findElement(By.id("gbqfq"));
		Actions oAction = new Actions(driver);
		oAction.moveToElement(element);
		oAction.contextClick(element).build().perform();  /* this will perform right click */
		WebElement elementOpen = driver.findElement(By.linkText("Paste")); /*This will select menu after right click */

		Thread.sleep(3000);
		elementOpen.click();
	}

}
