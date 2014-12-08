import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Google {
    public static void main(String[] args) throws InterruptedException {
      
    // Here we need to create logger instance so we need to pass Class name for which we want to create log file in my case Google is classname
    	PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/log4j.properties");
    	Logger logger=Logger.getLogger("Google");
        
       

        WebDriver driver = new FirefoxDriver();
        logger.info("Browser Opened");
      
      
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Implicit wait given");
      
        driver.get("https://www.google.co.in/");
        logger.info("Url opened");
      
        driver.findElement(By.name("q")).sendKeys("Selenium");
        logger.info("keyword type");   
        
        Thread.sleep(2000);
        
        logger.info("sleep applied for 2 sec");
        
        throw new RuntimeException();
        
        //driver.quit();
        //logger.info("Quiting browser");
        
    }
}
