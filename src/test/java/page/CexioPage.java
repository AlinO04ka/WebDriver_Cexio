package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CexioPage extends AbstractPage {
    public CexioPage(WebDriver driver) {
        super(driver);
    }

    private final By buttonSignIn = By.xpath("//*[@id=\"__next\"]/header/div/div/div[1]/div/div[2]/button[1]");
    private final By areaLogin = By.xpath("//*[@id=\"email\"]");
    private final By areaPassword = By.xpath("//*[@id=\"password\"]");
    private final By buttonSignInInArea = By.xpath("//*[@id=\"cex-ui-page\"]/div/div[2]/div[2]/section/div/div[1]/form/button");
    private final By buttonSell = By.xpath("//div[@class='chartArea--toolbar']/button[@class='gwt-Button button button-sell']");
    private final By buttonSendOrder = By.xpath("//footer//button[@class='button button-primary button-button-bid']");
    private final By buttonConfirm = By.xpath("//section[@class='popup popup-confirmation popup-confirmationMessage popup-modal popup-draggable popup-visible']//button[@class='button button-primary']");
    private final By locatorForNewPositions = By.xpath("//*[@id=\"main\"]/div/div/div[1]/div/div[1]/div[1]/div/div[1]/div/div[1]/div[3]/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/table/tbody/*");

    private int numElements;

    public CexioPage openPage() {
        driver.get("https://broker.cex.io/");
        return this;
    }

    public CexioPage clickSignInButton() {
       // driver.findElement(buttonSignIn).click();

        WebElement waitingButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(buttonSignIn));
        waitingButton.click();

      //  Thread.sleep(3000);
        return this;
   }

    public CexioPage inputLoginForSignIn(String login) {
        WebElement waitingButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(areaLogin));
//        waitingButton.click();
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
        WebElement frame = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("tradingTerminalIframe")));
        driver.switchTo().frame(frame);
        WebElement waitingButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(buttonSell));
        waitingButton.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public CexioPage clickSendOrderButton() {
        WebElement frame = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("tradingTerminalIframe")));
        driver.switchTo().frame(frame);
        WebElement waitingButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(buttonSendOrder));
        waitingButton.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public CexioPage clickConfirmButton() {
        WebElement frame = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("tradingTerminalIframe")));
        driver.switchTo().frame(frame);
        WebElement waitingButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(buttonConfirm));
        waitingButton.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public int countNumberPositions() {
        WebElement frame = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("tradingTerminalIframe")));
        driver.switchTo().frame(frame);
        List<WebElement> containerForNewPositions = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorForNewPositions));
        driver.switchTo().defaultContent();
        if (containerForNewPositions == null)
            return 0;
        return containerForNewPositions.size();
    }
}
