package task3.builder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

   @BeforeMethod
    public void setUp() {
        // Set up WebDriver (ChromeDriver in this case)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode for CI/CD
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        User user = new UserBuilder()
                .withUsername("standard_user")
                .withPassword("secret_sauce")
                .build();

        loginPage.loginAsUser(user);

        assert(driver.getTitle().contains("Swag Labs"));
    }

    @Test
    public void testInvalidLogin() {
        // Build a User object with invalid credentials
        User user = new UserBuilder()
                .withUsername("invalid_user")
                .withPassword("wrong_password")
                .build();

        // Perform login
        loginPage.loginAsUser(user);

        // Assert error message is displayed
        String errorMessage = loginPage.getErrorMessage();
       assert (errorMessage.contains("Epic sadface"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}