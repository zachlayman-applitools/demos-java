package com.applitools.demos;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.fluent.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmazonLayoutTest extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchName = "AmazonLayoutTest";
        super.setupEyes();
    }

    @Test
    public void chrome() {
        driver = new FirefoxDriver();
        config.setMatchLevel(MatchLevel.LAYOUT);
        config.setTestName("AmazonLayoutTest 001");
        config.setApiKey(System.getenv("APPLITOOLS_API_KEY_BUG"));
        // config.setServerUrl("https://zachary.applitools.com");
        eyes.setConfiguration(config);

        driver.get("https://www.amazon.com/s?k=pants"); //DUMMY GET

        eyes.open(driver);

        driver.get("https://www.amazon.com/s?k=watches");
        eyes.check(Target.window().fully().enablePatterns().useDom(true).ignoreDisplacements());

        driver.get("https://www.amazon.com/s?k=socks");
        eyes.check(Target.window().fully().enablePatterns().useDom(true).ignoreDisplacements());

        eyes.close();




        eyes.open(driver);
        
        driver.get("https://www.amazon.com/s?k=socks");
        eyes.check(Target.window().fully().enablePatterns().useDom(true).ignoreDisplacements());

        driver.get("https://www.amazon.com/s?k=watches");
        eyes.check(Target.window().fully().enablePatterns().useDom(true).ignoreDisplacements());
    }
}
