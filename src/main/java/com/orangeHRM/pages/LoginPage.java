package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
       this.driver=driver;
    }
    public WebElement getSearchBoxLocator() {
        By searchBoxL = By.name("q");
        return driver.findElement(searchBoxL);
    }

    public void enterTextinSearchBox(WebElement ele, String text) {
        ele.sendKeys(text);
    }

    public void getSuggestedListValues() {
        By searchBoxL = By.xpath("//div[@class='erkvQe']//li[@role='presentation']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxL));

        List<WebElement> allElements = driver.findElements(searchBoxL);
        List<String> allOptionsValues = new ArrayList<>();

        for (WebElement ele : allElements) {
            String data = ele.getText();
            if (!(data.isBlank() || data.isEmpty() || data == null)) {
                allOptionsValues.add(data);
                System.out.println(data + "\n");
            }
        }

        System.out.println("Total Size Of Suggested List :" + allOptionsValues.size());

    }

    public void selectIndexPositionValueFromSuggestedList(WebElement ele, int n) {
        for (int i = 0; i <= n; i++) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            ele.sendKeys(Keys.ARROW_DOWN);
        }

    }

    public String get_Actual_SelectedTextFromSearchBox(WebElement ele)
    {
        String actualText= ele.getAttribute("value");
        System.out.println("Actual Selected Text Is : "+actualText);
        return actualText;
    }
    public void clickOnSelectedTextFromTheSuggestedList(WebElement ele)
    {
        ele.sendKeys(Keys.ENTER);
    }
    public String get_Expected_SelectedText()
    {
        By searchBoxL= By.name("q");
        WebElement ele=driver.findElement(searchBoxL);
        String expectedText= ele.getAttribute("value");
        System.out.println("Expected Text Is : "+expectedText);
        return expectedText;
    }

    public boolean verifyActualSelectedText_and_ExpectedText(String actualText, String expectedText)
    {
        return actualText.equals(expectedText);
    }

}
