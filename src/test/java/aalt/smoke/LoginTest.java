package aalt.smoke;

import aalt.core.libraries.BaseUI;
import aalt.pages.landing.HomePage;
import org.testng.annotations.Test;

public class LoginTest extends BaseUI {
    @Test(description = "", groups = {"chrome"})
    public void loginTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage
                .navigateTo("https://www.saucedemo.com/")
                    .login("standard_user")
                ;
    }
}
