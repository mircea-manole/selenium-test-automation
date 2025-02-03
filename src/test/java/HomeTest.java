import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.pageobjects.HomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomeTest {

    HomePage homePage = new HomePage();
    Logger log = LogManager.getRootLogger();

    @BeforeEach
    public void setUp() {
        homePage.openHomePage();
    }

    @Test
    @DisplayName("Validate title test")
    public void validateTitle() {
        log.info("Validate title");
        String title = homePage.homePageTitle();
        assertEquals("Home Page", title, "Title of page was: " + title);
    }

    @Test
    @DisplayName("Validate banner test")
    public void validateBanner() {
        log.info("Validate home page banner");
        boolean isBannerDisplayed = homePage.isBannerDisplayed();
        boolean isBannerEnabled = homePage.isBannerEnabled();
        assertTrue(isBannerDisplayed, "Banner is not displayed");
        assertTrue(isBannerEnabled, "Banner is not enabled");
    }

    @Test
    @DisplayName("Validate products test")
    public void validateProducts() {
        log.info("Validate products");
        List<WebElement> listOfProducts = homePage.productsDisplayed();
        assertNotNull(listOfProducts, "List of products is null");
        assertEquals(6, listOfProducts.size(), "Number of products is: " + listOfProducts.size());
        assertTrue(listOfProducts.get(0).getText().contains("Radiant Tee"));
    }

    @AfterEach
    public void tearDown() {
        BrowserManager.closeDriver();
    }
}
