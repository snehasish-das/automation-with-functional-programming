package aalt.core.drivers.managers;

import aalt.core.constants.Capabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeManager {
    private ChromeManager(){}
    public static WebDriver getDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Capabilities.getCapabilities().forEach((option)->options.addArguments(option));
        return new ChromeDriver(options);
    }
}
