package com.applitools.demos;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CrossEnvironmentTests extends BaseTest {

    @Test
    public void chrome() {
        driver = new ChromeDriver();
        config.setTestName("Cross Environment Test");

        navigateToWebpage("http://google.com");
    }

    @Test
    public void firefox() {
        driver = new FirefoxDriver();
        config.setTestName("Cross Environment Test");

        navigateToWebpage("http://google.com");
    }

    @Test
    public void chromeMultistep() {
        driver = new ChromeDriver();
        config.setTestName("Cross Environment Multistep Test");

        performSearch("virginia tech");
    }

    @Test
    public void firefoxMultistep() {
        driver = new FirefoxDriver();
        config.setTestName("Cross Environment Multistep Test");

        performSearch("virginia tech");
    }
}
