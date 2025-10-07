package com.orangehrm.pages;

import com.orangehrm.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminCreationPage extends BasePage {

    public AdminCreationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[text()='User Role']/following::div[@class='oxd-select-text-input'][1]")
    WebElement userRoleField;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement employeeNameField;

    @FindBy(xpath = "//label[text()='Status']/following::div[@class='oxd-select-text-input'][1]")
    WebElement statusField;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    WebElement usernameField;

    @FindBy(xpath = "//label[text()='Password']/following::input[@type='password'][1]")
    WebElement passwordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[@type='password'][1]")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement saveButton;

    public void createAdmin() {
        selectDropdownByVisibleText(userRoleField, ConfigReader.get("userRole"));
        selectFromAutocomplete(employeeNameField, ConfigReader.get("employeeName"));
        selectDropdownByVisibleText(statusField, ConfigReader.get("status"));
        setInputText(usernameField, ConfigReader.get("username"));
        setInputText(passwordField, ConfigReader.get("password"));
        setInputText(confirmPasswordField, ConfigReader.get("password"));
        clickButton(saveButton);
    }
}
