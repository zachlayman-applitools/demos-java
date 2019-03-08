package com.applitools.demos;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BranchTests extends BaseTest {

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