package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.AssortedUtils;

import java.io.IOException;

public class ExtentReportDemo
{
    ExtentHtmlReporter reporter;
    ExtentReports extent;
    ExtentTest logger;
    WebDriver driver;


    @BeforeTest
    public void setup()
    {

        reporter = new ExtentHtmlReporter("./Reports/ReportExtent.html");

        extent = new ExtentReports();

        extent.attachReporter(reporter);

        logger= extent.createTest("accessTest");
    }


    @Test
    public void accessTest() throws Exception
    {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get("https://www.google.com");

        System.out.println("This page title is ..." + driver.getTitle());

        Assert.assertTrue(driver.getTitle().contains("Google"));
    }


    @AfterTest
    public void finishing(ITestResult result) throws IOException
    {

        System.out.println(result.getStatus());

        System.out.println(ITestResult.FAILURE);

        if (result.getStatus()== ITestResult.FAILURE)
        {

            String temp = AssortedUtils.captureScreenshot(driver, "AfterClassScreen");

            logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        } else {

            extent.flush();

            driver.close();

        }

    }
}
