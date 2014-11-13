package com.taxiforsure.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile
{

	public String getValue(String fileName, String key) 
	{
		String keyValue = null;
		FileInputStream propertiesFile = null;
		
		String relativePath = System.getProperty("user.dir");
		
		
		String filePath = relativePath + "/src/main/resources/" + fileName;
//		String parent = parentPath.getParent();
		
		File propertiesFileToRead = new File(filePath);
		
		if (propertiesFileToRead.exists()) 
		{

			try 
			{
				propertiesFile = new FileInputStream(propertiesFileToRead);
				Properties prop = new Properties();
				prop.load(propertiesFile);
				keyValue = prop.getProperty(key);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					propertiesFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return keyValue;
		} else {

			try {				
				propertiesFile = new FileInputStream(filePath);
				
				Properties prop = new Properties();
				prop.load(propertiesFile);
				keyValue = prop.getProperty(key);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					propertiesFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return keyValue;
	}

}
