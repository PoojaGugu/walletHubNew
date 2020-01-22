package UITestFrameWork;
import logger.Log;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;

public class GenericReports extends GenericMethods{

    public GenericReports(WebDriver driver) {
        super(driver);
    }

    /**
     * Validate the reporting condition
     * @param condition   true or false
     * @param message  report step message
     *
     */
    public void assertCondtion(boolean condition, String message) {
        if (condition) {
            Log.info(message + " validation is successfull");
            Reporter.log(message + " validation is successfull");
        }
        else
        {
            Reporter.log(message + " validation is unsuccessfull");
            takeScreenshot(message);
            Assert.fail(message + " validation is unsuccessfull");
        }
    }
    /**
     * Take the screen shot of the page
     * @param screenshotName   true or false
     *
     */
    public void takeScreenshot( String screenshotName)
    {
        String timeStamp = Utilities.getTime();
        String path = System.getProperty("user.dir")
                + "\\test-output\\images\\" + screenshotName + timeStamp
                + ".png";
        Reporter.log("Storing screenshot at " + path);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    }
}