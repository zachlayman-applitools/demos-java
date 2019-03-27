package com.applitools.demos.issues;

import com.applitools.demos.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class I_28256_NewBaseline_AutoMaintenance extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchName = "I_28256_NewBaseline_AutoMaintenance";
        super.setupEyes();
        batchInfo.setId("I_28256_NewBaseline_AutoMaintenance_006");
        config.setBatch(batchInfo);
    }

    @Test
    public void chrome() {
        driver = new ChromeDriver();
        config.setTestName("Chrome Test");

        snapWebpage("https://applitools.com/helloworld?diff1", By.className("demo-page"), null);
    }

    @Test
    public void firefox() {
        driver = new FirefoxDriver();
        config.setTestName("Firefox Test");

        snapWebpage("https://applitools.com/helloworld?diff1", By.className("demo-page"), null);
    }
}
