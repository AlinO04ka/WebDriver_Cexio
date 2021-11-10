package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CexioPage;


public class CexioTests {
    private WebDriver driver;
    protected CexioPage cexioPage;

    @BeforeMethod
    private void initializeBrowser() {
        System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");
        driver = new EdgeDriver();
        cexioPage = new CexioPage(driver);

        cexioPage.openPage()
                .clickSignInButton()
                .inputLoginForSignIn("alinakhartanovichlo@gmail.com")
                .inputPasswordForSignIn("12345678Admin")
                .clickSignInButtonInArea();
    }

    @Test
    private void searchOpenOrders() {
        int numPositionsBeforeSell = cexioPage.countNumberPositions();
        int numPositionsAfterSell = cexioPage.clickSellButton()
                .clickSendOrderButton()
                .clickConfirmButton()
                .countNumberPositions();

        Assert.assertTrue(numPositionsAfterSell - numPositionsBeforeSell > 0);
    }

    @AfterMethod
    private void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
