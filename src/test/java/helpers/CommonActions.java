package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class CommonActions {
    public static WebDriver createDriver() {
        WebDriver webDriver = null;
        switch (PropertyProvider.getInstance().getProperty("browser.name")) {
            case "win_chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
            case "lin_chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                webDriver = new ChromeDriver();
                break;
            default:
                Assert.fail("Incorrect platform or browser name: " +
                        PropertyProvider.getInstance().getProperty("browser.name"));
        }

        webDriver.manage().window().maximize();
        webDriver.manage().
                timeouts().
                implicitlyWait(Long.parseLong(PropertyProvider.getInstance().getProperty("page.load.timeout")), TimeUnit.SECONDS);
        return webDriver;
    }
}
