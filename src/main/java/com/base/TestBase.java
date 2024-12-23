package com.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
	@BeforeMethod
	public void setup() throws Exception {
		
		//Keyword keyword = new Keyword();
		Keyword.openBrowser("Chrome");
		Keyword.launchUrl("https://www.myntra.com/");
	    System.out.println("browser and url is launched");

	}
	@AfterMethod
	public void tearDown() throws Exception {
		Keyword.driver.quit();

	}


}
