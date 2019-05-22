package com.applitools.demos.issues;

import com.applitools.demos.BaseTest;
import com.applitools.eyes.selenium.fluent.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class I_28822_HoverElement extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchName = "I_28822_HoverElement";
        super.setupEyes();
    }

    @Test
    public void hoverButton() {
        driver = new FirefoxDriver();
        driver.get("https://coxkit-demo.homenetauto-np.cloud/");
        driver.findElement(By.id("core-clickableComponents")).click();

        config.setTestName("I_28822_HoverElement");
        eyes.setConfiguration(config);

        eyes.open(driver);

        eyes.check("before button", Target.region(By.id("buttonExamples")));

        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.id("primaryButton"))).perform();
        eyes.check("after button", Target.region(By.id("buttonExamples")));
    }

    @Test
    public void captureTooltip() {
        driver = new ChromeDriver();
        driver.get("https://d1q3vzvnjy4w20.cloudfront.net/");
        driver.findElement(By.id("coxkit-tooltip")).click();

        config.setTestName("I_28822_HoverElement_Tooltip");
        config.setForceFullPageScreenshot(true);
        eyes.setConfiguration(config);

        eyes.open(driver);

        eyes.check("before hover", Target.window());

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id("warningHover"))).perform();

        eyes.checkElement(By.cssSelector("div[content ^= 'I want to tell you']"), "Le Tooltip");
    }
}