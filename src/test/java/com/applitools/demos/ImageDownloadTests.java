package com.applitools.demos;

import com.applitools.demos.testresulthandler.ApplitoolsTestResultsHandler;
import com.applitools.eyes.TestResults;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ImageDownloadTests extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchName = "Image Download Tests";
        super.setupEyes();
    }

    @Test
    public void chrome() throws Exception {
        driver = new ChromeDriver();
        config.setTestName("Image Download Test");

        snapWebpage("http://speedrunslive.com", By.id("streamList"), null);

        TestResults testResult = eyes.close(false);
        ApplitoolsTestResultsHandler testResultHandler = new ApplitoolsTestResultsHandler(testResult, System.getenv("APPLITOOLS_API_KEY_RO"));
        testResultHandler.downloadImages("./images");
    }
}
