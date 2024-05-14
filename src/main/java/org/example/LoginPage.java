package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username) {
        String password = getPasswordForUser(username);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    private String getPasswordForUser(String username) {
        switch (username) {
            case "standard_user":
                return "secret_sauce";
            case "problem_user":
                return "secret_sauce";
            case "visual_user":
                return "secret_sauce";
            default:
                throw new IllegalArgumentException("Invalid username: " + username);
        }
    }
}
