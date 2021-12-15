package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CexioPage extends AbstractPage {
    private final By buttonSell = By.xpath("//div[@class='chartArea--toolbar']/button[@class='gwt-Button button button-sell']");
    private final By buttonBuy = By.xpath("//button[@class='gwt-Button button button-buy']");
    private final By buttonSendOrder = By.xpath("//footer//button[@class='button button-primary button-button-bid']");
    private final By buttonConfirm = By.xpath("//section[@class='popup popup-confirmation popup-confirmationMessage popup-modal popup-draggable popup-visible']//button[@class='button button-primary']");
    private final By locatorForNewPositions = By.xpath("//tr[@class=' table--row position']");
    private final By frameTradingTerminal = By.id("tradingTerminalIframe");
    private final By closePosition = By.xpath("//button[contains(@class, 'button-closePosition')]");
    private final By buttonClosePosition = By.xpath("//button//span[text()='Close Position']");
    private final By buttonConfirmClose = By.xpath("//section[@class='popup popup-confirmation popup-confirmationMessage popup-draggable popup-visible']//button[@class='button button-primary']");
    private final By buttonSide = By.xpath("//span[text()='Side' and not(ancestor::div[contains(@style,'display: none')])]");
    private final By buttonFilterSide = By.xpath("//span[text()='Side' and not(ancestor::div[contains(@style,'display: none')])]/following-sibling::span");
    private final By buttonBoughtSide = By.xpath("//li[@class='contextMenu--item contextMenu--item-multiSelectItem' and .='Bought']");
    private final By locatorForBoughtPositions = By.xpath("//span[@class='position--quantity']");
    private final By locatorForCreateConfirmMessage = By.xpath("//span[contains(@class, 'ToastHost-global__container')]/*");
    private final By locatorForClearFilters = By.xpath("//span[@class='filterPanel--clear' and not(ancestor::div[contains(@style, 'display: none')])]/span");


    public CexioPage(WebDriver driver) {
        super(driver);
    }

    public CexioPage clearFilters() {
        enterFrame(frameTradingTerminal);
        try {
            WebElement waitingButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOfElementLocated(locatorForClearFilters));
            waitingButton.click();
        } catch (TimeoutException ignored) {
        }
        exitFrame();
        return this;
    }

    public CexioPage clickSellButton() {
        enterFrame(frameTradingTerminal);
        WebElement waitingButton = waitElementToBeClickable(buttonSell);
        waitingButton.click();
        exitFrame();
        return this;
    }

    public CexioPage clickSendOrderButton() {
        enterFrame(frameTradingTerminal);
        WebElement waitingButton = waitElementToBeClickable(buttonSendOrder);
        waitingButton.click();
        exitFrame();
        return this;
    }

    public CexioPage clickConfirmButton() {
        enterFrame(frameTradingTerminal);
        WebElement waitingButton = waitElementToBeClickable(buttonConfirm);
        waitingButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locatorForCreateConfirmMessage));
        exitFrame();
        return this;
    }

    public int countNumberPositions() {
        enterFrame(frameTradingTerminal);
        int numPositions;
        try {
            List<WebElement> containerForNewPositions = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorForNewPositions));
            numPositions = containerForNewPositions.size();
        } catch (TimeoutException timeoutException) {
            numPositions = 0;
        }
        exitFrame();
        return numPositions;
    }

    public void deletePosition() {
        enterFrame(frameTradingTerminal);
        driver.findElement(locatorForNewPositions).findElement(closePosition).click();
        WebElement waitingButtonFirst = waitElementToBeClickable(buttonClosePosition);
        waitingButtonFirst.click();
        WebElement waitingButtonSecond = waitElementToBeClickable(buttonConfirmClose);
        waitingButtonSecond.click();
        exitFrame();
    }

    public CexioPage clickFilterSideButton() {
        enterFrame(frameTradingTerminal);
        WebElement waitingButton = waitElementToBeClickable(buttonFilterSide);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", waitingButton);
        exitFrame();
        return this;
    }

    public CexioPage clickBoughtSideButton() {
        enterFrame(frameTradingTerminal);
        driver.findElement(buttonBoughtSide).click();
        driver.findElement(buttonSide).click();
        exitFrame();
        return this;
    }

    public int countNumberBoughtPositions() {
        enterFrame(frameTradingTerminal);
        int numBoughtPositions;
        try {
            List<WebElement> containerForNewBoughtPositions = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorForBoughtPositions));
            numBoughtPositions = containerForNewBoughtPositions.size();
        } catch (TimeoutException e) {
            numBoughtPositions = 0;
        }
        exitFrame();
        return numBoughtPositions;
    }

    public CexioPage clickBuyButton() {
        enterFrame(frameTradingTerminal);
        WebElement waitingButton = waitElementToBeClickable(buttonBuy);
        waitingButton.click();
        exitFrame();
        return this;
    }

    private void enterFrame(By frame) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    private void exitFrame() {
        driver.switchTo().defaultContent();
    }
}
