package pages;

import helpers.SelectorCustomers;
import helpers.Wait;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

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

    @FindBy(xpath = "//tbody")
    public WebElement table;

    By deleted = By.xpath("//td[@class='ng-binding'  and text()='Harry'] | //td[@class='ng-binding' and text()='Albus']");
    public List<WebElement> deletedElement = driver.findElements(deleted);

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
    public CustomerPage sortByClickFirstName(String data) {
        firstColumnName.click();
        if (sortedByAlphabet(data) == false) {
            firstColumnName.click();
        }
        return this;
    }

    @Step("Checking if the table is sorted by name")
    public  boolean sortedByAlphabet(String data) {
        return SelectorCustomers.takeNames(table.getText()).stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList())
                .equals(SelectorCustomers.takeNames(table.getText())
                .stream().map(String::toLowerCase).sorted()
                .collect(Collectors.toList()));
    }
}
