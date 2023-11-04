package aalt.core.drivers;

import aalt.core.drivers.managers.ChromeManager;
import aalt.core.drivers.managers.FirefoxManager;
import aalt.core.enums.Browsers;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DriverFactory {
    private DriverFactory(){}
    private static final Map<Browsers, Supplier<WebDriver>> webDriverSupplier = new HashMap<>();
    static {
        webDriverSupplier.put(Browsers.CHROME, ChromeManager::getDriver);
        webDriverSupplier.put(Browsers.FIREFOX, FirefoxManager::getDriver);
    }
    public static WebDriver getDriver(String... browser){
        return webDriverSupplier
                .getOrDefault(
                        (browser.length>0)?Browsers.valueOf(browser[0].toUpperCase()):null,
                        webDriverSupplier.get(Browsers.CHROME)
                ).get();
    }
}
