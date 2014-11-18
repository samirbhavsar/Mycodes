package com.taxiforsure.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectMap {

	Properties properties;

	public ObjectMap(String mapFile) {
		properties = new Properties();

		try {
			FileInputStream fin = new FileInputStream(mapFile);
			properties.load(fin);
			fin.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public By getLocator(String logicalElementName) throws Exception {

		String locator = properties.getProperty(logicalElementName);
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		//System.out.println(locatorType + "\t" + locatorValue);

		if (locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return By.tagName(locatorValue);
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("Locator type'" + locatorType
					+ "'not defined!!");
	}
}
