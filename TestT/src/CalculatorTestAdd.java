import org.testng.annotations.*;
import org.testng.Assert;





public class CalculatorTestAdd{
	@Test(dataProvider="data-provider", dataProviderClass= CalculatorTest.class)
	public final void testAdd(int op1, int op2, int expResult) {
		Calculator cal = new Calculator();
		//Assert.assertEquals("actual not matching with expected", cal.add(op1,op2),expResult);
		Assert.assertEquals(cal.add(op1,op2), expResult,"actual not matching with expected");
	}
}
