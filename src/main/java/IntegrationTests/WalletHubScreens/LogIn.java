package IntegrationTests.WalletHubScreens;

import UITestFrameWork.GenericMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogIn extends GenericMethods {

    public  LogIn(WebDriver driver)
    {
        super(driver);
    }

    public By userName  =  By.xpath("//input[@placeholder='Email Address']");
    public By password  =  By.xpath("//input[@placeholder='Password']");
    public By loginTab  =  By.xpath( "//span[text()='Login']");
    public By LoginBtn = By.xpath("//button/span[text()='Login']/..");

}
