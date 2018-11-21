package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{


    //Constructor
    public HomePage (WebDriver driver, WebDriverWait wait){

        super(driver, wait);
    }

    //Page Variables
    String baseURL = "https://www.phptravels.net";

    //Web Elements
    String myAccountLinkXpath = "/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a";
    String signUpLinkXpath = "/html/body/nav/div/div[2]/ul[2]/ul/li[1]/ul/li[2]/a";
    String logOutLinkXpath = "/html/body/nav/div/div[2]/ul[2]/ul/li[1]/ul/li[2]/a";


    //Go to pages.HomePage
    public void goToHomePage (){
        driver.get(baseURL);
    }

    //Go to pages.SignUpPage
    public void goToSignUpPage (){
        click(By.xpath(myAccountLinkXpath));
        click(By.xpath(signUpLinkXpath));
    }

    public void logOut (){
        click(By.xpath(myAccountLinkXpath));
        click(By.xpath(logOutLinkXpath));
    }

}
