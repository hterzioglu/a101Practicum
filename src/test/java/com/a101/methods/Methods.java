package com.a101.methods;

import com.a101.driver.BaseTestWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Methods {

    WebDriver driver;

    FluentWait<WebDriver> wait;

    public Methods(){

        driver = BaseTestWeb.driver;
        wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class);

    }
    public WebElement findElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public boolean elementGorunurMu(By by){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        }catch(Exception e){
            return false;
        }
    }


    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement webElement, int seconds){

        WebDriverWait wait2 = new WebDriverWait(driver, seconds);
        WebElement element = wait2.until(ExpectedConditions.visibilityOf(webElement));
        return element;

    }

    public Select getSelect(By by) {
        return new Select(findElement(by));
    }

    public void selectByText(By by, String text) {
        getSelect(by).selectByVisibleText(text);
    }
}
