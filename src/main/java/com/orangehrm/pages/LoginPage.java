package com.orangehrm.pages;

import com.orangehrm.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "username")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(css = "button.orangehrm-login-button")
    WebElement loginButton;

    public void userLogin() {
        setInputText(usernameField, ConfigReader.get("superAdminUsername"));
        setInputText(passwordField, ConfigReader.get("superAdminPassword"));
        clickButton(loginButton);
    }
}
