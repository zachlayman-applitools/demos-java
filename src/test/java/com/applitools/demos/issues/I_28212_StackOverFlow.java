package com.applitools.demos.issues;

import com.applitools.demos.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class I_28212_StackOverFlow extends BaseTest {

    @Test
    public void chrome() {
        driver = new ChromeDriver();
        config.setTestName("I_28212_StackOverFlow");
        // config.setForceFullPageScreenshot(true);

        snapWebpage("https://www.reactine.ca/", By.id("content"), null);
    }
}
