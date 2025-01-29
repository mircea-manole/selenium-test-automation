package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.constants.LoginDetails;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginOrange {

    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);

    private final static By USERNAME_FIELD = By.xpath("//input[@name='username']");
    private final static By PASSWORD_FIELD = By.xpath("//input[@name='password']");
    private final static By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");
    private final static By ADMIN_SEARCH_BUTTON = By.xpath("//input[@placeholder='Search']");
    private final static By ADMIN_SEARCH_RESULT = By.xpath("//li[@class='oxd-main-menu-item-wrapper']");
    private final static By SEARCH_BOX_ADMIN_PAGE = By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active']");
    private final static By SEARCH_BUTTON_ADMIN_PAGE = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
    private final static By ADMIN_PAGE_RESULT = By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[2]");
    private final static By SPINNER = By.xpath("//div[@class='oxd-table-loader']//div[@class='oxd-loading-spinner']");
    private final static By RESET_ADMIN_SEARCH = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost']");

    public void openOrangeLoginPage() {
        log.info("Open Orange login page");
        manager.openBrowser();
        manager.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        manager.getDriver().manage().window().maximize();
    }

    public void loginOrange() {
        log.info("Login to Orange Page");
        String user = LoginDetails.LOGIN_USER.getUsername();
        String pass = LoginDetails.LOGIN_USER.getPassword();

        actions.waitElementToBeClickable(USERNAME_FIELD, 10);
        actions.sendKeys(USERNAME_FIELD, user);
        actions.sendKeys(PASSWORD_FIELD, pass);
        actions.clickElement(SUBMIT_BUTTON);
    }

    public List<WebElement> searchItemLeftPanel(String elementForSearch) {
        actions.waitElementToBeClickable(ADMIN_SEARCH_BUTTON, 5);
        actions.clickElement(ADMIN_SEARCH_BUTTON);
        actions.sendKeys(ADMIN_SEARCH_BUTTON, elementForSearch);
        return actions.getElements(ADMIN_SEARCH_RESULT);
    }

    public void clickOnResult(String objectToClick) {
        List<WebElement> results = actions.getElements(ADMIN_SEARCH_RESULT);
        for (WebElement element : results) {
            if (element.getText().equals(objectToClick)) {
                element.click();
            }
        }
    }

    public String searchAdminPage(String userForSearch, int timeToWait) {
        actions.waitFluentElementClickable(SEARCH_BOX_ADMIN_PAGE, timeToWait);
        actions.sendKeys(SEARCH_BOX_ADMIN_PAGE, userForSearch);
        actions.clickElement(SEARCH_BUTTON_ADMIN_PAGE);
        actions.waitToSpinnerToHide(SPINNER, 10);
        return actions.getElementText(ADMIN_PAGE_RESULT);
    }

    public String resetAdminSearch() throws InterruptedException {
        actions.clickElement(RESET_ADMIN_SEARCH);
//        Thread.sleep(10000);
        actions.waitToSpinnerToHide(SPINNER, 10);
        return actions.getElementText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input"));
    }
}
