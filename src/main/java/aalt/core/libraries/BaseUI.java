package aalt.core.libraries;

import aalt.core.drivers.DriverFactory;
import aalt.core.external.lib.ExtentReportsLib;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class BaseUI extends Base{
    private static final int MAX_WAIT_TIME = 30;
    private WebDriver driver;
    Map<String,String> env = jsonFileToMap("config/env.conf.json");

    @BeforeTest(alwaysRun = true)
    public void initReport(ITestContext context){
        ExtentReportsLib.initReport(context.getName());
    }
    /**
     * The test is requesting for a specific browser
     * @param browser
     */
    //@BeforeMethod(alwaysRun = true)
    public WebDriver initDriver(String... browser){
        this.driver = DriverFactory.getDriver(browser);
        this.driver.manage().window().maximize();
        return this.driver;
    }

    protected void setText(WebDriver driver, String locator, String value, String... params){
        if(params.length>0){
            locator = replaceParams(locator,params);
        }
        getElement(driver,locator).sendKeys(value);
        info("Setting text "+value+" to "+locator);
    }

    protected String getText(WebDriver driver, String locator, String... params){
        String text = null;
        if(params.length>0){
            locator = replaceParams(locator,params);
        }
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //waiting for the
            WebElement element = getElement(driver,locator);
            text = element.getText();
        }catch (Exception ex){
            info("Element not found for getText()");
        }
        info("Extracted text "+text+" from "+locator);
        return text;
    }

    protected void click(WebDriver driver, String locator, String... params){
        if(params.length>0){
            locator = replaceParams(locator,params);
        }
        getElement(driver,locator).click();
        info("clicking on "+locator);
    }

    protected WebElement getElement(WebDriver driver, String locator) {
        WebElement element;
        if(locator.contains("//")){
            element = (WebElement) fluentWait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        }else{
            element = (WebElement) fluentWait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
        }
        return element;
    }

    private String replaceParams(String locator, String[] params) {
        for(String param : params){
            locator = locator.replaceFirst("<<param>>", param);
        }
        return locator;
    }

    private Wait fluentWait(WebDriver driver){
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_WAIT_TIME))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        return wait;
    }
    public String captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    @AfterMethod
    public void closeBrowser(){
        ExtentReportsLib.endTest();
    }
    @AfterTest
    public void endReport(){
        ExtentReportsLib.endTest();
    }
}
