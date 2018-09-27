package cucumber.steps;

import core.TestDriver;
import org.openqa.selenium.WebDriver;

public class BaseStep {
    WebDriver driver;

    /** Setup webdriver for vaious page for execution.
     * @param browser name.
     */
    protected void setupDriver(String browser) {
        TestDriver.setupDriver(browser);
        this.driver = TestDriver.getDriver();
        TestDriver.getWait(driver);
    }

    /** Tear down webdriver after execution. */
    protected void tearDown() {
        this.driver.quit();
    }
}
