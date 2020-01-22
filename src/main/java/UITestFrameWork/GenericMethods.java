package UITestFrameWork;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import logger.Log;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GenericMethods {
 WebDriver driver =null;

    // common timeout for all tests can be set here
    public final int timeOut = 60;

    public GenericMethods(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * method verify whether element is present on screen
     *
     * @param targetElement element to be present
     * @return true if element is present else throws exception
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     *                              or otherwise occupied, and the thread is interrupted, either before
     *                              or during the activity.
     */

    public Boolean isElementPresent(By targetElement)  throws InterruptedException {
        Boolean isPresent = driver.findElements(targetElement).size() > 0;
        return isPresent;
    }


    /**
     * method to verify the expected text
     * @param  expectedText
     */
    public boolean isTextPresent(String expectedText)
    {
        By  dycObject  = By.xpath("//*[text()='"+expectedText+"'] | //*[contains(text(),'"+expectedText+"')]");
        try{

              WebDriverWait wait = new WebDriverWait(driver,timeOut);
              wait.until(visibilityOfElementLocated(dycObject));
              Boolean isPresent = driver.findElements(dycObject).size() > 0;
              return isPresent;
        }catch (TimeoutException e)
        {
            System.out.println("Expected  is not visible: " + dycObject);
            throw e;
        }
    }



    /**
     * method to get text  for the targetElement
     * @param  targetElement
     */
    public String getText(By targetElement)
    {

        try{

            WebDriverWait wait = new WebDriverWait(driver,timeOut);
            wait.until(visibilityOfElementLocated(targetElement));
            return driver.findElement(targetElement).getText();
        }catch (TimeoutException e)
        {
            System.out.println("Expected  is not visible: " + targetElement);
            throw e;
        }
    }
    /**
     * method to wait for an element to be visible
     *
     * @param targetElement element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public boolean waitForVisibility(By targetElement) {
        try{
            WebDriverWait wait = new WebDriverWait(driver,timeOut);
            wait.until(visibilityOfElementLocated(targetElement));
            return  true;
        }catch (TimeoutException e)
        {
            System.out.println("Element is not visible: " + targetElement);
            throw e;
        }
    }




    /**
     * method to wait for an element until it is invisible
     *
     * @param targetElement element to be invisible
     * @return true if element gets invisible else throws TimeoutException
     */

    public boolean waitForInvisibility(By targetElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(invisibilityOfElementLocated(targetElement));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is still visible: " + targetElement);
            System.out.println();
            System.out.println(e.getMessage());
            throw e;

        }
    }




    /**
     * method to find an element
     *
     * @param locator element to be found
     * @return WebElement if found else throws NoSuchElementException
     */
    public WebElement findElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element;
        } catch (NoSuchElementException e) {
            Log.logError(this.getClass().getName(), "findElement", "Element not found" + locator);
            throw e;
        }
    }

    /**
     * method to find all the elements of specific locator
     *
     * @param locator element to be found
     * @return return the list of elements if found else throws NoSuchElementException
     */
    public List<WebElement> findElements(By locator) {
        try {
            List<WebElement> element = driver.findElements(locator);
            return element;
        } catch (NoSuchElementException e) {
            Log.logError(this.getClass().getName(), "findElements", "element not found" + locator);
            throw e;
        }
    }

    /**
     * method to get message test of alert
     *
     * @return message text which is displayed
     */
    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            return alertText;
        } catch (NoAlertPresentException e) {
            throw e;
        }
    }

    /**
     * method to verify if alert is present
     *
     * @return returns true if alert is present else false
     */
    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(alertIsPresent());
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            throw e;
        }
    }
    /**
     * method to Accept Alert if alert is present
     */

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(alertIsPresent());
        driver.switchTo().alert().accept();
    }

    /**
     * method to Dismiss Alert if alert is present
     */

    public void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(alertIsPresent());
        driver.switchTo().alert().dismiss();
    }
    /**
     * method to click li element
     *
     * @param listItem element to be found
     *
     */

    public void clickListItem(String listItem)
    {
        By liElement  = By.xpath("//li[text()='"+listItem+"']");
        driver.findElement(liElement).click();
    }
}
