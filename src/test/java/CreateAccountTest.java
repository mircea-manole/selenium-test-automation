import org.automation.framework.BrowserManager;
import org.automation.pageobjects.CreateAccountPage;
import org.automation.pageobjects.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateAccountTest {

    HomePage homePage = new HomePage();
    CreateAccountPage createAccountPage = new CreateAccountPage();
    

    @BeforeEach
    public void setUp() {
        homePage.openHomePage();
    }

    @Test
    @DisplayName("Validate create account fields test")
    public void validateCreateAccountFields() {
        createAccountPage.clickCreateAccountButton();
        List<String> labelsText = createAccountPage.getAccountLabels();
    }

    @AfterEach
    public void tearDown() {
        BrowserManager.closeDriver();
    }
}
