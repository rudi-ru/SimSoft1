package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BurgerHomeElement {
    WebDriver driver;

    @FindBy(xpath = "//button[text() = 'Home']")
    WebElement burgerHomeButton;

    // Обработка кнопки Home
    public BurgerHomeElement(WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickHomeButton() {
        burgerHomeButton.click();
    }
}
