package com.applitools.demos;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class IPhoneTests extends BaseTest {
    private DesiredCapabilities dc;

    @Override
    @BeforeClass
    public void setupEyes() {
        super.setupEyes();

        dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.2");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone XS Max");
        dc.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
    }

    @Test
    public void googleSearch() throws MalformedURLException {
        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        config.setTestName("iOS Tests");

        snapWebpage("http://google.com", By.id("gb"), null);
    }
}