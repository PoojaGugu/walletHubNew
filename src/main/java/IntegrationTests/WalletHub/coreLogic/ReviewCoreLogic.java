package IntegrationTests.WalletHub.coreLogic;

import IntegrationTests.WalletHubScreens.ReviewPage;
import UITestFrameWork.GenericReports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ReviewCoreLogic {
    GenericReports genericReports ;
    ReviewPage reviewPage;
    public ReviewCoreLogic(WebDriver driver)
    {
        genericReports = new GenericReports(driver);
        reviewPage = new ReviewPage(driver);
    }
    /**
     * method to selects the number of star ratings
     * @param numberofStars
     *
     */
    public void selectReview(int numberofStars) throws InterruptedException {
        Reporter.log("Select Number of stars");
        genericReports.assertCondtion(reviewPage.isElementPresent(reviewPage.reviewbtn),"Verified Review Button");
        reviewPage.findElement(reviewPage.reviewbtn).click();
        //findElements

        reviewPage.findElements(reviewPage.stars).get(numberofStars-1).click();
        Reporter.log("Select Number of stars Ends");
    }
    /**
     * method to verifys the number of star ratings
     * @param expectedStars
     *
     */
    public  void verifySelectedStars(int expectedStars)
    {
        Reporter.log("Verify Number of stars");
        genericReports.assertCondtion( reviewPage.findElements(reviewPage.rating).size()==expectedStars,"Verifying the number of stars");
        Reporter.log("Verify Number of stars End");
    }
    /**
     * method to select type of Policy
     * @param nameOfPolicy
     *
     */
    public void selectPolicy(String nameOfPolicy) throws InterruptedException {
        Reporter.log("Select the type of Policy");
        genericReports.assertCondtion(reviewPage.isElementPresent(reviewPage.dropDown),"Verified Review Button");
        reviewPage.findElement(reviewPage.dropDown).click();
        reviewPage.clickListItem(nameOfPolicy);
        Reporter.log("Select the type of Policy");
    }

    /**
     * method to verifies the selected type of Policy
     * @param nameOfPolicy
     *
     */
    public void VerifyselectedPolicy(String nameOfPolicy) throws InterruptedException {
        Reporter.log("Verify the type of Policy Selected");
        String selectedItem = reviewPage.findElement(reviewPage.selectItem).getText();
        genericReports.assertCondtion(selectedItem.equals(nameOfPolicy),"Verified Type Selected Policy");
        Reporter.log("Verify the type of Policy Selected End");
    }
    /**
     * method  to write the review
     * @param writeReview
     *
     */
    public void writeReview(String writeReview ) throws InterruptedException {
        Reporter.log("Write the review ");
        genericReports.assertCondtion(reviewPage.isElementPresent(reviewPage.reviewTextArea),"Verified Review Text Area");
        reviewPage.findElement(reviewPage.reviewTextArea).sendKeys(writeReview);
        genericReports.assertCondtion(reviewPage.isElementPresent(reviewPage.submitBtn),"Verified Submit Button");
        reviewPage.findElement(reviewPage.submitBtn).click();
        Reporter.log("Write the review End  ");
    }

    /**
     * method  to verify the review and start rating
     * @param expectedwriteReview
     * @param stars
     * @param title
     * @param  reviewDesc
     *
     */
    public void verifyWriteReview(String title,String reviewDesc,String expectedwriteReview,int stars) throws InterruptedException {
        Reporter.log("Vrify the review written ");

        //Your review has been posted.
        genericReports.assertCondtion(reviewPage.isTextPresent(title),"Verify the title");
        genericReports.assertCondtion(reviewPage.isTextPresent(reviewDesc),"Verify the title description");
        genericReports.assertCondtion( reviewPage.findElements(reviewPage.selectedstars).size()==stars,"Verifying the number of stars");
        genericReports.assertCondtion( reviewPage.findElement(reviewPage.reviewTxt).getText().equals(expectedwriteReview),"Verify the review text");
        genericReports.assertCondtion(reviewPage.isElementPresent(reviewPage.continueBtn),"Verified Review Text Area");
       // reviewPage.findElement(reviewPage.continueBtn).click();
    }

    /**
     * method  to verify the review and start rating after complete submited
     * @param username
     * @param expectedwriteReview
     * @param stars
     *
     *
     */
    public void verifyWriteReviewAfterSubmit(String username,String expectedwriteReview,int stars) throws InterruptedException {
        Reporter.log("Vrify the review written After Submited ");
        String user = username.split("@")[0];
        By starsObject = By.xpath("(//*[contains(text(),'"+user+"')])[3]/../../following-sibling::review-star//*[@stroke='#4ae0e1']");
        By reviewText =By.xpath("((//*[contains(text(),'"+user+"')])[3]/../../../div)[4]");
        Reporter.log("Vrify the review written submited by user ");
        genericReports.assertCondtion( reviewPage.findElements(starsObject).size()==stars,"Verifying the number of stars");
        genericReports.assertCondtion( reviewPage.findElement(reviewText).getText().equals(expectedwriteReview),"Verify the review text");
        Reporter.log("Vrify the review written After Submited End ");
    }
}
