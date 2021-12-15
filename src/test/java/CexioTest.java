import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CexioHome;
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
        cexioPage = new CexioHome(driver).openPage()
                .clickSignInButton()
                .inputLoginForSignIn(login)
                .inputPasswordForSignIn(password)
                .clickSignInButtonInArea()
                .clickButtonLiveAccount()
                .clickButtonDemoAccount()
                .clearFilters();
    }

    @Test
    public void testSearchOpenOrders() {
        int numPositionsBeforeSell = cexioPage.countNumberPositions();
        int numPositionsAfterSell = cexioPage.clickSellButton()
                .clickSendOrderButton()
                .clickConfirmButton()
                .countNumberPositions();

        Assert.assertTrue(numPositionsAfterSell - numPositionsBeforeSell > 0);
    }

    @Test
    public void testSearchPositionsFiltered() {
        int numFilteredBoughtPositionsBeforeBought = cexioPage.clickFilterSideButton()
                .clickBoughtSideButton()
                .countNumberBoughtPositions();
        int numFilteredBoughtPositionsAfterBought = cexioPage.clickBuyButton()
                .clickSendOrderButton()
                .clickConfirmButton()
                .countNumberBoughtPositions();

        Assert.assertEquals(numFilteredBoughtPositionsAfterBought - numFilteredBoughtPositionsBeforeBought, 1);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        cexioPage.deletePosition();
        driver.quit();
        driver = null;
    }
}
