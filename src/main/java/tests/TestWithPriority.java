package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestWithPriority {

    WebDriver driver;


    @BeforeClass
    public void openBrowser()
    {
        System.setProperty("webdriver.gecko.driver", "/Users/jorgevelasquez/IdeaProjects/Drivers/SeleniumDrivers/geckodriver");

        driver =  new FirefoxDriver();

        driver.get("https://www.phptravels.net/login");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        System.out.println("==========  Browser Opened ==========");
    }

    @Test
    public void startApp()
    {

        String currURL = driver.getCurrentUrl();

        Assert.assertTrue(currURL.contains("/login"));

        System.out.println("Login Page Opened");
    }

    @Test(dependsOnMethods = "startApp")
    public void loginApp()
    {
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("user@phptravels.com");

        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("demouser");

        driver.findElement(By.xpath("//form[@id='loginfrm']//button[@type='submit']")).click();

        boolean status = driver.findElement(By.xpath("//h3[@class='RTL']")).isDisplayed();

        System.out.println("Login Successful");

        Assert.assertTrue(status);
    }

    @Test(dependsOnMethods = "loginApp")
    public void logoutApp()
    {

        driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a")).click();

        driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/div[2]/ul[2]/ul[1]/li[1]/ul[1]/li[2]/a[1]")).click();

        System.out.println("Logout Successful");

        Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginfrm']//button[@type='submit']")).isDisplayed());

        driver.quit();
    }


    @AfterClass
    public void closeBrowser()
    {
        driver.quit();

        System.out.println("==========  Browser Closed  ==========");
    }

}
