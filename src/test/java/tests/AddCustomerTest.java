package tests;


import helpers.PropertyProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.CustomerPage;
import pages.OpenAccountPage;
import static helpers.FirstNameGenerator.getFirstName;
import static helpers.PostCodeGenerator.getPostCode;

public class AddCustomerTest extends BaseTest {
    AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
    OpenAccountPage openAccountPage = new OpenAccountPage(driver);
    CustomerPage customerPage = new CustomerPage(driver);

    public static final String postCode = getPostCode();
    public static final String firstName = getFirstName(postCode);
    public static final String lastName = PropertyProvider.getInstance().getProperty("last.name");
    public static final String account = firstName + " " + PropertyProvider.getInstance().getProperty("last.name");

    @BeforeTest
    public void openScreen() {
        basePage.open();
    }

    @Test
    @Description("Creating a new account")
    public void addCustomer() throws InterruptedException {

        addCustomerPage
                .waitUntilOpen()
                .addPostCode(postCode)
                .addFirstName(firstName)
                .addLastName(lastName)
                .clickAddCustomerButton();

        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Customer added successfully with customer id"), "Customer not added");
    }

    @Test
    @Description("Select an account, select currency and click Process")
    @Severity(value = SeverityLevel.CRITICAL)
    public void openAccount() throws InterruptedException {
        openAccountPage
                .waitUntilOpen()
                .chooseUser(account)
                .chooseCurrency()
                .clickProcessButton();

        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Account created successfully with account Number"), "Not choosed");
    }

    @Test
    @Description("Сhecking that forms are filled out correctly")
    @Severity(value = SeverityLevel.CRITICAL)
    public void openAndCheckForm() {
        customerPage.clickCustomerPage();

        customerPage.setTextToSearchCustomer(firstName);

        Assert.assertTrue(customerPage.table.getText().contains(firstName), "Name not found");
        Assert.assertTrue(customerPage.table.getText().contains(lastName), "Last name not found");
        Assert.assertTrue(customerPage.table.getText().contains(postCode), "Post code not found");
    }

    // Принимаем алерты. Если алерт не найден, просто игнорируем ошибку
    @AfterMethod
    public void tearDown() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {

        }
    }
}
