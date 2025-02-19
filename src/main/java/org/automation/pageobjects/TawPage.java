package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class TawPage {

    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);

    private static final By FINANTARI = By.xpath("//li[@id='menu-item-2463']");
    private static final By SEARCH_ICON = By.xpath("//span[@class='sr-search-btn']");

    public void openTawPage() {
        manager.openBrowser();
        manager.getDriver().get("https://tawenergy.ro/blog/");
        log.info("Opened Taw page");
        Dimension laptopScreen = new Dimension(1500, 900);
        manager.getDriver().manage().window().setSize(laptopScreen);
        actions.waitElementToBeClickable(FINANTARI, 10);
        actions.hoverElement(FINANTARI, manager.getDriver());
        actions.hoverElement(SEARCH_ICON, manager.getDriver());
        actions.clickElement(SEARCH_ICON);
    }
}
