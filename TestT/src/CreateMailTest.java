import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
 

 
public class CreateMailTest {
     
    private static String username = "qatfsautomation@gmail.com";
    private static String password = "TFS@qa123";
    private WebDriver driver;
    private EmailBoxPage emailBoxPage;
    private WelcomePage welcomePage;
    private LoginPage loginPage;
     
    @BeforeClass
    public void before(){
    	System.setProperty("webdriver.chrome.driver", "D:\\test_suite\\TFSAutomation\\Lib\\chromedriver.exe");
        driver = new ChromeDriver();
        welcomePage = new WelcomePage(driver);
        loginPage = new LoginPage(driver, welcomePage, username, password);
        emailBoxPage = new EmailBoxPage(driver, loginPage);
    }
     
    @AfterSuite
    public void after(){
        driver.close();
    }
    
    @org.testng.annotations.Test
    public void testWelcomPage(){
        WebDriver driver = new ChromeDriver();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.get();
        welcomePage.fillSearchField("wedoqa");
    }
     
    @org.testng.annotations.Test
    public void testOpenMailBox(){
        emailBoxPage.get();
        emailBoxPage.clickComposeButton();
    }
}