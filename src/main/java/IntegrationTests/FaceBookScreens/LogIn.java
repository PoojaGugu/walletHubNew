package IntegrationTests.FaceBookScreens;

import UITestFrameWork.GenericMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogIn extends GenericMethods {

    public  LogIn(WebDriver driver)
    {
        super(driver);
    }

    // setting local
     public  By language =By.xpath("//*[@title='English (US)']");
    // Locators on the login screen of your
   public  By username = By.name("email");
   public  By password = By.name("pass");
   public By LoginBtn = By.xpath("//*[@type='submit']");
}
