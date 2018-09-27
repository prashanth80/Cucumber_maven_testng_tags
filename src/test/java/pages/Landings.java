package pages;

import helpers.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Landings {
    /**Webdriver to use across page object.*/
    protected WebDriver _driver;

    /** Helpers Elements. */
    protected Elements _elements;

    /** Landing page url. */
    private String landingUrl = "https://www.roomstogo.com/";

    private By mainContainer = By.cssSelector(".mainContainer");

    /** Landings constructor.
     * @param driver of webdriver instance.
     * */
    public Landings(WebDriver driver) {
        this._driver = driver;
        this._elements = new Elements(this._driver);
        this._driver.get(landingUrl);
    }

    /** Landing page load state.
     * @return Landings
     * */
    public Landings landingPageLoaded() {
        Assert.assertTrue(this._elements.pageLoaded(landingUrl));
        return this;
    }
}
