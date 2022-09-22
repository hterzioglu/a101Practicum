package com.a101web.driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.ExecutionContext;
import com.thoughtworks.gauge.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import javassist.bytecode.analysis.Executor;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.parallel.Execution;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.reflect.Executable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class BaseTestWeb {

    public static WebDriver driver;
    protected static AppiumDriver<MobileElement> appiumDriver;


    @BeforeScenario


    public void setUp(ExecutionContext executionContext) throws MalformedURLException {

        System.out.println("Kosulan Senaryo ismi: " + executionContext.getCurrentScenario().getName());
        String name = executionContext.getCurrentScenario().getName();
        System.out.println(name);


        if (name.contains("web")){
            System.setProperty("webdriver.chrome.driver", "src/test/java/com/a101web/resources/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disablgpu");
            chromeOptions.addArguments("--disable-popup-blocking");

            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            driver.get("https://www.a101.com.tr/");
            System.out.println("driver ayaga kalkti");
        } else {
            System.out.println("Android emulator ayaga kalktı ");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            //desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.studionord.a101");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "org.studionord.a101.MainActivity");
            //desiredCapabilities.setCapability(AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT,"4000");
            desiredCapabilities.setCapability("autoAcceptAlerts", true);

            URL url = new URL("http://127.0.0.1:4723/wd/hub/");
            appiumDriver = new AndroidDriver(url, desiredCapabilities);
        }
    }

    @AfterScenario
    public void tearDown(ExecutionContext executionContext) {

        System.out.println("Kapatılan senaryo: " + executionContext.getCurrentScenario().getName());
        String name = executionContext.getCurrentScenario().getName();


        if (name.contains("web")) {
            driver.close();
            driver.quit();
        } else {
            appiumDriver.quit();
        }

    }
}


