package com.taxiforsure.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.taxiforsure.commonLib.BDriver;
import com.taxiforsure.commonLib.BaseClass;

public class Screenshot extends TestListenerAdapter {
	@Override
	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.setCurrentTestResult(result);
		
		//String workingDirectory = System.getProperty("user.dir");
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		String relativePath=System.getProperty("user.dir");
		String destDir = relativePath+"/screenshots";
		//File ff=new File(destDir);
		new File(destDir).mkdirs();
		
		String destFile = dateFormat.format(new Date()) + ".png";
		/*String fileName = workingDirectory + "/screenshots/" + "/"
				+ "screenshots" + "/" + result.getMethod().getMethodName()
				+ ".png";// filename
*/		File scrFile = ((TakesScreenshot) BaseClass.driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {

			e.printStackTrace();
		}
		Reporter.log("<a href = file:///" + new File(destDir + "/" + destFile).getAbsolutePath() +" target='_blank'" +"> " +
				"<p align=\"left\", style= \"color: red; font: italic bold 12px/30px Georgia,serif;\" >Click here for screen shot </p> </a>");
		Reporter.setCurrentTestResult(null);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		/*System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.setCurrentTestResult(result);
		
		//String workingDirectory = System.getProperty("user.dir");
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		String relativePath=System.getProperty("user.dir");
		String destDir = relativePath+"/screenshots";
		//File ff=new File(destDir);
		new File(destDir).mkdirs();
		
		String destFile = dateFormat.format(new Date()) + ".png";
		String fileName = workingDirectory + "/screenshots/" + "/"
				+ "screenshots" + "/" + result.getMethod().getMethodName()
				+ ".png";// filename
		File scrFile = ((TakesScreenshot) BaseClass.driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {

			e.printStackTrace();
		}
		Reporter.log("<a href = file:///" + new File(destDir + "/" + destFile).getAbsolutePath() +" target='_blank'" +"> " +
				"<p align=\"left\", style= \"color: red; font: italic bold 12px/30px Georgia,serif;\" >Click here for screen shot </p> </a>");
		Reporter.setCurrentTestResult(null);*/

	}

}