package com.orangehrm.hooks;

import com.orangehrm.config.ConfigReader;
import com.orangehrm.config.DriverFactory;
import com.orangehrm.utils.Helper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.initDriver(ConfigReader.get("browser"));
        driver.get(ConfigReader.get("baseUrl"));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Helper.takeScreenshot(DriverFactory.getDriver(), scenario.getName());
        }

        DriverFactory.quitDriver();
    }
}