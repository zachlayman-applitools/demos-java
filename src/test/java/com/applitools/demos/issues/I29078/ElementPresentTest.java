package com.applitools.demos.issues.I29078;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ElementPresentTest extends BaseTests{
	
	@Test
	public void testElementPresent() {
//		validateElement(homePage.fabIcon);
		validateElement(By.id("com.google.android.apps.googlevoice:id/fab"));
	}

}
