package com.applitools.demos;

import java.net.MalformedURLException;
import java.net.URL;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CBTTests extends BaseTest {
    private DesiredCapabilities dc;
    String cbtURL;

    @Override
    @BeforeClass
    public void setupEyes() {
        batchInfo = new BatchInfo(batchName);

        config = new Configuration();
        config.setAppName("Zach's Demo Java App");
        config.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        config.setBatch(batchInfo);
        config.setMatchLevel(MatchLevel.STRICT);
        
        eyes = new Eyes();
        eyes.setConfiguration(config);
        
        dc = new DesiredCapabilities();
        dc.setCapability("browserName", "Chrome");
        dc.setCapability("deviceName", "Galaxy S7");
        dc.setCapability("platformVersion", "7.0");
        dc.setCapability("platformName", "Android");
        dc.setCapability("deviceOrientation", "portrait");

        cbtURL = String.format(
           "http://%s:%s@hub.crossbrowsertesting.com:80/wd/hub",
           System.getenv("CBTUSRNAME").replaceAll("@", "%40"), System.getenv("CBTAUTH"));
    }

    @Test
    public void cbtPage() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(cbtURL), dc);
        config.setTestName("CBT Test");

        snapWebpage("https://ehp-prod.literatumonline.com", By.id("logo"), null);
    }
}