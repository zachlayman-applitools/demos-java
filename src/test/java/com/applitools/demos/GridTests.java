package com.applitools.demos;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class GridTests extends BaseTest {

    @Test
    public void gridTest() {
        driver = new FirefoxDriver();

        snapSearchResults("cute puppies");
    }
}