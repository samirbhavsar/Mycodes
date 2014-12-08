package test;

import org.testng.annotations.*;
import org.testng.Assert;


public class CalculatorTestAdd {
	
	@BeforeMethod
	public void setUpBeforeMethod()
	{
		 
	}
	 
	@AfterMethod
	public void tearDownAfterMethod()
	{
		 
	}
	
	@BeforeClass
	public void setUpBeforeClass()
	{
		 
	}
	 
	@AfterClass
	public void tearDownAfterClass()
	{
		 
	}
	
	@BeforeGroups(groups = {"unit-test", "functional-test"})
	public void setUpBeforeFTandRT()
	{

	}
	
	@AfterGroups(groups = {"unit-test", "functional-test"})
	public void tearDownAfterFTandRT()
	{

	}
	
	@Test
	public final void testAdd1() {
		Calculator cal = new Calculator();
		long result = cal.add(3, 4);
		Assert.assertEquals(result, 7, "actual not matching with expected");
	}

	@Test(expectedExceptions = ArithmeticException.class)
	public void exceptionTest() {
		Calculator cal = new Calculator();
		cal.divide(1, 0);
	}
	
	@Test(timeOut=100)
	public final void testAdd2() {
		Calculator cal = new Calculator();
		long result = cal.add(3, 4);
		Assert.assertEquals(result, 7, "actual not matching with expected");
	}
	
	
	/*@Test(enabled=false)
	public final void testRational() {
		Calculator cal = new Calculator();
		long result = cal.rational(10, 4);
		Assert.assertEquals(result, 2, "actual not matching with expected");
	}*/
	

	@Test(invocationCount = 10)
	public final void testAdd3() {
		Calculator cal = new Calculator();
		long result = cal.add(3, 4);
		Assert.assertEquals(result, 7, "actual not matching with expected");
	}

	@Test(threadPoolSize = 3, invocationCount = 20)
	public void concurrencyTest() {
		System.out.print(" " + Thread.currentThread().getId());
	}
	
	@Test(groups = {"unit-test", "functional-test"})
	public final void testAdd4() {
		Calculator cal = new Calculator();
		long result = cal.add(3, 4);
		Assert.assertEquals(result, 7, "actual not matching with expected");
	}
}
