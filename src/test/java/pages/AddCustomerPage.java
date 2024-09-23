package pages;

import helpers.PropertyProvider;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import static helpers.FirstNameGenerator.getFirstName;
import static helpers.PostCodeGenerator.getPostCode;
import static helpers.Wait.waitUntilClickable;

// Класс обработки формы добавления клиента
public class AddCustomerPage extends BasePage {

    public static final String postCode = getPostCode();
    public static final String firstName = getFirstName(postCode);
    public static final String lastName = PropertyProvider.getInstance().getProperty("last.name");
    public static final String account = firstName + " " + PropertyProvider.getInstance().getProperty("last.name");


    @FindBy(xpath = "//*[contains(text(), 'Add Customer')]")
    WebElement addCustomer;

    @FindBy(xpath = "//div[@class='form-group']//input[@placeholder='First Name']")
    WebElement firstNameField;

    @FindBy(xpath = "//div[@class='form-group']//input[@placeholder='Last Name']")
    WebElement lastNameField;

    @FindBy(xpath = "//div[@class='form-group']//input[@placeholder='Post Code']")
    WebElement postCodeField;

    @FindBy(xpath = "//button[@type='submit' and text()='Add Customer']")
    WebElement addCustomerButton;


    public AddCustomerPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Step("Wait until form Add Customer is open")
    public AddCustomerPage waitUntilOpen() {
        waitUntilClickable(driver, addCustomer);
        addCustomer.click();
        return this;
    }

    @Step("Add Post Code")
    public AddCustomerPage addPostCode() {
        if (postCodeField.isDisplayed()) {
            postCodeField.sendKeys(postCode);
        }
        return this;
    }

    @Step("Add First Name")
    public AddCustomerPage addFirstName() {
        firstNameField.sendKeys(firstName);
        return this;
    }

    @Step("Add Last Name")
    public AddCustomerPage addLastName() {
        lastNameField.sendKeys(lastName);
        return this;
    }

    @Step("Click button \"Add Cuctomer\"")
    public AddCustomerPage clickAddCustomerButton() {
        addCustomerButton.click();
        return this;
    }
}
