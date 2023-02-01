package com.orangeHRM.testScripts;

import com.orangeHRM.pages.LoginPage;
import com.orangeHRM.utils.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginpageTest {

    WebDriver driver;
    @BeforeMethod
    public void setup()
    {
        driver =Browser.openBrowser("chrome") ;
        Browser.openURL("https://www.google.com/");
    }
    @AfterMethod
    public void tearDown()
    {
        Browser.closeBrowser();
    }

    @Test
    public void verifyAutoCompleateFeatureOfGoogle()
    {
        LoginPage obj = new LoginPage(driver);
        WebElement searchBox=obj.getSearchBoxLocator();
        obj.enterTextinSearchBox(searchBox, "Selenium");
        obj.getSuggestedListValues();
        obj.selectIndexPositionValueFromSuggestedList(searchBox, 9);
        String actualText=obj.get_Actual_SelectedTextFromSearchBox(searchBox);
        obj.clickOnSelectedTextFromTheSuggestedList(searchBox);
        String expectedText=obj.get_Expected_SelectedText();
        boolean status=obj.verifyActualSelectedText_and_ExpectedText(actualText, expectedText);
        System.err.println("\n Is Actual & Expected Text are same Or Not? \n\n"+status);
    }
}
