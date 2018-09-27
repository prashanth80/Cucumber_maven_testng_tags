package pages;

import helpers.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;

public class Login extends Landings {

    /** Navigation categories. */
    public enum RightNav {
        CART, WISH, SIGNIN, CREATEACC
    }

    /** Cart icon By */
    private By _miniCartIcon = By.cssSelector("a#miniCartLink");

    /** Cart pane By */
    private By _miniCartPane = By.cssSelector(".cartDrop.forCart");

    /** Wish list pane By */
    private By _wishListPane = By.cssSelector(".cartDrop.forWish");

    /** Sign in pane By */
    private By _signInPane = By.cssSelector(".loginDrop");

    /** Create account pane By */
    private By _createAccPane = By.cssSelector("#createAccountPanel");

    /** Sign in tab By */
    private By _signInTab = By.cssSelector("li[aria-controls='signinPanel']");

    /** Create account panel tab By */
    private By _createAccPanelTab = By.cssSelector("li[aria-controls='createAccountPanel']");

    /** Wish list Icon By */
    private By _wishListIcon = By.cssSelector("a#wishListLink");

    /** Sign in icon By */
    private By _signInIcon = By.cssSelector("a#accountLink");

    /** Sign in label By */
    private By _signInLabel = By.cssSelector("#signinLabel");

    /** Create account label By */
    private By _createAccountLabel = By.cssSelector("createAccountLabel");

    /** Email id By */
    private By _emailId = By.cssSelector("#atg_store_registerLoginLogin");

    /** Password By */
    private By _password = By.cssSelector("#atg_store_registerLoginPassword");

    /** Sign in button By */
    private By _signInButton = By.cssSelector("#loginSubmit");

    /** Login constructor.
     * @param driver of webdriver instance.
     * */
    public Login(WebDriver driver) {
        super(driver);
        this._elements = new Elements(this._driver);
    }


    /** Hover over right navigation icons.
     * @param nav to navigate.
     * @return Login.
     * */
    public Login rightNavigations(RightNav nav) {
        switch (nav) {
            case CART:
                this._elements.hover(this._miniCartIcon);
                break;
            case WISH:
                this._elements.hover(this._wishListIcon);
                break;
            case SIGNIN:
                this._elements.hover(this._signInIcon);
                break;
        }
        return this;
    }


    /** Locate navigation icons.
     * @param nav to navigate.
     * @return Login.
     * */
    public Login navigationIconLocated(RightNav nav) {
        switch (nav) {
            case CART:
                Assert.assertTrue(this._elements.elementVisible(this._miniCartIcon));
                break;
            case WISH:
                Assert.assertTrue(this._elements.elementVisible(this._wishListIcon));
                break;
            case SIGNIN:
                Assert.assertTrue(this._elements.elementVisible(this._signInIcon));
                break;
        }
        return this;
    }


    /** Right navigation icons loading the pane and its display state.
     * @param nav to navigate.
     * @return Login.
     * */
    public Login paneLoded(RightNav nav) {
        switch (nav) {
            case CART:
                Assert.assertTrue(this._elements.elementVisible(this._miniCartPane));
                break;
            case WISH:
                Assert.assertTrue(this._elements.elementVisible(this._wishListPane));
                break;
            case SIGNIN:
                Assert.assertTrue(this._elements.elementVisible(this._signInPane));
                break;
            case CREATEACC:
                Assert.assertTrue(this._elements.elementVisible(this._createAccPane));
                break;
        }
        return this;
    }

    /** Default tab selection in login drop.
     * @param tabName to verify.
     * @return Login.
     * */
    public Login defaultTab(String tabName) {
        WebElement loginDrop = this._driver.findElement(this._signInPane);
        WebElement tabEl;
        if(tabName.equals("SIGNIN")) {
            tabEl = loginDrop.findElement(this._signInTab);
        } else {
            tabEl = loginDrop.findElement(this._createAccPanelTab);
        }
        Assert.assertTrue(
                this._elements.waitForElementAttributeToBe(
                        tabEl, "aria-selected", "true"));
        return this;
    }

    /** Sign in into login page.
     * @param emailId to key in.
     * @param password to key in.
     * @return Login.
     * */
    public Login signIn(String emailId, String password) {
        this.rightNavigations(RightNav.SIGNIN);
        this._elements.safeClick(this._signInLabel);
        this._elements.safeSendKeys(this._emailId, emailId, false);
        this._elements.safeSendKeys(this._password, password, false);
        return this;
    }

    /** Sign in into login page.
     * @param accountMap to key in.
     * @return Login.
     * */
    public Login createAccount(HashMap<String, String> accountMap) {
        WebElement loginDrop = this._driver.findElement(this._signInPane);
        WebElement createAccEl = loginDrop.findElement(this._createAccPanelTab);
        createAccEl.click();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {}
        return this;
    }
}
