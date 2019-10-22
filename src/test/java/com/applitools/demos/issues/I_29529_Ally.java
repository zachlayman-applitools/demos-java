package com.applitools.demos.issues;

import com.applitools.demos.BaseTest;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class I_29529_Ally extends BaseTest {
    @Test
    public void main() {

        WebDriver driver = new ChromeDriver();

        ClassicRunner runner = new ClassicRunner();

        eyes = initializeEyes(runner);

        eyes.setLogHandler(new StdoutLogHandler(true));

        // eyes.setLogHandler(

        // new FileLogger(System.getProperty("user.dir") +
        // "/target/logs/appliLogfile.log", true, true));

        eyes.open(driver, "GAF VG vs Classic 002", "Home Page", new RectangleSize(1600, 900));

        driver.navigate().to("https://www.gaf.com/en-us");

        eyes.checkWindow("Home Page");

        // eyes.closeAsync();
        runner.getAllTestResults();

    }

    public static Eyes initializeEyes(ClassicRunner runner) {

        try {

            Eyes eyes = new Eyes(runner);

            // eyes.setServerUrl(new URI("https://allyeyes.applitools.com"));

            // eyes.setApiKey("YAT8101O65I3MFzP697j5f1cMoJyilOj9dM6seNEn0vzxo110");

            eyes.setForceFullPageScreenshot(true);

            eyes.setStitchMode(StitchMode.CSS);

            // eyes.setMatchLevel(MatchLevel.LAYOUT2);

            eyes.setConfiguration(setConfig());

            return eyes;

        } catch (Exception e) {

            return null;

        }

    }

    public static Configuration setConfig() {

        try {

            Configuration conf = new Configuration();

            conf.setAppName("Zachs Java App").setTestName("Sample Test");

            conf.addBrowser(1600, 900, BrowserType.CHROME);
            // .addBrowser(900, 600, BrowserType.CHROME)

            //         .addBrowser(1024, 786, BrowserType.FIREFOX).addBrowser(900, 600, BrowserType.EDGE)

            //         .addDeviceEmulation(DeviceName.iPhone6_7_8_Plus, ScreenOrientation.PORTRAIT)

            //         .addDeviceEmulation(DeviceName.Galaxy_S5, ScreenOrientation.LANDSCAPE);

            conf.setApiKey(System.getenv("APPLITOOLS_API_KEY_BUG"));
            return conf;

        } catch (Exception e) {

            return null;

        }

    }
}