package com.applitools.demos;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _ExampleTest extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchName = "_ExampleTest";
        super.setupEyes();
    }

    @Test
    public void chrome() {
        driver = new ChromeDriver();
        config.setTestName("_Example Test");
        eyes.setConfiguration(config);

        snapWebpage("http://google.com", By.id("hplogo"), null);
    }
}
