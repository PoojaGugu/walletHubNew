package IntegrationTests.FaceBookScreens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import UITestFrameWork.GenericMethods;

public class ProfilePage extends GenericMethods {

    public  ProfilePage(WebDriver driver)
    {

        super(driver);
    }

    // setting local

    public By innerStatusBox =By.xpath("//*[contains(@aria-label,'on your mind')]");
    // Locators on the login screen of your
    public  By create = By.xpath("//*[text()='Create Post']");
    public  By updateStatus = By.xpath("//div[text()='Tag Friends']");
    public By submitStatus = By.xpath("//button[contains(.,'Post')]");
    public By btnClose = By.xpath("//a[text()='Close']");
    public By statusVerify = By.xpath("//div[@data-testid='post_message']/descendant::p");
}
