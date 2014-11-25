package com.taxiforsure.driver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

	public String geturl() {
		System.out.println("url " + System.getProperty("appurl"));
		return System.getProperty("appurl");
	}

	public WebDriver getDriver() {

		ReadPropertiesFile prop = new ReadPropertiesFile();
		String browser = prop.getValue("Config.properties", "browser");
		WebDriver driver = null;

		if (browser.equals("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);
			driver = new FirefoxDriver(profile);
		} else if (browser.equals("ie")) {
			String relativePath = System.getProperty("user.dir");
			String ieDriverPath = relativePath + "\\lib\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			driver = new InternetExplorerDriver();

		} else if (browser.equals("chrome")) {
			String relativePath = System.getProperty("user.dir");
			String chromeDriverPath = relativePath + "\\lib\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(capability);
		}

		String url = prop.getValue("Config.properties", "url");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public void quitBrowser(WebDriver driver) {
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
