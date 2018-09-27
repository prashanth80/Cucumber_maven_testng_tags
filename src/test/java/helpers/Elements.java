package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Elements {
    private WebDriver _driver;
    private final int _timeout = 10;

    public Elements(WebDriver driver) {
        this._driver = driver;
    }

    /**
     * Elements display state.
     * @param by element to wait for.
     * @return boolean.
     * */
    public boolean elementVisible(By by) {
        return this.waitForElementVisibility(by).isDisplayed();
    }

    /**
     * Waits for an element's attribute.
     * @param by element to wait for.
     * @return WebElement.
     * */
    public WebElement waitForElementVisibility(By by) {
        return new WebDriverWait(this._driver, this._timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Waits for an element's attribute.
     * @param by element to wait for.
     * @param attribute to wait for.
     * @param value to match.
     * @return boolean.
     * */
    public boolean waitForElementAttributeToBe(By by, String attribute, String value) {
        return new WebDriverWait(this._driver, this._timeout)
                .until(ExpectedConditions.attributeToBe(by, attribute, value));
    }

    /**
     * Waits for an element's attribute.
     * @param el element to wait for.
     * @param attribute to wait for.
     * @param value to match.
     * @return boolean.
     * */
    public boolean waitForElementAttributeToBe(WebElement el, String attribute, String value) {
        return new WebDriverWait(this._driver, this._timeout)
                .until(ExpectedConditions.attributeToBe(el, attribute, value));
    }

    /**
     * Waits for an element's presence.
     * @param by element to wait for.
     * @return WebElement.
     * */
    public WebElement waitForElementPresent(By by) {
        return new WebDriverWait(this._driver, this._timeout)
                         .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Waits for an element's presence and is clickable.
     * @param by element to wait for.
     * @return WebElement.
     * */
    public WebElement waitForElementsVisiblePresentAndClickable(By by) {
        boolean conditions = new WebDriverWait(this._driver, this._timeout)
                        .until(
                            ExpectedConditions.and(
                                ExpectedConditions.visibilityOfElementLocated(by),
                                ExpectedConditions.presenceOfElementLocated(by),
                                ExpectedConditions.elementToBeClickable(by)
                            )
                        );
        if(conditions) {
            return this._driver.findElement(by);
        }
        throw new NoSuchElementException(by.toString());
    }

    /**
     * Waits for an element until clickable.
     * @param by element to wait for.
     * @return WebElement.
     * */
    public WebElement waitForElementToBeClickable(By by) {
        return new WebDriverWait(this._driver, this._timeout)
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Waits for page to load.
     * @param url to wait for.
     * @return WebElement.
     * */
    public boolean pageLoaded(String url) {
        return new WebDriverWait(this._driver, this._timeout)
                .until(ExpectedConditions.urlToBe(url));
    }

    /**
     * Hover over an element.
     * @param by element to wait for.
     * */
    public void hover(By by) {
        WebElement el = this.waitForElementPresent(by);
        Actions acts = new Actions(this._driver);
        Action act = acts.moveToElement(el).build();
        act.perform();
    }

    /**
     * Safely Click on element only when present and clickable.
     * @param by element to wait for.
     * */
    public void safeClick(By by) {
        WebElement el = this.waitForElementsVisiblePresentAndClickable(by);
        el.click();
    }

    /**
     * Safely Sendkeys into an element only when present and clickable.
     * @param by element to wait for.
     * */
    public void safeSendKeys(By by, String text, boolean append) {
        WebElement el = this.waitForElementsVisiblePresentAndClickable(by);
        if(!append) {
            text += el.getText();
        }
        el.clear();
        el.sendKeys(text);
    }
}
