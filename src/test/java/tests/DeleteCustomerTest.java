package tests;

import helpers.SelectorCustomers;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CustomerPage;
import java.util.List;

public class DeleteCustomerTest extends BaseTest {
    CustomerPage customerPage = new CustomerPage(driver);

    @BeforeTest
    public void openScreen() {
        basePage.open();
    }

    String list;

    @Test
    @Step("Open the form")
    public void clickCustomers(){
        customerPage.clickCustomerPage();
        Assert.assertTrue(customerPage.table.isDisplayed(), "Error");
        list = customerPage.table.getText();
    }

    @Test
    @Step("Searching and delete an account")
    @Severity(value = SeverityLevel.CRITICAL)
    public void deleteCustomer() throws InterruptedException {
        List<String> namesToDelete = SelectorCustomers.customersRemover(list);
        for (String nameToDelete : namesToDelete) {
            customerPage
                    .deleteUser(nameToDelete);
        }
        Assert.assertFalse(list.contains(customerPage.deletedElement.toString()), "Not deleted");
    }
}
