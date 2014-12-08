package test;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestSuite {
	public static void main(String[] args) {
		XmlSuite suite = new XmlSuite();
		suite.setName("SuiteMain");
		suite.setVerbose(1);
		//Create test
		XmlTest test = new XmlTest(suite);
		test.setName("UnitTest");
		//Create classes and add the test classes
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("E:\\TaxiForSureProject\\UnitTestingJava\\src\\test\\CalculatorTestAdd"));
		//classes.add(new XmlClass("CalculatorTestSubtract"));
		//set test classes
		test.setXmlClasses(classes) ;
		//Create suites from number of test suite
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		// Pass XmlSuite to TestNG
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();
	}

}
