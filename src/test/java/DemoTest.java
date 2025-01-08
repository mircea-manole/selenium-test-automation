import org.automation.framework.BrowserManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoTest {

    BrowserManager manager = new BrowserManager();

    @Test
    public void browserTest() {
        manager.openBrowser();
        WebDriver driver = manager.getDriver();
        driver.get("https://www.youtube.com");
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("google"));
    }
}
