package com.a101web.methods;

import com.a101web.driver.BaseTestWeb;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
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
    protected static AppiumDriver<MobileElement> appiumDriver;

    FluentWait<WebDriver> wait;
    FluentWait<AppiumDriver> waitMobile;

    public Methods(){


        driver = BaseTestWeb.driver;

        if(driver!=null){
        wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class);
    }
        if(appiumDriver!=null) {
            waitMobile = new FluentWait<>(appiumDriver);
            waitMobile.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class);

        }
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
