package org.automation.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BrowserManager {

    private static WebDriver driver;

    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver(new ChromeOptions().addArguments("--headless").addArguments("--disable-gpu").addArguments("--no-sandbox"));

//        driver = new ChromeDriver();
//        URL gridUrl = null;
//        try {
//            gridUrl = new URL("http://192.168.0.156:4444");
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
//        driver = new RemoteWebDriver(gridUrl, options);
    }

    public WebDriver getDriver() {
        System.out.println();
        return driver;
    }

    public static void closeDriver() {
        driver.close();
    }
}
