package cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.transformations.EmailTransform;
import pages.Landings;
import pages.Login;

import java.util.HashMap;

public class PagesSteps extends BaseStep {
    private Landings _landings;
    private Login _login;
    @Before
    public void setupSignIn() {
        this.setupDriver("CHROME");
        this._landings = new Landings(this.driver);
        this._login = new Login(this.driver);
    }

    @After
    public void tearDownSignIn() {
        this.tearDown();
    }

    @And("^Hover over Sign in icon$")
    public void hoverOverSignInIcon() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        System.out.println("Step: hoverOverSignInIcon");
        this._login.rightNavigations(Login.RightNav.SIGNIN);
    }

    @Given("^Home page is loaded$")
    public void homePageIsLoaded() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        System.out.println("Step: homePageIsLoaded");
        this._landings.landingPageLoaded();
    }

    @And("^Sign in icon is located$")
    public void signInIconIsLocated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        System.out.println("Step: signInIconIsLocated");
        this._login.navigationIconLocated(Login.RightNav.SIGNIN);

    }

    @Then("^Sign in hover opens sign in panel$")
    public void signInHoverOpensSignInPanel() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        System.out.println("Step: signInHoverOpensSignInPanel");
        this._login.paneLoded(Login.RightNav.SIGNIN);
    }

    @Then("^Sign in is by default selected$")
    public void signInIsByDefaultSelected() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        System.out.println("Step: signInIsByDefaultSelected");
        this._login.defaultTab("SIGNIN");
    }

    // @And("^Sign with <username> and <password>$")
    @And("^Sign with ([^\"]*) and ([^\"]*)$")
    public void signWithUsernameAndPassword
        (@Transform(EmailTransform.class) String username,
         String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        System.out.println(
                String.format("Username: %s || Password: %s", username, password));
        this._login.signIn(username, password);
    }

    @Then("^Login is successful$")
    public void loginIsSuccessful() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        System.out.println("Step: loginIsSuccessful");
    }

    @And("^click on CreateAccount label$")
    public void clickOnCreateAccountLabel() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
        HashMap<String, String> m = new HashMap<>();
        m.put("FName", "Prash");
        m.put("LName", "Kumar");
        m.put("email", "p.k@abc.com");
        m.put("cemail", "p.k@abc.com");
        m.put("password", "password123");
        m.put("cpassword", "password123");
        m.put("zip", "94555");
        this._login.rightNavigations(Login.RightNav.SIGNIN).createAccount(m);
    }

    @Then("^CreateAccount pane is loaded$")
    public void createaccountPaneIsLoaded() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
        this._login.paneLoded(Login.RightNav.CREATEACC);
    }

    @And("^WishList icon is located$")
    public void wishlistIconIsLocated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
        this._login.navigationIconLocated(Login.RightNav.WISH);
    }

    @And("^Hover over WishList icon$")
    public void hoverOverWishListIcon() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
        this._login.rightNavigations(Login.RightNav.WISH);
    }

    @Then("^WishList pane is loaded$")
    public void wishlistPaneIsLoaded() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
        this._login.paneLoded(Login.RightNav.WISH);
    }

    @And("^Cart icon is located$")
    public void cartIconIsLocated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
        this._login.navigationIconLocated(Login.RightNav.CART);
    }

    @And("^Hover over Cart icon$")
    public void hoverOverCartIcon() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
        this._login.rightNavigations(Login.RightNav.CART);
    }

    @Then("^Cart pane is loaded$")
    public void cartPaneIsLoaded() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
        this._login.paneLoded(Login.RightNav.CART);
    }
}
