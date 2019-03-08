package com.applitools.demos;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.config.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected Eyes eyes;
    protected WebDriver driver;
    protected Configuration config;
    protected BatchInfo batchInfo;
    protected String batchName;
    protected int timeout = 5;

    @BeforeClass
    public void setupEyes() {
        batchInfo = new BatchInfo(batchName);

        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        eyes.setBatch(batchInfo);
        eyes.setMatchLevel(MatchLevel.LAYOUT);

        config = new Configuration();
        config.setAppName("Zach's Demo Java App");
        config.setViewportSize(new RectangleSize(1024, 768));
    }

    @AfterMethod
    public void teardownEyes() {
        try {
            eyes.close();
        } catch (Throwable t) {
                t.printStackTrace();
        } finally {
            driver.quit();
            eyes.abortIfNotClosed();
        }
    }

    public void snapWebpage(String url, By displayed) {
        eyes.open(driver, config);
        driver.get(url);
        waitForIsDisplayed(driver, displayed, timeout);
        eyes.checkWindow("single page");
    }

    public void snapSearchResults(String searchTerm) {
        snapWebpage("http://google.com", By.id("hplogo"));

        driver.findElement(By.name("q")).sendKeys(searchTerm);
        driver.findElement(By.name("f")).submit();
        waitForIsDisplayed(driver, By.id("resultStats"), timeout);
        eyes.checkWindow("search results");
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