package com.applitools.demos.issues;

import java.io.Console;
import java.net.URL;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.remote.MobileCapabilityType;

public class I_28954_SaveDiffs {

   public static BatchInfo Boltcomponent;
   public WebDriver driver;
   public String testCaseName = "Comparedemo62Prod";

   /*
    * @DataProvider(name="env") public static Object[][] getEnvironments() { return
    * new Object[][]{ {700, 900}, {1000, 800}, {1212, 1000} }; }
    */

   public void setBatch() {
      Boltcomponent = new BatchInfo("Mobile_Batch - iOS 1");
   }

   public Eyes eyes;

   // Devices
   @BeforeMethod
   public void setUp() throws Exception {

      setBatch();
      // DesiredCapabilities caps = new DesiredCapabilities();

      /*
       * capability.setCapability("device",device);
       * capability.setCapability("os_version", version);
       * capability.setCapability("browserName",browser);
       * //capability.setCapability("browser_version",browser_version);
       * capability.setCapability("device-orientation", deviceOrientation);
       * capability.setCapability("realMobile", true); //Set for real devices on BS.
       * capability.setCapability("browserstack.appium_version", "1.6.3");
       */

      var builder = new ChromeDriverService.Builder();
      builder.usingPort(21212);
      driver = new ChromeDriver(builder.build());
      
      driver.get("https://applitools.com/helloworld?diff1");

   }

   @AfterMethod()
   public void tearDown() {
      eyes.close(false);
      eyes.abortIfNotClosed();
      driver.quit();
   }

   @Test
   public void BaselineTests_LogicalGroup4() {
      eyes = new Eyes();
      eyes.setLogHandler(new StdoutLogHandler());
      eyes.setSendDom(false);
      // eyes.setBaselineEnvName("Comparison");
      eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
      eyes.setForceFullPageScreenshot(true);
      eyes.setBatch(Boltcomponent);
      // eyes.setWaitBeforeScreenshots(5000);
      eyes.setMatchLevel(MatchLevel.LAYOUT);
      eyes.setStitchMode(StitchMode.CSS);
      eyes.setMatchTimeout(0); // disabli
      // eyes.setSaveFailedTests(true);
      eyes.setSaveDiffs(true);
      eyes.open(driver, "MobileiOS2", "MobileiOS2 1");

      eyes.checkWindow("Tagname1");
   }

   @Test
   public void CompareTests_LogicalGrouping4() {

      eyes = new Eyes();
      eyes.setLogHandler(new StdoutLogHandler());
      eyes.setSendDom(false);
      // eyes.setBaselineEnvName("Comparison");
      eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
      eyes.setForceFullPageScreenshot(true);
      eyes.setBatch(Boltcomponent);
      // eyes.setWaitBeforeScreenshots(5000);
      eyes.setMatchLevel(MatchLevel.LAYOUT);
      eyes.setStitchMode(StitchMode.CSS);
      eyes.setMatchTimeout(0); // disable
      // eyes.setSaveFailedTests(false);
      eyes.setSaveDiffs(false);
      eyes.open(driver, "MobileiOS2", "MobileiOS2 1");

      eyes.checkWindow("Tagname2");

   }
}
