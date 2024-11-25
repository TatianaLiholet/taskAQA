package task3.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(id = "password")

    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement logInButton;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void enterUsername(String username) {
        userName.sendKeys(username);
    }
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        logInButton.click();
    }

}