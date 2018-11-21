package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;
import utils.AssortedUtils;

import java.io.File;

public class PhantomJSTest
{
    @Test(priority = 1, description = "Opens Page and Take a Screenshot and Save it.")
    public void verifyPageTitle()
    {
        WebDriver driver = new PhantomJSDriver();

        File src = new File("/Users/jorgevelasquez/IdeaProjects/Drivers/PhantomJS/bin/phantomjs");

        System.setProperty("phantomjs.binary.path",src.getAbsolutePath());

        driver.get("https://www.phptravels.net/");

        driver.manage().window().maximize();

        System.out.println(driver.getTitle());

        AssortedUtils.captureScreenshot(driver,"PhantomJSDriverExample");
    }

}
