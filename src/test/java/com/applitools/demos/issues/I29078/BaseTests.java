package com.applitools.demos.issues.I29078;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.appium.Eyes;
import com.applitools.eyes.fluent.ICheckSettings;
import com.applitools.eyes.selenium.fluent.Target;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseTests {

	protected AndroidDriver<AndroidElement> driver = null;
	protected Eyes eyes = null;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		// Set desired capabilities.
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Pixel 2");
		capabilities.setCapability("appPackage", "com.google.android.apps.googlevoice");
		capabilities.setCapability("appActivity", ".SplashActivity");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("newCommandTimeout", 60 * 5);
		capabilities.setCapability("noReset", true);

		// Open the app.
		driver = new AndroidDriver<AndroidElement>(
				new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// driver = getEyesDriver();
		initiateEyes();
		System.out.println("driver is : "+ driver);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		eyes.abortIfNotClosed();
	}

	public void initiateEyes() {
		System.out.println("Inside initiate eyes!");
		eyes = new Eyes();
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
//		eyes.setForceFullPageScreenshot(true);
		System.out.println("Closing initiate eyes method.");
	}

	public void validateWindow() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
		System.out.println("driver is : "+ driver);
		eyes.open(driver, "GoogleVoice",
				Thread.currentThread().getStackTrace()[2].getMethodName(),new RectangleSize(1080,2160));
		System.out.println("eyes opened!");
		eyes.checkWindow();
		eyes.close();
	}
	
	public void validateFullWindow() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
		System.out.println("driver is : "+ driver);
		eyes.open(driver, "GoogleVoice",
				Thread.currentThread().getStackTrace()[2].getMethodName());
		System.out.println("eyes opened!");
		eyes.setForceFullPageScreenshot(true);
//		eyes.setMatchLevel(MatchLevel.LAYOUT);
		eyes.checkWindow();
		eyes.close();
	}
	
	public void validateWindowLayout() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
		System.out.println("driver is : "+ driver);
		eyes.open(driver, "GoogleVoice",
				Thread.currentThread().getStackTrace()[2].getMethodName());
		System.out.println("eyes opened!");
		eyes.setMatchLevel(MatchLevel.LAYOUT);
		eyes.checkWindow();
		eyes.close();
	}
	
	public void validateElement(By bySelector) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
		System.out.println("driver is : "+ driver);
		eyes.setLogHandler(new StdoutLogHandler());
		eyes.open(driver, "GoogleVoice", Thread.currentThread().getStackTrace()[2].getMethodName());
		System.out.println("eyes opened!");
		//eyes.checkElement(ele);
//		eyes.checkElement(bySelector);
		eyes.check("check name", Target.region(bySelector));
//		eyes.check("check name", bySelector);
		eyes.close();
	}
	
}
