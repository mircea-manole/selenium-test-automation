package org.automation.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserManager {

    private static WebDriver driver;

    public void openBrowser() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
    }

    public WebDriver getDriver() {
        System.out.println();
        return driver;
    }

    public static void closeDriver() {
        driver.close();
    }
}
