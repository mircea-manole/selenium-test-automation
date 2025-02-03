package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

public class HomePage {

    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);

    private final static By HOME_PAGE_BANNER = By.xpath("//div[@class='blocks-promo']//img");
    private final static By HOME_PAGE_PRODUCTS = By.xpath("//li[@class='product-item']");
    private final static By SEARCH_FIELD = By.xpath("//input[@name='q']");
    private final static By ELEMENTS_FROM_SEARCH = By.xpath("//*[@id=\"search_autocomplete\"]//li//span[@class='qs-option-name']");
    private final static By SEARCH_RESULTS = By.xpath("//ol[@class='products list items product-items']//li//div//a[@class='product-item-link']");

    public void openHomePage() {
        log.info("Open home page");
        manager.openBrowser();
        manager.getDriver().get("https://magento.softwaretestingboard.com/");
        manager.getDriver().manage().window().maximize();
    }

    public String homePageTitle() {
        log.info("Get home page title");
        return manager.getDriver().getTitle();
    }

    public boolean isBannerDisplayed() {
        log.info("Check if banner is displayed");
        return actions.isElementDisplayed(HOME_PAGE_BANNER);
    }

    public boolean isBannerEnabled() {
        log.info("Check if banner is enabled");
        return actions.isElementEnabled(HOME_PAGE_BANNER);
    }

    public List<WebElement> productsDisplayed() {
        log.info("Check if products are displayed");
        return actions.getElements(HOME_PAGE_PRODUCTS);
    }

    public void searchElementFromDropdown(String searchElement) {
        log.info("Search for element: {}", searchElement);
        actions.clickElement(SEARCH_FIELD);
        actions.sendKeys(SEARCH_FIELD, searchElement);
        actions.waitElementToBeClickable(By.xpath("(//*[@id=\"search_autocomplete\"]//li)[2]"), 5);
        List<WebElement> searchSuggestions = actions.getElements(ELEMENTS_FROM_SEARCH);
        for (var item : searchSuggestions) {
            if (item.getText().toLowerCase(Locale.ROOT).equals(searchElement.toLowerCase(Locale.ROOT))) {
                item.click();
                break;
            }
        }
    }

    public List<WebElement> getSearchResults() {
        return actions.getElements(SEARCH_RESULTS);
    }
}
