package com.applitools.demos;

import com.applitools.eyes.MatchLevel;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BranchTests extends BaseTest {

    @BeforeMethod
    public void setMatchLevelStrict() {
        eyes.setMatchLevel(MatchLevel.STRICT);
    }

    @Test
    public void defaultBranch() {
        driver = new ChromeDriver();
        config.setTestName("Branch Tests");
        config.setBranchName("default");

        performSearch("virginia tech");
    }

    @Test
    public void featureBranch() {
        driver = new ChromeDriver();
        config.setTestName("Branch Tests");
        config.setBranchName("feature");

        performSearch("texas lutheran university");
    }
}