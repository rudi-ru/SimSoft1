package tests;

import helpers.SelectorCustomersToDelete;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CustomerPage;

import java.util.List;
import java.util.NoSuchElementException;


public class DeleteCustomerTest extends BaseTest {
    CustomerPage customerPage = new CustomerPage(driver);


    @BeforeTest
    public void openScreen() {
        basePage.open();
    }

    @Test
    @Step("Searching and delete an account")
    @Severity(value = SeverityLevel.CRITICAL)
    public void checkAndDeleteCustomer() throws InterruptedException {
        customerPage.clickCustomerPage();
        String list = driver.findElement(By.xpath("//tbody")).getText();

        List<String> namesToDelete = SelectorCustomersToDelete.customersRemover(list);
        for (String nameToDelete : namesToDelete) {
            customerPage
                    .deleteUser(nameToDelete);
        }
    }

    @Test
    @Step("Data deletion check")
    @Severity(value = SeverityLevel.CRITICAL)
    public void checkForm() {
        try {
            List<WebElement> deletedElement = driver.findElements(By.xpath("//td[@class='ng-binding'  and text()='Harry'] | //td[@class='ng-binding' and text()='Albus']"));
            Assert.assertTrue(deletedElement.isEmpty(), "Not deleted");
        } catch (NoSuchElementException e) {
            new RuntimeException(e);
        }
    }
}
