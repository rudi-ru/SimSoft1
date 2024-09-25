package tests;

import helpers.SelectorCustomers;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CustomerPage;

public class DeleteCustomerTest extends BaseTest {
    CustomerPage customerPage = new CustomerPage(driver);

    @BeforeTest
    public void openScreen() {
        basePage.open();
    }

    @Test
    @Description("Open the form")
    @Severity(value = SeverityLevel.CRITICAL)
    public void clickCustomers(){
        customerPage.clickCustomerPage();
        Assert.assertTrue(customerPage.table.isDisplayed(), "Error");
    }

    @Test
    @Description("Searching and delete an account")
    @Severity(value = SeverityLevel.CRITICAL)
    public void deleteCustomer() throws InterruptedException {
        SelectorCustomers.customersRemover(customerPage.table.getText()).stream().forEach(nameToDelete -> {
            customerPage.deleteUser(nameToDelete);
        });
        Assert.assertFalse(customerPage.table.getText().contains(customerPage.deletedElement.toString()), "Not deleted");
    }
}
