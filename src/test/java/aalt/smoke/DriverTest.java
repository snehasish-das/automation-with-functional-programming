package aalt.smoke;

import aalt.core.drivers.DriverFactory;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class DriverTest {
    @Test(description = "Launching chrome")
    public void launchChrome() {
        DriverFactory.getDriver("chrome");
    }

    @Test(description = "Launching Firefox")
    public void launchFirefox() {
        DriverFactory.getDriver("firefox");
    }

    @Test(description = "Launching default")
    public void launchDefault() {
        DriverFactory.getDriver();
    }
}
