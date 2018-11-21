package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.AssortedUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class ExtentReport2 {

    ExtentReports reports;
    ExtentTest testInfo;
    ExtentHtmlReporter htmlReporter;
    WebDriver driver;

    @BeforeTest
    public void start() {

        htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/Reports/AutomationReport.html"));

        htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/Extent-Config.xml"));

        reports = new ExtentReports();

        reports.setSystemInfo("Environment", "QA");

        reports.attachReporter(htmlReporter);
    }


    @Test(priority = 2)
    public void accessTest1() throws Exception {

        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();

        driver.get("https://www.amazon.com");

        System.out.println("This page title is ..." + driver.getTitle());

        Assert.assertTrue(driver.getTitle().contains("Amazon"));

        testInfo.log(Status.INFO, "This is Acces Test 1");

    }

    @Test(priority = 1)
    public void accessTest2() throws Exception {

        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();

        driver.get("https://www.google.com");

        System.out.println("This page title is ..." + driver.getTitle());

        Assert.assertTrue(driver.getTitle().contains("Google"));

        testInfo.log(Status.INFO, "This is Acces Test 2");

    }

    @Test(priority = 3)
    public void phantomjsTest()
    {
        driver = new PhantomJSDriver();

        File src = new File("/Users/jorgevelasquez/IdeaProjects/Drivers/PhantomJS/bin/phantomjs");

        System.setProperty("phantomjs.binary.path",src.getAbsolutePath());

        driver.get("https://www.phptravels.net/");

        System.out.println("This page title is ..." + driver.getTitle());

        Assert.assertTrue(driver.getTitle().contains("PHPTRAVELS"));

        testInfo.log(Status.INFO, "This is PhantomJS Test 3");

    }


    @BeforeMethod
    public void register(Method method) {

        String testName = method.getName();

        testInfo = reports.createTest(testName);

    }


    @AfterMethod
    public void captureStatus(ITestResult result) throws IOException {

        String temp = AssortedUtils.captureScreenshot(driver, result.getName());

        if (result.getStatus() == ITestResult.SUCCESS) {

            testInfo.log(Status.PASS, "The Test method named as " + result.getName() + " has PASSED");

            testInfo.pass("SS-"+result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        } else if (result.getStatus() == ITestResult.FAILURE) {

            testInfo.log(Status.FAIL, "The Test method named as " + result.getName() + " has FAILED");

            testInfo.log(Status.FAIL, "Test Failure : " + result.getThrowable());

            testInfo.fail("SS-"+result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        } else if (result.getStatus() == ITestResult.SKIP) {

            testInfo.log(Status.SKIP, "The Test method named as " + result.getName() + " has SKIPPED");

        }

    }


    @AfterTest
    public void cleanUp() {

        reports.flush();

        driver.close();

    }

}
