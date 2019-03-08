package com.applitools.demos;

import com.applitools.eyes.MatchLevel;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BranchTests extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        super.setupEyes();
        eyes.setMatchLevel(MatchLevel.STRICT);
    }

    @Test
    public void defaultBranch() {
        driver = new ChromeDriver();
        config.setTestName("Branch Tests");
        config.setBranchName("default");

        snapSearchResults("virginia tech");
    }

    @Test
    public void featureBranch() {
        driver = new ChromeDriver();
        config.setTestName("Branch Tests");
        config.setBranchName("feature");

        snapSearchResults("texas lutheran university");
    }
}