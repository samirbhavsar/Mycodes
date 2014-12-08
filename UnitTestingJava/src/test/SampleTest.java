package test;


import org.testng.annotations.BeforeSuite;
import org.testng.xml.*;
import org.testng.TestNG;
import java.util.*;

public class SampleTest {
	
	@BeforeSuite
	public static void createTestngXML()
	{
		//Create test suite
		XmlSuite suite = new XmlSuite();
		suite.setName("SuiteMain");
		suite.setVerbose(1);
	
		//Create test
		XmlTest test = new XmlTest(suite);
	
		test.setName("UnitTest");
	
		//Create classes and add the test classes
		List<XmlClass> classes = new ArrayList<XmlClass>();
	
		classes.add(new XmlClass("CalculatorTestAdd"));
		//classes.add(new XmlClass("CalcTestSubtract"));
	
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