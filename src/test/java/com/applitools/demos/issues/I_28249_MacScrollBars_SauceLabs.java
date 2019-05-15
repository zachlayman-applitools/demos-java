package com.applitools.demos.issues;

import java.net.MalformedURLException;
import java.net.URL;

import com.applitools.demos.BaseTest;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class I_28249_MacScrollBars_SauceLabs extends BaseTest {

    private DesiredCapabilities dc;
    String sauceURL;

    @Override
    @BeforeClass
    public void setupEyes() {
        batchInfo = new BatchInfo("I_28249_MacScrollBars_SauceLabs");

        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        eyes.setBatch(batchInfo);
        eyes.setMatchLevel(MatchLevel.STRICT);
        eyes.setLogHandler(new StdoutLogHandler(true));

        config = new Configuration();
        config.setAppName("Zach's Demo Java App");

        // https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
        dc = DesiredCapabilities.chrome();
        dc.setCapability("platform", "macOS 10.14");
        dc.setCapability("version", "72.0");
        dc.setCapability("screenResolution", "2360x1770");
        dc.setCapability("extendedDebugging", "false");
        dc.setCapability("maxDuration", "5400");
        dc.setCapability("idleTimeout", "5400");

        sauceURL = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", System.getenv("SAUCE_USERNAME"),
                System.getenv("SAUCE_ACCESS_KEY"));
    }

    @Test
    public void chromeSCROLL() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(sauceURL), dc);
        config.setTestName("SCROLL I_28249_MacScrollBars_SauceLabs Test");
        config.setHideScrollbars(true);
        config.setForceFullPageScreenshot(true);
        config.setStitchMode(StitchMode.SCROLL);

        snapWebpage("https://www.yahoosmallbusiness.com/stores", By.id("logo"), null);
    }

    @Test
    public void chromeCSS() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(sauceURL), dc);
        config.setTestName("CSS I_28249_MacScrollBars_SauceLabs Test");
        config.setHideScrollbars(true);
        config.setForceFullPageScreenshot(true);
        config.setStitchMode(StitchMode.CSS);

        snapWebpage("https://www.yahoosmallbusiness.com/stores", By.id("logo"), null);
    }
}
