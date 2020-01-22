package IntegrationTests.FaceBook.coreLogic;

import IntegrationTests.FaceBookScreens.ProfilePage;
import UITestFrameWork.GenericReports;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ProfileCoreLogic {
    ProfilePage profilePage;
    GenericReports genericReports ;

    public ProfileCoreLogic(WebDriver driver)
    {

        profilePage  = new ProfilePage(driver);
        genericReports = new GenericReports(driver);
    }
    /**
     * method to post stastus message
     * @param statusMsg  post message
     *
     */
    public void postMessage(String statusMsg) throws InterruptedException {
        Reporter.log("Verify post Message ");
        profilePage.waitForVisibility(profilePage.innerStatusBox);
        genericReports.assertCondtion(profilePage.isElementPresent(profilePage.innerStatusBox),"Verify  Status text area ");
        profilePage.findElement(profilePage.innerStatusBox).click();
        profilePage.waitForVisibility(profilePage.submitStatus);
        genericReports.assertCondtion(profilePage.isElementPresent(profilePage.innerStatusBox),"Verify  Status submit button ");
        profilePage.findElement(profilePage.innerStatusBox).sendKeys(statusMsg);
        Thread.sleep(2000);
        profilePage.waitForVisibility(profilePage.submitStatus);
        profilePage.findElement(profilePage.submitStatus).click();
        Reporter.log("Verify post Message End ");
    }
    /**
     * method to verifyes the posted stastus message
     * @param postMsg  post message
     *
     */
    public void verifyPostMessage(String postMsg) throws InterruptedException {
        Reporter.log("Verify poste status  Message ");
        //
        profilePage.waitForVisibility(profilePage.statusVerify);
        genericReports.assertCondtion(profilePage.isElementPresent(profilePage.innerStatusBox),"Verify  Status Posted meassge ");
        genericReports.assertCondtion(profilePage.getText(profilePage.statusVerify).equals(postMsg),"Verified Successfully  status posted message");

    }
}
