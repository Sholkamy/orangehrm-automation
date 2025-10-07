package com.orangehrm.stepDefinitions;

import com.orangehrm.config.DriverFactory;
import com.orangehrm.pages.AdminCreationPage;
import com.orangehrm.pages.AdminPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ManageAdminUsersSteps {

    LoginPage loginObject;
    DashboardPage dashboardObject;
    AdminPage adminObject;
    AdminCreationPage adminCreationObject;

    int initialNumberOfRecords;
    int updatedNumberOfRecords;

    public ManageAdminUsersSteps() {
        loginObject = new LoginPage(DriverFactory.getDriver());
        dashboardObject = new DashboardPage(DriverFactory.getDriver());
        adminObject = new AdminPage(DriverFactory.getDriver());
        adminCreationObject = new AdminCreationPage(DriverFactory.getDriver());
    }

    @Given("the super admin navigates to the OrangeHRM login page")
    public void theSuperAdminNavigatesToTheOrangeHRMLoginPage() {
        loginObject.userLogin();
    }

    @When("the user navigates to the Manage Admin page")
    public void theUserNavigatesToTheManageAdminPage() {
        dashboardObject.navigationToManageAdminsPage();
    }

    @And("the user gets the current number of records")
    public void theUserGetsTheCurrentNumberOfRecords() {
        initialNumberOfRecords = adminObject.getTheCurrentNumberOfAdmins();
    }

    @And("the user navigates to the creation admin page")
    public void theUserNavigatesToTheCreationAdminPage() {
        adminObject.navigationToCreationAdminPage();
    }

    @And("the user adds a new admin with valid data")
    public void theUserAddsANewAdminWithValidData() {
        adminCreationObject.createAdmin();
    }

    @Then("the number of records should increase by one")
    public void theNumberOfRecordsShouldIncreaseByOne() {
        updatedNumberOfRecords = adminObject.getTheCurrentNumberOfAdmins();
        Assert.assertEquals(initialNumberOfRecords + 1, updatedNumberOfRecords);
    }

    @When("the user searches for the newly added admin")
    public void theUserSearchesForTheNewlyAddedAdmin() {
        adminObject.searchByUsername();
    }

    @And("the user deletes the user")
    public void theUserDeletesTheUser() {
        adminObject.deleteUserFromTable();
    }

    @Then("the number of records should decrease by one")
    public void theNumberOfRecordsShouldDecreaseByOne() {
        adminObject.resetSearchResults();
        updatedNumberOfRecords = adminObject.getTheCurrentNumberOfAdmins();
        Assert.assertEquals(initialNumberOfRecords, updatedNumberOfRecords);
    }
}
