package com.taxiforsure.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadTextFile {

	public String getText(String path) {
		String retrunThis = null;
		try {
			Scanner readText = new Scanner(new File(path));
			while (readText.hasNextLine()) {
				retrunThis = retrunThis + readText.nextLine();
			}
			readText.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retrunThis;
	}

}
