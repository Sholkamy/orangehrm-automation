package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void clickButton(WebElement element) {
        element.click();
    }

    protected void setInputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // To handle the custom dropdown list
    protected void selectDropdownByVisibleText(WebElement element, String optionText) {
        clickButton(element);
        WebElement selection = driver.findElement(By.xpath(
                "//div[@role='option' and normalize-space()='" + optionText + "']"));
        clickButton(selection);
    }

    // To handle the autocomplete fields
    public void selectFromAutocomplete(WebElement element, String visibleText) {
        setInputText(element, visibleText);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement selection = wait.until(driver -> {
            List<WebElement> options = driver.findElements(By.xpath("//*[contains(text(),'"
                    + visibleText + "')]"));
            return !options.isEmpty() ? options.get(0) : null;
        });

        selection.click();
    }

    public List<WebElement> getTableRows(By tableLocator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // To wait for any row to display in the table
        List<WebElement> rows = wait.until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(tableLocator));

        if (rows.isEmpty()) {
            throw new RuntimeException("No rows found in the table!");
        }
        return rows;
    }

    public WebElement getRowFromTable(By tableLocator, int rowIndex) {
        List<WebElement> rows = getTableRows(tableLocator);

        if (rowIndex < 0 || rowIndex >= rows.size()) {
            throw new IllegalArgumentException(
                    "Invalid row index: " + rowIndex + ", total rows: " + rows.size()
            );
        }

        return rows.get(rowIndex);
    }

    private List<WebElement> getCellsFromRow(WebElement rowElement) {
        return rowElement.findElements(By.cssSelector("div.oxd-table-cell > div"));
    }

    public WebElement getCellFromRow(By tableLocator, int rowIndex, int columnIndex) {
        WebElement row = getRowFromTable(tableLocator, rowIndex);
        List<WebElement> cells = getCellsFromRow(row);

        if (columnIndex <= 0 || columnIndex > cells.size()) {
            throw new IllegalArgumentException(
                    "Invalid column index: " + columnIndex + ", total cells: " + cells.size()
            );
        }

        return cells.get(columnIndex);
    }

    public void clickDeleteIcon(By tableLocator, int rowIndex, int columnIndex) {
        WebElement actionsCell = getCellFromRow(tableLocator, rowIndex, columnIndex);
        WebElement deleteButton = actionsCell.findElement(By.cssSelector("button"));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));

        deleteButton.click();

        deletionConfirmationFromTheConfirmModal();
    }

    public void deletionConfirmationFromTheConfirmModal() {
        WebElement confirmButton = new WebDriverWait(driver, 5).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.oxd-button--label-danger"))
        );
        confirmButton.click();
    }
}