package com.a101.driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTestWeb {

    public static WebDriver driver;
    @BeforeScenario
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/a101/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disablgpu");
        chromeOptions.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.a101.com.tr/");
        System.out.println("driver ayaga kalkti");


    }
    @AfterScenario
    public void tearDown(){
        if(driver != null){
            driver.close();
            driver.quit();
        }
    }
}
