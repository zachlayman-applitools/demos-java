package com.applitools.demos.issues;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;

public class I_29032_404 {
	//private static Eyes eyes;
	
	//private static WebDriver driver;

	public static void main(String[] args) throws Exception {

		// Set desired capabilities.
		DesiredCapabilities capabilities = new DesiredCapabilities();
		  capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("deviceName", "DEVICE_NAME");
//	       capabilities.setCapability("platformVersion", "PLATFORM_VERSION");
	        //NOTE: 📣 Download this app from https://bintray.com/applitools/Examples/Android_Demo_APK
//	        capabilities.setCapability("app", "C:\\Users\\Prachi\\eclipse-workspace\\AplitoolsPOC\\ApiDemos-debug.apk");
	        capabilities.setCapability("appPackage", "io.appium.android.apis");
	        capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
	        capabilities.setCapability("automationName", "UiAutomator2");
	        capabilities.setCapability("newCommandTimeout", 60 * 5);

		// Open the app.
	        WebDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),
//				capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println(driver);
//		RectangleSize viewportSize = new RectangleSize(/*width*/ 1024, /*height*/ 768 );
		// Initialize the eyes SDK and set your private API key.
		Eyes eyes = new Eyes();
		System.out.println(eyes);
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
//		RectangleSize myViewportSize = eyes.getViewportSize();
//		System.out.println("myViewportSize is : "+myViewportSize);
		eyes.setForceFullPageScreenshot(true);

		try {

			System.out.println("In try statement");
			// Start the test.
			
			eyes.open(driver, "AndroidTest", "abc");
			System.out.println("abc");
			driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Accessibility\"]")).click();
            Thread.sleep(10000);

			// Visual validation.
			eyes.checkWindow("App");

			// End the test.
			eyes.close();

		} finally {
			System.out.println("quiting the driver");
			// Close the app.
			driver.quit();

			// If the test was aborted before eyes.close was called, ends the test as
			// aborted.
			eyes.abortIfNotClosed();

		}

	}
		

}
