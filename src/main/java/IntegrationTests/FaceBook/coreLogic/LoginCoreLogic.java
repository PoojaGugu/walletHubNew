package IntegrationTests.FaceBook.coreLogic;

import IntegrationTests.FaceBookScreens.LogIn;
import UITestFrameWork.GenericReports;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public  class LoginCoreLogic {

    LogIn logIn;
    GenericReports genericReports ;

    public  LoginCoreLogic(WebDriver driver)
    {
            logIn =new LogIn(driver);
            genericReports = new GenericReports(driver);

    }
    /**
     * method to verify login scenario on iOS app
     * @param userName emailId to be used for login
     * @param password - valid password
     */

    public  void verifyLoginScenario(String userName, String password)
            throws InterruptedException{
            logIn.waitForVisibility(logIn.language);
            Reporter.log("Verify Login method ");
            genericReports.assertCondtion(logIn.isElementPresent(logIn.language),"Verified Language Link");
            logIn.findElement(logIn.language).click();
            logIn.waitForVisibility(logIn.username);
            genericReports.assertCondtion(logIn.isElementPresent(logIn.username),"Verified username text field");
            genericReports.assertCondtion(logIn.isElementPresent(logIn.password),"Verified password text field");
            genericReports.assertCondtion(logIn.isElementPresent(logIn.LoginBtn),"Verified Signin button field");
            logIn.findElement(logIn.username).sendKeys(userName);
            logIn.findElement(logIn.password).sendKeys(password);
            logIn.waitForVisibility(logIn.LoginBtn);
            logIn.findElement(logIn.LoginBtn).click();
            genericReports.assertCondtion(logIn.isTextPresent("Create Post"),"Verified Login Successfully ");
            Reporter.log("Verify Login method  End");

    }
}
