import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.constants.Products;
import org.automation.framework.BrowserManager;
import org.automation.pageobjects.HomePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
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

    @Tags(value = {@Tag("products"), @Tag("home")})
    @Test
    @DisplayName("Validate banner test")
    public void validateBanner() {
        log.info("Validate home page banner");
        boolean isBannerDisplayed = homePage.isBannerDisplayed();
        boolean isBannerEnabled = homePage.isBannerEnabled();
        assertTrue(isBannerDisplayed, "Banner is not displayed");
        assertTrue(isBannerEnabled, "Banner is not enabled");
    }

    @Tag("homeproducts")
    @Test
    @DisplayName("Validate products test")
    public void validateProducts() {
        log.info("Validate products");
        List<WebElement> listOfProducts = homePage.productsDisplayed();
        assertNotNull(listOfProducts, "List of products is null");
        assertEquals(6, listOfProducts.size(), "Number of products is: " + listOfProducts.size());
        assertTrue(listOfProducts.get(0).getText().contains("Radiant Tee"));
    }

    @DisplayName("Search products test")
    @ParameterizedTest
    @ValueSource(strings = {"Hoodie", "Tank", "Jeans"})
    public void searchTest(String testData) {
        homePage.searchElementFromDropdown(testData);
        List<WebElement> results = homePage.getSearchResults();
        for (WebElement item : results) {
            assertTrue(item.getText().contains(testData), "I found item: " + item.getText());
        }
    }

    @Tag("product")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Search products test with enum")
    @ParameterizedTest
//    @EnumSource()
    @EnumSource(value = Products.class, names = {"FIRST_PRODUCT", "FOURTH_PRODUCT"})
    public void searchTestWithEnum(Products products) {
        homePage.searchElementFromDropdown(products.getProduct());
        List<WebElement> results = homePage.getSearchResults();
        for (WebElement item : results) {
            assertTrue(item.getText().contains(products.getProduct()), "I found item: " + item.getText());
        }

        String firstProduct = results.get(0).getText();
        results.get(0).click();
        homePage.selectSize("XL");
        String productCart = homePage.getCart();
        assertEquals(firstProduct, productCart);
    }

    @AfterEach
    public void tearDown() {
        BrowserManager.closeDriver();
    }
}
