package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


import static helpers.Wait.*;

// Создаем новый аккаунт
public class OpenAccountPage extends BasePage {

    final String xpathUserAccount = "//select[@id='userSelect']/option[text()='" + AddCustomerPage.account + "']";

    @FindBy(xpath = "//button[@ng-click='openAccount()']")
    WebElement openAccount;

    @FindBy(xpath = "//select[@id='userSelect']")
    WebElement userSelect;

    @FindBy(xpath = "//select[@id='currency']")
    WebElement currencySelect;

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
    public OpenAccountPage chooseUser() {
        waitUntilVisible(driver, userSelect);
        userSelect.click();
        waitThenClick(driver, driver.findElement(By.xpath(xpathUserAccount)));
        driver.findElement(By.xpath(xpathUserAccount)).click();
        return this;
    }

    @Step("Choose currency")
    public OpenAccountPage chooseCurrency() {
        currencySelect.click();
        currencySelect.sendKeys(Keys.DOWN);
        currencySelect.sendKeys(Keys.ENTER);
        return this;
    }


    @Step("Click button Pocess")
    public OpenAccountPage clickProcessButton() {
        addProcessButton.click();
        return this;
    }

}
