package IntegrationTests.WalletHubScreens;

import UITestFrameWork.GenericMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReviewPage extends GenericMethods {


    public ReviewPage(WebDriver driver)
    {
        super(driver);
    }

    public By reviewbtn  = By.xpath("//*[@class='profile-nav-module']//span[text()='Reviews']");
    public By stars = By.xpath("(//review-star)[3]/div/*[@class='rvs-star-svg']");
    public By rating =By.xpath("(//h4[text()='Test Insurance Company']/following-sibling::review-star/div/*)//*[@stroke='#4ae0e1']");
    public By dropDown=By.xpath(" //div[@class='dropdown second']");


    public By selectItem  = By.xpath("//div[@class='dropdown second selected']/*[@class='dropdown-selected']");
    public By reviewTextArea = By.xpath("//*[@placeholder='Write your review...']");
    public By submitBtn =By.xpath("//*[text()='Submit']");
    //After Review Submit  page
    public By  selectedstars = By.xpath("//div[@class='rating-box-wrapper']//*[@stroke='#4ae0e1']") ;
    public By reviewTxt = By.xpath("//*[text()='Edit Review']/../following-sibling::*/P");
    public By continueBtn =By.xpath("//*[text()='Continue']");
  //  (//*[contains(text(),'sree1_svenkat')])[3]/../../following-sibling::review-star//*[@stroke='#4ae0e1']

    //After review submit verify the star

}


