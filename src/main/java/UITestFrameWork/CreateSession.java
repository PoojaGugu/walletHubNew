package UITestFrameWork;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import logger.Log;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.remote.RemoteWebDriver;


public class CreateSession {
    public WebDriver driver = null;

    protected static Properties lobConfigProp = new Properties();
    protected static Properties localeConfigProp = new Properties();
    protected FileInputStream configFis, lobConfigFis;

    private final String CONFIG_FILE_PATH="//src//main//java//config//config.properties";
    protected File file = new File("");
    Properties configProp = new Properties();



    /**
     * this method creates the driver depending upon the passed parameter (Windows or iOS)
     *  and loads the properties files (config and test data properties files).
     * @param Browser ex Chrome ,Firefox , IE
     * @param methodName - name of the method under execution
     * @param  url
     * @throws Exception issue while loading properties files or creation of driver.
     */
    @Parameters({"Browser","url"})
    @BeforeMethod
    public  void createDriver(String Browser, String url, Method methodName) throws Exception{
        propertiesFileLoad();
        File propertiesFile = new File(file.getAbsoluteFile() + "//src//main//java//log4j.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
        Log.info("--------------------------------------");
        chooseBrowser(Browser,url,methodName);
            Log.info("Created the  driver Object");

    }

    /**
     *  this method loads properties files config and file having test data.
     *
     * @throws Exception property files are not loaded successfully
     */
    public void propertiesFileLoad() throws Exception{
        configFis = new FileInputStream(file.getAbsoluteFile()
                + CONFIG_FILE_PATH);
        configProp .load(configFis);

        File f = new File(file.getAbsoluteFile() + "//src//main//java//config//" + "config.properties");

        if (f.exists() && !f.isDirectory()) {
            lobConfigFis = new FileInputStream(file.getAbsoluteFile()
                    + "/src//main//java//config//" + "config.properties");
            lobConfigProp.load(lobConfigFis);


        }
        else {
            throw new Exception("Properties files loading failed ");
        }
    }

    public WebDriver getDriver()
    {
        return  this.driver;
    }
    /**
     * this method creates the driver depending upon the passed parameter browser
     *  and loads the properties files (config and test data properties files).
     * @param Browser ex Chrome ,Firefox , IE
     * @param methodName - name of the method under execution
     * @param  url
     * @throws Exception issue while loading properties files or creation of driver.
     */
    public void chooseBrowser(String Browser, String url, Method methodName) throws IOException {

        String executionMode = lobConfigProp.getProperty("executionMode");
        String serverIP = lobConfigProp.getProperty("serverIP");
        if (Browser.equalsIgnoreCase("ff")||Browser.equalsIgnoreCase(""))
        {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("name", methodName.getName());
            firefoxOptions.setCapability("screenResolution","1280x1024");
             if (executionMode.equalsIgnoreCase("local"))
             {
                driver = new FirefoxDriver(firefoxOptions);
            } else {
                driver = new RemoteWebDriver(new URL("http://" + serverIP + ":4444/wd/hub"), firefoxOptions);
            }

        }
        else if (Browser.equalsIgnoreCase("ie"))
        {
                InternetExplorerOptions  ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability("name", methodName.getName());
                ieOptions.setCapability("screenResolution","1280x1024");
                ieOptions.setCapability("name", methodName.getName());

                if(executionMode.equalsIgnoreCase("local"))
                {
                    File file = new File("IEDriverServer.exe");
                    System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                    driver = new InternetExplorerDriver(ieOptions);
                }
                {

                    driver =new RemoteWebDriver(new URL("http://"+serverIP+":4444/wd/hub"), ieOptions);
                }

                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.get(url);

        }
        else if (Browser.equalsIgnoreCase("chrome"))
        {

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("name", methodName.getName());
                chromeOptions.setCapability("screenResolution","1280x1024");
                chromeOptions.setCapability("name", methodName.getName());
                chromeOptions.setCapability("name", methodName.getName());

                //options.addArguments("--disable-notifications");
                Map prefs=new HashMap();
                prefs.put("profile.default_content_setting_values.notifications", 1);
                chromeOptions.setExperimentalOption("prefs",prefs);
                chromeOptions.addArguments("--lang=es");
                chromeOptions.addArguments("--disable-notifications");
                if(executionMode.equalsIgnoreCase("local"))
                {
                   // File file = new File("chromedriver.exe");
                    //System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                    driver =new ChromeDriver(chromeOptions);
                }
                else
                {
                        driver =new RemoteWebDriver(new URL("http://"+serverIP+":4444/wd/hub"), chromeOptions);
                }
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                driver.get(url);

        }
       else if (Browser.equalsIgnoreCase("ie"))
        {
            InternetExplorerOptions  ieOptions = new InternetExplorerOptions();
            ieOptions.setCapability("name", methodName.getName());
            ieOptions.setCapability("screenResolution","1280x1024");
            ieOptions.setCapability("name", methodName.getName());
            if(executionMode.equalsIgnoreCase("local"))
            {
                File file = new File("IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                driver = new InternetExplorerDriver(ieOptions);
            }
            {

                driver =new RemoteWebDriver(new URL("http://"+serverIP+":4444/wd/hub"), ieOptions);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(url);

        }
        else
        {

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("name", methodName.getName());
            chromeOptions.setCapability("screenResolution","1280x1024");
            chromeOptions.setCapability("name", methodName.getName());
            chromeOptions.setCapability("name", methodName.getName());
            Map prefs=new HashMap();
            prefs.put("profile.default_content_setting_values.notifications", 1);
            chromeOptions.setExperimentalOption("prefs",prefs);
            chromeOptions.addArguments("--lang=es");
            chromeOptions.addArguments("--disable-notifications");
            if(executionMode.equalsIgnoreCase("local"))
            {
               // File file = new File("chromedriver.exe");
                //System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                driver =new ChromeDriver(chromeOptions);
            }
            else
            {
                driver =new RemoteWebDriver(new URL("http://"+serverIP+":4444/wd/hub"), chromeOptions);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.get(url);
        }

        }
    }

