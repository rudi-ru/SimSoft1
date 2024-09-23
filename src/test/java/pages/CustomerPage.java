package pages;

import helpers.Wait;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class CustomerPage extends BasePage {

    // Класс для работы с формой Customers
    public CustomerPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//button[@ng-click='showCust()']")
    WebElement openCustomerButton;

    @FindBy(xpath = "//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    WebElement firstColumnName;

    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    public WebElement searchCustomer;

    @FindBy(xpath = "//tbody//button[text()='Delete']")
    WebElement deleteButton;

    @Step("Open Customers")
    public CustomerPage clickCustomerPage() {
        openCustomerButton.click();
        return this;
    }

    @Step("Insert text into field Search Customer")
    public CustomerPage setTextToSearchCustomer(String text) {
        searchCustomer.click();
        searchCustomer.sendKeys(text);
        return this;
    }

    @Step("Delete Customer")
    public CustomerPage deleteUser(String name) {
        setTextToSearchCustomer(name);
        Wait.waitUntilClickable(driver, deleteButton);
        deleteButton.click();
        searchCustomer.clear();
        return this;
    }

    @Step("Sorting customers by clicking on the column name First Name")
    public CustomerPage sortByClickFirstName() {
        firstColumnName.click();
        if (sortedByAlphabet(driver) == false) {
            firstColumnName.click();
        }
        return this;
    }

    @Step("Checking if the table is sorted by name")
    public static boolean sortedByAlphabet(WebDriver driver) {
        List<WebElement> list = driver.findElements(By.xpath("//tbody"));
        boolean byAlphabet = false;
        if (!list.isEmpty()) {
            if (list.getFirst().getText().toLowerCase().startsWith("a")) {
                byAlphabet = true;
            } else byAlphabet = false;
        }
        return byAlphabet;
    }
}
