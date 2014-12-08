import org.testng.annotations.DataProvider;

public class CalculatorTest{
	@DataProvider(name = "data-provider",parallel=true)
	public static Object[][] data() {
		return new Object[][] { { 0, 5, 5 }, { 1, 5, 6 }, { 100, 5, 105 }};
	}
}