package org.automation.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BrowserManager {

    private static WebDriver driver;
    Logger log = LogManager.getRootLogger();

    public void openBrowser() {
        Properties properties = new Properties();

        try (FileInputStream file = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(file);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        final String grid = properties.getProperty("browser.grid");

        URL gridUrl = getURL(grid);

        final String browser = properties.getProperty("browser.environment");

        switch (browser) {
            case "cloud" -> driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
            case "local" -> driver = new ChromeDriver(new ChromeOptions());
            case "grid" -> driver = new RemoteWebDriver(gridUrl, new ChromeOptions());
            default -> throw new RuntimeException("Invalid browser type");
        }
    }

    private URL getURL(String grid) {
        URL gridUrl = null;
        try {
            gridUrl = new URL(grid);
        } catch (MalformedURLException e) {
            log.error("Invalid grid URL");
        }
        return gridUrl;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static void closeDriver() {
        driver.close();
    }
}
