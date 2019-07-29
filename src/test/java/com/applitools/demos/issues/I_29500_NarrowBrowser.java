package com.applitools.demos.issues;

import com.applitools.demos.BaseTest;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class I_29500_NarrowBrowser extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchName = "I_29500_NarrowBrowser";
        super.setupEyes();
    }

    @Test
    public void ff() {
        driver = new FirefoxDriver();
        config.setTestName("I_29500_NarrowBrowser");
        config.setViewportSize(new RectangleSize(400, 800));
        config.setStitchMode(StitchMode.CSS);
        config.setForceFullPageScreenshot(true);
        eyes.setConfiguration(config);

        eyes.open(driver);
        driver.get("https://qa-www.seasoned.co/job/c32bf94c-415d-46c4-8deb-869ed3ee6d68");

        JavascriptExecutor jsexe = (JavascriptExecutor) driver;

        // hide overlow of images in carousel
        jsexe.executeScript("document.getElementsByClassName('carousel')[0].style.overflow = 'hidden';");
        
        // hide overlow of images in carousel
        jsexe.executeScript("document.getElementsByClassName('fixed-element-container')[0].style.position = 'relative';");
        

        eyes.check("job listing", Target.region(By.id("content")).fully().ignoreCaret(true));
    }
}
