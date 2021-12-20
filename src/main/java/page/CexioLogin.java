package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CexioLogin extends AbstractPage{
    private final By areaLogin = By.id("email");
    private final By areaPassword = By.id("password");
    private final By buttonSignInInArea = By.id("login_start");
    private final By buttonLiveAccount = By.xpath("//div[@class='header-second_terminal_accounts_block__2qaBM']");
    private final By buttonDemoAccount = By.xpath("//div[text()='BTC_up144895566_4']");

    public CexioLogin(WebDriver driver) {
        super(driver);
    }

    public CexioLogin inputLoginForSignIn(String login) {
        WebElement waitingButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(areaLogin));
        waitingButton.sendKeys(login);
        return this;
    }

    public CexioLogin inputPasswordForSignIn(String password) {
        driver.findElement(areaPassword).sendKeys(password);
        return this;
    }

    public CexioLogin clickSignInButtonInArea() {
        driver.findElement(buttonSignInInArea).click();
        return this;
    }

    public CexioLogin clickButtonLiveAccount() {
        WebElement waitingButton = waitElementToBeClickable(buttonLiveAccount);
        waitingButton.click();
        return this;
    }

    public CexioPage clickButtonDemoAccount() {
        WebElement waitingButton = waitElementToBeClickable(buttonDemoAccount);
        waitingButton.click();
        return new CexioPage(driver);
    }
}
