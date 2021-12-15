package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CexioHome extends AbstractPage{
    private final String siteUrl = "https://broker.cex.io/";
    private final By buttonSignIn = By.xpath("//div[contains(@class, 'main-header_main_header')]/button[text()='Sign In']");

    public CexioHome(WebDriver driver) {
        super(driver);
    }

    public CexioHome openPage() {
        driver.get(siteUrl);
        return this;
    }

    public CexioLogin clickSignInButton() {
        WebElement waitingButton = waitElementToBeClickable(buttonSignIn);
        waitingButton.click();
        return new CexioLogin(driver);
    }
}
