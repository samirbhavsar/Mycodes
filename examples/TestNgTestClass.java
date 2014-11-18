import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNgTestClass {
 int a;
 int b;

 public TestNgTestClass(int a, int b) {
  this.a = a;
  this.b = b;
 }

 @Test
 public final void testEqual() { 
  Assert.assertEquals(a , b);
 }
}