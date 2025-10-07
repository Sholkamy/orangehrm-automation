package com.orangehrm.pages;

import com.orangehrm.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage {

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(., 'Records Found')]")
    WebElement numberOfAddedAdmins;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addAdminButton;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    WebElement usernameSearchField;

    @FindBy(css = "button.oxd-button--secondary.orangehrm-left-space")
    WebElement searchButton;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    WebElement resetButton;

    By tableBody = By.cssSelector(
            ".oxd-table-body .oxd-table-row");

    public int getTheCurrentNumberOfAdmins() {
        waitForElementToBeVisible(numberOfAddedAdmins);
        String text = numberOfAddedAdmins.getText();
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    public void navigationToCreationAdminPage() {
        clickButton(addAdminButton);
    }

    public String searchByUsername() {
        setInputText(usernameSearchField, ConfigReader.get("username"));
        clickButton(searchButton);

        WebElement usernameCell = getCellFromRow(tableBody, 0, 1);
        return usernameCell.getText();
    }

    public void deleteUserFromTable() {
        clickDeleteIcon(tableBody, 0,5);
    }

    public void resetSearchResults() {
        clickButton(resetButton);
    }
}
