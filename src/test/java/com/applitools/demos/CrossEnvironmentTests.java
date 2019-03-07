package com.applitools.demos;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.config.Configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CrossEnvironmentTests {
    private Eyes eyes;
    private Configuration config;
    private BatchInfo batchInfo;

    @BeforeClass
    public void setupEyesConfig() {
        batchInfo = new BatchInfo(null);

        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        // Cross environment tests should always use MatchLevel.LAYOUT.
        eyes.setMatchLevel(MatchLevel.LAYOUT);
        eyes.setBatch(batchInfo);

        config = new Configuration();
        config.setAppName("Zach's Demo Java App");
        config.setTestName("Cross Environment Test");
        config.setViewportSize(new RectangleSize(1024, 768));

        // Setting the baseline environment name
        // is what enables cross environment tests.
        config.setBaselineEnvName("default desktop");
    }

    @Test
    public void chrome() {
        WebDriver chromeDriver = new ChromeDriver();

        navigateToGoogleHomepage(chromeDriver);
    }

    @Test
    public void firefox() {
        WebDriver firefoxDriver = new FirefoxDriver();

        navigateToGoogleHomepage(firefoxDriver);
    }

    private void navigateToGoogleHomepage(WebDriver driver) {
        try {
            eyes.open(driver, config);
            
            driver.get("http://google.com");
            eyes.checkWindow("navigate to homepage");

            eyes.close();
        } finally {
            driver.quit();
            eyes.abortIfNotClosed();
        }
    }
}
