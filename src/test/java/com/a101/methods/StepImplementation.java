package com.a101.methods;

import com.a101.driver.BaseTestWeb;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.Random;

public class StepImplementation extends BaseTestWeb {

    Methods methods;
    FluentWait<WebDriver> wait;

    public StepImplementation() {
        methods = new Methods();

    }

    @Step("<seconds> kadar bekle")
    public void waitForSecond(int second) throws InterruptedException {
        Thread.sleep(1000 * second);
    }

    @Step("<key> id'li elemente tıkla")
    public void clickByid(String Key) {
        driver.findElement(By.id(Key)).click();
        System.out.println(Key + "id li elemente tıklandı");
    }

    @Step("<key> css'li elemente tıkla")
    public void clickByCss(String Key) {
        driver.findElement(By.cssSelector(Key)).click();
        System.out.println(Key + "css'li elemente tıklandı");
    }

    @Step("<key> xpath'li elemente tıkla")
    public void clickByXpath(String Key) {
        driver.findElement(By.xpath(Key)).click();
        System.out.println(Key + "xpath li elemente tıkla");
    }

    @Step("<key> id li elemente <keywordc> degerini yaz")
    public void sendKeyByid(String Key, String Keywordc) {
        driver.findElement(By.id(Key)).sendKeys(Keywordc);
        System.out.println(Key + "elemente tıklandı");
    }

    @Step("<key> css li elemente <keywordc> degerini yaz")
    public void sendKeyByCss(String Key, String Keywordc) {
        driver.findElement(By.cssSelector(Key)).sendKeys(Keywordc);
        System.out.println(Key + "elemente tıklandı");
    }

    @Step("<key> xpath li elemente <keywordc> degerini yaz")
    public void sendKeyByXpath(String Key, String Keywordc) {
        driver.findElement(By.xpath(Key)).sendKeys(Keywordc);
        System.out.println(Key + "elemente tıklandı");
    }

    @Step("Id elementi <id> bul ve <keyword> degerini kontrol et")
    public void textControlById(String id, String keyword) {
        System.out.println("Dogru acılan sayfada ki text degeri" + driver.findElement(By.id(id)).getText());
        Assert.assertTrue("Text degeri bulunamadı", driver.findElement(By.id(id)).getText().equals(keyword));
    }

    @Step("Css elementi <css> bul ve <keyword> degerini kontrol et")
    public void textControlByCss(String css, String keyword) {
        System.out.println("Dogru acılan sayfada ki text degeri" + driver.findElement(By.cssSelector(css)).getText());
        Assert.assertTrue("text degeri bulunamadı", driver.findElement(By.cssSelector(css)).getText().equals(keyword));
    }

    @Step("Xpath elementi <xpath> bul ve <keyword> degerini kontrol et")
    public void textControlByxpath(String xpath, String keyword) {
        System.out.println("dogru acılan sayafada ki text degeri" + driver.findElement(By.xpath(xpath)).getText());
        Assert.assertTrue("text degeri bulunamadı", driver.findElement(By.xpath(xpath)).getText().equals(keyword));
    }

    @Step("<key> css li elementle random urun secme")
    public void randomlyClick(String key) {

        List<WebElement> randomPick =driver.findElements(By.cssSelector(key));
        Random random = new Random();
        int selectRandomInt =0;
        selectRandomInt = random.nextInt(randomPick.size());
        randomPick.get(selectRandomInt).click();
    }

    @Step("<key> id li elemente scroll et")
    public void scrollWithActionById(String key) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id(key))).build().perform();
    }

    @Step("<key> css li elemente scroll et")
    public void scrollWithActionBycss(String key) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(key))).build().perform();
    }

    @Step("<key> xpath li elemente scroll et")
    public void scrollWithActionByxpath(String key) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(key))).build().perform();
    }

    @Step("<key> xpath'li elementi görene kadar bekle")
    public WebElement findElementByxpath(String Key){

        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Key)));
    }
    @Step("<key> id'Li elementi görene kadar bekle")
    public WebElement findElementByid(String Key){
        return  wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Key)));
    }
    @Step("<key> css'li elementi görene kadar bekle")
    public WebElement findElementBycss(String Key){
        return  wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Key)));
    }

    @Step("<key> xpath'li elementinin görünür olması kontrol edilir")
    public void isElementVisiblebyXpath(String Key){

        Assert.assertTrue("sayfa açılmadı",methods.elementGorunurMu(By.xpath(Key)));
        System.out.println("dogru sayfa oldugu kontrol edildi");
    }
    @Step("<key> css'li elementinin görünür olması kontrol edilir")
    public void isElementVisiblebyCss(String Key) {

        Assert.assertTrue("sayfa açılmadı", methods.elementGorunurMu(By.cssSelector(Key)));
        System.out.println("dogru sayfa oldugu kontrol edildi");
    }

    @Step("<key> id'li elementinin görünür olması kontrol edilir")
    public void isElementVisiblebyid(String Key) {

        Assert.assertTrue("sayfa açılmadı", methods.elementGorunurMu(By.id(Key)));
        System.out.println("dogru sayfa oldugu kontrol edildi");


    }

    @Step("<key> xpath'li elementin <text> degerini seç")
    public void selectByTextbyXpath(String Key, String text)
    {
        methods.selectByText(By.xpath(Key),text);

    }

    @Step("<key> css'li elementin <text> degerini seç")
    public void selectByTextbyCss(String Key, String text)
    {
        methods.selectByText(By.cssSelector(Key), text);

    }

    @Step("<key> id'li elementin <text> degerini seç")
    public void selectByTextbyId(String Key, String text)
    {
        methods.selectByText(By.id(Key), text);
    }
}
