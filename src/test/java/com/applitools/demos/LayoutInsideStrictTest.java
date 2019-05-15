package com.applitools.demos;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.fluent.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LayoutInsideStrictTest extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchName = "Layout Inside Strict";
        super.setupEyes();
    }

    @Test
    public void layoutInsideStrict() {
        driver = new FirefoxDriver();
        config.setTestName("Layout Inside Strict");
        config.setMatchLevel(MatchLevel.STRICT);
        
        eyes.setConfiguration(config);
        eyes.open(driver);
        driver.get("https://applitools.com/helloworld?diff1");
        waitForIsDisplayed(driver, By.className("section"), timeout);

        eyes.check(Target.window().layout());
    }
}
