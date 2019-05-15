package com.applitools.demos.issues;

import com.applitools.demos.BaseTest;
import com.applitools.eyes.selenium.fluent.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public void chrome() {
        driver = new ChromeDriver();
        driver.get("https://coxkit-demo.homenetauto-np.cloud/");
        
        config.setTestName("I_28822_HoverElement");
        eyes.setConfiguration(config);

        eyes.open(driver);
        eyes.check("buttons", Target.region(By.id("buttonExamples")));
    }
}
