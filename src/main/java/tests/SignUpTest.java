package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;
import utils.AssortedUtils;
import utils.ExcelDataConfig;

public class SignUpTest extends BaseTest {

    @Test (dataProvider = "signupData")
    public void ValidSignUpTest (String firstname, String lastname, String mobilenumber, String email, String password, String confpassword) throws InterruptedException{

        //*************PAGE INSTANTIATIONS*************
        HomePage homePage = new HomePage(driver,wait);
        SignUpPage signupPage = new SignUpPage(driver,wait);

        //*************PAGE METHODS********************
        //Open pages.HomePage
        homePage.goToHomePage();

        AssortedUtils.captureScreenshot(driver,"HomePage");

        //Go to pages.SignUpPage
        homePage.goToSignUpPage();


        Thread.sleep(2000);

        AssortedUtils.captureScreenshot(driver,"SignupPage");

        //SignUp
        signupPage.SignUp(firstname, lastname, mobilenumber, email, password, confpassword);

        //*************ASSERTIONS***********************

        Thread.sleep(2000);

        signupPage.verifySignUp("My Account");

        AssortedUtils.captureScreenshot(driver,"S-Up"+firstname+lastname);

        signupPage.logOut();
    }


    @DataProvider(name = "signupData")
    public Object[][] passData()
    {
        ExcelDataConfig config = new ExcelDataConfig("/Users/jorgevelasquez/IdeaProjects/LearnAutomation/TestData/PHPTravelsTestData.xlsx");

        int sheetSel = 1;  // put here the index of Excel Sheet

        int rows = config.getRowCount(sheetSel);

        int cols = 6;  // put here the # of columns your Excel Sheet has

        Object[][] data = new Object[rows][cols];

        for (int i=0; i<rows; i++)
        {
            for (int j=0; j<cols; j++){

                data[i][j] = config.getData(sheetSel, i, j);
            }

        }

        return data;

    }

}
