package WalletHub;


import IntegrationTests.WalletHub.coreLogic.LoginCoreLogic;
//import IntegrationTests.WalletHub.coreLogic.ProfileCoreLogic;
import IntegrationTests.WalletHub.coreLogic.ReviewCoreLogic;
import UITestFrameWork.CreateSession;
import UITestFrameWork.ExcelDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class VerifyWriteRevie extends CreateSession {

    IntegrationTests.WalletHub.coreLogic.LoginCoreLogic loginCoreLogic;
    IntegrationTests.WalletHub.coreLogic.ReviewCoreLogic reviewCoreLogic;
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
        reviewCoreLogic = new ReviewCoreLogic(driver);
       // profileCoreLogic = new ProfileCoreLogic(driver);
    }

    @DataProvider
    public Object[][] FormsData() throws Exception{

        String datafile =System.getProperty("user.dir")+ "/src/test/resources/WalletHub.xlsx";
        return   ExcelDataProvider.excelDataExtrator(datafile ,"Wallet","execute","Y");

    }
@Test(dataProvider="FormsData")
public void verifyWriteReview (HashMap<String, String> data) throws InterruptedException {
    loginCoreLogic.verifyLoginScenario(data.get("email"),data.get("password"));
    reviewCoreLogic.selectReview(Integer.parseInt(data.get("ratingStars")));
    reviewCoreLogic.verifySelectedStars(Integer.parseInt(data.get("ratingStars")));
    reviewCoreLogic.selectPolicy(data.get("policyDropdown"));
    reviewCoreLogic.VerifyselectedPolicy(data.get("policyDropdown"));
    reviewCoreLogic.writeReview(data.get("review"));
    reviewCoreLogic.verifyWriteReview(data.get("title"),data.get("reviewDesc"),data.get("review"),Integer.parseInt(data.get("ratingStars")));
    reviewCoreLogic.verifyWriteReviewAfterSubmit(data.get("email"),data.get("review"),Integer.parseInt(data.get("ratingStars")));

}
}
