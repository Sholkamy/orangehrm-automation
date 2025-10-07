package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "span.oxd-main-menu-item--name")
    WebElement manageAdminsButton;

    public void navigationToManageAdminsPage() {
        clickButton(manageAdminsButton);
    }
}
