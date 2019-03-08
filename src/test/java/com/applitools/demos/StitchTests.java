package com.applitools.demos;

import com.applitools.eyes.selenium.StitchMode;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StitchTests extends BaseTest {

    @Override
    @BeforeClass
    public void setupEyes() {
        batchName = "Stitch Tests";
        super.setupEyes();
        config.setForceFullPageScreenshot(true);
    }

    @Test
    public void fullPageScroll() {
        driver = new ChromeDriver();
        config.setTestName("Scroll Stitch Test");
        config.setStitchMode(StitchMode.SCROLL);
        
        String jsToInject = 
            "function scrollHide() {"
            + "    if (document.querySelector('html').scrollTop > 0) {"
            + "        document.querySelector('#ac-localnav').style.visibility = 'hidden';"
            + "    }"
            + "    else {"
            + "        document.querySelector('#ac-localnav').style.visibility = 'visible';"
            + "    }"
            + "};"
            + "window.addEventListener('scroll', scrollHide);   "
        ;

        snapWebpage("https://www.apple.com/apple-music/", By.id("ac-globalnav"), jsToInject);
    }

    @Test
    public void fullPageCSS() {
        driver = new ChromeDriver();
        config.setTestName("CSS Stitch Test");
        config.setStitchMode(StitchMode.CSS);

        snapWebpage("https://www.apple.com/apple-music/", By.id("ac-globalnav"), null);
    }
}