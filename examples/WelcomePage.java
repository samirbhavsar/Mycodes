import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
 
public class WelcomePage extends LoadableComponent<WelcomePage>{
 
    private final WebDriver driver;
     
    @FindBy(css = "#gb_70")
    WebElement signInButton;
     
    @FindBy(css = "#gbqfq")
    WebElement searchField;
     
    @FindBy(css = "#gbqfba")
    WebElement searchButton;
     
    public WelcomePage(WebDriver driver){
        this.driver = driver;
         
        PageFactory.initElements(driver, this);
    }
     
    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue("Not on the right page.", url.contains("google"));
    }
 
    @Override
    protected void load() {
        driver.get("https://www.google.com/");
    }
     
    public void clickSignIn(){
        signInButton.click();
    }
     
    public void fillSearchField(String keyword){
        searchField.sendKeys(keyword);
    }
     
    public void clickSearch(){
        searchButton.click();
    }
     
}