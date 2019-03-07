package com.applitools.demos;

import java.util.function.Consumer;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.config.Configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CrossEnvironmentTests {
    private Eyes eyes;
    private Configuration config;
    private BatchInfo batchInfo;
    private int timeout = 5;

    @BeforeClass
    public void setupEyesConfig() {
        batchInfo = new BatchInfo(null);

        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        // Cross environment tests should always use MatchLevel.LAYOUT.
        eyes.setMatchLevel(MatchLevel.LAYOUT);
        eyes.setBatch(batchInfo);

        config = new Configuration();
        config.setAppName("Zach's Demo Java App");
        config.setViewportSize(new RectangleSize(1024, 768));

        // Setting the baseline environment name
        // is what enables cross environment tests.
        config.setBaselineEnvName("default desktop");
    }

    @Test
    public void chrome() {
        WebDriver chromeDriver = new ChromeDriver();
        config.setTestName("Cross Environment Test");

        navigateToGoogleHomepage(chromeDriver);
    }

    @Test
    public void firefox() {
        WebDriver firefoxDriver = new FirefoxDriver();
        config.setTestName("Cross Environment Test");

        navigateToGoogleHomepage(firefoxDriver);
    }

    @Test
    public void chromeMultistep() {
        WebDriver chromeDriver = new ChromeDriver();
        config.setTestName("Cross Environment Multistep Test");

        performSearch(chromeDriver);
    }

    @Test
    public void firefoxMultistep() {
        WebDriver firefoxDriver = new FirefoxDriver();
        config.setTestName("Cross Environment Multistep Test");

        performSearch(firefoxDriver);
    }

    private void navigateToGoogleHomepage(WebDriver driver) {
        eyesTest(driver, new Consumer<WebDriver>() {
            public void accept(WebDriver driver) {
                snapHomepage(driver, eyes);
            }
        });
    }

    private void eyesTest(WebDriver driver, Consumer<WebDriver> f) {
        try {
            eyes.open(driver, config);

            f.accept(driver);

            eyes.close();
        } finally {
            driver.quit();
            eyes.abortIfNotClosed();
        }
    }

    private Boolean snapHomepage(WebDriver driver, Eyes eyes) {
        driver.get("http://google.com");
        waitForIsDisplayed(driver, By.id("hplogo"), timeout);
        eyes.checkWindow("homepage");
        return true;
    }

    private void performSearch(WebDriver driver) {
        eyesTest(driver, new Consumer<WebDriver>() {
            public void accept(WebDriver driver) {
                snapHomepage(driver, eyes);
                snapSearchResults(driver, eyes);
            }
        });
    }

    private Boolean snapSearchResults(WebDriver driver, Eyes eyes) {
        driver.findElement(By.name("q")).sendKeys("virginia tech");
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
