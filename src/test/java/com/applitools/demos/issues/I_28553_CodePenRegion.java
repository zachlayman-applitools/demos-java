package com.applitools.demos.issues;

import com.applitools.demos.BaseTest;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.config.Configuration;
import com.applitools.eyes.selenium.fluent.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class I_28553_CodePenRegion extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchInfo = new BatchInfo("I_28553_CodePenRegion");

        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        eyes.setBatch(batchInfo);
        eyes.setMatchLevel(MatchLevel.STRICT);
        eyes.setLogHandler(new StdoutLogHandler(true));

        config = new Configuration();
        config.setAppName("I_28553_CodePenRegion");
    }

    @Test
    public void codePenRegions() {
        driver = new FirefoxDriver();
        config.setTestName("I_28553_CodePenRegion");
        config.setHideScrollbars(true);
        // config.setForceFullPageScreenshot(true);
        // config.setStitchMode(StitchMode.CSS);
        config.setViewportSize(new RectangleSize(1600, 900));

        driver.get("https://codepen.io/TrentWalton/pen/eyaDr");
        waitForIsDisplayed(driver, By.id("item-title"), timeout);

        eyes.open(driver, config);

        // eyes.checkWindow();
        // eyes.checkRegion(By.xpath("//div[@class='output-container']"), "test no sticth", false);
        // eyes.checkRegionInFrame("result", By.tagName("body"), "test in frame");
        // eyes.checkFrame("result");

        JavascriptExecutor jsexe = (JavascriptExecutor) driver;
        jsexe.executeScript("document.getElementById('resizer').style.boxShadow = '0 0';");
        eyes.check(Target.frame(By.id("result")).fully());
    }
}
