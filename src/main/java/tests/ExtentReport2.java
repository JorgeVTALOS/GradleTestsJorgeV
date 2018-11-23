package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
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
    ExtentTest test;
    ExtentTest testChild;
    ExtentTest testParent;
    ExtentHtmlReporter htmlReporter;
    WebDriver driver;

    String rootPath = System.getProperty("user.dir");


    @BeforeTest
    public void start() {

        htmlReporter = new ExtentHtmlReporter(new File(rootPath + "/Reports/AutomationReport.html"));

        htmlReporter.loadXMLConfig(new File(rootPath + "/Extent-Config.xml"));

        reports = new ExtentReports();

        reports.attachReporter(htmlReporter);

        testParent = reports.createTest("Testing some Sites with Firefox Driver");

        reports.setSystemInfo("Environment", "QA");

        reports.setSystemInfo("User Name", System.getProperty("user.name"));

        reports.setSystemInfo("Time Zone", System.getProperty("user.timezone"));

        reports.setSystemInfo("Machine", System.getProperty("os.name") + "-" + System.getProperty("os.arch"));

        reports.setSystemInfo("Selenium", "3.7.0");

        reports.setSystemInfo("Maven", "3.5.2");

        reports.setSystemInfo("Java Version", System.getProperty("java.vm.version"));

    }


    @Test(priority = 1)
    public void accessTest1() throws Exception {

        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();

        testChild.log(Status.PASS, MarkupHelper.createLabel("Browser opened GeckoDriver.", ExtentColor.PURPLE));

        testChild.assignCategory("Functional Testing");

        driver.get("https://www.amazon.com");

        System.out.println("This page title is ..." + driver.getTitle());

        testChild.log(Status.PASS, MarkupHelper.createLabel("Website has been opened.", ExtentColor.PURPLE));

        Assert.assertTrue(driver.getTitle().contains("Amazon"));

        testChild.log(Status.PASS, MarkupHelper.createLabel("Website title verified.", ExtentColor.PURPLE));

    }

    @Test(priority = 2)
    public void accessTest2() throws Exception {

        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();

        testChild.log(Status.PASS, MarkupHelper.createLabel("Browser opened GeckoDriver.", ExtentColor.PURPLE));

        testChild.assignCategory("Functional Testing");

        driver.get("https://www.google.com");

        testChild.log(Status.PASS, MarkupHelper.createLabel("Website has been opened.", ExtentColor.PURPLE));

        System.out.println("This page title is ..." + driver.getTitle());

        Assert.assertTrue(driver.getTitle().contains("Google"));

        testChild.log(Status.PASS, MarkupHelper.createLabel("Website title verified.", ExtentColor.PURPLE));

    }

    @Test(priority = 3)
    public void phantomjsTest()
    {

        driver = new PhantomJSDriver();

        File src = new File("/Users/jorgevelasquez/IdeaProjects/Drivers/PhantomJS/bin/phantomjs");

        System.setProperty("phantomjs.binary.path",src.getAbsolutePath());

        testChild.log(Status.PASS, MarkupHelper.createLabel("Headless Browser PhantomJS Opened.", ExtentColor.PURPLE));

        testChild.assignCategory("Functional Testing");

        driver.get("https://www.phptravels.net/");

        testChild.log(Status.PASS, MarkupHelper.createLabel("Website has been opened.", ExtentColor.PURPLE));

        System.out.println("This page title is ..." + driver.getTitle());

        Assert.assertTrue(driver.getTitle().contains("PHPTRAVELS"));

        testChild.log(Status.PASS, MarkupHelper.createLabel("Website title verified.", ExtentColor.PURPLE));

    }


    @BeforeMethod
    public void register(Method method) {

        String testName = method.getName();

        testChild = testParent.createNode(testName);

    }


    @AfterMethod
    public void captureStatus(ITestResult result) throws IOException {

        String temp = AssortedUtils.captureScreenshot(driver, result.getName());

        if (result.getStatus() == ITestResult.SUCCESS) {

            testChild.log(Status.PASS, "The Test method named as " + result.getName() + " has PASSED");

            testChild.pass("SS-"+result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        } else if (result.getStatus() == ITestResult.FAILURE) {

            testChild.log(Status.FAIL, "The Test method named as " + result.getName() + " has FAILED");

            testChild.log(Status.FAIL, "Test Failure : " + result.getThrowable());

            testChild.fail("SS-"+result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        } else if (result.getStatus() == ITestResult.SKIP) {

            testChild.log(Status.SKIP, "The Test method named as " + result.getName() + " has SKIPPED");

        }

    }


    @AfterTest
    public void cleanUp() {

        reports.flush();

        driver.quit();

    }

}
