import org.junit.After;
import org.junit.Before;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
public class TestNGTutorialGroupTest {
 
	@Before
	public void before() {
		System.out.println("Before");

	}
  

	@After
	public void after() {
		System.out.println("After");

	}
	
	
	
	@Test
	public void test1() {
		System.out.println("Test1");

	}
	  
	@Test
	public void test2() {
		System.out.println("Test2");

	}
	
  
}