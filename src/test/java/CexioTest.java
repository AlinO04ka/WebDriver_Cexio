import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CexioPage;


public class CexioTest {
    private WebDriver driver;
    private CexioPage cexioPage;

    private final String login = "bzorssdyyqikibhxli@adfskj.com";
    private final String password = "12345678Admin";

    @BeforeMethod
    public void initializeBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        cexioPage = new CexioPage(driver).openPage()
                .clickSignInButton()
                .inputLoginForSignIn(login)
                .inputPasswordForSignIn(password)
                .clickSignInButtonInArea();
    }

    @Test
    public void searchOpenOrders() {
        int numPositionsBeforeSell = cexioPage.countNumberPositions();
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
