package aalt.pages.landing;

import aalt.core.libraries.BaseUI;
import aalt.pages.inventory.InventoryPage;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class LoginPage<T> extends BaseUI {
    private Map locatorMap = jsonFileToMap("locators/landing/LoginPageLocators.json");
    private WebDriver driver;
    private T originPage;
    public LoginPage(WebDriver driver, T originPage) {
        this.driver = driver;
        this.originPage = originPage;
    }

    public InventoryPage<LoginPage<T>> login(String userkey){
        Map userCreds = jsonFileToMap("config/users.conf.json");
        Map<String,String> userDetails = (Map) userCreds.get(userkey);
        String username = userDetails.get("username");
        String password = userDetails.get("password");

        setText(this.driver, locatorMap.get("usernameInput").toString(), username);
        setText(this.driver, locatorMap.get("passwordInput").toString(), password);
        click(this.driver, locatorMap.get("loginButton").toString());

        return new InventoryPage<>(this.driver,this);
    }

}
