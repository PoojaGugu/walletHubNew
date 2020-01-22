package FaceBook;

import IntegrationTests.FaceBook.coreLogic.LoginCoreLogic;
import IntegrationTests.FaceBook.coreLogic.ProfileCoreLogic;
import UITestFrameWork.CreateSession;
import UITestFrameWork.ExcelDataProvider;
import logger.Log;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Hashtable;

public class VerifyStatusUpdate extends CreateSession {

        LoginCoreLogic loginCoreLogic;
        ProfileCoreLogic profileCoreLogic;
        String userName;
        String password;

    /**
     * this method instantiate required helpers depending on the platform(android or iOS)
     * @param invokeDriver for browser
     */
    @Parameters({"Browser","url"})
    @BeforeMethod
    public void instantiateHelpers(String invokeDriver,String url){
        loginCoreLogic = new LoginCoreLogic(driver);
        profileCoreLogic = new ProfileCoreLogic(driver);
    }


    @DataProvider
    public Object[][] FormsData() throws Exception{

        String datafile =System.getProperty("user.dir")+ "/src/test/resources/FaceBook.xlsx";
         return   ExcelDataProvider.excelDataExtrator(datafile ,"facebook","execute","Y");

    }
    /**
     * method to verify login to android and iOS app
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     * or otherwise occupied, and the thread is interrupted, either before
     *  or during the activity.
     */
    @Test(dataProvider="FormsData")
    public void LoginVerification(HashMap<String, String> data) throws InterruptedException {
        Reporter.log("Verify face book status post message");
        loginCoreLogic.verifyLoginScenario(data.get("userName"), data.get("Password"));
        profileCoreLogic.postMessage(data.get("status"));
        profileCoreLogic.verifyPostMessage(data.get("status"));
        Log.info("Verified the login");
    }
}
