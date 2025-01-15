import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoTest {

    BrowserManager manager = new BrowserManager();
    Logger log = LogManager.getRootLogger();

    @Test
    public void browserTest() {
        manager.openBrowser();
        log.info("Browser opened");
        WebDriver driver = manager.getDriver();
        log.warn("This is a warning message");
        driver.get("https://www.youtube.com");
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("google"));
    }
}
