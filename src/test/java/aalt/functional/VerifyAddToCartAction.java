package aalt.functional;

import aalt.core.libraries.BaseUI;
import aalt.pages.landing.HomePage;
import org.testng.annotations.Test;

public class VerifyAddToCartAction extends BaseUI {
    @Test(description = "Add \"Sauce Labs Bike Light\" to the cart and verify", groups = {"InventoryPageTests"})
    public void addToCart(){
        HomePage homePage = new HomePage(initDriver("chrome"));
        homePage
                .navigateTo("https://www.saucedemo.com/")
                .login("standard_user")
                .addProductToCart("Sauce Labs Bike Light")
        ;
    }
    @Test(description = "Add and remove \"Sauce Labs Bike Light\" to the cart and verify", groups = {"InventoryPageTests"})
    public void removeFromCart(){
        String productName = "Sauce Labs Bike Light";
        HomePage homePage = new HomePage(initDriver("firefox"));
        homePage
            .navigateTo("https://www.saucedemo.com/")
                .login("standard_user")
                    .addProductToCart(productName)
                    .removeFromCart(productName)
        ;
    }
}
