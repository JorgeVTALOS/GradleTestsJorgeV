package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

// This is what I need to go to a Home page
public class SignUpPage extends HomePage{


    //Constructor
    public SignUpPage (WebDriver driver, WebDriverWait wait){

        super(driver, wait);
    }

    //Page Variables


    //Web Elements
    String firstnameXpath = "//input[@placeholder='First Name']";
    String lastnameXpath = "//input[@placeholder='Last Name']";
    String mobilenumberXpath = "//input[@placeholder='Mobile Number']";
    String emailXpath = "//input[@placeholder='Email']";
    String passwordXpath = "//input[@placeholder='Password']";
    String confirmpasswordXpath = "//input[@placeholder='Confirm Password']";
    String signupButtonXpath = "//div[@class='form-group']//button[@type='submit']";
    String errorMessagefirstnameXpath = "//p[contains(text(),'The First name field is required.')]";
    String errorMessagelastnameXpath = "//p[contains(text(),'The Last Name field is required.')]";
    String errorMessageemailXpath = "//p[contains(text(),'The Email field is required.')]";
    String errorMessagepasswordXpath = "//p[contains(text(),'The Password field is required.')]";
    String errorMessageconfirmpasswordXpath = "//p[contains(text(),'Password not matching with confirm password.')]";

    //       ***  PAGE METHODS  ***       //

    //SignUp to Home Page
    public void SignUp(String firstname, String lastname, String mobilenumber, String email, String password, String confirmpassword){

        //Enter user's first name
        writeText(By.xpath(firstnameXpath),firstname);
        //Enter user's last name
        writeText(By.xpath(lastnameXpath),lastname);
        //Enter user's mobile number
        writeText(By.xpath(mobilenumberXpath),mobilenumber);
        //Enter user's email
        writeText(By.xpath(emailXpath),email);
        //Enter user's password
        writeText(By.xpath(passwordXpath),password);
        //Enter user's confirmation password
        writeText(By.xpath(confirmpasswordXpath),confirmpassword);
        //Click on SignUp Button
        click(By.xpath(signupButtonXpath));
    }


    public void verifySignUpFirstName (String expectedText){
        Assert.assertEquals(readText(By.xpath(errorMessagefirstnameXpath)), expectedText);
    }

    public void verifySignUpLastName (String expectedText) {
        Assert.assertEquals(readText(By.xpath(errorMessagelastnameXpath)), expectedText);
    }

    public void verifySignUpEmail (String expectedText) {
        Assert.assertEquals(readText(By.xpath(errorMessageemailXpath)), expectedText);
    }

    public void verifySignUpPassword (String expectedText) {
        Assert.assertEquals(readText(By.xpath(errorMessagepasswordXpath)), expectedText);
    }

    public void verifySignUpConfirmPassword (String expectedText) {
        Assert.assertEquals(readText(By.xpath(errorMessageconfirmpasswordXpath)), expectedText);
    }

    public void verifySignUp (String expectedText) {
        Assert.assertTrue(driver.getTitle().contains(expectedText));
    }

}
