import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class CaptureElement {
 static WebDriver driver;
 // Browser startup
 @BeforeClass
 public void setup() {
  driver = new HtmlUnitDriver();
 }

 @Test
 public void gomezTest() throws InterruptedException, IOException {
  // Capturing Required Element Screen shot
  driver.get("http://www.google.com");
  WebElement ele = driver.findElement(By.id("hplogo"));  
  System.out.println(ele.toString().split(":")[2].replace("]", ""));;
 // System.out.println(ele.toString());
 /* try
  {
  File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  BufferedImage  fullImg = ImageIO.read(screenshot);
  //Get the location of element on the page
  Point point = ele.getLocation();
  //Get width and height of the element
  int eleWidth = ele.getSize().getWidth();
  int eleHeight = ele.getSize().getHeight();
  //Crop the entire page screenshot to get only element screenshot
  BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
  ImageIO.write(eleScreenshot, "png", screenshot);
  //Copy the element screenshot to disk
  FileUtils.copyFile(screenshot, new File("GoogleLogo_screenshot.png"));
  }
  catch(Exception e)
  {
   e.printStackTrace();
  }*/
 }
 @AfterClass
 public void tear() {
  driver.close();
 }
} 