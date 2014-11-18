package com.taxiforsure.driver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
//import com.taxiforsure.utils.ReadPropertiesFile;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.taxiforsure.utils.ReadPropertiesFile;

public class MyDriver {

	/**
	 * This method will get the url if we pass it as an argument in cmd mvn test
	 * appurl="ururl"
	 */
	public static Logger ESS_LOGS=Logger.getLogger("devpinoyLogger");
	public static String downoad_Dir=System.getProperty("user.dir")+"/downloads/";

	public String geturl() {
		System.out.println("url " + System.getProperty("appurl"));
		return System.getProperty("appurl");
	}

	public static FirefoxProfile getFirefoxProfile(){
		FirefoxProfile profile = new FirefoxProfile();
				
		//SSL handling	profile	
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		
		//File Download propfile
		profile.setPreference("browser.download.folderList", 2); 
		profile.setPreference("browser.download.dir",downoad_Dir); 
//		profile.setPreference("browser.download.manager.alertOnEXEOpen", false); 
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword,application/csv,text/csv,image/png ,image/jpeg, application/pdf, text/html,text/plain,application/octet-stream"); 
		profile.setPreference("browser.download.manager.showWhenStarting", false); 
		/*profile.setPreference("browser.download.manager.focusWhenStarting", false); 
		profile.setPreference("browser.download.useDownloadDir", true); 
		profile.setPreference("browser.helperApps.alwaysAsk.force", false); 
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false); 
		profile.setPreference("browser.download.manager.closeWhenDone", false); 
		profile.setPreference("browser.download.manager.showAlertOnComplete", false); 
		profile.setPreference("browser.download.manager.useWindow", false); 
		profile.setPreference("browser.download.manager.showWhenStarting",false); 
		profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false); 

		profile.setPreference("pdfjs.disabled", true); 
*/		
		return profile;
		
	}
	
	public static DesiredCapabilities getChromeProfile(){
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(ChromeOptions.CAPABILITY, "--test-type");
		
		
		
		return capability;
		
	}
	public WebDriver getDriver() {

		ReadPropertiesFile prop = new ReadPropertiesFile();
		String browser = prop.getValue("Config.properties", "browser");
		WebDriver driver = null;

		if (browser.equals("firefox")) {
			ESS_LOGS.debug("Opening FirefoxBrowser");
			driver = new FirefoxDriver(MyDriver.getFirefoxProfile());
		} else if (browser.equals("ie")) {
			String relativePath = System.getProperty("user.dir");
			String ieDriverPath = relativePath + "/lib/IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			driver = new InternetExplorerDriver();

		} else if (browser.equals("chrome")) {
			String relativePath = System.getProperty("user.dir");
			String chromeDriverPath = relativePath + "/lib/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ESS_LOGS.debug("Opening Chrome Browser");
			
			driver = new ChromeDriver(MyDriver.getChromeProfile());
		}

		String url = prop.getValue("Config.properties", "url");
		try{
		ESS_LOGS.debug("Navigating to "+url);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}catch(Exception e){
			throw new RuntimeException("Cannot start browser because:  "+e.getMessage());
		}
		return driver;
	}

	public void quitBrowser(WebDriver driver) {
		ESS_LOGS.debug("Closing Browser");
		driver.quit();
	}

	public void takeScreenShot(ITestResult result, WebDriver driver) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.setCurrentTestResult(result);
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		String destDir = "target/surefire-reports/screenshots";
		new File(destDir).mkdirs();
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			System.out.println("File path "
					+ new File(destDir + "/" + destFile).getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out
				.println(new File(destDir + "/" + destFile).getAbsolutePath());
		Reporter.log("<a href = file:///"
				+ new File(destDir + "/" + destFile).getAbsolutePath()
				+ " target='_blank'"
				+ "> "
				+ "<p align=\"left\", style= \"color: red; font: italic bold 12px/30px Georgia,serif;\" >Click here for screen shot </p> </a>");
		System.out.println("Taken screen shots");
	}
}
