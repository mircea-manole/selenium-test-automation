package org.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SeleniumActions {

    private final BrowserManager browserManager;

    public SeleniumActions(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public boolean isElementDisplayed(By locator) {
       return browserManager.getDriver().findElement(locator).isDisplayed();
    }

    public boolean isElementEnabled(By locator) {
        return browserManager.getDriver().findElement(locator).isEnabled();
    }

    public List<WebElement> getElements(By locator) {
      return browserManager.getDriver().findElements(locator);
    }

    public void clickElement(By locator) {
        browserManager.getDriver().findElement(locator).click();
    }

    public String getElementText(By locator) {
        return browserManager.getDriver().findElement(locator).getText();
    }
}
