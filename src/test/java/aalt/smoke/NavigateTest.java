package aalt.smoke;

import aalt.core.libraries.BaseUI;
import aalt.pages.landing.HomePage;
import org.testng.annotations.Test;

public class NavigateTest extends BaseUI {
    @Test(description = "Navigate to https://www.saucedemo.com/", groups = {"chrome"})
    public void launchChrome(){
        HomePage homePage = new HomePage(getDriver());
        homePage
                .navigateTo("https://www.saucedemo.com/");
    }

    @Test(description = "Navigate to https://www.saucedemo.com/ using firefox", groups = {"firefox"})
    public void launchFirefox(){
        HomePage homePage = new HomePage(getDriver());
        homePage
                .navigateTo("https://www.saucedemo.com/");
    }
}
