package com.applitools.demos;

import java.util.function.Consumer;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.config.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BranchTests {
    private Eyes eyes;
    private WebDriver driver;
    private Configuration config;
    private BatchInfo batchInfo;
    private int timeout = 5;

    @BeforeClass
    public void setupEyesConfig() {
        batchInfo = new BatchInfo(null);

        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        eyes.setBatch(batchInfo);

        config = new Configuration();
        config.setAppName("Zach's Demo Java App");
        config.setViewportSize(new RectangleSize(1024, 768));
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

    private void eyesTest(String searchTerm, Consumer<String> f) {
        try {
            eyes.open(driver, config);

            f.accept(searchTerm);

            eyes.close();
        } finally {
            driver.quit();
            eyes.abortIfNotClosed();
        }
    }

    private void performSearch(String searchTerm) {
        eyesTest(searchTerm, new Consumer<String>() {
            public void accept(String searchTerm) {
                snapHomepage();
                snapSearchResults(searchTerm);
            }
        });
    }

    private Boolean snapHomepage() {
        WebDriver driver = eyes.getDriver();

        driver.get("http://google.com");
        waitForIsDisplayed(driver, By.id("hplogo"), timeout);
        eyes.checkWindow("homepage");
        return true;
    }

    private Boolean snapSearchResults(String searchTerm) {
        WebDriver driver = eyes.getDriver();

        driver.findElement(By.name("q")).sendKeys(searchTerm);
        driver.findElement(By.name("f")).submit();
        waitForIsDisplayed(driver, By.id("resultStats"), timeout);
        eyes.checkWindow("search results");
        return true;
    }

    private Boolean waitForIsDisplayed(WebDriver driver, By locator, Integer... timeout) {
        try {
            waitFor(driver, ExpectedConditions.visibilityOfElementLocated(locator),
                    (timeout.length > 0 ? timeout[0] : null));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }

        return true;
    }

    private void waitFor(WebDriver driver, ExpectedCondition<WebElement> condition, Integer timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }
}