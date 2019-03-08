package com.applitools.demos;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CrossEnvironmentTests extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        super.setupEyes();
        config.setBaselineEnvName("default desktop");
    }

    @Test
    public void chrome() {
        driver = new ChromeDriver();
        config.setTestName("Cross Environment Test");

        snapWebpage("http://google.com", By.id("hplogo"), null);
    }

    @Test
    public void firefox() {
        driver = new FirefoxDriver();
        config.setTestName("Cross Environment Test");

        snapWebpage("http://google.com", By.id("hplogo"), null);
    }

    @Test
    public void chromeMultistep() {
        driver = new ChromeDriver();
        config.setTestName("Cross Environment Multistep Test");

        snapSearchResults("virginia tech");
    }

    @Test
    public void firefoxMultistep() {
        driver = new FirefoxDriver();
        config.setTestName("Cross Environment Multistep Test");

        snapSearchResults("virginia tech");
    }
}
