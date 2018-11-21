package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelDataConfig;

public class MenuTest {

    WebDriver driver;

    @Test(dataProvider = "phptravelsData")
    public void CheckLinksPHPTravels(String confirmationText, String xPath) throws InterruptedException {

//        driver = new FirefoxDriver();
//
//        driver.manage().window().maximize();
//
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://www.phptravels.net");

        driver.findElement(By.xpath(xPath)).click();

        Assert.assertTrue(driver.getTitle().contains(confirmationText), "Wrong Link redirection, Check Code");

        System.out.println("Page Title verified, Succesfull Test, NO CODE PROBLEMS");

    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }


    @DataProvider(name = "phptravelsData")
    public Object[][] passData()
    {
        ExcelDataConfig config = new ExcelDataConfig("/Users/jorgevelasquez/IdeaProjects/LearnAutomation/TestData/PHPTravelsTestData.xlsx");

        int sheetSel = 2;  // put here the index of Excel Sheet

        int rows = config.getRowCount(sheetSel);

        Object[][] data = new Object[rows][2];

        for (int i=0; i<rows; i++)
        {
            data[i][0] = config.getData(sheetSel, i, 0);
            data[i][1] = config.getData(sheetSel, i, 1);
        }

        return data;

    }

}
