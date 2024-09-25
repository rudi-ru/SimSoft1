package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CustomerPage;

public class SortByFirstNameTest extends BaseTest {
    CustomerPage customerPage = new CustomerPage(driver);

    @BeforeTest
    public void openScreen() {
        basePage.open();
    }

    @Test
    @Description("Checking and sort accounts")
    @Severity(value = SeverityLevel.CRITICAL)
    public void checkFirstName() throws InterruptedException {
        customerPage.clickCustomerPage();
        Assert.assertTrue(customerPage.table.isDisplayed(), "Error");
        String data = customerPage.table.getText().toLowerCase();
        customerPage.sortByClickFirstName(data);
        Assert.assertFalse(data.equals(customerPage.table.getText().toLowerCase()), "Not sorted by alphabet");
    }
}
