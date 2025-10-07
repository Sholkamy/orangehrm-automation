package com.orangehrm.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static void takeScreenshot(WebDriver driver, String testName) {

        // Time format for the file name to be Unique
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        // Specify the path to the Screenshots folder within the project
        String screenshotDir = System.getProperty("user.dir") + "/Screenshots/";

        // Ensure folder exists
        new File(screenshotDir).mkdirs();

        // Take a screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            String filePath = screenshotDir + testName + "_" + timestamp + ".png";
            File destFile = new File(filePath);

            // Copy the file without external dependencies
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("✅ Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Failed to save screenshot: " + e.getMessage());
        }
    }
}
