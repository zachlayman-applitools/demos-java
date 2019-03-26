package com.applitools.demos.issues;

import com.applitools.demos.BaseTest;
import com.applitools.eyes.selenium.StitchMode;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class I_28247_Scroll_Screenshot extends BaseTest {

    @BeforeClass
    @Override
    public void setupEyes() {
        batchName = "I_28247_Scroll_Screenshot";
        super.setupEyes();
    }

    @Test
    public void cssFullPage() {
        driver = new ChromeDriver();
        config.setTestName("css");
        config.setForceFullPageScreenshot(true);
        config.setStitchMode(StitchMode.CSS);

        snapWebpage("https://www.esri.com/en-us/home", By.id("globalnav"), null);
    }

    @Test
    public void scrollFullPage() {
        driver = new ChromeDriver();
        config.setTestName("scroll");
        config.setForceFullPageScreenshot(true);
        config.setStitchMode(StitchMode.SCROLL);

        snapWebpage("https://www.esri.com/en-us/home", By.id("globalnav"), "window.scrollTo(0, document.body.scrollHeight); window.scrollTo(0, -document.body.scrollHeight)");
    }

    @Test
    public void cssWithFullScrollFullPage() {
        driver = new ChromeDriver();
        config.setTestName("hybrid");
        config.setForceFullPageScreenshot(true);
        config.setStitchMode(StitchMode.CSS);

        snapWebpage("https://www.esri.com/en-us/home", By.id("globalnav"), "document.documentElement.scrollTop=999999; document.documentElement.scrollTop=0;");
    }
}
