import org.automation.pageobjects.TawPage;
import org.junit.jupiter.api.Test;

public class TawTest {

    TawPage tawPage = new TawPage();

    @Test
    public void testTawPage() {
        tawPage.openTawPage();
    }
}
