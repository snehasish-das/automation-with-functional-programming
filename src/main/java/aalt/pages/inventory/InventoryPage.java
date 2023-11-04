package aalt.pages.inventory;

import aalt.core.libraries.BaseUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

public class InventoryPage<T> extends BaseUI {
    private Map locatorMap = jsonFileToMap("locators/inventory/InventoryPageLocators.json");
    private WebDriver driver;
    private T originPage;
    public InventoryPage(WebDriver driver, T originPage) {
        this.driver = driver;
        this.originPage = originPage;
    }

    public InventoryPage<T> addProductToCart(String productName){
        int initialValue = 0;
        try{
            initialValue = Integer.parseInt(getText(this.driver,locatorMap.get("shoppingCartItems").toString()));
        }catch (Exception ex){

        }
        click(this.driver, locatorMap.get("productButton").toString(),productName);
        int cartItems = Integer.parseInt(getText(this.driver,locatorMap.get("shoppingCartItems").toString()));

        //Assertions for to confirm the add to cart action
        Assert.assertEquals(getText(this.driver,locatorMap.get("productButton").toString(),productName), "Remove","Product button text must be remove");
        Assert.assertEquals(cartItems, initialValue+1,"Item not added to cart successfully");

        return this;
    }

    public InventoryPage<T> removeFromCart(String productName){
        click(this.driver, locatorMap.get("productButtonByText").toString(),productName,"Remove");
        Assert.assertEquals(getText(this.driver,locatorMap.get("productButton").toString(),productName), "Add to cart","Product button text must be remove");
        return this;
    }

}
