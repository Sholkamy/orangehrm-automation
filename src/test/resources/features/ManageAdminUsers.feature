Feature: Manage Admin Users
  I want to verify the super admin ability to add another admin,
  ensure that the number of admins changes accordingly after this action,
  search for the newly added admin, and delete it.

  Scenario: Add, Search and delete an admin successfully
    Given the super admin navigates to the OrangeHRM login page
    When the user navigates to the Manage Admin page
    And the user gets the current number of records
    And the user navigates to the creation admin page
    And the user adds a new admin with valid data
    Then the number of records should increase by one
    When the user searches for the newly added admin
    And the user deletes the user
    Then the number of records should decrease by one


