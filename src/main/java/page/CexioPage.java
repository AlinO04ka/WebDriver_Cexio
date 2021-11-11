package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CexioPage extends AbstractPage {
    private final String siteUrl = "https://broker.cex.io/";

    private final By buttonSignIn = By.xpath("//div[contains(@class, 'main-header_main_header')]/button[text()='Sign In']");
    private final By areaLogin = By.xpath("//*[@id='email']");
    private final By areaPassword = By.xpath("//*[@id='password']");
    private final By buttonSignInInArea = By.xpath("//button[@class='cex-ui-button cex-ui-button-big cex-ui-button-primary-contained cex-ui-form-submit-btn']");
    private final By buttonSell = By.xpath("//div[@class='chartArea--toolbar']/button[@class='gwt-Button button button-sell']");
    private final By buttonSendOrder = By.xpath("//footer//button[@class='button button-primary button-button-bid']");
    private final By buttonConfirm = By.xpath("//section[@class='popup popup-confirmation popup-confirmationMessage popup-modal popup-draggable popup-visible']//button[@class='button button-primary']");
    private final By locatorForNewPositions = By.xpath("//*[@id='main']/div/div/div[1]/div/div[1]/div[1]/div/div[1]/div/div[1]/div[3]/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/table/tbody/*");
    private final By frameTradingTerminal = By.id("tradingTerminalIframe");

    public CexioPage(WebDriver driver) {
        super(driver);
    }

    public CexioPage openPage() {
        driver.get(siteUrl);
        return this;
    }

    public CexioPage clickSignInButton() {
        WebElement waitingButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonSignIn));
        waitingButton.click();
        return this;
    }

    public CexioPage inputLoginForSignIn(String login) {
        WebElement waitingButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(areaLogin));
        waitingButton.sendKeys(login);
        return this;
    }

    public CexioPage inputPasswordForSignIn(String password) {
        driver.findElement(areaPassword).sendKeys(password);
        return this;
    }

    public CexioPage clickSignInButtonInArea() {
        driver.findElement(buttonSignInInArea).click();
        return this;
    }

    public CexioPage clickSellButton() {
        EnterFrame(frameTradingTerminal);
        WebElement waitingButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonSell));
        waitingButton.click();
        ExitFrame();
        return this;
    }

    public CexioPage clickSendOrderButton() {
        EnterFrame(frameTradingTerminal);
        WebElement waitingButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonSendOrder));
        waitingButton.click();
        ExitFrame();
        return this;
    }

    public CexioPage clickConfirmButton() {
        EnterFrame(frameTradingTerminal);
        WebElement waitingButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonConfirm));
        waitingButton.click();
        ExitFrame();
        return this;
    }

    public int countNumberPositions() {
        EnterFrame(frameTradingTerminal);
        int numPositions;
        try {
            List<WebElement> containerForNewPositions = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorForNewPositions));
            numPositions = containerForNewPositions.size();
        } catch (TimeoutException timeoutException) {
            numPositions = 0;
        }
        ExitFrame();
        return numPositions;
    }

    private void EnterFrame(By frame) {
        driver.switchTo().frame(new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(frame)));
    }

    private void ExitFrame() {
        driver.switchTo().defaultContent();
    }
}
