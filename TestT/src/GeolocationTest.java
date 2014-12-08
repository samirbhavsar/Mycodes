import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GeolocationTest {

	public static void main(String[] args) throws InterruptedException {
		
		FirefoxProfile profile=new FirefoxProfile();
		profile.setPreference("geo.enabled", true);
		profile.setPreference("geo.wifi.uri", "file:///C:/Users/sabyasachi.mishra/Desktop/geolocation.json");
		WebDriver driver=new FirefoxDriver(profile);
		
		driver.get("http://html5demos.com/geo");
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success")));
		
//		driver.findElement(By.xpath(".//*[@id='demo']/button")).click();
		
		//Thread.sleep(5000);

	}

}
