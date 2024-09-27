package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import static helpers.Wait.*;
import static tests.AddCustomerTest.account;

// Создаем новый аккаунт
public class OpenAccountPage extends BasePage {

    final String xpathUserAccount = "//select[@id='userSelect']/option[text()='" + account + "']";

    @FindBy(xpath = "//button[@ng-click='openAccount()']")
    WebElement openAccount;

    @FindBy(xpath = "//select[@id='userSelect']")
    WebElement userSelect;

    @FindBy(xpath = "//select[@id='currency']")
    WebElement currencySelect;

    @FindBy(xpath = "//select[@id='currency']/option[@value='Dollar']")
    WebElement dollar;

    @FindBy(xpath = "//button[@type='submit' and text()='Process']")
    WebElement addProcessButton;


    public OpenAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Open Account")
    public OpenAccountPage waitUntilOpen() {
        openAccount.click();
        return this;
    }

    @Step("Choose added user")
    public OpenAccountPage chooseUser(String account) {
        userSelect.click();
        driver.findElement(By.xpath(xpathUserAccount)).click();
        return this;
    }

    @Step("Choose currency")
    public OpenAccountPage chooseCurrency() {
        currencySelect.click();
        waitUntilClickable(driver, dollar);
        dollar.click();
        return this;
    }

    @Step("Click button Pocess")
    public OpenAccountPage clickProcessButton() {
        addProcessButton.click();
        return this;
    }
}
