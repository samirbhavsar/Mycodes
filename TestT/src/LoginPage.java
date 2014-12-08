import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
 
public class LoginPage extends LoadableComponent<LoginPage> {
 
    private final WebDriver driver;
    private final LoadableComponent<?> parent;
    private String username;
    private String password;
     
    public LoginPage(WebDriver driver, LoadableComponent<?> parent, String username, String password){
        this.driver = driver;
        this.parent = parent;
        this.username = username;
        this.password = password;
         
        PageFactory.initElements(driver, this);
    }
     
    @Override
    protected void isLoaded() throws Error {
        try{
        String account = driver.findElement(By.cssSelector("a.gb_8")).getAttribute("title");
        Assert.assertTrue("Not logged in with username: " + username, account.contains(username));
        }catch(NoSuchElementException ex){
            throw new AssertionError();
        }
    }
 
    @Override
    protected void load() {
        parent.get();
        driver.get("https://accounts.google.com/ServiceLogin?hl=en&continue=https://www.google.com/%3Fgws_rd%3Dcr%26ei%3DRIUtVKiyJun7ywPvrYGICQ");
        driver.findElement(By.cssSelector("#Email")).sendKeys(username);
        driver.findElement(By.cssSelector("#Passwd")).sendKeys(password);
        driver.findElement(By.cssSelector("#signIn")).click();
        driver.get("https://www.google.com/");
    }
}