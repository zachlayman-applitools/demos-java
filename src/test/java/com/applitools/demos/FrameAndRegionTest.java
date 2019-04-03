package com.applitools.demos;

import com.applitools.eyes.RectangleSize;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FrameAndRegionTest extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchName = "Frame and Region Test";
        super.setupEyes();
    }

    @Test
    public void framePageTest() {
        driver = new FirefoxDriver();
        config.setTestName("Frame Page Test");
        config.setForceFullPageScreenshot(true);
        config.setViewportSize(new RectangleSize(1200, 900));
        
        eyes.open(driver, config);
        driver.get("https://zachlayman-applitools.github.io/iframe-page.html");
        super.waitForIsDisplayed(driver, By.id("outer"), timeout);
        
        eyes.checkFrame("myFrame");
    }

    @Test
    public void framePDFTest() {
        driver = new FirefoxDriver();
        config.setTestName("Frame PDF Test");
        config.setForceFullPageScreenshot(true);
        config.setViewportSize(new RectangleSize(1200, 900));
        
        eyes.open(driver, config);
        driver.get("https://zachlayman-applitools.github.io/iframe-pdf.html");
        super.waitForIsDisplayed(driver, By.id("outer"), timeout);
        
        eyes.checkFrame("myFrame", "Frame PDF Test");
    }

    @Test
    public void regionOverflowTest() {
        driver = new FirefoxDriver();
        config.setTestName("Region Overflow Test");
        config.setForceFullPageScreenshot(true);
        config.setViewportSize(new RectangleSize(1200, 900));
        
        eyes.open(driver, config);
        driver.get("https://zachlayman-applitools.github.io/region-overflow.html");
        super.waitForIsDisplayed(driver, By.id("outer"), timeout);
        
        eyes.checkRegion(By.id("outer"), "my test name", true);
    }
}
