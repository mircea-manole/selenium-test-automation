package org.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.framework.BrowserManager;
import org.automation.framework.SeleniumActions;
import org.openqa.selenium.By;

import java.util.List;

public class CreateAccountPage {

    Logger log = LogManager.getRootLogger();
    BrowserManager manager = new BrowserManager();
    SeleniumActions actions = new SeleniumActions(manager);

    private final static By CREATE_ACCOUNT_BUTTON = By.xpath("(//a[text()='Create an Account'])[1]");
    private final static By CUSTOMER_ACCOUNT_TEXT = By.xpath("//span[text()='Create New Customer Account']");
    private final static By PERSONAL_INFO_TEXT = By.xpath("//span[text()='Personal Information']");
    private final static By FIRST_NAME_TEXT = By.xpath("//span[text()='First Name']");
    private final static By LAST_NAME_TEXT = By.xpath("//span[text()='Last Name']");
    private final static By SIGN_IN_TEXT = By.xpath("//span[text()='Sign-in Information']");
    private final static By EMAIL_TEXT = By.xpath("//span[text()='Email']");
//    private final static By PASSWORD_TEXT = By.xpath("//*[@id=\"form-validate\"]/fieldset[2]/div[2]/label/span]");
    private final static By CONFIRM_PASSWORD_TEXT = By.xpath("//span[text()='Confirm Password']");

    public void clickCreateAccountButton() {
        log.info("Click create account button");
        actions.clickElement(CREATE_ACCOUNT_BUTTON);
    }

    public List<String> getAccountLabels() {
        log.info("Get account labels");
        String customerText = actions.getElementText(CUSTOMER_ACCOUNT_TEXT);
        String personalText = actions.getElementText(PERSONAL_INFO_TEXT);
        String firstNameText = actions.getElementText(FIRST_NAME_TEXT);
        String lastNameText = actions.getElementText(LAST_NAME_TEXT);
        String signInText = actions.getElementText(SIGN_IN_TEXT);
        String emailText = actions.getElementText(EMAIL_TEXT);
//        String passwordText = actions.getElementText(PASSWORD_TEXT);
        String confirmPasswordText = actions.getElementText(CONFIRM_PASSWORD_TEXT);

        return List.of(customerText, personalText, firstNameText, lastNameText, signInText, emailText, confirmPasswordText);
    }
}
