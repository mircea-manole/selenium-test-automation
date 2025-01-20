package org.automation.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserManager {

    private static WebDriver driver;

    public void openBrowser() {
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static void closeDriver() {
        driver.close();
    }
}
