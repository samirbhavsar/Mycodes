import org.testng.annotations.Factory;

public class FactoryClassImp {

 @Factory
 public Object[] createTest() {
  Object[] res = new Object[3];
  res[0] = new TestNgTestClass(2, 2);
  res[1] = new TestNgTestClass(2, 2);
  res[2] = new TestNgTestClass(2, 1);

  return res;
 }
}