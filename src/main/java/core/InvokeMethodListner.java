/*
package core;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokeMethodListner implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        if(method.isTestMethod()) {
            String testName = method.getTestMethod().getDescription();
            Thread.currentThread().setName(testName);
            System.out.println(String.format("Before Test Method \n Name: %s \n Id: %s",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getId()));
            String browserName = method.getTestMethod().getXmlTest()
                    .getLocalParameters().get("browser");
            TestDriver.setDriver(browserName);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if(method.isTestMethod()) {
            System.out.println(String.format("After Test Method \n Name: %s \n Id: %s",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getId()));
            WebDriver driver = TestDriver.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
*/
