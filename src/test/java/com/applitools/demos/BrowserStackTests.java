package com.applitools.demos;

import java.net.MalformedURLException;
import java.net.URL;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;

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

        config = new Configuration();
        config.setAppName("Zach's BS Tests");
        config.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        config.setBatch(batchInfo);
        config.setMatchLevel(MatchLevel.STRICT);

        eyes = new Eyes();
        eyes.setLogHandler(new StdoutLogHandler(true));

        dc = new DesiredCapabilities();
    }

    @Test
    public void androidTest() throws MalformedURLException {
        dc.setCapability("device", "Samsung Galaxy S9 Plus");
        dc.setCapability("real_mobile", "true");
        dc.setCapability("browserstack.local", "false");

        driver = new RemoteWebDriver(new URL(browserstackURL), dc);
        config.setTestName("BrowserStack Test");

        snapSearchResultsMobile("virginia tech");
    }

    @Test
    public void iOSTest() throws MalformedURLException {
        dc.setCapability("browserName", "safari");
        dc.setCapability("device", "iPhone XS");
        dc.setCapability("realMobile", "true");
        dc.setCapability("os_version", "12");
        dc.setCapability("browserstack.console", "verbose");
        dc.setCapability("browserstack.networkLogs", "true");

        driver = new RemoteWebDriver(new URL(browserstackURL), dc);
        config.setTestName("BrowserStack Test");

        snapSearchResultsMobile("virginia tech");
    }

    @Test
    public void browserTest() throws MalformedURLException {
        dc.setCapability("os", "Windows");
        dc.setCapability("os_version", "10");
        dc.setCapability("browser", "Chrome");
        dc.setCapability("browser_version", "73.0");
        dc.setCapability("browserstack.local", "false");
        dc.setCapability("browserstack.selenium_version", "3.5.2");

        driver = new RemoteWebDriver(new URL(browserstackURL), dc);
        config.setTestName("BrowserStack Test");

        snapSearchResultsMobile("virginia tech");
    }
}