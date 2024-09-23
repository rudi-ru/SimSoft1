package tests;


import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.CustomerPage;
import pages.OpenAccountPage;

import static helpers.Wait.waitUntilVisible;

public class AddCustomerTest extends BaseTest {
    AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
    OpenAccountPage openAccountPage = new OpenAccountPage(driver);
    CustomerPage customerPage = new CustomerPage(driver);


    @BeforeTest
    public void openScreen() {
        basePage.open();
    }

    @Test
    @Step("Creating a new account")
    public void addCustomer() throws InterruptedException {

        addCustomerPage
                .waitUntilOpen()
                .addPostCode()
                .addFirstName()
                .addLastName()
                .clickAddCustomerButton();

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    @Step("Select an account, select currency and click Process")
    public void openAccount() throws InterruptedException {
        openAccountPage
                .waitUntilOpen()
                .chooseUser()
                .chooseCurrency()
                .clickProcessButton();

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    // Xpath-ы для элементов таблицы окна Customers
    String xpathFirstName = "//td[@class='ng-binding' and text()='" + AddCustomerPage.firstName + "']";

    String xpathLastName = "//td[@class='ng-binding' and text()='" + AddCustomerPage.lastName + "']";

    String xpathPostCode = "//td[@class='ng-binding' and text()='" + AddCustomerPage.postCode + "']";

    @Test
    @Step("Сhecking that forms are filled out correctly")
    @Severity(value = SeverityLevel.CRITICAL)
    public void openAndCheckForm() {

        customerPage.clickCustomerPage();
        customerPage.setTextToSearchCustomer(AddCustomerPage.firstName);
        waitUntilVisible(driver, driver.findElement(By.xpath(xpathFirstName)));


        Assert.assertEquals(driver.findElement(By.xpath(xpathFirstName)).getText(), AddCustomerPage.firstName);
        Assert.assertEquals(driver.findElement(By.xpath(xpathLastName)).getText(), AddCustomerPage.lastName);
        Assert.assertEquals(driver.findElement(By.xpath(xpathPostCode)).getText(), AddCustomerPage.postCode);
    }
}
