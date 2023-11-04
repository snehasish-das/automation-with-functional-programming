package aalt.pages.landing;

import aalt.core.libraries.BaseUI;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class HomePage<T> extends BaseUI {
    private Map<String,String> locators = jsonFileToMap("locators/landing/HomePageLocators.json");
    private WebDriver driver;
    private T originPage=null;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    /**
     * Used to instantiate the browser and navigate to a given url
     * @param url
     * @return
     */
    public LoginPage<HomePage<T>> navigateTo(String url){
        this.driver.navigate().to(url);
        return new LoginPage<>(this.driver, this);
    }
}
