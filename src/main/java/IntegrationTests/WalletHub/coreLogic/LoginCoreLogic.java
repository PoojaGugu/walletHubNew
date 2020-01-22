package IntegrationTests.WalletHub.coreLogic;

import IntegrationTests.WalletHubScreens.LogIn;
import UITestFrameWork.GenericReports;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class LoginCoreLogic {
    GenericReports genericReports ;
    LogIn logIn;

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
        logIn.waitForVisibility(logIn.loginTab);
        Reporter.log("Verify Wallet Hub Login method ");
        genericReports.assertCondtion(logIn.isElementPresent(logIn.loginTab),"Verified log in Tab text field");
        logIn.findElement(logIn.loginTab).click();
        logIn.waitForVisibility(logIn.userName);
        genericReports.assertCondtion(logIn.isElementPresent(logIn.userName),"Verified username text field");
        genericReports.assertCondtion(logIn.isElementPresent(logIn.password),"Verified Password text field");
        logIn.findElement(logIn.userName).sendKeys(userName);
        logIn.findElement(logIn.password).sendKeys(password);
        logIn.waitForVisibility(logIn.LoginBtn);
        logIn.findElement(logIn.LoginBtn).click();
        Reporter.log("Verify Wallet Hub Login method End ");
    }
}
