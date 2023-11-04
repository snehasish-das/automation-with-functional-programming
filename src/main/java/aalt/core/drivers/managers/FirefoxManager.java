package aalt.core.drivers.managers;

import aalt.core.constants.Capabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxManager {
    private FirefoxManager(){}
    public static WebDriver getDriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        Capabilities.getCapabilities().forEach((option)->options.addArguments(option));
        return new FirefoxDriver();
    }
}
