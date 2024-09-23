package tests;

import helpers.CommonActions;
import helpers.PropertyProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import pages.BasePage;
import pages.elements.BurgerHomeElement;


public class BaseTest {

    protected WebDriver driver = CommonActions.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected BurgerHomeElement burgerHomeElement = new BurgerHomeElement(driver);

    @AfterTest
    public void clearCookiesAndLocalStorage() {

        if (PropertyProvider.getInstance().getProperty("clear.cookies.and.storage").equals("true")) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser() {
        if (PropertyProvider.getInstance().getProperty("hold.browser.open").equals("false")) {
            driver.quit();

        }
    }
}