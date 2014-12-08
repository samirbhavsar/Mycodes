import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.texen.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;




public class WindowHandleTest {
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver=new FirefoxDriver();
		driver.get("file:///C:/Users/sabyasachi.mishra/Desktop/windowsTest.html");
		//String parentW=driver.getWindowHandle();
		
		driver.findElement(By.linkText("Google")).click();
		String  firstWinHandle = driver.getWindowHandle();
		Set <String>handles = driver.getWindowHandles();
		
		handles.remove(firstWinHandle);
		 String winHandle=handles.iterator().next();
		 if (winHandle!=firstWinHandle){
			 //To retrieve the handle of second window, extracting the handle which does not match to first window handle
			String secondWinHandle=winHandle; //Storing handle of second window handle
			 
			//Switch control to new window
			 driver.switchTo().window(secondWinHandle);
			//Control is switched now
			 
			// driver.close();
			 }
		 String window2=(String)handles.toArray()[1];
		 Object[] windows=handles.toArray();
		 
		 
		 
		 driver.switchTo().window((String)windows[1]);
		 
		 driver.close();
		 
		
		EventFiringWebDriver edriver=new EventFiringWebDriver(driver);
		File src=edriver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:/screenshot.png"));
	}

}
