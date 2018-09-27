package cucumber.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {
    @Before
    public void initializeTest() {
        System.out.println("Opening browser...");
    }

    @After
    public void tearDownTest() {
        System.out.println("Closing browser...");
    }
}
