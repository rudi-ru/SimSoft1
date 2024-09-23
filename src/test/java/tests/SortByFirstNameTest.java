package tests;

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
    @Step("Checking and sort accounts")
    @Severity(value = SeverityLevel.CRITICAL)
    public void checkFirstName() throws InterruptedException {

        customerPage
                .clickCustomerPage()
                .sortByClickFirstName();
    }

    @Test
    @Step("Сhecking that forms are filled out correctly")
    public void checkForm() {
        String firstChar = driver.findElement(By.xpath("//tbody")).getText().toLowerCase();
        Assert.assertTrue(firstChar.startsWith("a"), "Not sorted by alphabet");
    }
}
