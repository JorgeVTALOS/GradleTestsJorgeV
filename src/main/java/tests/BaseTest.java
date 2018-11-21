package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeTest
    public void setup(){

        System.setProperty("webdriver.gecko.driver", "/Users/jorgevelasquez/IdeaProjects/Drivers/SeleniumDrivers/geckodriver");

        // Create Object of FirefoxOption Class
        FirefoxOptions option=new FirefoxOptions();

        //Set the setHeadless is equal to true which will run test in Headless mode
        option.setHeadless(false);

        driver = new FirefoxDriver(option);

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 25);

    }

    @AfterTest
    public void teardown(){ driver.quit(); }


}
