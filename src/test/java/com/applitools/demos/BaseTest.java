package com.applitools.demos;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.config.SeleniumConfiguration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgridclient.model.EmulationDevice;
import com.applitools.eyes.visualgridclient.model.EmulationInfo;
import com.applitools.eyes.visualgridclient.model.ScreenOrientation;
import com.applitools.eyes.visualgridclient.services.VisualGridRunner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    protected SeleniumConfiguration config;
    protected BatchInfo batchInfo;
    protected String batchName;
    protected int timeout = 5;

    @BeforeClass
    public void setupEyes() {
        VisualGridRunner vgRunner = new VisualGridRunner(4);
        vgRunner.setLogHandler(new StdoutLogHandler(true));
        vgRunner.setApiKey(System.getenv("APPLITOOLS_API_KEY"));

        batchInfo = new BatchInfo(batchName);
        eyes = new Eyes(vgRunner);
        eyes.setBatch(batchInfo);

        config = new SeleniumConfiguration();
        config.setAppName("Zach's Applitools Java3 Grid Demo");
        config.setTestName("Applitools Visual Grid Demo");

        config.addBrowser(800, 600, SeleniumConfiguration.BrowserType.FIREFOX);
        config.addBrowser(1200, 800, SeleniumConfiguration.BrowserType.FIREFOX);
        config.addBrowser(1920, 1000, SeleniumConfiguration.BrowserType.FIREFOX);

        EmulationDevice emulationDevice = new EmulationDevice(300, 400, 1f, true, ScreenOrientation.LANDSCAPE);
        EmulationInfo emulationInfo = new EmulationInfo(EmulationInfo.DeviceName.Galaxy_Note_II, ScreenOrientation.PORTRAIT);
        config.addDeviceEmulation(emulationDevice);
        config.addDeviceEmulation(emulationInfo);
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

    public void snapWebpage(String url, By displayed, String jsToInject) {
        eyes.open(driver, config);
        driver.get(url);
        waitForIsDisplayed(driver, displayed, timeout);

        if (jsToInject != null) {
            JavascriptExecutor jsexe = (JavascriptExecutor) driver;
            jsexe.executeScript(jsToInject);
        }

        eyes.check("single page", Target.window());
    }

    public void snapSearchResults(String searchTerm) {
        snapWebpage("http://google.com", By.id("hplogo"), null);

        driver.findElement(By.name("q")).sendKeys(searchTerm);
        driver.findElement(By.name("f")).submit();
        waitForIsDisplayed(driver, By.id("resultStats"), timeout);
        eyes.check("search result", Target.window());
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