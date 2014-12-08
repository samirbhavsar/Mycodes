import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
 
public class EmailBoxPage extends LoadableComponent<EmailBoxPage>{
     
    private final WebDriver driver;
    private final LoadableComponent<?> parent;
     
    @FindBy(css = ".T-I-KE")
    WebElement composeButton;
     
    public EmailBoxPage(WebDriver driver, LoadableComponent<?> parent){
        this.driver = driver;
        this.parent = parent;
         
        PageFactory.initElements(driver, this);
    }
 
    @Override
    protected void isLoaded() throws Error {
        try{
            Assert.assertTrue("Compose button is not displayed", driver.findElement(By.cssSelector(".T-I-KE")).isDisplayed());
        }catch(NoSuchElementException ex){
            throw new AssertionError();
        }
    }
 
    @Override
    protected void load() {
        parent.get();
        driver.get("https://mail.google.com/mail/u/0/?tab=wm&pli=1#inbox");
    }
     
    public void clickComposeButton(){
        composeButton.click();
    }
}