package org.automation.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.util.Properties;

public class BrowserManager {

    private static WebDriver driver;
    Logger log = LogManager.getRootLogger();

    public void openBrowser() {
        Properties properties = new Properties();

        try (FileInputStream file = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(file);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        String browser = properties.getProperty("browser.environment");

        switch (browser) {
            case "cloud" -> driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
            case "local" -> driver = new ChromeDriver();
//            case "grid" -> driver = new RemoteWebDriver()
            default -> throw new RuntimeException("Invalid browser type");
        }

    }

    public WebDriver getDriver() {
        return driver;
    }

    public static void closeDriver() {
        driver.close();
    }
}
