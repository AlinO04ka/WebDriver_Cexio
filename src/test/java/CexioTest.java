import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CexioPage;


public class CexioTest {
    private WebDriver driver;
    protected CexioPage cexioPage;

    @BeforeMethod
    public void initializeBrowser() {
        driver = new ChromeDriver();
        cexioPage = new CexioPage(driver).openPage()
                .clickSignInButton()
                .inputLoginForSignIn("alinakhartanovichlo@gmail.com")
                .inputPasswordForSignIn("12345678Admin")
                .clickSignInButtonInArea();
    }

    @Test
    public void searchOpenOrders() {
        int numPositionsBeforeSell = cexioPage.clickCloseVerificationBlock()
                .countNumberPositions();
        int numPositionsAfterSell = cexioPage.clickSellButton()
                .clickSendOrderButton()
                .clickConfirmButton()
                .countNumberPositions();

        Assert.assertTrue(numPositionsAfterSell - numPositionsBeforeSell > 0);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
