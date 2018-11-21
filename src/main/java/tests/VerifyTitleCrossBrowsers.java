package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VerifyTitleCrossBrowsers
{

    WebDriver driver;

    @Test
    @Parameters("browser")
    public void verifypageTitle(String browsername) throws Exception {

        if (browsername.equalsIgnoreCase("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", "/Users/jorgevelasquez/IdeaProjects/Drivers/SeleniumDrivers/geckodriver");
            driver = new FirefoxDriver();
        }else if (browsername.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "/Users/jorgevelasquez/IdeaProjects/Drivers/SeleniumDrivers/chromedriver");
            driver = new ChromeDriver();
        }else if (browsername.equalsIgnoreCase("safari"))
        {
            System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
            driver = new SafariDriver();
        }

        driver.manage().window().setSize(new Dimension(1600, 900));;

        driver.get("https://www.phptravels.net/");

        System.out.println(driver.getTitle());

        driver.close();

    }

}
