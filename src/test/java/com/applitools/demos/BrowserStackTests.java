package com.applitools.demos;

import java.net.MalformedURLException;
import java.net.URL;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.config.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrowserStackTests extends BaseTest {
    private DesiredCapabilities dc;
    String browserstackURL;

    @Override
    @BeforeClass
    public void setupEyes() {
        batchInfo = new BatchInfo(batchName);

        String u = System.getenv("BROWSERSTACK_USERNAME");
        String k = System.getenv("BROWSERSTACK_ACCESS_KEY");
        browserstackURL = "https://" + u + ":" + k + "@hub-cloud.browserstack.com/wd/hub";
  
        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        eyes.setBatch(batchInfo);
        eyes.setMatchLevel(MatchLevel.STRICT);
        eyes.setLogHandler(new StdoutLogHandler(true));

        config = new Configuration();
        config.setAppName("Zach's BS Tests");
        
        dc = new DesiredCapabilities();
        dc.setCapability("browserName", "safari");
        dc.setCapability("device", "iPhone XS");
        dc.setCapability("realMobile", "true");
        dc.setCapability("os_version", "12");
        dc.setCapability("browserstack.console", "verbose");
        dc.setCapability("browserstack.networkLogs", "true");
        
    }
    
    @Test
    public void defaultTest() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(browserstackURL), dc);
        config.setTestName("BrowserStack Test");

        snapWebpage("http://google.com", By.id("gb"), null);
    }
}