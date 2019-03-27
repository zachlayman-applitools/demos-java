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

public class SauceLabsTests extends BaseTest {
    private DesiredCapabilities dc;
    String sauceURL;

    @Override
    @BeforeClass
    public void setupEyes() {
        batchInfo = new BatchInfo(batchName);

        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        eyes.setBatch(batchInfo);
        eyes.setMatchLevel(MatchLevel.STRICT);
        eyes.setLogHandler(new StdoutLogHandler(true));

        config = new Configuration();
        config.setAppName("Zach's Demo Java App");
        
        // https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
        dc = new DesiredCapabilities();
        dc.setCapability("browserName", "Chrome");
        dc.setCapability("platform", "macOS 10.14");
        dc.setCapability("version", "72.0");
        dc.setCapability("screenResolution", "1376x1032");

        sauceURL = String.format(
           "http://%s:%s@ondemand.saucelabs.com:80/wd/hub",
           System.getenv("SAUCE_USERNAME"), System.getenv("SAUCE_ACCESS_KEY"));
    }

    @Test
    public void saucePage() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(sauceURL), dc);
        config.setTestName("Sauce Test");

        snapWebpage("https://www.yahoosmallbusiness.com/stores", By.id("logo"), null);
    }
}