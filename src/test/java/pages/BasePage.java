package pages;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.elements.BurgerHomeElement;


public class BasePage {
    protected final WebDriver driver;

    BurgerHomeElement burgerHomeElement;

    public BasePage(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
            burgerHomeElement = new BurgerHomeElement(driver);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    public void open() {
        driver.get(PropertyProvider.getInstance().getProperty("web.url"));
    }
}
