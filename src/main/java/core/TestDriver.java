package core;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestDriver {

    /** ThreadLocal WebDriver for thread safety.*/
    private static ThreadLocal<WebDriver> _threadLocalDriver = new ThreadLocal<>();

    /** Setup webdriver which is thread safe.
     * @param browser name.
     */
    public static synchronized void setupDriver(String browser) {
        if (browser.equals("CHROME")) {
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            _threadLocalDriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
        } else {
            WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            _threadLocalDriver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
        }
    }

    /** Wait for the webdriver to initialize.
     * @param driver to wait on.
     */
    public static synchronized WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, 20);
    }

    /** Get's the thread local webdriver.
     * @return thread local webdriver.
     */
    public static synchronized WebDriver getDriver() {
        return _threadLocalDriver.get();
    }

    public static void main(String[] args) throws Exception {
        // Working Fine 1
        // WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        // WebDriver driver = new ChromeDriver();
        // driver.get("https://www.google.com/");


        // Working Fine 2
        // System.setProperty("webdriver.chrome.driver",
        //         "C:\\Users\\eTouch\\Downloads\\chromedriver_win32\\chromedriver.exe");
        // WebDriver driver = new ChromeDriver();
        // driver.get("https://www.google.com/");

        setupDriver("FF");
        WebDriver driver = getDriver();
        getWait(driver);
        driver.get("https://www.google.com/");
    }
}

class OptionsManager {
    /**
     * This sets standard options for Chrome browser.
     * */
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        return options;
    }

    /**
     * This sets standard options for Chrome browser.
     * */
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setPreference("network.proxy.type", 0);
        options.setCapability(FirefoxDriver.PROFILE, profile);
        return options;
    }
}
